package com.wise.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String name;
    private String phone;
    private String email;
    private String password;
}
