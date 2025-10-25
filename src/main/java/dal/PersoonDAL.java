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
        String sql = "INSERT INTO persoon (username, age, password, email) VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persoon.getUsername());
            stmt.setInt(2, persoon.getAge());
            stmt.setString(3, persoon.getPassword());
            stmt.setString(4, persoon.getEmail());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                PersoonModel saved = new PersoonModel(id, persoon.getUsername(), persoon.getAge(), persoon.getPassword(), persoon.getEmail());
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
        String sql = "UPDATE persoon SET username = ?, age = ?, email = ? WHERE id = ? RETURNING id, username, age, password, email";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persoon.getUsername());
            stmt.setInt(2, persoon.getAge());
            stmt.setString(3, persoon.getEmail());
            stmt.setInt(4, persoon.getId());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                PersoonModel updated = new PersoonModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getString("password"),
                        rs.getString("email")
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
        String sql = "SELECT id, username, age, password, email FROM persoon";

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PersoonModel p = new PersoonModel(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("age"),
                        rs.getString("password"),
                        rs.getString("email")
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
