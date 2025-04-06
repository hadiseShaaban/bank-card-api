# 💳 Bank Card API

A secure and extensible REST API for managing bank cards, built with Java and Spring Boot.

---

## 📚 Description

This project provides a backend service for handling bank card information, including creation, retrieval, and deactivation. Designed for banking and fintech use cases with focus on modular architecture, in-memory and database storage, and clean separation of layers.

---

## 🛠 Tech Stack

- Java 17  
- Spring Boot 3.3.x  
- Spring Web, Spring Data JPA  
- Lombok  
- Swagger/OpenAPI  
- H2 / PostgreSQL (configurable)  
- Maven  

---

## 📦 Features

- Add new bank cards with customer info  
- Search and retrieve card details by ID or card number  
- Enable/disable (activate/deactivate) cards  
- In-memory or database-backed repository support  
- Detailed logging and separation of concerns (Controller, Service, Repository layers)

---

## 📁 Project Structure
com.example.bankcard ├── controller ├── service ├── repository ├── model ├── dto └─ config

---

## 🚀 Getting Started

### Prerequisites

- Java 17  
- Maven  

### Clone & Run

```bash
git clone https://github.com/hadiseShaaban/bank-card-api.git
cd bank-card-api
mvn spring-boot:run

---

## ⚙️ Configuration
You can switch between in-memory and database storage by modifying application.properties:

### For H2 (default)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver

# For PostgreSQL (optional)
# spring.datasource.url=jdbc:postgresql://localhost:5432/bank_card_db
# spring.datasource.username=your_user
# spring.datasource.password=your_pass


---

## 🔍 API Endpoints

Method	Endpoint	Description
POST	/cards	Create a new bank card
GET	/cards/{id}	Get card by ID
GET	/cards/by-number/{cardNumber}	Get card by number
PUT	/cards/{id}/activate	Activate a card
PUT	/cards/{id}/deactivate	Deactivate a card
Use Postman or Swagger UI to test.


---

## 📘 API Documentation

Swagger UI available at:
http://localhost:8080/swagger-ui.html

##  Running Tests
mvn test

## 🙋‍♀️ Author
programmed by Hadise Shaaban

