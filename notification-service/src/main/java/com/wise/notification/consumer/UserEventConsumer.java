package com.wise.notification.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wise.notification.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;
import com.wise.common.events.UserCreatedEvent;

@Component
@Slf4j
public class UserEventConsumer {

    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @Autowired
    public UserEventConsumer(ObjectMapper objectMapper, NotificationService notificationService) {
        this.objectMapper = objectMapper;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-service")
    public void listenUserEvents(String message) {
        UserCreatedEvent event = null;
        try {
            log.debug("Received user event message: {}", message);
            event = objectMapper.readValue(message, UserCreatedEvent.class);
            log.info("Processing welcome email for user: {}", event.getEmail());

            notificationService.sendWelcomeEmail(event.getEmail(), event.getName());

        } catch (JsonProcessingException e) {
            log.error("Failed to parse user event message: {}. Error: {}", message, e.getMessage());
            // Don't retry for JSON parsing errors
            return;
        } catch (MailSendException e) {
            String email = event != null ? event.getEmail() : "unknown";
            log.error("Mail server error while sending email to {}: {}", email, e.getMessage());
            // Retry for mail server errors
            throw e;
        } catch (Exception e) {
            String email = event != null ? event.getEmail() : "unknown";
            log.error("Unexpected error while processing email for {}: {}", email, e.getMessage());
            // Don't retry for other errors
            return;
        }
    }
}