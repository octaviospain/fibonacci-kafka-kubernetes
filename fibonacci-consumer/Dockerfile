# syntax=docker/dockerfile:1
FROM openjdk:8-jdk-alpine
RUN addgroup -S fibo && adduser -S fibo -G fibo
USER fibo:fibo
ARG JAR_FILE=build/libs/fibonacci-consumer-2.0.jar
COPY ${JAR_FILE} /opt/fibonacci-service.jar
ENTRYPOINT ["java", "-jar", "/opt/fibonacci-consumer.jar"]
