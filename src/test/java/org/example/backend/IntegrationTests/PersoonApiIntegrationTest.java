package org.example.backend.IntegrationTests;

import dal.PersoonDAL;
import model.PersoonModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PersoonDalIntegrationTests {

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
    private PersoonDAL dal;

    @Test
    void testSavePersoon_persistsToDatabase() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "Banaan12", "john@example.com");
        PersoonModel saved = dal.save(persoon);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }
}
