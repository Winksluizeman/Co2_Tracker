package controller;

import dto.PersoonDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PersoonController {

    @PostMapping("/submit")
    public String handleForm(@RequestBody PersoonDTO dto) {
        System.out.println("Ontvangen persoon: " + dto);
        return "Formulier ontvangen voor: " + dto.getUsername();
    }
}
