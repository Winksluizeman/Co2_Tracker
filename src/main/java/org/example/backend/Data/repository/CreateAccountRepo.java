package org.example.backend.Data.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.backend.Data.converter.CreateAccountConverter;
import org.example.backend.Data.table.AccountTable;
import org.example.backend.Domain.CreateAccountModel;

public class CreateAccountRepo {

    @PersistenceContext
    final EntityManager em;

    public CreateAccountRepo(EntityManager em)
    {
        this.em = em;
    }

    @Override
    @Transactional
    public CreateAccountModel createAccount(CreateAccountModel createAccountModel)
    {
        final AccountTable accountTable = CreateAccountConverter.toTable(createAccountModel);
        //VOOR NU SYSTEM.OUT.PRINTLN MAAR HIERNA LOGGING
        System.out.println(accountTable.getId());
        System.out.println(accountTable.getUsername());
        System.out.println(accountTable.getPassword());
        System.out.println(accountTable.getAge());
        System.out.println(accountTable.getEmail());

        em.persist(accountTable);
        return createAccountModel;
    };








}
