package kz.com.nurzandars.message_system.service;

import kz.com.nurzandars.message_system.module.Chat;
import kz.com.nurzandars.message_system.module.ChatMember;
import kz.com.nurzandars.message_system.module.User;
import kz.com.nurzandars.message_system.repository.ChatMemberRepository;
import kz.com.nurzandars.message_system.repository.ChatRepository;
import kz.com.nurzandars.message_system.repository.UserRepository;
import kz.com.nurzandars.message_system.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMemberRepository chatMemberRepository;

    public Chat createChat(Chat chat) {
       if (chat.getMembers() == null) {
           chat.setMembers(new ArrayList<>());
       }
       ChatMember creatorMember = ChatMember.builder()
               .chat(chat)
               .user(chat.getCreatedBy())
               .joinedAt(new Date())
               .build();
           chat.getMembers().add(creatorMember);

        return chatRepository.save(chat);
    }

    public Chat addMember(Long chatId,ChatMember chatMember) {


        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException("Chat not found"));
        User user = userRepository.findById(chatMember.getUser().getId()).orElseThrow(() -> new NotFoundException("User not found"));
        chatMember.setChat(chat);
        chatMember.setUser(user);
        chatMember.setJoinedAt(new Date());

        boolean isAlreadyMember = chat.getMembers().stream()
                .anyMatch(chatMember1 -> chatMember1.getChat().getId().equals(chatId));

        if (!isAlreadyMember) {
            chat.getMembers().add(chatMember);
        } else {
            throw new IllegalArgumentException("User is already a member of the chat");
        }
            return chatRepository.save(chat);
    }

    public void removeMember(Long chatId, Long userId) {
        ChatMember member =  chatMemberRepository.findByChatIdAndUserId(chatId,userId);
        if(member == null) {
            throw new NotFoundException("Member not found in chat");
        }
        chatMemberRepository.delete(member);
        /*Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Chat not found"));
        chat.getMembers()
                .removeIf(member -> member.getUser().getId().equals(userId));
        chatRepository.save(chat);*/
    }

    public Chat getChatById(Long chatId) {
        return chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Chat not found"));
    }
    public List<ChatMember> getChatMemberById(Long chatId) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new NotFoundException("Chat not found"));
        return chat.getMembers();
    }

}
