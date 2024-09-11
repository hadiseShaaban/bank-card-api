package com.example.bank_card_api.controller;

import com.example.bank_card_api.model.Card;
import com.example.bank_card_api.repository.InMemoryCardRepository;
import com.example.bank_card_api.service.InMemoryCardService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/card-api")
public class InMemoryCardController {

    @Autowired
    InMemoryCardRepository inMemoryCardRepository;

    private static final Logger logger = LoggerFactory.getLogger(CardController.class);
    private int loginCounter=0;

    public InMemoryCardController(InMemoryCardRepository inMemoryCardRepository) {
        this.inMemoryCardRepository = inMemoryCardRepository;
    }

    @GetMapping("/cards/{nationalId}")
    public ResponseEntity<?> getCardsByNationalId(@PathVariable String nationalId,
                                                          @RequestParam(required = false) String cardType,
                                                          @RequestParam(required = false) String issuerName) {
        try {
            Set<Card> cards = inMemoryCardRepository.getCardsByNationalId(nationalId);

            if (cards.isEmpty()) {
                String errorMessage = String.format("No cards found for national ID: %s", nationalId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
            }

            if (cardType != null || issuerName != null) {//if cardType and issuerName entered we must check validity of them
                cards.removeIf(card ->
                        (cardType != null && !card.getCardType().equalsIgnoreCase(cardType)) ||
                        (issuerName != null && !card.getIssuerName().equalsIgnoreCase(issuerName))
                );
            }

            return ResponseEntity.ok(cards);

        } catch (Exception e) {
            String errorMessage = "An unexpected error occurred. Please try again later.";
            logger.error("Error fetching cards for national ID: {}", nationalId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping("/login-failed")
    public void handleLoginFailure() {
        logger.error("Failed login attempt detected.");
        incrementFailedLoginAttempts();
    }

    private void incrementFailedLoginAttempts() {
        loginCounter++;
        logger.info("failed login attempts "+loginCounter);
    }
}
