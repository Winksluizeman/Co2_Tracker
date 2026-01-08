package org.example.backend.Api.controller;

import org.example.backend.Api.converter.CreateAccountConverter;
import org.example.backend.Api.dto.CreateAccountDto;
import org.example.backend.Application.service.CreateAccountService;
import org.example.backend.Domain.CreateAccountModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class CreateAccountController {

    private final CreateAccountService  createAccountService;

    public CreateAccountController(CreateAccountService createAccountService) {
        this.createAccountService = createAccountService; }


    //Create (C --- Crud)
    @PostMapping()
    public ResponseEntity<CreateAccountDto> createAccount(
            @Validated @RequestBody CreateAccountDto createAccountDTO)
    {
        createAccountService.createAccount(createAccountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    };





    //Read (R --- Crud)


    //Update (U --- Crud)


    //Delete (D --- Crud)

}
