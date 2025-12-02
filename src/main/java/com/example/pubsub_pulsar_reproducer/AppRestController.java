package com.example.pubsub_pulsar_reproducer;


import io.dapr.Topic;
import io.dapr.client.DaprClient;
import io.dapr.client.domain.CloudEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PostExchange;

@RestController
public class AppRestController {

  @Autowired
  private DaprClient client;



  @PostMapping("/send/{message}")
  public String sendMessage(@PathVariable("message") String message) {
    System.out.println("Sending message: " + message);
    client.publishEvent("pubsub", "topic", message)
        .block();
    return "Message sent successfully";
  }




//  @PostMapping("/consume")
//  @Topic(name = "topic", pubsubName = "pubsub")
//  public String consume(CloudEvent<String> message) {
//    System.out.println("Message CE: " + message);
//    System.out.println("Message CE Data: " + message.getData());
//    System.out.println("Message CE TraceParent: " + message.getTraceParent());
//    System.out.println("Message CE TraceState: " + message.getTraceState());
//    return "Message sent successfully";
//  }
}
