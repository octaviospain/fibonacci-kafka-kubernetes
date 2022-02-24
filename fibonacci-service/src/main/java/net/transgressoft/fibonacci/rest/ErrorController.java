package net.transgressoft.fibonacci.rest;

import net.transgressoft.fibonacci.service.FibonacciException;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(FibonacciException.class)
    public ResponseEntity<String> handleNegativeNumberRequest(WebRequest webRequest) {
        logger.info("Invalid request received: {}", webRequest.getParameter("n"));
        return new ResponseEntity<>("Input parameter should be a positive number", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchRequest(MethodArgumentTypeMismatchException exception) {
        logger.info("Invalid request received: {}", exception.getValue());
        return new ResponseEntity<>("Input parameter should be a number, not a sequence of characters", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SerializationException.class)
    public ResponseEntity<String> handleSerializationException(SerializationException exception) {
        logger.error(exception.getMessage(), exception);
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
