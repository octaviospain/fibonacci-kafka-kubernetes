package net.transgressoft.fibonacci;

import java.math.BigInteger;
import java.util.Objects;
import java.util.StringJoiner;

public class FibonacciNumberRequest {
    private Integer number;
    private BigInteger result;
    private String executionTime;

    public FibonacciNumberRequest(Integer number, BigInteger result, String executionTime) {
        this.number = number;
        this.result = result;
        this.executionTime = executionTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigInteger getResult() {
        return result;
    }

    public void setResult(BigInteger result) {
        this.result = result;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FibonacciNumberRequest that = (FibonacciNumberRequest) o;
        return Objects.equals(number, that.number) && Objects.equals(result, that.result) && Objects.equals(executionTime, that.executionTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, result, executionTime);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FibonacciNumberRequest.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("result=" + result)
                .add("executionTime='" + executionTime + "'")
                .toString();
    }
}
