package controller;

import dto.PersoonDTO;
import model.PersoonModel;
import org.springframework.web.bind.annotation.*;
import serviceInterfaces.PersoonServiceInterface;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PersoonController {

    private final PersoonServiceInterface service;

    public PersoonController(PersoonServiceInterface service) {
        this.service = service;
    }

    // POST endpoint voor registratie via DTO
    @PostMapping("/register")
    public String handleForm(@RequestBody PersoonDTO dto) {
        System.out.println("[PersoonController] handleForm called");
        System.out.println("[PersoonController] Ontvangen DTO: " + dto);

        service.createPersoon(dto);

        return "Formulier opgeslagen voor: " + dto.getUsername();
    }

    // POST endpoint om een PersoonModel direct op te slaan
    @PostMapping("/persoon")
    public PersoonModel createPersoon(@RequestBody PersoonDTO persoon) {
        return service.createPersoon(persoon);
    }

    // GET endpoint om alle personen op te halen
    @GetMapping("/persoon")
    public List<PersoonModel> getAllPersonen() {
        return service.getAllPersonen();
    }
}
