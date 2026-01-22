package kz.com.ars.message_system.controller;

import kz.com.ars.message_system.DTO.AddMemberRequest;
import kz.com.ars.message_system.DTO.ChatRequestDto;
import kz.com.ars.message_system.module.Chat;
import kz.com.ars.message_system.module.ChatMember;
import kz.com.ars.message_system.service.impl.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final ChatServiceImpl chatService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Chat> createChatMember(@RequestBody ChatRequestDto request) {
        //add when i adding JWT
        Long creatorId = 1L;
        return chatService.createChat(request.getName(),creatorId);
    }

    @PostMapping("{chatId}/add")
    public Mono<Void> addMemberToChat(@PathVariable Long chatId,
                                      @RequestBody AddMemberRequest request) {
        return chatService.addMember(chatId,request.getUserId());
    }

    @DeleteMapping("{chatId}/remove/{userId}")
    public Mono<Void> removeMemberFromChat(@PathVariable Long chatId,
                                           @PathVariable Long userId) {
      return chatService.removeMember(chatId, userId);
    }

    @GetMapping("{chatId}")
    public Mono<Chat> getChatById(@PathVariable Long chatId) {
        return chatService.getChatById(chatId);
    }

    @GetMapping("{chatId}/members")
    public Flux<ChatMember> getChatMembers(@PathVariable Long chatId) {
        return chatService.getChatMemberById(chatId);

    }
}
