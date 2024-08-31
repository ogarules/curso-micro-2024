package com.example.demo.payments.services;

import org.springframework.stereotype.Service;

import com.example.demo.payments.models.Card;
import com.example.demo.payments.repositories.CardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository repository;

    public Iterable<Card> getAll() {
        return repository.findAll();
    }
    
    public Card getCard(Long id) {
        return repository.findById(id).orElse(null);
    }
    
    public Card addCard(Card entity) {
        Card dbCard = this.repository.save(entity);
        
        return dbCard;
    }

    public Card updateCard(Long id, Card entity){
        Card carddb = repository.findById(id).orElse(null);

        carddb.setCardNumber(entity.getCardNumber());
        carddb.setExpirationDate(entity.getExpirationDate());
        carddb = repository.save(carddb);

        return carddb;
    }
}
