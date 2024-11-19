package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PatientController {

    @FXML
    private Pane contentPane;

    @FXML
    private Label pageTitle;


    /**
     * Load the Dashboard view into the contentPane.
     */
    @FXML
    public void loadDashboard() {
        loadPage("historique-patient.fxml", "/ Dashboard");
    }

    /**
     * Load the Appointments view into the contentPane.
     */
    @FXML
    private void loadAppointments() {
        loadPage("rendezvous-patient.fxml", "/ Appointment");
    }

    /**
     * Load the Profile view into the contentPane.
     */
    @FXML
    public void loadProfilePage() {
        loadPage("profil-patient.fxml", "/ Profiel");
    }


    /**
     * Logout logic.
     */
    @FXML
    private void logout() {
        try {
            // Load the login FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/Authentification/login-view.fxml"));
            Parent root = loader.load();

            // Get the current stage (window) and set the new scene
            Stage currentStage = (Stage) contentPane.getScene().getWindow(); // or pageTitle.getScene().getWindow()
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Login");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads an FXML page into the contentPane and updates the page title.
     *
     * @param fxmlFile the FXML file to load
     * @param title    the title to display
     */
    public void loadPage(String fxmlFile, String title) {
        try {
            // Ensure the path is relative to the resource folder
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/" + fxmlFile));
            Node page = loader.load();

            // Clear the existing content and load the new page
            contentPane.getChildren().clear();
            contentPane.getChildren().add(page);

            // Update the page title
            pageTitle.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
