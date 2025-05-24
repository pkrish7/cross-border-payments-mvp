package com.wise.user.service;

import com.wise.user.dto.CreateUserDto;
import com.wise.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(CreateUserDto dto);
    UserResponseDto getUser(Long id);
}
