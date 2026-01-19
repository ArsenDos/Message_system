package kz.com.ars.message_system.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("refresh_tokens")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private Long id;
    @Column("user_id")
    private Long userId;
    @Column("token_value")
    private String tokenValue;
    @Column("expiry_date")
    private String expiryDate;
}
