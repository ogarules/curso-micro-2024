package com.example.demo.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.common.UserMessage;
import com.example.demo.users.models.User;

@Service
public class NotificationService {
    
    @Autowired
    KafkaTemplate<String, UserMessage> kafkaTemplate;

    public void sendUserMessage(User user){

        this.kafkaTemplate.send("users", java.util.UUID.randomUUID().toString(), UserMessage.builder().dob(user.getDob())
                                           .email(user.getEmail())
                                           .id(user.getId())
                                           .lastName(user.getLastName())
                                           .name(user.getName())
                                           .phone(user.getPhone())
                                           .build());
    }
}
