package org.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")   // Zorg dat application-test.properties gebruikt wordt
class BackendApplicationTests {

    @Test
    void contextLoads() {
        // Check dat de Spring context start
    }
}
