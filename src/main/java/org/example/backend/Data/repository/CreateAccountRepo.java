package org.example.backend.Data.repository;

import org.example.backend.Application.Port.ICreateAccountRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.backend.Data.converter.CreateAccountConverter;
import org.example.backend.Data.table.AccountTable;
import org.example.backend.Domain.CreateAccountModel;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class CreateAccountRepo implements ICreateAccountRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public CreateAccountModel createAccount(CreateAccountModel createAccountModel) {
        AccountTable accountTable = CreateAccountConverter.toTable(createAccountModel);
        em.persist(accountTable);
        return createAccountModel;
    }
}
