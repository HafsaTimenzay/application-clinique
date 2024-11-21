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
            stmt.setString(6, patient.getDateNaissance());
            stmt.setString(7, patient.getAdresse());
            stmt.setDouble(8, patient.getTaille());
            stmt.setDouble(9, patient.getPoids());
            stmt.setLong(10, patient.getCompteId());
            stmt.executeUpdate();
        }
    }

    public boolean InsertPatientSignUp(Patient patient) throws SQLException {
        String query = "INSERT INTO Patient (nom, prenom, compte_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patient.getNom());
            preparedStatement.setString(2, patient.getPrenom());
            preparedStatement.setLong(3, patient.getCompteId());
            return preparedStatement.executeUpdate() > 0;
        }
    }


    public Patient getPatientById(int idPatient) throws SQLException {
        String query = "SELECT p.id_patient, p.nom, p.prenom, c.id_compte AS compte_id, c.email " +
                "FROM Patient p " +
                "JOIN Compte c ON p.compte_id = c.id_compte " +
                "WHERE p.id_patient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idPatient);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setIdPatient(rs.getInt("id_patient"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setCompteId(rs.getInt("compte_id"));
                patient.setEmail(rs.getString("email")); // Map the email correctly
                return patient;
            }
        }
        return null; // Handle cases where no patient is found
    }


    public int getPatientIdByCompteId(int compteId) throws SQLException {
        // SQL query to fetch the patient ID by compteId
        String query = "SELECT id_patient FROM Patient WHERE compte_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, compteId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_patient");
            }
            return 0; // or handle as appropriate
        }
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
        String query = "UPDATE Patient SET nom = ?, prenom = ?, sexe = ?, CIN = ?, GSM = ?, dateNaissance = ?, adress = ?, taille = ?, poids = ?, compte_id = ? WHERE id_patient = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, patient.getNom());
            stmt.setString(2, patient.getPrenom());
            stmt.setString(3, patient.getSexe());
            stmt.setString(4, patient.getCIN());
            stmt.setString(5, patient.getGSM());
            stmt.setString(6, patient.getDateNaissance());
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
        patient.setDateNaissance(rs.getString("dateNaissance"));
        patient.setAdresse(rs.getString("adresse"));
        patient.setTaille(rs.getDouble("taille"));
        patient.setPoids(rs.getDouble("poids"));
        patient.setCompteId(rs.getLong("compteId"));
        return patient;
    }

    public Patient getPatientByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Patient p JOIN Compte c ON p.compte_id = c.id_compte WHERE c.email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Patient patient = new Patient();
                patient.setIdPatient(rs.getInt("id_patient"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setEmail(rs.getString("email"));
                patient.setCIN(rs.getString("CIN"));
                patient.setGSM(rs.getString("GSM"));
                patient.setAdresse(rs.getString("adress"));
                patient.setPoids(rs.getDouble("poids"));
                patient.setTaille(rs.getDouble("Taille"));
                patient.setDateNaissance(rs.getString("DateNaissance"));
                patient.setSexe(rs.getString("sexe"));
                patient.setCompteId(rs.getInt("compte_id"));
                return patient;
            }
            return null; // No patient found
        }
    }

}
