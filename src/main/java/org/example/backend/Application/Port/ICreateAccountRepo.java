package org.example.backend.Application.Port;

import org.example.backend.Domain.CreateAccountModel;

public interface ICreateAccountRepo {

    CreateAccountModel createAccount(CreateAccountModel createAccountModel);

}
