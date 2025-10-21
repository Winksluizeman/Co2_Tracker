package controller;

import dto.PersoonDTO;
import org.springframework.web.bind.annotation.*;
import serviceInterfaces.PersoonServiceInterface;

@RestController
@RequestMapping("/api")
public class PersoonController {

    private final PersoonServiceInterface service;

    public PersoonController(PersoonServiceInterface service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public String handleForm(@RequestBody PersoonDTO dto) {
        service.createPersoon(dto); // ⬅️ Opslaan via DAL
        return "Formulier opgeslagen voor: " + dto.getUsername();
    }
}