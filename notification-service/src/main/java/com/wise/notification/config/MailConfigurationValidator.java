package com.wise.notification.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
@Slf4j
public class MailConfigurationValidator {

    @Autowired
    private Environment environment;

    @PostConstruct
    public void validateMailConfiguration() {
        log.info("Mail Configuration:");
        log.info("Host: {}", environment.getProperty("spring.mail.host"));
        log.info("Port: {}", environment.getProperty("spring.mail.port"));
        log.info("Username configured: {}", environment.getProperty("spring.mail.username"));
        log.info("Auth enabled: {}", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        log.info("STARTTLS enabled: {}", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));
    }
}