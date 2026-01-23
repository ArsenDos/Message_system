package kz.com.ars.message_system.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("refresh_tokens")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
     Long id;
    @Column(value = "user_id")
     Long userId;
    @Column(value = "token_value")
     String tokenValue;
    @Column(value = "expiry_date")
    Instant expiryDate;
}
