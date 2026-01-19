package kz.com.ars.message_system.service;

import kz.com.ars.message_system.DTO.MessageDto;
import kz.com.ars.message_system.DTO.MessageFileDto;
import reactor.core.publisher.Mono;

public interface SenderService {
    public Mono<Void> sendMessage(MessageDto message);

    public Mono<Void> sendMessageWithAttachments(MessageFileDto messageFileDto);
}
