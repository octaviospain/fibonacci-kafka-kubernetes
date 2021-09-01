package net.transgressoft.argyletask.fibonacci;

import org.junit.jupiter.api.*;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciServiceTest {

    FibonacciService fibonacciService;

    @BeforeEach
    public void beforeEach() {
        fibonacciService = new FibonacciService();
    }

    @Test
    @DisplayName("First positive numbers as parameter")
    public void validInputTest() {
        assertEquals(BigInteger.ZERO, fibonacciService.fibonacciNumber(0));
        assertEquals(BigInteger.ONE, fibonacciService.fibonacciNumber(1));
        assertEquals(BigInteger.ONE, fibonacciService.fibonacciNumber(2));
        assertEquals(BigInteger.valueOf(2), fibonacciService.fibonacciNumber(3));
    }

    @Test
    @DisplayName("Negative number as parameter")
    public void invalidInputTest() {
        assertThrows(FibonacciException.class,
                     () -> fibonacciService.fibonacciNumber(-1));
    }
}
