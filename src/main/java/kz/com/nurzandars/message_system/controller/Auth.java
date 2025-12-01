package kz.com.nurzandars.message_system.controller;

import kz.com.nurzandars.message_system.DTO.AuthDTO;
import kz.com.nurzandars.message_system.module.User;
import kz.com.nurzandars.message_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class Auth {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> registration(@RequestBody AuthDTO authDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.saveUser(authDTO));
    }

    //return access token and refresh Arsen you need to JWT And Security for it
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthDTO authDTO) {
        return null;
    }


    @PostMapping("/refresh")
    public ResponseEntity<User> refresh(@RequestBody AuthDTO authDTO) {
        return null;
    }


}
