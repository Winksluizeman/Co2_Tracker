package org.example.backend.e2E;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class CreateAccountE2ETest {

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
    private MockMvc mockMvc;

    @Test
    void shouldCreateAccountEndToEnd() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType("application/json")
                        .content("""
                    {
                        "username": "Jeroen",
                        "age": 21,
                        "email": "Jeroen@Gmail.com",
                        "password": "Password123"
                    }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Jeroen"))
                .andExpect(jsonPath("$.email").value("Jeroen@Gmail.com"));
    }
}
