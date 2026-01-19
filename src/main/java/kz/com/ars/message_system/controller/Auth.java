package kz.com.ars.message_system.controller;

import kz.com.ars.message_system.DTO.Auth.LoginRequestDto;
import kz.com.ars.message_system.DTO.Auth.RegisterRequestDto;
import kz.com.ars.message_system.module.User;
import kz.com.ars.message_system.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class Auth {
    private final AuthServiceImpl authService;

    @PostMapping("/register")
    public Mono<User> registration(@RequestBody RegisterRequestDto registerRequestDto) {
        return authService.saveUser(registerRequestDto);
    }

    //return access token and refresh Arsen you need to JWT And Security for it
    @PostMapping("/login")
    public Mono<User> login(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }

    @PostMapping("/refresh")
    public ResponseEntity<User> refresh(@RequestBody String refreshToken) {
        return null;
    }


}
