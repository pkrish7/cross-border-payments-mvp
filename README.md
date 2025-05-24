# cross-border-payments-mvp

This repository contains a simplified implementation of a cross-border payments platform inspired by [Wise](https://wise.com).  
The goal is to showcase a clean microservices architecture using Java, Spring Boot, Kafka, and PostgreSQL, suitable for learning and interview discussions.

---

## Architecture Overview

![Architecture Diagram](./docs/mini-wise-architecture.png)

### Core Components:

- **API Gateway**  
  Single entry point for all clients. Routes incoming HTTP requests to appropriate microservices, handling authentication, rate limiting, and logging.

- **User Service**  
  Manages user profiles, authentication, and KYC information.

- **Account Service**  
  Handles user balances across different currencies and transactional operations.

- **Payment Service**  
  Processes payment initiation, currency conversion, and money transfers.

- **Exchange Rate Service**  
  Fetches and caches real-time currency exchange rates from external APIs.

- **Fraud Detection Service**  
  Listens to payment events and applies rules to detect suspicious transactions.

- **Notification Service**  
  Sends out notifications via email, SMS, or in-app alerts based on payment events.

- **PostgreSQL Database**  
  Stores all persistent data including users, accounts, transactions, and logs.

- **Kafka (Event Bus)**  
  Facilitates asynchronous communication between services, ensuring loose coupling and scalability.

---

## How It Works

1. The client interacts with the **API Gateway**, which routes requests to the appropriate microservices.
2. The **User Service** manages authentication and user data.
3. When a payment request is made, the **Payment Service** verifies the user's balance via the **Account Service**.
4. The **Payment Service** consults the **Exchange Rate Service** for up-to-date currency conversions.
5. Once payment processing begins, events are published to Kafka.
6. The **Fraud Detection Service** consumes these events to monitor suspicious activity.
7. The **Notification Service** sends alerts based on event outcomes.
8. All data is persisted reliably in the **PostgreSQL** database.

---

## Technologies Used

- Java 17  
- Spring Boot  
- Spring Cloud Gateway  
- Apache Kafka  
- PostgreSQL  
- Docker (for containerization)  
- OpenAPI / Swagger (for API documentation)  

---

## Getting Started

Instructions to clone, build, and run the microservices will be provided soon.

---

## Future Enhancements

- Add OAuth2 / JWT authentication  
- Implement CI/CD pipelines  
- Add monitoring and observability tools  
- Expand fraud detection with machine learning models  
- Container orchestration with Kubernetes  

---

Feel free to explore, contribute, or ask questions!

---

# License

MIT License

