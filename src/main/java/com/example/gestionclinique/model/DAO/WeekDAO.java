package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Week;
import com.example.gestionclinique.model.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeekDAO {
    private final Connection connection;

    // Constructor to initialize connection
    public WeekDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a week into the database
    public boolean insertWeek(Week week) throws SQLException {
        String query = "INSERT INTO week (week_range) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, week.getWeekRange());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Method to get all saved weeks from the database
    public List<Week> getAllWeeks() throws SQLException {
        List<Week> weeks = new ArrayList<>();
        String query = "SELECT * FROM week";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Week week = new Week(rs.getString("week_range"));
                week.setId(rs.getInt("id"));
                weeks.add(week);
            }
        }
        return weeks;
    }
}

