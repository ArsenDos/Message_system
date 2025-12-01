package kz.com.nurzandars.message_system.service;

import kz.com.nurzandars.message_system.DTO.AuthDTO;
import kz.com.nurzandars.message_system.module.User;
import kz.com.nurzandars.message_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    //Password need to encode Arsen pls do it and google how
    public User saveUser(AuthDTO authDTO) {
        User user = User.builder()
                .username(authDTO.getUsername())
                .email(authDTO.getEmail())
                .password(authDTO.getPassword())
                .createdAt(new Date(System.currentTimeMillis()))
                .build();

        return userRepository.save(user);
    }
}
