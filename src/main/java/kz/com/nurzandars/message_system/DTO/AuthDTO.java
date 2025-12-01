package kz.com.nurzandars.message_system.DTO;

import lombok.Data;

@Data
public class AuthDTO {
    private String username;
    private String password;
    private String email;
}
