package kz.com.nurzandars.message_system.repository;

import kz.com.nurzandars.message_system.module.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember,Long> {
    ChatMember findByChatIdAndUserId(Long chatId,Long userId);
}
