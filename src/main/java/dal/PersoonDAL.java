package dal;

import interfacesdal.IPersoonDal;
import model.PersoonModel;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersoonDAL implements IPersoonDal {

    private final DataSource dataSource;

    public PersoonDAL(DataSource dataSource) {
        this.dataSource = dataSource;
        System.out.println("[PersoonDAL] Instantiated with DataSource");
    }

    @Override
    public PersoonModel save(PersoonModel persoon) {
        System.out.println("[PersoonDAL] save() called with: " + persoon);
        String sql = "INSERT INTO persoon (naam, leeftijd, wachtwoord) VALUES (?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persoon.getNaam());
            stmt.setInt(2, persoon.getLeeftijd());
            stmt.setString(3, persoon.getWachtwoord());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                PersoonModel saved = new PersoonModel(id, persoon.getNaam(), persoon.getLeeftijd(), persoon.getWachtwoord());
                System.out.println("[PersoonDAL] save() success: " + saved);
                return saved;
            }
        } catch (SQLException e) {
            System.out.println("[PersoonDAL] save() failed: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("[PersoonDAL] save() returning: null");
        return null;
    }

    @Override
    public PersoonModel update(PersoonModel persoon) {
        System.out.println("[PersoonDAL] update() called with: " + persoon);
        String sql = "UPDATE persoon SET naam = ?, leeftijd = ? WHERE id = ? RETURNING id, naam, leeftijd, wachtwoord";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persoon.getNaam());
            stmt.setInt(2, persoon.getLeeftijd());
            stmt.setInt(3, persoon.getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PersoonModel updated = new PersoonModel(
                        rs.getInt("id"),
                        rs.getString("naam"),
                        rs.getInt("leeftijd"),
                        rs.getString("wachtwoord")
                );
                System.out.println("[PersoonDAL] update() success: " + updated);
                return updated;
            }
        } catch (SQLException e) {
            System.out.println("[PersoonDAL] update() failed: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("[PersoonDAL] update() returning: null");
        return null;
    }

    @Override
    public List<PersoonModel> getAllPersonen() {
        System.out.println("[PersoonDAL] getAllPersonen() called");
        List<PersoonModel> personen = new ArrayList<>();
        String sql = "SELECT id, naam, leeftijd, wachtwoord FROM persoon";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PersoonModel p = new PersoonModel(
                        rs.getInt("id"),
                        rs.getString("naam"),
                        rs.getInt("leeftijd"),
                        rs.getString("wachtwoord")
                );
                personen.add(p);
            }
        } catch (SQLException e) {
            System.out.println("[PersoonDAL] getAllPersonen() failed: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("[PersoonDAL] getAllPersonen() returning count: " + personen.size());
        return personen;
    }
}
