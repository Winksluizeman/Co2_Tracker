package org.example.backend.Application.service;

import org.example.backend.Data.PersoonDAL;
import org.example.backend.Api.dto.PersoonDTO;
import mapper.PersoonMapper;
import org.example.backend.Domain.PersoonModel;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersoonService {

    private final PersoonDAL persoonDAL;
    private final PasswordEncoder passwordEncoder;

    public PersoonService(PersoonDAL persoonDAL, PasswordEncoder passwordEncoder) {
        this.persoonDAL = persoonDAL;
        this.passwordEncoder = passwordEncoder;
    }

    public PersoonModel createPersoon(PersoonDTO dto) {
        validate(dto);
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        PersoonModel model = PersoonMapper.toModel(dto, hashedPassword);
        return persoonDAL.save(model);
    }

    public PersoonModel updatePersoon(PersoonModel persoon) {
        if (persoon.getId() <= 0) {
            throw new IllegalArgumentException("Id moet groter dan 0 zijn");
        }
        return persoonDAL.save(persoon);
    }

    public List<PersoonModel> getAllPersonen() {
        return persoonDAL.findAll();
    }

    public Optional<PersoonModel> getById(int id) {
        return persoonDAL.findById(id);
    }

    public void deleteById(int id) {
        persoonDAL.deleteById(id);
    }

    private void validate(PersoonDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().isBlank()) {
            throw new IllegalArgumentException("Username mag niet leeg zijn");
        }
        if (dto.getAge() <= 0) {
            throw new IllegalArgumentException("Leeftijd moet groter dan 0 zijn");
        }
        if (dto.getPassword() == null || dto.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password mag niet leeg zijn");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank() || !dto.getEmail().contains("@")) {
            throw new IllegalArgumentException("Email is ongeldig");
        }
    }
}
