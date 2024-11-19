package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    private final Connection connection;

    public PatientDAO(Connection connection) {
        this.connection = connection;
    }

    // Add Patient
    public void InsertPatient(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (nom, prenom, sexe, CIN, GSM, dateNaissance, adresse, taille, poids, compteId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getSexe());
            stmt.setString(4, patient.getCIN());
            stmt.setString(5, patient.getGSM());
            stmt.setDate(6, new java.sql.Date(patient.getDateNaissance().getTime()));
            stmt.setString(7, patient.getAdresse());
            stmt.setDouble(8, patient.getTaille());
            stmt.setDouble(9, patient.getPoids());
            stmt.setLong(10, patient.getCompteId());
            stmt.executeUpdate();
        }
    }

    public boolean InsertPatientSignUp(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (nom, prenom) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            return stmt.executeUpdate()>0;
        }
    }

    // Retrieve Patient by ID
    public Patient getPatientById(int id) throws SQLException {
        String query = "SELECT * FROM Patient WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPatient(rs);
            }
        }
        return null;
    }

    // Retrieve All Patients
    public List<Patient> getAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patients.add(mapResultSetToPatient(rs));
            }
        }
        return patients;
    }

    // Update Patient
    public boolean updatePatient(Patient patient) throws SQLException {
        String query = "UPDATE Patient SET nom = ?, prenom = ?, sexe = ?, CIN = ?, GSM = ?, dateNaissance = ?, adresse = ?, taille = ?, poids = ?, compteId = ? WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getSexe());
            stmt.setString(4, patient.getCIN());
            stmt.setString(5, patient.getGSM());
            stmt.setDate(6, new java.sql.Date(patient.getDateNaissance().getTime()));
            stmt.setString(7, patient.getAdresse());
            stmt.setDouble(8, patient.getTaille());
            stmt.setDouble(9, patient.getPoids());
            stmt.setLong(10, patient.getCompteId());
            stmt.setInt(11, patient.getIdPatient());
            return stmt.executeUpdate()>0;
        }
    }

    // Delete Patient
    public void deletePatient(int id) throws SQLException {
        String query = "DELETE FROM Patient WHERE idPatient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Map ResultSet to Patient
    private Patient mapResultSetToPatient(ResultSet rs) throws SQLException {
        Patient patient = new Patient();
        patient.setIdPatient(rs.getInt("idPatient"));
        patient.setNom(rs.getString("nom"));
        patient.setPrenom(rs.getString("prenom"));
        patient.setSexe(rs.getString("sexe"));
        patient.setCIN(rs.getString("CIN"));
        patient.setGSM(rs.getString("GSM"));
        patient.setDateNaissance(rs.getDate("dateNaissance"));
        patient.setAdresse(rs.getString("adresse"));
        patient.setTaille(rs.getDouble("taille"));
        patient.setPoids(rs.getDouble("poids"));
        patient.setCompteId(rs.getLong("compteId"));
        return patient;
    }
}
