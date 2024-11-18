package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientDAO {

    private Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    public void createPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO Patient (id_patient, nom, prenom, sexe, CIN, GSM, DateNaissance, adresse, Taille, poids, compte_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, patient.getIdPatient());
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setString(4, patient.getSexe());
            stmt.setString(5, patient.getCIN());
            stmt.setString(6, patient.getGSM());
            stmt.setDate(7, patient.getDateNaissance());
            stmt.setString(8, patient.getAdresse());
            stmt.setDouble(9, patient.getTaille());
            stmt.setDouble(10, patient.getPoids());
            stmt.setLong(11, patient.getCompteId());
            stmt.executeUpdate();
        }
    }

    public Patient getPatientById(int id) throws SQLException {
        String sql = "SELECT * FROM Patient WHERE id_patient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt("id_patient"), rs.getString("nom"),
                        rs.getString("prenom"), rs.getString("sexe"), rs.getString("CIN"),
                        rs.getString("GSM"), rs.getDate("DateNaissance"),
                        rs.getString("adresse"), rs.getDouble("Taille"),
                        rs.getDouble("poids"), rs.getLong("compte_id"));
            }
            return null;
        }
    }

    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE Patient SET nom = ?, prenom = ?, sexe = ?, CIN = ?, GSM = ?, DateNaissance = ?, adresse = ?, Taille = ?, poids = ?, compte_id = ? WHERE id_patient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getSexe());
            stmt.setString(4, patient.getCIN());
            stmt.setString(5, patient.getGSM());
            stmt.setDate(6, patient.getDateNaissance());
            stmt.setString(7, patient.getAdresse());
            stmt.setDouble(8, patient.getTaille());
            stmt.setDouble(9, patient.getPoids());
            stmt.setLong(10, patient.getCompteId());
            stmt.setInt(11, patient.getIdPatient());
            stmt.executeUpdate();
        }
    }

    public void deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM Patient WHERE id_patient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
