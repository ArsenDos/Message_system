package kz.com.nurzandars.message_system.module;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(name = "created_at")
    private Date createdAt;
}
