package br.com.meetime.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WebhookProcessingException extends RuntimeException {
    public WebhookProcessingException(String message) {
        super(message);
    }
}
