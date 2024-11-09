package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_clinique";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Probleme de connection");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Class not found");
        }

        return connection;
    }
}

