package com.example.demo.services.impl;

import org.springframework.stereotype.Service;

import com.example.demo.models.Card;
import com.example.demo.repository.CardRepository;
import com.example.demo.services.ICardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {
    
    private final CardRepository repository;

    @Override
    public Card getCardById(Long id) {
        log.info("Getting card {}", id);

        Card card = repository.findById(id).orElseThrow();

        log.info("Card => {}", card);

        return card;
    }

    @Override
    public Iterable<Card> getAllCards() {
        log.info("Getting all cards...");

        return this.repository.findAll();
    }

    @Override
    public Card addCard(Card card) {
        log.info("Inserting new card {}", card.getCardNumber());
        
        Card cardDB = this.repository.save(card);

        log.info("Card id {} for card {}", cardDB.getId(), cardDB.getCardNumber());

        return cardDB;
    }

    @Override
    public Card updateCard(Long id, Card card) {
        log.info("Updating card {}", id);

        Card cardb = repository.findById(id).orElseThrow();
        cardb.setCardExpiration(card.getCardExpiration());
        cardb.setCardName(card.getCardName());
        cardb.setCardNumber(card.getCardNumber());

        return repository.save(cardb);
    }

    @Override
    public Card deleteCard(Long id) {
        log.info("Deleting card {}", id);
        
        Card cardDb = this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);

        return cardDb;
    }
}
