package org.example.backend.Api.controller;

import org.example.backend.Api.dto.PersoonDTO;
import org.example.backend.Domain.PersoonModel;
import org.example.backend.Application.service.PersoonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class PersoonController {

    private final PersoonService service;

    public PersoonController(PersoonService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public String handleForm(@RequestBody PersoonDTO dto) {
        service.createPersoon(dto);
        return "Formulier opgeslagen voor: " + dto.getUsername();
    }

    @PostMapping("/persoon")
    public PersoonModel createPersoon(@RequestBody PersoonDTO dto) {
        return service.createPersoon(dto);
    }

    @GetMapping("/persoon")
    public List<PersoonModel> getAllPersonen() {
        return service.getAllPersonen();
    }
}
