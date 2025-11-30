package kz.com.nurzandars.message_system.module;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "messages")
@Data
public class MessageMongo {
    @Id
    private String id;
    private Long chatId;
    private Long userId;
    private String content;
    private List<String> attachments;
    private Date createdAt;



}
