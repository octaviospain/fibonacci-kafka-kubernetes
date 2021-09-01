# Fibonacci microservice in kubernetes

Implementation of a simple HTTP server that returns the _n_-th fibonacci sequence number, serving it as a
GET method at `http://localhost:8000/fib?n=<N>` where `N` is the _n_-th number to obtain;
then create a Dockerfile that builds an image with the application and deploy it to a Kubernetes load balanced cluster.

## How to make it work
1. Build application:
```bash
> ./gradlew build
```
2. Create Docker image:
```bash
> docker build -t fibonacci-service .
```
3. Create Kubernetes cluster:
```bash
> kubectl apply -f fib.yml
```
4. Run the service:
```bash
> minikube service fibonacci-deployment
```

### Stress test
In order to test the load of the cluster, we can execute a provided script in several terminal sessions
that will send endless incremental requests given the `URL` and the initial _n_-th number to request.
```bash
> ./stress_test.sh `minikube service fibonacci-deployment --url` 100000
```

We can check the activity of the pods with
```bash
> kubectl logs <POD_NAME> -f
```

... and rescale the cluster incrementing the number of replicas with
```bash
> kubectl scale --replicas=<NUMBER_OF_REPLICAS> deployment/fibonacci-deployment
```
