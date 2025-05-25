package com.wise.user.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserEventsConsumerService {

    @KafkaListener(topics = "user-events", groupId = "user-event-consumers")
    public void consumeUserEvents(String message) {
        System.out.println("Received user event: " + message);
        // You can deserialize JSON and do something with it here, e.g. update cache, call other services
    }
}
