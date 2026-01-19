package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.DTO.Auth.RegisterRequestDto;
import kz.com.ars.message_system.module.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl {
    public Mono<User> saveUser(RegisterRequestDto registerRequestDto) {


    }
}
