package kz.com.ars.message_system.DTO;

import lombok.Data;

@Data
public class ChatResponseDto {
    private Long id;
    private String name;
    private String createdByUserId;
}
