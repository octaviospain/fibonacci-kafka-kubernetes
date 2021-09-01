package net.transgressoft.argyletask.fibonacci;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FibonacciService {

    public BigInteger fibonacciNumber(int n) {
        if (n < 0)
            throw new FibonacciException("Input number can not be below 0");

        if (n == 0 || n == 1) {
            return BigInteger.valueOf(n);
        } else {
            BigInteger numberPosMin2 = BigInteger.ZERO;
            BigInteger numberPosMin1 = BigInteger.ONE;
            BigInteger sum = BigInteger.ZERO;

            for (int i = 2; i <= n; i++) {
                sum = numberPosMin1.add(numberPosMin2);
                numberPosMin2 = numberPosMin1;
                numberPosMin1 = sum;
            }
            return sum;
        }
    }
}
