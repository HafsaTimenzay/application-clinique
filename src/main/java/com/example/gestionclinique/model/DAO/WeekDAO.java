package com.example.gestionclinique.model.DAO;

import com.example.gestionclinique.model.Week;
import com.example.gestionclinique.model.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeekDAO {

    public void addWeek(Week week) {
        String query = "INSERT INTO Week (startDate, endDate) VALUES (?, ?)";

        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDate(1, Date.valueOf(week.getStartDate()));
            statement.setDate(2, Date.valueOf(week.getEndDate()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Week> getAllWeeks() {
        List<Week> weeks = new ArrayList<>();
        String query = "SELECT * FROM Week";

        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Week week = new Week(
                        resultSet.getInt("id_week"),
                        resultSet.getDate("startDate").toLocalDate(),
                        resultSet.getDate("endDate").toLocalDate()
                );
                weeks.add(week);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weeks;
    }
}
