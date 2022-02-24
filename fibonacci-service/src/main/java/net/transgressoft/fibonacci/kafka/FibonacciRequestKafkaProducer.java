package net.transgressoft.fibonacci.kafka;

import net.transgressoft.fibonacci.domain.FibonacciNumberRequest;
import net.transgressoft.fibonacci.service.FibonacciException;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Properties;

@Component
public class FibonacciRequestKafkaProducer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    @Value("${kafka.topic}")
    private String topic;

    private KafkaProducer<Integer, FibonacciNumberRequest> kafkaProducer;

    @PostConstruct
    public void postConstruct() {
        initializeKafkaProducer();
    }

    private void initializeKafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, FibonacciNumberRequestSerializer.class.getName());
        kafkaProducer = new KafkaProducer<>(properties);

        logger.debug("Kafka Producer initialized");
    }

    public void publishRequest(Integer n, BigInteger result, String executionTime) {
        ProducerRecord<Integer, FibonacciNumberRequest> producerRecord = new ProducerRecord<>(topic, n, new FibonacciNumberRequest(n, result, executionTime));
        kafkaProducer.send(producerRecord, this::producerCallback);
        logger.info("Request published:\n{}", producerRecord);
    }

    private void producerCallback(RecordMetadata recordMetadata, Exception exception) {
        if (exception == null)
            logger.info("Request recorded: Offset: {}, ", recordMetadata.offset());
        else
            throw new FibonacciException("Exception when publishing request record", exception);
    }
}
