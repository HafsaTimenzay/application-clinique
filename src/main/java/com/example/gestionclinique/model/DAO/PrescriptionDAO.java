package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Prescription;
import com.example.gestionclinique.model.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public static boolean insertPrescription(Prescription prescription) {
        String sql = "INSERT INTO prescription (consultation_id, medication, dosage, duration, instructions) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, prescription.getConsultationId());
            stmt.setString(2, prescription.getMedication());
            stmt.setString(3, prescription.getDosage());
            stmt.setString(4, prescription.getDuration());
            stmt.setString(5, prescription.getInstructions());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addPrescription(Prescription prescription) {
        String query = "INSERT INTO Prescription (consultation_id, medication, dosage, duration, instructions) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, prescription.getConsultation_id());
            statement.setString(2, prescription.getMedication());
            statement.setString(3, prescription.getDosage());
            statement.setString(4, prescription.getDuration());
            statement.setString(5, prescription.getInstructions());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Prescription> getAllPrescriptions() {
//        List<Prescription> prescriptions = new ArrayList<>();
//        String query = "SELECT * FROM Prescription";
//
//        try (Connection connection = ConnectionUtil.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(query)) {
//
//            while (resultSet.next()) {
//                Prescription prescription = new Prescription(
//                        resultSet.getInt("id_prescription"),
//                        resultSet.getInt("consultation_id"),
//                        resultSet.getString("medication"),
//                        resultSet.getString("dosage"),
//                        resultSet.getString("duration"),
//                        resultSet.getString("instructions")
//                );
//                prescriptions.add(prescription);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return prescriptions;
//    }
}
