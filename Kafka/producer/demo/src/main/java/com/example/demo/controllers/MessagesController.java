package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.EventMessage;
import com.example.demo.services.MessageProducerServivce;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class MessagesController {
    
    private final MessageProducerServivce service;

    @PostMapping("messages")
    public EventMessage postMethodName(@RequestBody EventMessage entity) {
        this.service.sendMessage(entity);
        
        return entity;
    }
    
}
