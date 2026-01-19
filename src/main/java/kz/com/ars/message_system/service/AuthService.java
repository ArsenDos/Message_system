package kz.com.ars.message_system.service;

import kz.com.ars.message_system.DTO.Auth.RegisterRequestDto;
import kz.com.ars.message_system.module.User;
import reactor.core.publisher.Mono;

public interface AuthService {
    public Mono<User> saveUser(RegisterRequestDto registerRequestDto);
}
