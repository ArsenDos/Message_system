package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.module.Chat;
import kz.com.ars.message_system.module.ChatMember;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ChatServiceImpl {
    public Mono<Chat> createChat(String name, Long creatorId) {
        return null;
    }

    public Mono<Void> addMember(Long chatId, Long userId) {

        return null;
    }

    public Mono<Void> removeMember(Long chatId, Long userId) {
            return null;
    }

    public Mono<Chat> getChatById(Long chatId) {
        return null;
    }

    public Flux<ChatMember> getChatMemberById(Long chatId) {
        return null;
    }
}
