package org.example.backend.Application.service;

import jakarta.transaction.Transactional;
import org.example.backend.Api.converter.CreateAccountConverter;
import org.example.backend.Api.dto.CreateAccountDto;
import org.example.backend.Application.Port.ICreateAccountRepo;
import org.example.backend.Domain.CreateAccountModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateAccountService {

    private final PasswordEncoder passwordEncoder;
    private final ICreateAccountRepo iCreateAccountRepo;

    public CreateAccountService(PasswordEncoder passwordEncoder, ICreateAccountRepo iCreateAccountRepo){
        this.passwordEncoder = passwordEncoder;
        this.iCreateAccountRepo = iCreateAccountRepo;
    }

    @Transactional
    public CreateAccountModel createAccount(CreateAccountDto createAccountDto)
    {
        CreateAccountModel createAccountModel = CreateAccountConverter.toDomain(createAccountDto);

        validate(createAccountModel);

        createAccountModel.setPassword(
                passwordEncoder.encode(createAccountModel.getPassword())
        );

        return iCreateAccountRepo.createAccount(createAccountModel);

    };

    private void validate(CreateAccountModel createAccountModel) {
        if (createAccountModel.getUsername() == null || createAccountModel.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username can't be empty");
        }
        if (createAccountModel.getAge() <= 0) {
            throw new IllegalArgumentException("Age has to be higher than zero");
        }
        if (createAccountModel.getPassword() == null || createAccountModel.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password can't be empty");
        }
        if (createAccountModel.getEmail() == null || createAccountModel.getEmail().isBlank() || !createAccountModel.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email needs to contain a '@' ");
        }

    }

}


