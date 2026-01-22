package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.module.AUserDetails;
import kz.com.ars.message_system.module.Role;
import kz.com.ars.message_system.module.User;
import kz.com.ars.message_system.repository.AuthorityRepository;
import kz.com.ars.message_system.repository.RoleRepository;
import kz.com.ars.message_system.repository.RolesAuthoritiesRepository;
import kz.com.ars.message_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final RolesAuthoritiesRepository rolesAuthoritiesRepository;

    @Override
    public Mono<UserDetails> findByUsername(String email) {
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found: " + email)))
                .flatMap(this::buildUserDetails);
    }

    private Mono<UserDetails> buildUserDetails(User user) {
        Mono<Role> roleMono = roleRepository.findById(user.getRole());

        Mono<List<GrantedAuthority>> authoritiesMono = getAuthorities(user.getRole());

        return Mono.zip(roleMono, authoritiesMono)
                .map(tuple -> {
                    Role role = tuple.getT1();
                    List<GrantedAuthority> authorities = tuple.getT2();

                    authorities.add(new SimpleGrantedAuthority(role.getName()));

                    return new AUserDetails(
                            user.getId(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getEnabled(),
                            authorities
                    );
                });
    }

    private Mono<List<GrantedAuthority>> getAuthorities(Long roleId) {
        return rolesAuthoritiesRepository.getAllByRoleId(roleId)
                .flatMap(roleAuthority -> authorityRepository.findById(roleAuthority.getAuthorityId()))
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
    }
}