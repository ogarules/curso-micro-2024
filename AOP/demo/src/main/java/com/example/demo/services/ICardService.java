package com.example.demo.services;

import com.example.demo.models.Card;

public interface ICardService {
    
    Card getCardById(Long id);
    Iterable<Card> getAllCards();
    Card addCard(Card card);
    Card updateCard(Long id, Card card);
    Card deleteCard(Long id);
}
