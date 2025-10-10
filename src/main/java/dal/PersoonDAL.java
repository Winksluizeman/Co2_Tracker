// dal/PersoonDAL.java
package dal;

import model.PersoonModel;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersoonDAL {
    private final String URL = "jdbc:postgresql://localhost:5432/co2tracker";
    private final String USER = "postgres";
    private final String PASSWORD = "Appel12";

    public PersoonModel save(PersoonModel persoon) {
        String sql = "INSERT INTO persoon (naam, leeftijd) VALUES (?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persoon.getNaam());
            stmt.setInt(2, persoon.getLeeftijd());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                return new PersoonModel(id, persoon.getNaam(), persoon.getLeeftijd());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PersoonModel> findAll() {
        List<PersoonModel> personen = new ArrayList<>();
        String sql = "SELECT id, naam, leeftijd FROM persoon";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                personen.add(new PersoonModel(rs.getInt("id"), rs.getString("naam"), rs.getInt("leeftijd")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personen;
    }
}
