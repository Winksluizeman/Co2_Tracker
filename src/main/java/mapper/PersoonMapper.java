package mapper;

import dto.PersoonDTO;
import model.PersoonModel;

public class PersoonMapper {

    public static PersoonModel toModel(PersoonDTO dto, String hashedPassword) {
        return new PersoonModel(0, dto.getUsername(), dto.getAge(), hashedPassword, dto.getEmail());
    }

    public static PersoonDTO toDTO(PersoonModel model) {
        return new PersoonDTO(model.getUsername(), model.getAge(), model.getPassword(), model.getEmail());
    }
}
