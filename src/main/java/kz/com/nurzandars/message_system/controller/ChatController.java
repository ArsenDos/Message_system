package kz.com.nurzandars.message_system.controller;

import jakarta.validation.Valid;
import kz.com.nurzandars.message_system.module.Chat;
import kz.com.nurzandars.message_system.module.ChatMember;
import kz.com.nurzandars.message_system.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Chat> createChatMember(@Valid @RequestBody Chat chat){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(chatService.createChat(chat));
    }

    @PostMapping("{chatId}/add")
    public ResponseEntity<Chat> addMemberToChat(@PathVariable Long chatId,
            @RequestBody ChatMember chatMember){
        return ResponseEntity.ok(chatService.addMember(chatId,chatMember));
    }

    @DeleteMapping("{chatId}/remove/{userId}")
    public ResponseEntity<Void> removeMemberFromChat(@PathVariable Long chatId,
            @PathVariable Long userId) {
        chatService.removeMember(chatId, userId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("{chatId}")
    public ResponseEntity<Chat> getChatById(@PathVariable Long chatId) {
        return ResponseEntity.ok().body(chatService.getChatById(chatId));
    }

    @GetMapping("{chatId}/members")
    public ResponseEntity<List<ChatMember>> getChatMembers(@PathVariable Long chatId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(chatService.getChatMemberById(chatId));
    }

}
