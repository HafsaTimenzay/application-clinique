package com.example.gestionclinique.model.database.DAO;

import com.example.gestionclinique.model.Consultation;

import java.sql.*;

public class ConsultationDAO {

    private Connection connection;

    public ConsultationDAO(Connection connection) {
        this.connection = connection;
    }

    public void createConsultation(Consultation consultation) throws SQLException {
        String sql = "INSERT INTO Consultation (symptoms, diagnosis, notes, date, medecin_id, patient_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consultation.getSymptoms());
            stmt.setString(2, consultation.getDiagnosis());
            stmt.setString(3, consultation.getNotes());
            stmt.setDate(4, consultation.getDate());
            stmt.setLong(5, consultation.getMedecinId());
            stmt.setInt(6, consultation.getPatientId());
            stmt.executeUpdate();
        }
    }

    public void updateConsultation(Consultation consultation) throws SQLException {
        String sql = "UPDATE Consultation SET symptoms = ?, diagnosis = ?, notes = ?, date = ?, medecin_id = ?, patient_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, consultation.getSymptoms());
            stmt.setString(2, consultation.getDiagnosis());
            stmt.setString(3, consultation.getNotes());
            stmt.setDate(4, consultation.getDate());
            stmt.setLong(5, consultation.getMedecinId());
            stmt.setInt(6, consultation.getPatientId());
            stmt.setInt(7, consultation.getIdConsultation());
            stmt.executeUpdate();
        }
    }

    public void deleteConsultation(int id) throws SQLException {
        String sql = "DELETE FROM Consultation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

