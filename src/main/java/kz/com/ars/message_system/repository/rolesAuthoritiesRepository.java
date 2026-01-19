package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.RoleAuthorities;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface rolesAuthoritiesRepository extends R2dbcRepository<RoleAuthorities, String> {

}
