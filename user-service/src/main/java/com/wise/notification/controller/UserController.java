package com.wise.notification.controller;

import com.wise.common.dto.CreateUserDto;
import com.wise.notification.model.User;
import com.wise.notification.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/health")
    public String healthCheck() {
        return "User service is up!";
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        User user = User.builder()
                .name(createUserDto.getName())
                .email(createUserDto.getEmail())
                .phone(createUserDto.getPhone())
                .passwordHash(createUserDto.getPassword())
                .build();

        User savedUser = userService.createUser(user);  // <-- use createUser here
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/by-email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-phone")
    public ResponseEntity<User> getUserByPhone(@RequestParam String phone) {
        return userService.getUserByPhone(phone)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
