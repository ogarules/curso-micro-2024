package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Card;
import com.example.demo.services.ICardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final ICardService service;

    @GetMapping("/{id}")
    public Card getCardById(@PathVariable Long id) {
        log.info("Devolviendo tarjeta");

        return service.getCardById(id);
    }

    @GetMapping()
    public Iterable<Card> getAllCards() {
        log.info("Obteniendo todas las tarjetas");

        return service.getAllCards();
    }

    @PostMapping()
    public Card addCard(@RequestBody Card entity) {
        log.info("Agregando tarjeta...");
        
        return service.addCard(entity);
    }
    
    @PutMapping("/{id}")
    public Card updateCard(@PathVariable Long id, @RequestBody Card entity) {
        log.info("Actualizando tarjeta...");
        
        return service.updateCard(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Long id){
        Card card = service.deleteCard(id);

        log.info("Eliminando tarjeta {}", card);
    }
    
}
