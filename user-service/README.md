## User Service

### Running Locally

- Start with `mvn spring-boot:run` (port 8081)
- Swagger UI: `http://localhost:8081/swagger-ui.html`

### Docker

```bash
docker build -t yourrepo/user-service:latest .
docker run -p 8081:8081 yourrepo/user-service:latest
