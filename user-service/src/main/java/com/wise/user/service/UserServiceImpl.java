package com.wise.user.service;

import com.wise.user.dto.CreateUserDto;
import com.wise.user.dto.UserResponseDto;
import com.wise.user.model.User;
import com.wise.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDto createUser(CreateUserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                // In real life hash the password; for MVP store as-is or stub
                .passwordHash(dto.getPassword())
                .build();
        User saved = userRepository.save(user);
        return UserResponseDto.fromEntity(saved);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserResponseDto.fromEntity(user);
    }
}
