package com.example.demo.crosscutting;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Card;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Configuration
public class CardMaskingAspect {
 
    @Around("execution(Iterable<com.example.demo.models.Card> com.example.demo.services.impl.*.*(..))")
    public Object maskCards(ProceedingJoinPoint pjp) throws Throwable{
        log.info("Masking cards...");

        Object result = pjp.proceed();

        Iterable<Card> cardItems = (Iterable<Card>) result;

        if(Objects.isNull(cardItems)){
            return null;
        }

        for (Card card : cardItems) {
            String numCard = card.getCardNumber();
            card.setCardNumber("XXXX-XXXX-XXXX-" + numCard.substring(numCard.length() -4));

            log.info("Maskingcard {} to {}", numCard, card.getCardNumber());
        }

        return cardItems;
    }
}
