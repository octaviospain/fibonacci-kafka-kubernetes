package net.transgressoft.fibonacci;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerRunnable implements Runnable {

    private static final String KAFKA_TOPIC = "fibonacci_numbers";

    private final Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);

    private final String consumerId;
    private final CountDownLatch latch;
    private final Properties kafkaConsumerProperties;

    private KafkaConsumer<Integer, FibonacciNumberRequest> consumer;

    public ConsumerRunnable(String consumerId, CountDownLatch latch, Properties kafkaConsumerProperties) {
        this.consumerId = consumerId;
        this.latch = latch;
        this.kafkaConsumerProperties = kafkaConsumerProperties;
    }

    @Override
    public void run() {
        try (KafkaConsumer<Integer, FibonacciNumberRequest> consumer = new KafkaConsumer<>(kafkaConsumerProperties)) {
            try {
                while (true) {
                    this.consumer = consumer;

                    consumer.subscribe(Collections.singleton(KAFKA_TOPIC));

                    ConsumerRecords<Integer, FibonacciNumberRequest> records = consumer.poll(Duration.ofMillis(100));

                    records.forEach(record -> logger.info("Fibonacci number request received:\n{}", record));
                }
            }
            catch (WakeupException exception) {
                logger.info("Received shutdown signal, closing the consumer {}", consumerId);
            }
            finally {
                latch.countDown();
            }
        }
    }

    public void shutdown() {
        // Interrupts consumer.poll() and throws WakeupException from its callee
        consumer.wakeup();
    }
}
