package mapper;

import dto.PersoonDTO;
import model.PersoonModel;

public class PersoonMapper {

    public static PersoonModel toModel(PersoonDTO dto) {
        System.out.println("[PersoonMapper] Mapping DTO to Model: " + dto);
        // ID wordt op 0 gezet; database genereert later een echte ID
        PersoonModel model = new PersoonModel(0, dto.getUsername(), dto.getAge(), dto.getPassword(), dto.getEmail());
        System.out.println("[PersoonMapper] Resulting Model: " + model);
        return model;
    }

    public static PersoonDTO toDTO(PersoonModel model) {
        System.out.println("[PersoonMapper] Mapping Model to DTO: " + model);
        // Wachtwoord wordt niet meegegeven aan de DTO
        PersoonDTO dto = new PersoonDTO(model.getUsername(), model.getAge(), model.getEmail(), model.getPassword());
        System.out.println("[PersoonMapper] Resulting DTO: " + dto);
        return dto;
    }
}
