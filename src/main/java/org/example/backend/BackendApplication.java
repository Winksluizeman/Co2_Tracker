package org.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.example.backend",
        "org.example.backend.Api",
        "org.example.backend.Application",
        "org.example.backend.Data",
        "org.example.backend.Domain"

})
@EnableJpaRepositories(basePackages = "org.example.backend")
@EntityScan(basePackages = "org.example.backend.Data.table")
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
