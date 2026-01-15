package org.example.backend.data.converter;

import org.example.backend.data.table.AccountTable;
import org.example.backend.domain.CreateAccountModel;

public class CreateAccountConverter {

    public static AccountTable toTable(CreateAccountModel model) {
        AccountTable table = new AccountTable();
        table.setUsername(model.getUsername());
        table.setEmail(model.getEmail());
        table.setAge(model.getAge());

        table.setPassword(model.getPassword());
        return table;
    }
}



