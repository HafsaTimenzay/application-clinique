package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Disponibilite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisponibiliteDAO {

    private Connection connection;

    public DisponibiliteDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertAvailability(int doctorId, String day, String startTime, String endTime, int weekID) throws SQLException {
        if (startTime == null || startTime.isEmpty() || endTime == null || endTime.isEmpty()) {
            System.out.printf("Incomplete availability for day ID %d. Skipping...%n", day);
            return;
        }

        String sql = "INSERT INTO disponibilite (medecin_id, jour, hour_debut, hour_fin, week_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, day);
            preparedStatement.setString(3, startTime);
            preparedStatement.setString(4, endTime);
            preparedStatement.setInt(5, weekID);

            preparedStatement.executeUpdate();
        }
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
