package com.example.demo.payments.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.common.UserMessage;
import com.example.demo.payments.models.Customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentsMessageHandler {
    
    private final CustomerService service;
    
    @KafkaListener(topics = "users", groupId = "payments")
    public void consumeUsers(UserMessage message){
        log.info("Mensaje consumido en el grupo payments => {}", message);

        service.addOrUpdateCustomer(Customer.builder()
                                            .id(message.getId())
                                            .fullName(message.getName() + " " + message.getLastName())
                                            .build());


    }
}
