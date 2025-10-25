package controller;

import dto.PersoonDTO;
import org.springframework.web.bind.annotation.*;
import serviceInterfaces.PersoonServiceInterface;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // pas aan naar je frontend origin
public class PersoonController {

    private final PersoonServiceInterface service;

    public PersoonController(PersoonServiceInterface service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String handleForm(@RequestBody PersoonDTO dto) {
        System.out.println("[PersoonController] handleForm called");
        System.out.println("[PersoonController] Ontvangen DTO: " + dto);

        service.createPersoon(dto);

        return "Formulier opgeslagen voor: " + dto.getUsername();
    }
}
