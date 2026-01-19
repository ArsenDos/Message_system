package kz.com.ars.message_system.service;

import kz.com.ars.message_system.module.Chat;
import kz.com.ars.message_system.module.ChatMember;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ChatService {
    public Mono<Chat> createChat(String name, Long creatorId);

    public Mono<Void> addMember(Long chatId, Long userId);

    public Mono<Void> removeMember(Long chatId, Long userId);

    public Mono<Chat> getChatById(Long chatId);

    public Flux<ChatMember> getChatMemberById(Long chatId);
}
