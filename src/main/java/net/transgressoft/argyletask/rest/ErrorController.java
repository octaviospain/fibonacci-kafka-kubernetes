package net.transgressoft.argyletask.rest;

import net.transgressoft.argyletask.fibonacci.FibonacciException;
import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorController extends ResponseEntityExceptionHandler {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(FibonacciException.class)
    public ResponseEntity<String> handleNegativeNumberRequest(WebRequest webRequest) {
        LOG.info("Invalid request received: {}", webRequest.getParameter("n"));
        return new ResponseEntity<>("Input parameter should be a positive number", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatchRequest(MethodArgumentTypeMismatchException exception) {
        LOG.info("Invalid request received: {}", exception.getValue());
        return new ResponseEntity<>("Input parameter should be a number, not a sequence of characters", HttpStatus.BAD_REQUEST);
    }
}
