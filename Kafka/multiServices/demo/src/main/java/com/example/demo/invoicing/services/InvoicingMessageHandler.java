package com.example.demo.invoicing.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.demo.common.UserMessage;
import com.example.demo.invoicing.models.TaxPayer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoicingMessageHandler {
    
    private final TaxPayerService service;
    
    @KafkaListener(topics = "users", groupId = "invoicing")
    public void consumeUsers(UserMessage message){
        log.info("Mensaje consumido en el grupo invoicing => {}", message);

        service.addOrUpdateTaxPayer(TaxPayer.builder()
                                            .id(message.getId())
                                            .fullName(message.getName() + " " + message.getLastName())
                                            .dateOfBirth(message.getDob())
                                            .build());


    }
}
