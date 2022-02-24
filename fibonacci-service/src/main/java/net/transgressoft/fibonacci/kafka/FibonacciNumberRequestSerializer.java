package net.transgressoft.fibonacci.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.transgressoft.fibonacci.domain.FibonacciNumberRequest;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class FibonacciNumberRequestSerializer implements Serializer<FibonacciNumberRequest> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, FibonacciNumberRequest data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        }
        catch (JsonProcessingException e) {
            throw new SerializationException("Error when serializing FibonacciNumberRequest to byte[]");
        }
    }
}
