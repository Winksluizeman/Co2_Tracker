package org.example.backend.IntegrationTests;

import dto.PersoonDTO;
import model.PersoonModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersoonApiIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreatePersoon_andRetrieveFromDatabase() {
        // Arrange
        PersoonDTO dto = new PersoonDTO("john_doe", 30, "Banaan12", "john@example.com");

        // Act: stuur POST naar je API (endpoint /api/persoon)
        ResponseEntity<PersoonModel> response =
                restTemplate.postForEntity("/api/persoon", dto, PersoonModel.class);

        // Assert: controleer dat het gelukt is
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PersoonModel saved = response.getBody();
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);

        // Extra check: haal alle personen op via GET
        ResponseEntity<List> allResponse = restTemplate.getForEntity("/api/persoon", List.class);
        assertEquals(HttpStatus.OK, allResponse.getStatusCode());
        assertTrue(allResponse.getBody().toString().contains("john_doe"));
    }
}


