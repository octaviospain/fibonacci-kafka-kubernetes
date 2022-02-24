package net.transgressoft.fibonacci;

import net.transgressoft.fibonacci.service.FibonacciException;
import net.transgressoft.fibonacci.service.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FibonacciServiceTest {

    FibonacciService fibonacciService;

    @BeforeEach
    public void beforeEach() {
        fibonacciService = new FibonacciService();
    }

    @Test
    @DisplayName("First positive numbers")
    void validInputTest() {
        assertEquals(BigInteger.ZERO, fibonacciService.getFibonacciNumber(0));
        assertEquals(BigInteger.ONE, fibonacciService.getFibonacciNumber(1));
        assertEquals(BigInteger.ONE, fibonacciService.getFibonacciNumber(2));
        assertEquals(BigInteger.valueOf(2), fibonacciService.getFibonacciNumber(3));
    }

    @Test
    @DisplayName("Negative number as parameter")
    void invalidInputTest() {
        assertThrows(FibonacciException.class,
                     () -> fibonacciService.getFibonacciNumber(-1));

    }
}