package org.example.backend.application.port;

import org.example.backend.domain.CreateAccountModel;


public interface ICreateAccountRepo {

    CreateAccountModel createAccount(CreateAccountModel createAccountModel);

}
