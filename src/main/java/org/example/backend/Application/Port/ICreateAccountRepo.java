package org.example.backend.Application.Port;

import org.example.backend.Domain.CreateAccountModel;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreateAccountRepo {

    CreateAccountModel createAccount(CreateAccountModel createAccountModel);

}
