package kz.com.ars.message_system.exception;

import java.time.LocalDateTime;

public record ErrorResponce(String errorMessage , LocalDateTime timestamp) {
}
