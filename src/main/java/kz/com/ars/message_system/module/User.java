package kz.com.ars.message_system.module;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("users")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    Long id;
    @Column("username")
    String username;
    @Column("email")
    String email;
    @Column("password")
    String password;
    @Column("enabled")
    Boolean enabled;
    @Column("role_id")
    Long role;
}
