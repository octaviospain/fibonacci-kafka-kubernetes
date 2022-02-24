package net.transgressoft.fibonacci.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FibonacciService {

    private static final Map<Integer, BigInteger> FIBONACCI_MAP = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    static {
        FIBONACCI_MAP.put(0, BigInteger.ZERO);
        FIBONACCI_MAP.put(1, BigInteger.ONE);
    }

    public BigInteger getFibonacciNumber(Integer n) {
        if (n < 0)
            throw new FibonacciException("Input number can not be below 0");
        if (n == 0)
            return FIBONACCI_MAP.get(0);
        if (n == 1)
            return FIBONACCI_MAP.get(1);
        if (FIBONACCI_MAP.containsKey(n))
            return FIBONACCI_MAP.get(n);
        else {
            logger.info("Computing fibonacci number {}", n);

            BigInteger numberPosMin2 = BigInteger.ZERO;
            BigInteger numberPosMin1 = BigInteger.ONE;
            BigInteger sum = BigInteger.ZERO;

            for (int i = 2; i <= n; i++) {
                sum = numberPosMin1.add(numberPosMin2);
                FIBONACCI_MAP.put(i, sum);
                logger.info("Number {} computed {}", i, sum);

                numberPosMin2 = numberPosMin1;
                numberPosMin1 = sum;
            }
            return sum;
        }
    }
}
