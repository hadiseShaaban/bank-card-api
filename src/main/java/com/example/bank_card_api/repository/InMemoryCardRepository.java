package com.example.bank_card_api.repository;

import com.example.bank_card_api.model.Card;
import com.example.bank_card_api.service.InMemoryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class InMemoryCardRepository {

    private final InMemoryCardService cardStorageService;

    @Autowired
    public InMemoryCardRepository(InMemoryCardService cardStorageService) {
        this.cardStorageService = cardStorageService;
        this.cardStorageService.initializeWithSampleData();
    }

    public InMemoryCardRepository(InMemoryCardService cardStorageService, String fileName) {
        this.cardStorageService = cardStorageService;
        this.cardStorageService.loadCardsFromFile(fileName);
    }

    public boolean addCard(Card card) {
        return cardStorageService.addCard(card);
    }

    public Set<Card> getCardsByNationalId(String nationalId) {
        return cardStorageService.getCardsByNationalId(nationalId);
    }
}
