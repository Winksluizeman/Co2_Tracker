package org.example.backend.DalTests;

import org.example.backend.Data.PersoonDAL;
import org.example.backend.Domain.PersoonModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class PersoonDalIntegrationTests {

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
