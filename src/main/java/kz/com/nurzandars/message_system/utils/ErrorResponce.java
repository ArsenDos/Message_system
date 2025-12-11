package kz.com.nurzandars.message_system.utils;

import java.time.LocalDateTime;

public record ErrorResponce(String errorMessage , LocalDateTime timestamp) {
}
