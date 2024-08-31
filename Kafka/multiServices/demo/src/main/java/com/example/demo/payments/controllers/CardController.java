package com.example.demo.payments.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.payments.models.Card;
import com.example.demo.payments.services.CardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("cards")
public class CardController {
    
     private final CardService service;

    @GetMapping()
    public Iterable<Card> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}")
    public Card getUser(@PathVariable Long id) {
        return service.getCard(id);
    }
    
    @PostMapping()
    public Card postMethodName(@RequestBody Card entity) {
        return service.addCard(entity);
    }

    @PutMapping("/{id}")
    public Card putMethodName(@PathVariable Long id, @RequestBody Card entity) {
        
        return service.updateCard(id, entity);
    }
}
