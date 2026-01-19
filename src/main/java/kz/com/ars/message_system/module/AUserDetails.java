package kz.com.ars.message_system.module;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AUserDetails extends User {
    private final Long userId;

    public AUserDetails(
            Long userId,
            String email,
            String password,
            boolean enabled,
            Collection<? extends GrantedAuthority> authorities

    ) {
        super(email,password,enabled,true,true,true,authorities);
        this.userId = userId;
    }
    public Long getUserId() {
        return userId;
    }
}
