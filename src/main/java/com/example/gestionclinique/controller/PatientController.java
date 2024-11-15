package com.example.gestionclinique.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.IOException;

public class PatientController {

    @FXML
    private Pane contentPane;  // The Pane where dynamic content will be loaded

    @FXML
    private Button dashboardButton, appointmentsButton, profileButton, logoutButton;

    // Function to load a specific panel into contentPane
    private void loadPanel(String fxmlFile) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Pane pane = loader.load();  // Load the panel dynamically
        contentPane.getChildren().setAll(pane);  // Replace the current content with the new panel
    }

    // Event handler for the Dashboard button
    @FXML
    private void loadDashboard(ActionEvent event) {
        try {
            loadPanel("/com/example/gestionclinique/view/patient/historique-patient.fxml");  // Replace with the actual path to your dashboard FXML
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event handler for the Appointments button
    @FXML
    private void loadAppointments(ActionEvent event) {
        try {
            loadPanel("/com/example/gestionclinique/view/patient/rendezvous-patient.fxml");  // Replace with the actual path to your appointments FXML
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event handler for the Profile button
    @FXML
    public void loadProfilePage() {
        try {
            loadPanel("/com/example/gestionclinique/view/patient/profil-patient.fxml");  // Path to profile FXML
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Event handler for the Logout button
    @FXML
    private void logout(ActionEvent event) {
        System.out.println("login out ...");
    }


    // Function to load the Profile page initially

    // Function to load a specific panel into contentPane

}
