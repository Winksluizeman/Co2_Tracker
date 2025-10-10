package mapper;

import dto.PersoonDTO;
import model.PersoonModel;

public class PersoonMapper {

    public static PersoonModel toModel(PersoonDTO dto) {
        // ID wordt hier op 0 gezet, tenzij je die later via database of service toewijst
        return new PersoonModel(0, dto.getUsername(), dto.getAge(), dto.getPassword());
    }

    public static PersoonDTO toDTO(PersoonModel model) {
        return new PersoonDTO(model.getNaam(), model.getLeeftijd(), ""); // wachtwoord niet beschikbaar in model
    }
}

