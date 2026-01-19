package kz.com.ars.message_system.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

@Data
public class MessageDto {
    @Id
    private Long id;
    private Long chatId;
    private Long senderId;
    private String mongoId;
    private Instant createdAt;
}
