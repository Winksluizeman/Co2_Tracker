package org.example.backend.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDto {

    private String username;
    private int age;
    private String password;
    private String email;
}
