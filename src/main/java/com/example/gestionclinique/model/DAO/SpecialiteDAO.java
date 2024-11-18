package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Specialite;
import com.example.gestionclinique.model.util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialiteDAO {

    public void addSpecialite(Specialite specialite) {
        String query = "INSERT INTO Specialite (titre) VALUES (?)";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, specialite.getTitre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Specialite> getAllSpecialites() {
        List<Specialite> specialites = new ArrayList<>();
        String query = "SELECT * FROM Specialite";

        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Specialite specialite = new Specialite(
                        resultSet.getInt("id"),
                        resultSet.getString("titre")
                );
                specialites.add(specialite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return specialites;
    }
}
