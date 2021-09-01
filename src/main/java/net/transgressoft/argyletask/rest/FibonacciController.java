package net.transgressoft.argyletask.rest;

import net.transgressoft.argyletask.fibonacci.FibonacciService;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class FibonacciController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final FibonacciService fibonacciService;

    public FibonacciController(FibonacciService fibonacciService) {
        this.fibonacciService = fibonacciService;
    }

    @GetMapping("fib")
    public String fibonacci(@RequestParam Integer n) {
        long executionStart = System.currentTimeMillis();

        BigInteger result = fibonacciService.fibonacciNumber(n);

        long executionTime = System.currentTimeMillis() - executionStart;
        String executionTimeFormatted = new SimpleDateFormat("mm:ss:SSS").format(new Date(executionTime));

        LOG.info(String.format("Request for '%d' took %s", n, executionTimeFormatted));
        return result.toString();
    }
}
