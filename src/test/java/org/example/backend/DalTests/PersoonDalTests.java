package org.example.backend.DalTests;

import dal.PersoonDAL;
import org.junit.jupiter.api.Test;
import model.PersoonModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersooonDalTests {

    @Autowired
    private PersoonDAL dal;

    @Test
    void testSavePersoon_persistsToDatabase() {
        // Arrange
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "Banaan12", "john@example.com");

        // Act
        PersoonModel saved = dal.save(persoon);

        // Assert
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
    }

    @Test
    void testSavePersoon_invalidPassword_returnsNull() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "", "john@example.com");


        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }

    @Test
    void testSavePersoon_invalidUsername_returnsNull() {
        PersoonModel persoon = new PersoonModel(0, "", 30, "Banaan12", "john@example.com");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }

    @Test
    void testSavePersoon_invalidAge_returnsNull() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 0, "Banaan12", "john@example.com");

        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }


    @Test
    void testSavePersoon_invalidEmail_returnsNull() {
        PersoonModel persoon = new PersoonModel(0, "john_doe", 30, "Banaan12", "");
        assertThrows(IllegalArgumentException.class, () -> dal.save(persoon));
    }
}


