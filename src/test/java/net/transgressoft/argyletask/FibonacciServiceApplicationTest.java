package net.transgressoft.argyletask;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FibonacciServiceApplicationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    @DisplayName("Valid request")
    public void testValidRequest() throws Exception {
        webTestClient.get().uri("fib?n=17")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> assertEquals("1597", response.getResponseBody()));
    }

    @Test
    @DisplayName("Invalid request given negative number")
    public void testInvalidRequest_negativeNumber() throws Exception {
        webTestClient.get().uri("fib?n=-2")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .consumeWith(response -> assertEquals("Input parameter should be a positive number", response.getResponseBody()));
    }

    @Test
    @DisplayName("Invalid request given not a number")
    public void testInvalidRequestTest_notNumberParameter() throws Exception {
        webTestClient.get().uri("fib?n=loremIpsum")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody(String.class)
                .consumeWith(response -> assertEquals("Input parameter should be a number, not a sequence of characters", response.getResponseBody()));
    }
}
