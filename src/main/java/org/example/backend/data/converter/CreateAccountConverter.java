package org.example.backend.data.converter;

import org.example.backend.data.table.AccountTable;
import org.example.backend.domain.CreateAccountModel;

public class CreateAccountConverter {

    public static AccountTable toTable(CreateAccountModel createAccountModel)
    {
        final AccountTable account = new AccountTable();

        account.setAge(createAccountModel.getAge());
        account.setEmail(createAccountModel.getEmail());
        account.setUsername(createAccountModel.getUsername());
        account.setPassword(createAccountModel.getPassword());

        return account;
    }
}
