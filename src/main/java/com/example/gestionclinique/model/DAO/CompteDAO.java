package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompteDAO {

    private Connection connection;

    public CompteDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCompte(Compte compte) throws SQLException {
        String sql = "INSERT INTO Compte (id_compte, email, username, password, type_utilisateur) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, compte.getIdCompte());
            stmt.setString(2, compte.getEmail());
            stmt.setString(3, compte.getUsername());
            stmt.setString(4, compte.getPassword());
            stmt.setString(5, compte.getTypeUtilisateur());
            stmt.executeUpdate();
        }
    }

    public Compte getCompteById(long id) throws SQLException {
        String sql = "SELECT * FROM Compte WHERE id_compte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Compte(rs.getLong("id_compte"), rs.getString("email"),
                        rs.getString("username"), rs.getString("password"),
                        rs.getString("type_utilisateur"));
            }
            return null;
        }
    }

    public boolean authenticate(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Compte WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void updateCompte(Compte compte) throws SQLException {
        String sql = "UPDATE Compte SET email = ?, username = ?, password = ?, type_utilisateur = ? WHERE id_compte = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, compte.getEmail());
            stmt.setString(2, compte.getUsername());
            stmt.setString(3, compte.getPassword());
            stmt.setString(4, compte.getTypeUtilisateur());
            stmt.setLong(5, compte.getIdCompte());
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
}
