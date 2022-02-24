package net.transgressoft.fibonacci;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class FibonacciNumberRequestDeserializer implements Deserializer<FibonacciNumberRequest> {

    private static final Logger logger = LoggerFactory.getLogger(FibonacciNumberRequestDeserializer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public FibonacciNumberRequest deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), FibonacciNumberRequest.class);
        }
        catch (Exception exception) {
            logger.error("Error deserializing message from kafka", exception);
        }
        return null;
    }
}
