package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.module.RoleAuthorities;
import kz.com.ars.message_system.module.UserRole;
import kz.com.ars.message_system.repository.RoleRepository;
import kz.com.ars.message_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final RoleAuthorities roleAuthorities;
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return null;
    }
}
