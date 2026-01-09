package org.example.backend.api.converter;

import org.example.backend.api.dto.CreateAccountDto;
import org.example.backend.domain.CreateAccountModel;
import org.springframework.stereotype.Component;

@Component
public class CreateAccountConverter {

    //Dto -> Domain (model)
    public static CreateAccountModel toDomain(CreateAccountDto createAccountDTO)
    {
        if (createAccountDTO == null) return null;

        return new CreateAccountModel(
                createAccountDTO.getUsername(),
                createAccountDTO.getAge(),
                createAccountDTO.getEmail(),
                createAccountDTO.getPassword()
        );


    }

    //Domain -> Dto
    public static CreateAccountDto toDto(CreateAccountModel createAccountModel) {
        if (createAccountModel == null) return null;

        CreateAccountDto createAccountDTO = new CreateAccountDto();
        createAccountDTO.setEmail(createAccountModel.getEmail());
        createAccountDTO.setAge(createAccountModel.getAge());
        createAccountDTO.setPassword(createAccountModel.getPassword());
        createAccountDTO.setUsername(createAccountModel.getUsername());
        return createAccountDTO;
    }
}