package com.wise.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wise.user.events.UserCreatedEvent;
import com.wise.user.model.User;
import com.wise.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    private static final String TOPIC = "user-events";

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);

        UserCreatedEvent event = new UserCreatedEvent(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone()
        );

        try {
            String eventString = objectMapper.writeValueAsString(event);
            kafkaTemplate.send(TOPIC, eventString);
        } catch (JsonProcessingException e) {
            log.error("Error serializing UserCreatedEvent", e);
            e.printStackTrace();
        }

        return savedUser;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
