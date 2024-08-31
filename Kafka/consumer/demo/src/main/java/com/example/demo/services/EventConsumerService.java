package com.example.demo.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.models.EventMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EventConsumerService {
    
    @KafkaListener(topics = "messages", groupId = "group1")
    public void consume11(EventMessage message){
        log.info("Mensaje consumido en el grupo 1.1 => {}", message);
    }

    @KafkaListener(topics = "messages", groupId = "group1")
    public void consume12(EventMessage message){
        log.info("Mensaje consumido en el grupo 1.2 => {}", message);
    }

    @KafkaListener(topics = "messages", groupId = "group2")
    public void consume2(EventMessage message){
        log.info("Mensaje consumido en el grupo 2 => {}", message);
    }
}
