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
        "serviceInterfaces"
})

public class BackendApplication {

    public static void main(String[] args) {
        // Start Spring Boot
        ApplicationContext context = SpringApplication.run(BackendApplication.class, args);

        // Haal je service op uit de Spring context
        PersoonServiceInterface service = context.getBean(PersoonServiceInterface.class);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Voer een nieuwe persoon in:");

        System.out.print("Voer naam in: ");
        String naam = scanner.nextLine();

        System.out.print("Voer leeftijd in: ");
        int leeftijd = scanner.nextInt();

        // Maak DTO en sla op via de service
        PersoonDTO dto = new PersoonDTO(naam, leeftijd);
        service.createPersoon(dto);

        System.out.println("Huidige personen in de database:");
        service.getAllPersonen().forEach(System.out::println);

        scanner.close();
    }
}
