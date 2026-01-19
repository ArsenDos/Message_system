package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.Authority;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AuthorityRepository extends R2dbcRepository<Authority, String> {
        Mono<Authority> findByName(String name);
}
