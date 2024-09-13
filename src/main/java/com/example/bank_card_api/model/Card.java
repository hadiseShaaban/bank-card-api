package com.example.bank_card_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "bank_card")
@Data//for adding automatically getters and setters
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Card number cannot be null")
    @Size(min = 16, max = 16, message = "Card number must be 16 digits")
    @Column(name = "card_number", nullable = false, unique = true, length = 16)
    private String cardNumber;

    @NotNull(message = "Card number cannot be null")
    @Size(min = 6, max = 6, message = "Issuer Code must be 16 digits")
    @Column(name = "issuer_code", nullable = false, length = 6)
    private String issuerCode;

    @NotNull(message = "Card type cannot be null")
    @Column(name = "card_type", nullable = false, length = 20)
    private String cardType;

    @NotNull(message = "Issuer name cannot be null")
    @Column(name = "issuer_name", nullable = false, length = 50)
    private String issuerName;

    @NotNull(message = "National ID cannot be null")
    @Size(min = 10, max = 10, message = "National ID must be 10 digits")
    private String nationalId;

    @NotNull(message = "Phone number cannot be null")
    @Column(name = "contact_number", nullable = false, length = 11)
    @Pattern(regexp = "^[0-9]{11}$", message = "Phone number must be 11 digits")
    private String contactNumber;

    @NotNull(message = "Expiration date cannot be null")
    @Column(name = "expiration_date", nullable = false, length = 5)
    private String expirationDate;

    @NotNull(message = "Address cannot be null")
    @Column(name = "residence_address", nullable = false, length = 255)
    private String residenceAddress;

    @NotNull(message = "account number cannot be null")
    @Column(name = "account_number", nullable = false, length = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "account number must be 10 digits")
    private String accountNumber;

    @NotNull(message = "fullName cannot be null")
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;


    public Card(String cardNumber, String issuerCode, String cardType, String issuerName, String nationalId, String contactNumber,
                String expirationDate, String residenceAddress, String accountNumber, String fullName, boolean isActive) {
        this.cardNumber = cardNumber;
        this.issuerCode = issuerCode;
        this.cardType = cardType;
        this.issuerName = issuerName;
        this.nationalId = nationalId;
        this.contactNumber = contactNumber;
        this.expirationDate = expirationDate;
        this.residenceAddress = residenceAddress;
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.isActive = isActive;
    }

    public Card(Long id, String cardNumber, String issuerCode, String cardType, String issuerName, String nationalId, String contactNumber,
                String expirationDate, String residenceAddress, String accountNumber, String fullName, boolean isActive) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.issuerCode = issuerCode;
        this.cardType = cardType;
        this.issuerName = issuerName;
        this.nationalId = nationalId;
        this.contactNumber = contactNumber;
        this.expirationDate = expirationDate;
        this.residenceAddress = residenceAddress;
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.isActive = isActive;
    }
}