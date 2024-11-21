package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.RendezVous;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RendezVousDAO {
    private final Connection connection;

    public RendezVousDAO(Connection connection) {
        this.connection = connection;
    }

    public List<RendezVous> getRendezVousByPatientId(int patientId) {
        List<RendezVous> rendezVousList = new ArrayList<>();
        String query = """
            SELECT r.id AS rendezvous_id,
                   r.date AS date_rendezvous,
                   s.titre AS specialite,
                   m.nom AS nom_medecin,
                   m.prenom AS prenom_medecin,
                   c.diagnosis AS diagnostic
            FROM rendezvous r
            JOIN medecin m ON r.medecin_id = m.id
            JOIN specialite s ON m.specialite_id = s.id
            LEFT JOIN consultation c ON r.id = c.calendrier_id
            WHERE r.patient_id = ?;
        """;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String date = rs.getString("date");
                String specialite = rs.getString("specialite");
                String doctor = rs.getString("doctor");
                String diagnosis = rs.getString("diagnosis");

                RendezVous rendezVous = new RendezVous(date, specialite, doctor, diagnosis);
                rendezVousList.add(rendezVous);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rendezVousList;
    }
}


//public class RendezVousDAO {
//
//    private Connection connection;
//
//    public RendezVousDAO(Connection connection) {
//        this.connection = connection;
//    }
//
//    public void createRendezVous(RendezVous rendezVous) throws SQLException {
//        String sql = "INSERT INTO RendezVous (type, medecin_id, patient_id, date_rendezvous, horaire) VALUES (?, ?, ?, ?, ?)";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setString(1, rendezVous.getType());
//            stmt.setLong(2, rendezVous.getMedecinId());
//            stmt.setInt(3, rendezVous.getPatientId());
//            stmt.setDate(4, rendezVous.getDateRendezVous());
//            stmt.setTime(5, rendezVous.getHoraire());
//            stmt.executeUpdate();
//        }
//    }
//
//    public List<RendezVous> getRendezVousByPatientId(int patientId) throws SQLException {
//        String sql = "SELECT * FROM RendezVous WHERE patient_id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, patientId);
//            ResultSet rs = stmt.executeQuery();
//            List<RendezVous> rendezVousList = new ArrayList<>();
//            while (rs.next()) {
//                RendezVous rdv = new RendezVous();
//                rdv.setId(rs.getInt("id"));
//                rdv.setType(rs.getString("type"));
//                rdv.setMedecinId(rs.getLong("medecin_id"));
//                rdv.setPatientId(rs.getInt("patient_id"));
//                rdv.setDateRendezVous(rs.getDate("date_rendezvous"));
//                rdv.setHoraire(rs.getTime("horaire"));
//                rendezVousList.add(rdv);
//            }
//            return rendezVousList;
//        }
//    }
//
//    public void cancelRendezVous(int id) throws SQLException {
//        String sql = "DELETE FROM RendezVous WHERE id = ?";
//        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
//            stmt.setInt(1, id);
//            stmt.executeUpdate();
//        }
//    }
//}
