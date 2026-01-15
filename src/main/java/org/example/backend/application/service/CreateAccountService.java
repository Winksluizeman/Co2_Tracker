package org.example.backend.application.service;

import org.example.backend.application.port.ICreateAccountRepo;
import org.example.backend.domain.CreateAccountModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAccountService {

    private final PasswordEncoder passwordEncoder;
    private final ICreateAccountRepo createAccountRepo;

    public CreateAccountService(PasswordEncoder passwordEncoder, ICreateAccountRepo createAccountRepo) {
        this.passwordEncoder = passwordEncoder;
        this.createAccountRepo = createAccountRepo;
    }
    //

    public CreateAccountModel createAccount(CreateAccountModel model) {
        validate(model);

        String encodedPassword = passwordEncoder.encode(model.getPassword());
        model.setPassword(encodedPassword);

        return createAccountRepo.createAccount(model);
    }

    private void validate(CreateAccountModel model) {
        if (model.getUsername() == null || model.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (model.getAge() <= 0) {
            throw new IllegalArgumentException("Age must be greater than 0");
        }
        if (model.getEmail() == null || !model.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (model.getPassword() == null || model.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }
}
