package org.example.backend.integrationTests;

import org.example.backend.application.port.ICreateAccountRepo;
import org.example.backend.domain.CreateAccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateAccountRepoIntegrationTest {

    @Autowired
    private ICreateAccountRepo repo;

    @Test
    void shouldPersistAccountInDatabase() {
        CreateAccountModel model = new CreateAccountModel(
                "Jeroen",
                21,
                "Test@Gmail.com",
                "ENCODED_PASSWORD"
        );

        CreateAccountModel saved = repo.createAccount(model);

        assertEquals("Jeroen", saved.getUsername());
    }
}
