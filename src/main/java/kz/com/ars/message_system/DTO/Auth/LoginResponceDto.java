package kz.com.ars.message_system.DTO.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponceDto {
    private String accessToken;
    private String refreshToken;
}
