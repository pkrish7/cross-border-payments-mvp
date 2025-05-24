# User Service

This microservice handles user registration, retrieval, and management as part of the larger [Cross-Border Payments MVP](../README.md) inspired by [Wise](https://wise.com). It is built with **Spring Boot**, exposes REST APIs documented via **Swagger**, and integrates cleanly into a microservices architecture with Kafka and PostgreSQL.

---

## Features

- Create new users (auto-generated ID)
- Retrieve all users
- Get user by email or phone
- Validates email and phone format
- Designed to integrate with Kafka for asynchronous events (WIP)

---

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- SpringDoc OpenAPI (Swagger UI)
- H2 (in-memory DB for local testing)
- JUnit (for testing)

---

## API Endpoints

Once the application is running (default on `http://localhost:8081`):

### Swagger UI
→ [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### REST Endpoints

| Method | Endpoint                       | Description              |
|--------|--------------------------------|--------------------------|
| POST   | `/api/users`                   | Create a new user        |
| GET    | `/api/users`                   | Get all users            |
| GET    | `/api/users/email/{email}`     | Get user by email        |
| GET    | `/api/users/phone/{phone}`     | Get user by phone number |

---

## Data Model
```bash
{
  "name": "Alice Smith",
  "email": "alice@example.com",
  "phone": "+447911123456"
}
```
* email must contain a @
* phone must contain only digits and may start with +

## Local Setup
1. Run the Application
```bash
   cd user-service
   ./mvn spring-boot:run
```
Service will start at: http://localhost:8081

2. Test with Swagger
   Visit: http://localhost:8081/swagger-ui.html

## Validation Rules
* Email: must contain @
* Phone: must contain only digits (and optional + prefix)
Invalid inputs will result in a 400 Bad Request.

## Error Handling
* 404 Not Found for email/phone if no matching user exists.
* 400 Bad Request for invalid input.

## Testing
Run unit tests:
```bash
./mvnw test
```

## Next Steps
* Add Kafka producer to publish UserCreatedEvent
* Add database persistence (PostgreSQL)
* Integrate with Notification Service
* Implement authentication (JWT / OAuth2)

## Folder Structure
user-service/
├── src/
│   ├── main/
│   │   ├── java/com/wise/user
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── model/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── config/
│   └── test/
├── pom.xml
└── README.md

## Author
Part of the Cross-Border Payments MVP

## License
MIT License