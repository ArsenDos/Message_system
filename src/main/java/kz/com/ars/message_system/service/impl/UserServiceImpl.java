package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.module.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl {
    public Flux<User> getUsers(Pageable pageable) {
            return null;
    }

    public Mono<User> findById(Long id) {
            return null;
    }

    public Mono<Void> create(User user) {
        return null;
    }

    public Mono<Void> updateUser(Long id, User user) {
        return null;
    }

    public Mono<Void> deleteUser(Long id) {
        return null;
    }
}
