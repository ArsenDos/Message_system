package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.DTO.Auth.*;
import kz.com.ars.message_system.module.RefreshToken;
import kz.com.ars.message_system.module.User;
import kz.com.ars.message_system.repository.RefreshTokenRepository;
import kz.com.ars.message_system.repository.RoleRepository;
import kz.com.ars.message_system.repository.UserRepository;
import kz.com.ars.message_system.service.AuthService;
import kz.com.ars.message_system.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final ReactiveUserDetailsServiceImpl reactiveUserDetailsService;
    private final RoleRepository roleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public Mono<LoginResponceDto> loginUser(LoginRequestDto loginRequestDto) {
               return validteUserPassword(loginRequestDto.getEmail(), loginRequestDto.getPassword())
                        .flatMap(this::generateTokens);
    }

    @Override
    public Mono<Void> register(RegisterRequestDto registerRequestDto) {
                return userRepository.findByEmail(registerRequestDto.getEmail())
                        .flatMap(user -> Mono.error(new RuntimeException("User with this email already exists")))
                        .switchIfEmpty(
                                roleRepository.findByName("ROLE_USER")
                                        .switchIfEmpty(Mono.error(new RuntimeException("Default role not found")))
                                        .flatMap(role -> {
                                                    User user = createNewUser(registerRequestDto, role.getId());
                                                    return userRepository.save(user);
                                                })
                        ).then();
    }
    @Override
    public Mono<RefreshTokenResponceDto> refreshAccessToken(RefreshTokenRequestDto tokenRequestDto) {
        String token = tokenRequestDto.getRefreshToken();
        Mono<String> userEmailMono = Mono.fromCallable(() -> jwtUtil.getUsernameFromToken(token));

        return userEmailMono.flatMap(
                userEmail -> refreshTokenRepository.findByTokenValue(token)
                        .zipWith(reactiveUserDetailsService.findByUsername(userEmail))
                        .flatMap(tuple -> {
                            RefreshToken refreshToken = tuple.getT1();
                            UserDetails userDetails = tuple.getT2();

                            if (refreshToken.getExpiryDate().isBefore(Instant.now()) ||
                            !jwtUtil.validateToken(token, userDetails)) {
                                return Mono.error(new RuntimeException("Refresh token has expired"));
                            }

                            String newAccessToken = jwtUtil.generateAccessToken(userDetails);
                            return Mono.just(new RefreshTokenResponceDto(newAccessToken));
                        })
                        .switchIfEmpty(Mono.error(new BadCredentialsException("refresh token not found")))
        );
    }


    private User createNewUser(RegisterRequestDto registerRequestDto , Long roleId) {
        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(roleId);
        user.setEnabled(true);
        return user;
    }

    private Mono<LoginResponceDto> generateTokens(UserDetails userDetails) {
        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshTokenStr = jwtUtil.generateRefreshToken(userDetails);

        return userRepository.findByEmail(userDetails.getUsername())
                .flatMap(user -> {
                    RefreshToken refreshToken = new RefreshToken();
                    refreshToken.setUserId(user.getId());
                    refreshToken.setTokenValue(refreshTokenStr);
                    Instant expiry = jwtUtil.getExpirationDateFromToken(refreshTokenStr).toInstant();
                    refreshToken.setExpiryDate(expiry);
                    return refreshTokenRepository.save(refreshToken)
                            .map(savedToken -> new LoginResponceDto(accessToken, refreshTokenStr));
                });
    }
    private Mono<UserDetails> validteUserPassword(String email, String rpassword) {
        return reactiveUserDetailsService.findByUsername(email)
                .flatMap(userDetails -> {
                    if (passwordEncoder.matches(rpassword, userDetails.getPassword())) {
                        return Mono.just(userDetails);
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                }).switchIfEmpty(Mono.error(new BadCredentialsException("User not found")));

    }

}
