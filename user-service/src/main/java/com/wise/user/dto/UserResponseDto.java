package com.wise.user.dto;

import com.wise.user.model.User;
import lombok.Data;
import java.time.Instant;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private Instant createdAt;

    public static UserResponseDto fromEntity(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.id = user.getId();
        dto.name = user.getName();
        dto.email = user.getEmail();
        dto.createdAt = user.getCreatedAt();
        return dto;
    }
}
