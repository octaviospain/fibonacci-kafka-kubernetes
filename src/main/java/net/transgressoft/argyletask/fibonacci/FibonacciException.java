package net.transgressoft.argyletask.fibonacci;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FibonacciException extends ResponseStatusException {

    public FibonacciException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
