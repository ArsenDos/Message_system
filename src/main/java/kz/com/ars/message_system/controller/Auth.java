package kz.com.ars.message_system.controller;

import kz.com.ars.message_system.DTO.Auth.*;
import kz.com.ars.message_system.module.User;
import kz.com.ars.message_system.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class Auth {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public Mono<ResponseEntity<Object>> registration(@RequestBody RegisterRequestDto registerRequestDto) {
        return authService.register(registerRequestDto)
                .then(Mono.fromCallable(() -> ResponseEntity.status(HttpStatus.CREATED).build()));
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<LoginResponceDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        return authService.loginUser(loginRequestDto)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/refresh")
    public Mono<ResponseEntity<RefreshTokenResponceDto>> refresh(@RequestBody RefreshTokenRequestDto tokenRequestDto) {
        return authService.refreshAccessToken(tokenRequestDto)
                .map(ResponseEntity::ok);
    }


}
