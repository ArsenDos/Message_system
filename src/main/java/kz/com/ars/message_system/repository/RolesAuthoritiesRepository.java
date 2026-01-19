package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.RoleAuthorities;
import kz.com.ars.message_system.module.UserRole;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.GrantedAuthority;
import reactor.core.publisher.Flux;

import java.util.List;

public interface RolesAuthoritiesRepository extends R2dbcRepository<RoleAuthorities, String> {

    Flux<List<GrantedAuthority>> getAllByRoleId(UserRole role);
}
