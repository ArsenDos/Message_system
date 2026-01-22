package kz.com.ars.message_system.config;

import kz.com.ars.message_system.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;
    private final ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        try {
            String username = jwtUtil.getUsernameFromToken(authToken);
            if (username == null) {
                return Mono.empty();
            }

            Mono<UserDetails> userDetailsMono = userDetailsService.findByUsername(username);

            return userDetailsMono.flatMap(userDetails -> {
                if (jwtUtil.validateToken(authToken, userDetails)) {
                    return Mono.just(new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    ));
                } else {
                    return Mono.empty();
                }
            });
        } catch (Exception e) {
            return Mono.empty();
        }
    }
}

