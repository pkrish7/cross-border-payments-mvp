# Build stage with Maven 3.9.9 and Java 17
FROM maven:3.9.9-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]