package org.alpagu.sinemaotomasyonu.Core.utilities.ExceptionHandling;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;
    private String errorCode;

    public ErrorDetails(Date timestamp, String message, String details, String errorCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }


    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidInputException extends RuntimeException {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class AccessDeniedException extends RuntimeException {
        public AccessDeniedException(String message) {
            super(message);
        }
    }


    // Getters and Setters


}
