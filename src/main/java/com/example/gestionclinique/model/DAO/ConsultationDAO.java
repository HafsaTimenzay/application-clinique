package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Consultation;
import com.example.gestionclinique.model.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConsultationDAO {

    private Connection connection;

    public ConsultationDAO(Connection connection) {
        this.connection = connection;
    }

    public static boolean insertConsultation(Consultation consultation) {
        String sql = "INSERT INTO consultation (patient_id, symptoms, diagnosis, notes, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, consultation.getPatientId());
            stmt.setString(2, consultation.getSymptoms());
            stmt.setString(3, consultation.getDiagnosis());
            stmt.setString(4, consultation.getNotes());
            stmt.setDate(5, java.sql.Date.valueOf(consultation.getDate()));
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                // Retrieve the generated ID if needed
                var rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    consultation.setIdConsultation(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    public void createConsultation(Consultation consultation) throws SQLException {
//        String sql = "INSERT INTO Consultation (symptoms, diagnosis, notes, date, medecin_id, patient_id) VALUES (?, ?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, consultation.getSymptoms());
//            stmt.setString(2, consultation.getDiagnosis());
//            stmt.setString(3, consultation.getNotes());
//            stmt.setDate(4, consultation.getDate());
//            stmt.setLong(5, consultation.getMedecinId());
//            stmt.setInt(6, consultation.getPatientId());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void updateConsultation(Consultation consultation) throws SQLException {
//        String sql = "UPDATE Consultation SET symptoms = ?, diagnosis = ?, notes = ?, date = ?, medecin_id = ?, patient_id = ? WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, consultation.getSymptoms());
//            stmt.setString(2, consultation.getDiagnosis());
//            stmt.setString(3, consultation.getNotes());
//            stmt.setDate(4, consultation.getDate());
//            stmt.setLong(5, consultation.getMedecinId());
//            stmt.setInt(6, consultation.getPatientId());
//            stmt.setInt(7, consultation.getIdConsultation());
//            stmt.executeUpdate();
//        }
//    }
//
//    public void deleteConsultation(int id) throws SQLException {
//        String sql = "DELETE FROM Consultation WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        }
//    }
}

