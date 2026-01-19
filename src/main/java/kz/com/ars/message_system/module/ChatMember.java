package kz.com.ars.message_system.module;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table(name = "chat_member")
@Data
@RequiredArgsConstructor
@Builder
public class ChatMember {
    @Id
    private Long id;
    @Column("chat_id")
    private Chat chat;
   @Column("user_id")
    private User user;
    @Column("joined_at")
    private LocalDateTime joinedAt;
}

