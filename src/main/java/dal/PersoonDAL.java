package dal;

import model.PersoonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersoonDAL extends JpaRepository<PersoonModel, Integer> {
    Optional<PersoonModel> findByUsername(String username);
}
//test