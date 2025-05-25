package com.wise.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PropertyDebugConfig implements InitializingBean {

    @Autowired
    private Environment env;

    @Override
    public void afterPropertiesSet() {
        log.info("Active profiles: {}", String.join(", ", env.getActiveProfiles()));
        log.info("Default profiles: {}", String.join(", ", env.getDefaultProfiles()));
        log.info("Mail properties from Environment:");
        log.info("spring.mail.host = {}", env.getProperty("spring.mail.host"));
        log.info("spring.mail.port = {}", env.getProperty("spring.mail.port"));
        log.info("spring.mail.username = {}", env.getProperty("spring.mail.username"));
        log.info("spring.profiles.active = {}", env.getProperty("spring.profiles.active"));
    }
}