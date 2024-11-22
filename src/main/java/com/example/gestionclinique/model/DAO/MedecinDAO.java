package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Medecin;
import com.example.gestionclinique.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedecinDAO {

    private Connection connection;

    public MedecinDAO(Connection connection) {
        this.connection = connection;
    }

    public int getMedecinIdByCompteId(int compteId) throws SQLException {
        // SQL query to fetch the patient ID by compteId
        String query = "SELECT id FROM Medecin WHERE compte_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, compteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
            return 0; // or handle as appropriate
        }
    }


    public Medecin getMedecinByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Medecin m JOIN Compte c ON m.compte_id = c.id_compte WHERE c.email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Medecin medecin = new Medecin();
                medecin.setIdMedecin(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setEmail(rs.getString("email"));
                medecin.setAdresse(rs.getString("adress"));
                medecin.setDateNaissance(rs.getString("DateNaissance"));
                medecin.setCompteId(rs.getInt("compte_id"));
                return medecin;
            }
            return null; // No patient found
        }
    }

    public boolean InsertMedecinSignUp(Medecin medecin) throws SQLException {
        String query = "INSERT INTO Medecin (nom, prenom, compte_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, medecin.getNom());
            preparedStatement.setString(2, medecin.getPrenom());
            preparedStatement.setLong(3, medecin.getCompteId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public void createMedecin(Medecin medecin) throws SQLException {
        String sql = "INSERT INTO Medecin (id, nom, prenom, adresse, dateNaissance, specialite_id, compte_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, medecin.getIdMedecin());
            stmt.setString(2, medecin.getNom());
            stmt.setString(3, medecin.getPrenom());
            stmt.setString(4, medecin.getAdresse());
            stmt.setString(5, medecin.getDateNaissance());
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
                Medecin medecin = new Medecin();

            }
            return null;
        }
    }

    public boolean updateMedecin(Medecin medecin) throws SQLException {
        String sql = "UPDATE Medecin SET nom = ?, prenom = ?, adress = ?, dateNaissance = ?, specialite_id = ?, compte_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, medecin.getNom());
            stmt.setString(2, medecin.getPrenom());
            stmt.setString(3, medecin.getAdresse());
            stmt.setString(4, medecin.getDateNaissance());
            stmt.setInt(5, medecin.getSpecialiteId());
            stmt.setLong(6, medecin.getCompteId());
            stmt.setLong(7, medecin.getIdMedecin());
            return stmt.executeUpdate()>0;
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

