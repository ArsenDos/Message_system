package kz.com.ars.message_system.controller;


import kz.com.ars.message_system.DTO.MessageDto;
import kz.com.ars.message_system.DTO.MessageFileDto;
import kz.com.ars.message_system.service.impl.SenderServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class SenderController {

    private final SenderServiceImpl senderService;


    @PostMapping(value = "/{chat_id}/send",
                consumes = {MediaType.APPLICATION_JSON_VALUE})

    public Mono<Void> sendMessage(@PathVariable Long chat_id, MessageDto message){
        return senderService.sendMessage(message)
                .thenReturn(Mono.just(ResponseEntity.ok().build()));

    }

    @PostMapping(value = "/{chat_id}/send/file",
    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Mono<Void> sendMessageWithAttachments( @PathVariable Long chat_id,
                                                                  @RequestPart("file") MessageFileDto messageFileDto){
        return senderService.sendMessageWithAttachments(messageFileDto)
                .thenReturn(Mono.just(ResponseEntity.ok().build()));
    }

}
