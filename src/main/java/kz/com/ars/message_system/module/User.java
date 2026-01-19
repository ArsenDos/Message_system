package kz.com.ars.message_system.module;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Table("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private Long id;
    @Column("username")
    private String username;
    @Column("email")
    private String email;
    @Column("password")
    private String password;
    @Column("role")
    private UserRole role;
    @Column("created_at")
    private Date createdAt;
}
