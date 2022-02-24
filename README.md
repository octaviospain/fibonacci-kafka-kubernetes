# Fibonacci microservice in kubernetes

Implementation of a simple HTTP server `fibonacci-service` that returns the _n_-th fibonacci sequence number, serving it as a
GET method at `http://localhost:8000/fib?n=<N>` where `N` is the _n_-th number to obtain.
Requests with result are published to a Kafka topic, which is consumed by a java application `fibonacci-consumer`.

### To do
Create a Dockerfile with Kafka in order to deploy it alongside `fibonacci-service` and `fibonacci-consumer` to a  Kubernetes load balanced cluster.

## How to make it work

### Preconditions:

* Kafka `2.13-3.1.0` is installed at `/opt/kafka_2.13-3.1.0`
* Java 8 is in the `$PATH`

1. Start Kafka zookeeper and broker
```bash
> cd /opt/kafka_2.13-3.1.0/
> bin/zookeeper-server-start.sh config/zookeeper.properties
> bin/kafka-server-start.sh config/server.properties
```
2. Create kafka topic
```bash
> kafka-topics.sh --bootstrap-server localhost:9092 --topic fibonacci_numbers --create --partitions 3 --replication-factor 1
```

3. Build application, assuming the project is cloned ad `$HOME/fibonacci-kafka-kubernetes`:
```bash
> cd $HOME/fibonacci-kafka-kubernetes
> ./gradlew build
```

4. Run the `fibonacci-service`
```bash
> java -jar fibonacci-service/build/libs/fibonacci-service-2.jar
```

5. Run the `fibonacci-consumer` in another console
```bash
> java -jar fibonacci-consumer/build/libs/fibonacci-consumer-2.jar 
```

6. User the provided never ending script that makes requests to the service.
```bash
> ./stress_test.sh localhost:8000 30
```

Logs showing the computation of the numbers and the consumption of the messages from kafka are shown in the console.

## License
Copyright (c) 2022 Octavio Calleya Garcia.

This project is free software under GNU GPL version 3 license. The license is here [LICENSE](https://github.com/octaviospain/fibonacci-kafka-kubernetes/tree/master/LICENSE.txt "License")