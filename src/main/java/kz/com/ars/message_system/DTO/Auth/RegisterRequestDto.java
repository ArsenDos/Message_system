package kz.com.ars.message_system.DTO.Auth;

import lombok.Data;

@Data
public class RegisterRequestDto {
    private String email;
    private String password;
    private String chatId;
}
