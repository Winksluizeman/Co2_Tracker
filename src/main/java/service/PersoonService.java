// service/PersoonService.java
package service;

import dal.PersoonDAL;
import dto.PersoonDTO;
import model.PersoonModel;
import serviceInterfaces.PersoonServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PersoonService implements PersoonServiceInterface {
    private PersoonDAL dal = new PersoonDAL();

    @Override
    public PersoonModel createPersoon(PersoonDTO dto) {
        PersoonModel persoon = new PersoonModel(0, dto.getNaam(), dto.getLeeftijd());
        return dal.save(persoon);
    }

    @Override
    public List<PersoonModel> getAllPersonen() {
        return dal.findAll();
    }
}
