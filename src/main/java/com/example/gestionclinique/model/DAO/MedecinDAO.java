package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Medecin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedecinDAO {

    private Connection connection;

    public MedecinDAO(Connection connection) {
        this.connection = connection;
    }

    public void createMedecin(Medecin medecin) throws SQLException {
        String sql = "INSERT INTO Medecin (id, nom, prenom, adresse, dateNaissance, specialite_id, compte_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, medecin.getIdMedecin());
            stmt.setString(2, medecin.getNom());
            stmt.setString(3, medecin.getPrenom());
            stmt.setString(4, medecin.getAdresse());
            stmt.setDate(5, medecin.getDateNaissance());
            stmt.setInt(6, medecin.getSpecialiteId());
            stmt.setLong(7, medecin.getCompteId());
            stmt.executeUpdate();
        }
    }

    public Medecin getMedecinById(long id) throws SQLException {
        String sql = "SELECT * FROM Medecin WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Medecin(rs.getLong("id"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("adresse"),
                        rs.getDate("dateNaissance"), rs.getInt("specialite_id"),
                        rs.getLong("compte_id"));
            }
            return null;
        }
    }

    public void updateMedecin(Medecin medecin) throws SQLException {
        String sql = "UPDATE Medecin SET nom = ?, prenom = ?, adresse = ?, dateNaissance = ?, specialite_id = ?, compte_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medecin.getNom());
            stmt.setString(2, medecin.getPrenom());
            stmt.setString(3, medecin.getAdresse());
            stmt.setDate(4, medecin.getDateNaissance());
            stmt.setInt(5, medecin.getSpecialiteId());
            stmt.setLong(6, medecin.getCompteId());
            stmt.setLong(7, medecin.getIdMedecin());
            stmt.executeUpdate();
        }
    }

    public void deleteMedecin(long id) throws SQLException {
        String sql = "DELETE FROM Medecin WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}

