package interfacesdal;

import model.PersoonModel;

import java.util.List;

public interface IPersoonDal {
    PersoonModel save(PersoonModel persoon);
    PersoonModel update(PersoonModel persoon);
    List<PersoonModel> getAllPersonen();
}
