package kz.com.ars.message_system.service;

import kz.com.ars.message_system.DTO.Auth.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface AuthService {
    public Mono<LoginResponceDto> loginUser(LoginRequestDto loginRequestDto);
    public Mono<Void> register(RegisterRequestDto registerRequestDto);
    public Mono<RefreshTokenResponceDto> refreshAccessToken(RefreshTokenRequestDto tokenRequestDto);
}
