package kz.com.ars.message_system.service.impl;

import kz.com.ars.message_system.DTO.MessageDto;
import kz.com.ars.message_system.DTO.MessageFileDto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SenderServiceImpl {
    public  Mono<Void> sendMessage(MessageDto message) {
            return null;
    }

    public Mono<Void> sendMessageWithAttachments(MessageFileDto messageFileDto) {
            return null;
    }
}
