package net.transgressoft.fibonacci;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FibonacciConsumerApplication {

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, FibonacciNumberRequestDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "fibonacci-application");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

        CountDownLatch latch = new CountDownLatch(1);
        ConsumerRunnable consumer = new ConsumerRunnable("Consumer-1", latch, properties);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(consumer);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    consumer.shutdown();
                    try {
                        latch.await();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }));
    }
}
