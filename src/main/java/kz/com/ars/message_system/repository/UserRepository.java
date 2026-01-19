package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.User;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User,Long> {
    Mono<UserDetails> findByEmail(String email);
}
