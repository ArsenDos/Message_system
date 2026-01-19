package kz.com.ars.message_system.repository;

import kz.com.ars.message_system.module.ChatMember;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMemberRepository extends R2dbcRepository<ChatMember,Long> {
    ChatMember findByChatIdAndUserId(Long chatId,Long userId);
}
