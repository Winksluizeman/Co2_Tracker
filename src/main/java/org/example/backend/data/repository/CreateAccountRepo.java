package org.example.backend.data.repository;

import org.example.backend.application.port.ICreateAccountRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.backend.data.converter.CreateAccountConverter;
import org.example.backend.data.table.AccountTable;
import org.example.backend.domain.CreateAccountModel;
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
