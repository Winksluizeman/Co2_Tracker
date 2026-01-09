package org.example.backend.api.controller;

import org.example.backend.api.converter.CreateAccountConverter;
import org.example.backend.api.dto.CreateAccountDto;
import org.example.backend.application.service.CreateAccountService;
import org.example.backend.domain.CreateAccountModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class CreateAccountController {

    private final CreateAccountService createAccountService;
    private final CreateAccountConverter createAccountConverter;

    public CreateAccountController(CreateAccountService createAccountService, CreateAccountConverter createAccountConverter) {
        this.createAccountService = createAccountService;
        this.createAccountConverter = createAccountConverter;
    }

    @PostMapping
    public ResponseEntity<CreateAccountDto> create(@RequestBody CreateAccountDto dto) {

        // Convert DTO → Domain
        CreateAccountModel model = CreateAccountConverter.toDomain(dto);

        // Call service
        CreateAccountModel saved = createAccountService.createAccount(model);

        // Convert Domain → DTO
        CreateAccountDto response = CreateAccountConverter.toDto(saved);

        return ResponseEntity.ok(response);
    }
}
