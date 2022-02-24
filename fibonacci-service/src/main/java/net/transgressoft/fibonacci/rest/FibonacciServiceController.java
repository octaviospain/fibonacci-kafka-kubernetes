package net.transgressoft.fibonacci.rest;

import net.transgressoft.fibonacci.service.FibonacciException;
import net.transgressoft.fibonacci.service.FibonacciService;
import net.transgressoft.fibonacci.kafka.FibonacciRequestKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class FibonacciServiceController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final FibonacciService fibonacciService;
    private final FibonacciRequestKafkaProducer requestProducer;

    public FibonacciServiceController(FibonacciService fibonacciService, FibonacciRequestKafkaProducer requestProducer) {
        this.fibonacciService = fibonacciService;
        this.requestProducer = requestProducer;
    }

    @GetMapping("fib")
    public String fibonacci(@RequestParam Integer n) {
        if (n < 0)
            throw new FibonacciException("Input number can not be below 0");

        long executionStart = System.currentTimeMillis();
        BigInteger result = fibonacciService.getFibonacciNumber(n);
        long executionTime = System.currentTimeMillis() - executionStart;
        String executionTimeFormatted = new SimpleDateFormat("mm:ss:SSS").format(new Date(executionTime));

        logger.info(String.format("Request for '%d' took %s", n, executionTimeFormatted));

        requestProducer.publishRequest(n, result, executionTimeFormatted);

        return result.toString();
    }
}
