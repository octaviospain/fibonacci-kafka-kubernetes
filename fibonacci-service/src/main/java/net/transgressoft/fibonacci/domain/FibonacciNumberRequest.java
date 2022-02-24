package net.transgressoft.fibonacci.domain;

import java.math.BigInteger;

public record FibonacciNumberRequest(Integer number, BigInteger result, String executionTime) {

}
