package com.example.bank_card_api.repository;

import com.example.bank_card_api.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
