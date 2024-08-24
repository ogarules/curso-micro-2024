package com.example.demo.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.models.EventMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageProducerServivce {
    
    private final KafkaTemplate<String, EventMessage> kafkaTemplate;

    public void sendMessage(EventMessage message){

        this.kafkaTemplate.send("messages", java.util.UUID.randomUUID().toString(), message);
    }
}
