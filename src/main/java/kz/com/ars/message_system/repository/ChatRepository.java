package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.Chat;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ChatRepository extends R2dbcRepository<Chat,Long> {
    public Mono<Chat> findByChatId(Long chatId);
}
