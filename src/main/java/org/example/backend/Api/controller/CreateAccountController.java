package org.example.backend.Api.controller;

import org.example.backend.Api.converter.CreateAccountConverter;
import org.example.backend.Api.dto.CreateAccountDto;
import org.example.backend.Application.service.CreateAccountService;
import org.example.backend.Domain.CreateAccountModel;
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
