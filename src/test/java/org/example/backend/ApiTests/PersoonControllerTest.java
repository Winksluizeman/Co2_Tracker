package org.example.backend.ApiTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PersoonDTO;
import controller.PersoonController;
import org.mockito.Mock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import serviceInterfaces.PersoonServiceInterface;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersoonController.class)
class PersoonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PersoonServiceInterface service;

    @TestConfiguration
    static class TestConfig {
        @Bean
        PersoonServiceInterface service() {
            return Mockito.mock(PersoonServiceInterface.class);
        }
    }

    @Test
    void testRegisterEndpoint() throws Exception {
        PersoonDTO dto = new PersoonDTO("john_doe", 30, "secret123", "john@example.com");
        Mockito.when(service.createPersoon(dto)).thenReturn(null);

        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().string("Formulier opgeslagen voor: john_doe"));
    }
}


