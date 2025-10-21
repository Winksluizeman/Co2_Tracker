package service;

import interfacesdal.IPersoonDal;
import dto.PersoonDTO;
import model.PersoonModel;
import serviceInterfaces.PersoonServiceInterface;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class PersoonService implements PersoonServiceInterface {

    private final IPersoonDal dal;

    @Autowired
    public PersoonService(IPersoonDal dal) {
        this.dal = dal;
        System.out.println("PersoonService instantiated");
    }

    @Override
    public PersoonModel createPersoon(PersoonDTO dto) {
        System.out.println("PersoonService.createPersoon() called with DTO: " + dto);
        PersoonModel persoon = new PersoonModel(0, dto.getUsername(), dto.getAge(), dto.getPassword());
        PersoonModel result = dal.save(persoon);
        System.out.println("PersoonService.createPersoon() result: " + result);
        return result;
    }

    @Override
    public List<PersoonModel> getAllPersonen() {
        System.out.println("PersoonService.getAllPersonen() called");
        List<PersoonModel> result = dal.getAllPersonen();
        System.out.println("PersoonService.getAllPersonen() returned count: " + result.size());
        return result;

    }
}
