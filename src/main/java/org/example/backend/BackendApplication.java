package org.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import serviceInterfaces.PersoonServiceInterface;
import dto.PersoonDTO;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {
        "org.example.backend",
        "service",
        "dal",
        "dto",
        "model",
        "serviceInterfaces",
        "controller"
})

public class BackendApplication {

    public static void main(String[] args) {
        // Start Spring Boot
        ApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        // Haal je service op uit de Spring context
        PersoonServiceInterface service = context.getBean(PersoonServiceInterface.class);

    }
}
