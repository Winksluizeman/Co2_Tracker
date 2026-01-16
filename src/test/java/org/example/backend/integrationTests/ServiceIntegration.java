package org.example.backend.integrationTests;

import org.example.backend.application.port.ICreateAccountRepo;
import org.example.backend.domain.CreateAccountModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
@Testcontainers
@SpringBootTest
@ActiveProfiles("integration")
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
                "Password123",
                "jeroen@gmail.com"
        );

        CreateAccountModel saved = repo.createAccount(model);

        assertNotNull(saved);
        assertEquals("Jeroen", saved.getUsername());
        assertEquals("jeroen@gmail.com", saved.getEmail());
    }
}
