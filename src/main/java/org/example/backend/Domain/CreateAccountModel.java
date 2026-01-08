package org.example.backend.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateAccountModel {

    private String username;
    private int age;
    private String email;
    private String password;

}
