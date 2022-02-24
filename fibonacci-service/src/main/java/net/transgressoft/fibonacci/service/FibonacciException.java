package net.transgressoft.fibonacci.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FibonacciException extends ResponseStatusException {

    public FibonacciException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

    public FibonacciException(String message, Throwable throwable) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, throwable);
    }
}
