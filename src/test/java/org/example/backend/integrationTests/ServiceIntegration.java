package org.example.backend.integrationTests;

import org.example.backend.application.port.ICreateAccountRepo;
import org.example.backend.domain.CreateAccountModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
class CreateAccountRepoIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
            .withDatabaseName("co2tracker")
            .withUsername("postgres")
            .withPassword("Appel12");

    @DynamicPropertySource
    static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

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

