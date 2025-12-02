package com.example.pubsub_pulsar_reproducer;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PubsubPulsarReproducerApplication {

	public static void main(String[] args) {

    new Thread(() -> {
      Server server = ServerBuilder.forPort(3000)
          .intercept(new SubscriberGrpcService.MetadataInterceptor())
          .addService(new SubscriberGrpcService())
          .build();
      try {
        server.start();
        System.out.println("GRPC Server Started and listener registered.");
        server.awaitTermination();
      } catch (Exception e) {
        throw new RuntimeException(e);
      }

    }).start();


    String newArgs = String.format("--grpc.server.port=%d", 3000);

    SpringApplication.run(PubsubPulsarReproducerApplication.class, newArgs);
	}


}
