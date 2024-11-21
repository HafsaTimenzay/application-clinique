package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Compte;

import java.sql.*;

public class CompteDAO {

    private Connection connection;

    public CompteDAO(Connection connection) {
        this.connection = connection;
    }

    public int createCompte(Compte compte) throws SQLException {
        String sql = "INSERT INTO Compte (email, firstName, lastName, password, type_utilisateur) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, compte.getEmail());
            stmt.setString(2, compte.getFirstName());
            stmt.setString(3, compte.getLastName());
            stmt.setString(4, compte.getPassword());
            stmt.setString(5, compte.getTypeUtilisateur());
            stmt.executeUpdate();
            try (var rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }




    public Compte getCompteById(long id) throws SQLException {
        String sql = "SELECT * FROM Compte WHERE id_compte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create a new Compte object
                Compte compte = new Compte();
                compte.setEmail(rs.getString("email"));
                compte.setFirstName(rs.getString("firstName"));
                compte.setLastName(rs.getString("lastName"));
                compte.setPassword(rs.getString("password"));
                compte.setTypeUtilisateur(rs.getString("type_utilisateur"));

                return compte;
            }
        }

        return null;
    }


    public boolean authenticate(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Compte WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public String getUserType(String email, String password) throws SQLException {
        String sql = "SELECT type_utilisateur FROM Compte WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Return the type_utilisateur if a match is found
                return rs.getString("type_utilisateur");
            } else {
                // Return null or throw an exception if no match is found
                return null;
            }
        }
    }


    public void updateCompte(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET email = ?, firstName = ?, lastName = ?, password = ?, type_utilisateur = ? WHERE id_compte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, compte.getEmail());
            stmt.setString(2, compte.getFirstName());
            stmt.setString(3, compte.getLastName());
            stmt.setString(4, compte.getPassword());
            stmt.setString(5, compte.getTypeUtilisateur());
            stmt.setLong(6, compte.getIdCompte());
            stmt.executeUpdate();
        }
    }

    public void deleteCompte(long id) throws SQLException {
        String sql = "DELETE FROM Compte WHERE id_compte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    public Compte getCompteByEmail(String email) throws SQLException {
        String query = "SELECT * FROM Compte WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Compte compte = new Compte();
                compte.setIdCompte(rs.getInt("idCompte"));
                compte.setEmail(rs.getString("email"));
                compte.setFirstName(rs.getString("firstName"));
                compte.setLastName(rs.getString("lastName"));
                compte.setPassword(rs.getString("password"));
                compte.setTypeUtilisateur(rs.getString("typeUtilisateur"));
                return compte;
            }
            return null; // No account found
        }
    }

}
