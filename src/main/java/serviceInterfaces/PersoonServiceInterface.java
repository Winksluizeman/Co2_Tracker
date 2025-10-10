package serviceInterfaces;

import dto.PersoonDTO;
import model.PersoonModel;
import java.util.List;

public interface PersoonServiceInterface {
    PersoonModel createPersoon(PersoonDTO dto);
    List<PersoonModel> getAllPersonen();
}
