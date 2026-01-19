package kz.com.ars.message_system.module;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "roles_authorities")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthorities {
    @Column("role_id")
    private Long roleId;
    @Column("authority_id")
    private Long authorityId;
}
