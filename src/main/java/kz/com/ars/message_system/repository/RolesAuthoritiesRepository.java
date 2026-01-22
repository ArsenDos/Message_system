package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.RoleAuthorities;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;


public interface RolesAuthoritiesRepository extends R2dbcRepository<RoleAuthorities, Long> {
    Flux<RoleAuthorities> getAllByRoleId(Long role);
}
