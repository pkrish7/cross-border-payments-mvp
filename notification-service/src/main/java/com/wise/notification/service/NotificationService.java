package com.wise.notification.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


@Service
@Slf4j
public class NotificationService {
    private final JavaMailSender mailSender;
    private final String mailUsername;
    private static final String APP_NAME = "MINI WISE";

    public NotificationService(
            JavaMailSender mailSender,
            @Value("${spring.mail.host}") String mailHost,
            @Value("${spring.mail.port}") String mailPort,
            @Value("${spring.mail.username}") String mailUsername) {
        this.mailSender = mailSender;
        this.mailUsername = mailUsername;

        log.info("Mail Configuration in NotificationService:");
        log.info("Mail Host: {}", mailHost);
        log.info("Mail Port: {}", mailPort);
        log.info("Mail Username: {}", mailUsername);
    }

    public void sendWelcomeEmail(String email, String name) {
        try {
            log.info("Sending welcome email to {} ({})", name, email);

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");  // Changed to true for multipart support

            // Set the from address with display name
            String displayName = "MINI WISE";
            helper.setFrom(mailUsername, displayName);

            // Debug log to verify the From header
            log.debug("Setting From header as: {} <{}>", displayName, mailUsername);

            helper.setTo(email);
            helper.setSubject("Welcome to " + APP_NAME);
            helper.setText(String.format("Hello %s,\n\nWelcome to MINI WISE! 🚀\n\nWe're thrilled to have you join our community. Get ready to explore awesome features and stay tuned for exciting updates!\n\nIf you have any questions, feel free to reach out.\n\nBest wishes,\nThe MINI WISE Team", name));

            mailSender.send(mimeMessage);
            log.info("Welcome email sent successfully to: {}", email);

            // Debug log to verify the actual message headers
            log.debug("Message headers: {}", mimeMessage.getAllHeaders());

        } catch (MailException e) {
            log.error("Failed to send welcome email to {}: {}", email, e.getMessage());
            throw new RuntimeException("Failed to send welcome email", e);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
