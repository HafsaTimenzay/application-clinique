package com.example.gestionclinique.model.database.DAO;

import com.example.gestionclinique.model.Disponibilite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteDAO {

    private Connection connection;

    public DisponibiliteDAO(Connection connection) {
        this.connection = connection;
    }

    public void createDisponibilite(Disponibilite disponibilite) throws SQLException {
        String sql = "INSERT INTO Disponibilite (medecin_id, jour, hour_debut, hour_fin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, disponibilite.getMedecinId());
            stmt.setString(2, disponibilite.getJour());
            stmt.setTime(3, disponibilite.getHourDebut());
            stmt.setTime(4, disponibilite.getHourFin());
            stmt.executeUpdate();
        }
    }

    public List<Disponibilite> getDisponibilitesByMedecin(long medecinId) throws SQLException {
        String sql = "SELECT * FROM Disponibilite WHERE medecin_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, medecinId);
            ResultSet rs = stmt.executeQuery();
            List<Disponibilite> disponibilites = new ArrayList<>();
            while (rs.next()) {
                Disponibilite dispo = new Disponibilite();
                dispo.setIdDisponibilite(rs.getInt("id"));
                dispo.setMedecinId(rs.getLong("medecin_id"));
                dispo.setJour(rs.getString("jour"));
                dispo.setHourDebut(rs.getTime("hour_debut"));
                dispo.setHourFin(rs.getTime("hour_fin"));
                disponibilites.add(dispo);
            }
            return disponibilites;
        }
    }

    public void deleteDisponibilite(int id) throws SQLException {
        String sql = "DELETE FROM Disponibilite WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
