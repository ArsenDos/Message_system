package kz.com.ars.message_system.DTO;

import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;


@Data
public class MessageFileDto {
    private Long id;
    private Long chatId;
    private Long senderId;
    private String mongoId;
    private FilePart filePart;
    private String caption;
}
