package com.marcbentoy.user.service;

import com.marcbentoy.proto.HelloReply;
import com.marcbentoy.proto.HelloRequest;
import com.marcbentoy.proto.SimpleGrpc;
import io.grpc.stub.StreamObserver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.grpc.server.service.GrpcService;

@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {

  private static final Log log = LogFactory.getLog(GrpcServerService.class);

  @Override
  public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    log.info("Received request: " + request.getName());

    if (request.getName().startsWith("error")) {
      throw new IllegalArgumentException("Bad name: " + request.getName());
    }
    if (request.getName().startsWith("internal")) {
      throw new RuntimeException();
    }

    HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==>" + request.getName()).build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  @Override
  public void streamHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
    log.info("Received request: " + request.getName());

    int count = 0;
    while (count < 10) {
      HelloReply reply = HelloReply.newBuilder().setMessage("Hello (" + count + ") ==>" + request.getName()).build();
      responseObserver.onNext(reply);

      count++;

      try {
        Thread.sleep(1000L);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        responseObserver.onError(e);
        return;
      }
    }

    responseObserver.onCompleted();
  }

}
