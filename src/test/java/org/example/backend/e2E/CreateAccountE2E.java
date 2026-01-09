package org.example.backend.e2e;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CreateAccountE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateAccountEndToEnd() throws Exception {
        mockMvc.perform(post("/accounts")
                        .contentType("application/json")
                        .content("""
                    {
                        "username": "Wink",
                        "age": 21,
                        "email": "wink@example.com",
                        "password": "Password123"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Wink"))
                .andExpect(jsonPath("$.email").value("wink@example.com"));
    }
}
