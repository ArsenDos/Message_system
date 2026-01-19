package kz.com.ars.message_system.DTO.Auth;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
