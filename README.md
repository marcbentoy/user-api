# user-api

An experimental user-api service that acts as the server for serving gRPC-enabled resource.

## How to run

### Pre-requisites

1. Ensure that you have installed Java 21 on your machine.
2. Optional: if you want to make requests on this service, you can install gRPCurl by running `brew install grpcurl`.

## Steps to run
1. Clone the project, `git clone git@github.com:marcbentoy/user-api.git`.

2. Build the project to generate the Java implementation of the gRPC service, `./mvnw clean package`.

3. Run the project, `./mvnw spring-boot:run`.

4. Make gRPC requests using `grpcurl` or any gRPC client of your choice. For example, to run the `SayHello` method, you can run:
```shell
grpcurl -d '{"name":"Marc"}' -plaintext localhost:9090 Simple.SayHello
```

## Notes

This service is purely experimental and is not intended for production use. It serves as a learning exercise for implementing gRPC services in Java using Spring Boot.
The service also has a simple implementation of a REST endpoint for testing purposes, which can be accessed at `http://localhost:8080/user`. This is to test if it is possible to have a gRPC service and a REST endpoint running on the same application and on the same port.