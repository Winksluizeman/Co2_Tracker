package org.example.backend.DalTests;

import dal.PersoonDAL;
import model.PersoonModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class PersooonDalTests {

    @Autowired
    private PersoonDAL dal;

    @Test
    void testSavePersoon_persistsToDatabase() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "Banaan12", "john@example.com");

        PersoonModel saved = dal.save(persoon);

        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    void testSavePersoon_invalidPassword_returnsException() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "", "john@example.com");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }

    @Test
    void testSavePersoon_invalidUsername_returnsException() {
        PersoonModel persoon = new PersoonModel(0, "", 30, "Banaan12", "john@example.com");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }

    @Test
    void testSavePersoon_invalidAge_returnsException() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 0, "Banaan12", "john@example.com");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }

    @Test
    void testSavePersoon_invalidEmail_returnsException() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "Banaan12", "");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }
}
