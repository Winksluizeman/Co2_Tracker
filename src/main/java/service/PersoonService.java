package service;

import interfacesdal.IPersoonDal;
import dto.PersoonDTO;
import model.PersoonModel;
import serviceInterfaces.PersoonServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class PersoonService implements PersoonServiceInterface {

    private final IPersoonDal dal;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersoonService(IPersoonDal dal, PasswordEncoder passwordEncoder) {
        System.out.println("[PersoonService] Constructor called, DAL and Encoder injected");
        this.dal = dal;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PersoonModel createPersoon(PersoonDTO dto) {
        System.out.println("[PersoonService] createPersoon() called with DTO: " + dto);

        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Wachtwoord mag niet leeg zijn");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        System.out.println("[PersoonService] Wachtwoord gehashed (BCrypt).");

        // Maak het model aan met gehashte wachtwoord
        PersoonModel persoon = new PersoonModel(
                0,
                dto.getUsername(),
                dto.getAge(),
                hashedPassword,
                dto.getEmail()
        );

        // Sla op via DAL
        PersoonModel savedPersoon = dal.save(persoon);
        System.out.println("[PersoonService] DAL returned: " + savedPersoon);

        return savedPersoon;
    }

    @Override
    public List<PersoonModel> getAllPersonen() {
        System.out.println("[PersoonService] getAllPersonen() called");
        List<PersoonModel> personen = dal.getAllPersonen();
        System.out.println("[PersoonService] DAL returned " + personen.size() + " personen");
        return personen;
    }
}
