package com.example.bank_card_api.service;

import com.example.bank_card_api.model.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class InMemoryCardServiceTest {
    private InMemoryCardService service;

    @BeforeEach//before each test we need a new instance of our service
    public void setUp() {
        service = new InMemoryCardService();
    }

    @Test
    public void testAddCard_NewCard() {
        Card newCard = new Card(12345678910L,"1234567890123456", "654321", "Debit", "sina",
                "1234567890", "09123456789", "01/26", "address",
                "1234567890", "sadjad talakoob", true);
        boolean result = service.addCard(newCard);
        assertTrue(result, "Card should be added successfully.");
        Set<Card> cards = service.getCardsByNationalId("1234567890");
        assertTrue(cards.contains(newCard), "Card should be in the set.");
    }

    @Test
    public void testAddCard_DuplicateCard() {
        Card existingCard = new Card(12345678910L,"1234567890123456", "654321", "Debit", "sina",
                "1234567890", "09123456789", "01/26", "address",
                "1234567890", "sadjad talakoob", true);
        service.addCard(existingCard);
        Card newCard = new Card(12345678911L,"1234567890123456", "654321", "Debit", "sina",
                "1234567890", "09123456789", "01/26", "address",
                "1234567890", "sadjad talakoob", true);
        boolean result = service.addCard(newCard);
        assertFalse(result, "Duplicate card should not be added.");
    }

    @Test
    public void testGetCardsByNationalId() {
        Card card1 = new Card(12345678910L,"1234567890123456", "654321", "Debit", "bank",
                "1234567890", "09123456789", "01/26", "address",
                "1234567890", "hadise shaaban", true);
        Card card2 = new Card(12345678911L,"6543210987654321", "123456", "Credit", "bank",
                "1234567890", "09123456789", "02/27", "address",
                "1234567890", "sadjad talakoob", true);
        service.addCard(card1);
        service.addCard(card2);
        Set<Card> cards = service.getCardsByNationalId("1234567890");
        assertEquals(2, cards.size(), "There should be two cards for the given national ID.");
    }

    @Test
    public void testInitializeWithSampleData() {
        service.initializeWithSampleData();
        Set<Card> cards = service.getCardsByNationalId("1111111111");
        assertEquals(2, cards.size(), "There should be two sample cards added.");
    }

    @Test
    public void testLoadCardsFromFile() throws IOException {

        Path tempFile = Files.createTempFile("cards", ".txt");
        try (BufferedWriter writer = Files.newBufferedWriter(tempFile)) {
            writer.write("1234567890123456,654321,Debit,bank,1234567890,09123456789,01/26,address,1234567890,hadise shaaban,true\n");
            writer.write("6543210987654321,123456,Credit,bank,1234567890,09123456789,02/27,address,1234567890,hadise shaaban,true\n");
        }

        service.loadCardsFromFile(tempFile.toString());
        Set<Card> cards = service.getCardsByNationalId("1234567890");
        assertEquals(2, cards.size(), "Cards should be loaded correctly from the file.");

        // Clean up
        Files.delete(tempFile);
    }
}
