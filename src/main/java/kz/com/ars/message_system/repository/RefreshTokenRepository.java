package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.RefreshToken;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface RefreshTokenRepository extends R2dbcRepository<RefreshToken ,Long> {
    Mono<RefreshToken> findByTokenValue(String token);
}
