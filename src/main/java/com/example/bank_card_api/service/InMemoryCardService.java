package com.example.bank_card_api.service;

import com.example.bank_card_api.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class InMemoryCardService {
    private final Map<String, Set<Card>> cardStorage = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(InMemoryCardService.class);

    public boolean addCard(Card card) {
        String nationalId = card.getNationalId();
        Set<Card> cards = cardStorage.getOrDefault(nationalId, new HashSet<>());

        if (nationalId == null || nationalId.isEmpty()) {
            logger.error("Invalid national ID: input is null or empty.");
            return false;
        }

        boolean exists = cards.stream().anyMatch(c ->
                c.getCardType().equals(card.getCardType()) &&
                        c.getIssuerName().equals(card.getIssuerName())
        );

        if (exists) {
            logger.info("Card already exists for national ID: {}", nationalId);
            return false;
        }

        cards.add(card);
        cardStorage.put(nationalId, cards);
        logger.info("Card added successfully for national ID: {}", nationalId);
        return true;
    }

    public Set<Card> getCardsByNationalId(String nationalId) {
        Set<Card> result = cardStorage.get(nationalId);
        if (result == null)
            logger.error("No cards found for national ID: {}", nationalId);

        return result != null ? result : Collections.emptySet();
    }

    public void initializeWithSampleData() {
        Card sampleCard1 = new Card(12345678910L,"1234567812345678", "123456", "Debit", "mellat",
                "1111111111", "09123456789", "12/25", "tehran, iran",
                "9876543210", "hadise shaaban", true);
        Card sampleCard2 = new Card(12345678911L,"8765432187654321", "654321", "Credit", "melli",
                "1111111111", "09123456789", "11/24", "tehran, iran",
                "1234567890", "hadise shaaban", true);

        addCard(sampleCard1);
        addCard(sampleCard2);
    }

    public void loadCardsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Card card = new Card(
                        Long.parseLong(data[0]), data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9],data[10], Boolean.parseBoolean(data[11])
                );
                addCard(card);
            }
        } catch (IOException e) {
            logger.error("Error reading file: {}", fileName, e);
        }
    }
}