package org.example.backend.Unittests;

import dal.PersoonDAL;
import dto.PersoonDTO;
import model.PersoonModel;
import service.PersoonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersoonServiceTest {

    @InjectMocks
    private PersoonService persoonService;

    @Mock
    private PersoonDAL dal;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreatePersoon_success() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("john_doe", 30, "Banaan12", "john@example.com");
        String hashedPassword = "hashedSecret123";

        when(passwordEncoder.encode("Banaan12")).thenReturn(hashedPassword);

        PersoonModel expectedModel = new PersoonModel(0, "john_doe", 30, hashedPassword, "john@example.com");
        when(dal.save(any(PersoonModel.class))).thenReturn(expectedModel);

        // Act
        PersoonModel result = persoonService.createPersoon(dto);

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        assertEquals(hashedPassword, result.getPassword());
        verify(dal).save(any(PersoonModel.class));
    }

    @Test
    void testCreatePersoon_emptyPassword_throwsException() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("john_doe", 30, "", "john@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> persoonService.createPersoon(dto));
    }

    @Test
    void testCreatePersoon_emptyUsername_throwsException() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("", 30, "Banaan12", "john@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> persoonService.createPersoon(dto));
    }

    @Test
    void testCreatePersoon_invalidAge_throwsException() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("john_doe", 0, "Banaan12", "john@example.com");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> persoonService.createPersoon(dto));
    }

    @Test
    void testCreatePersoon_emptyEmail_throwsException() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("john_doe", 30, "Banaan12", "");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> persoonService.createPersoon(dto));
    }
}
