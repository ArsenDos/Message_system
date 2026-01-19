package kz.com.ars.message_system.config;

import kz.com.ars.message_system.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequiredArgsConstructor
public class JwtAuthManager {
    private final JwtUtil jwtUtil;
    private final ReactiveUserDetailsService userDetailsService;
}
