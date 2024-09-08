package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Core.utilities.ExceptionHandling.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exceptions")
public class ExceptionController {

    @GetMapping("/resource-not-found")
    public ResponseEntity<String> triggerResourceNotFoundException() {
        throw new ErrorDetails.ResourceNotFoundException("Resource not found");
    }

    @GetMapping("/invalid-input")
    public ResponseEntity<String> triggerInvalidInputException() {
        throw new ErrorDetails.InvalidInputException("Invalid input provided");
    }

    @GetMapping("/access-denied")
    public ResponseEntity<String> triggerAccessDeniedException() {
        throw new ErrorDetails.AccessDeniedException("Access denied");
    }

    @GetMapping("/illegal-argument")
    public ResponseEntity<String> triggerIllegalArgumentException() {
        throw new IllegalArgumentException("Illegal argument provided");
    }

    @GetMapping("/unhandled-exception")
    public ResponseEntity<String> triggerUnhandledException() {
        throw new RuntimeException("Unhandled exception occurred");
    }
}
