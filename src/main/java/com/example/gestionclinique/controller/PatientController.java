package com.example.gestionclinique.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PatientController {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button appointmentsButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Pane contentPane;

    @FXML
    public void initialize() {
        // Attach event handlers to buttons
        dashboardButton.setOnAction(event -> loadView("/com/example/gestionclinique/view/patient/historique-patient.fxml"));
        appointmentsButton.setOnAction(event -> loadView("/com/example/gestionclinique/view/patient/rendezvous-patient.fxml"));
        profileButton.setOnAction(event -> loadView("/com/example/gestionclinique/view/patient/profil-patient.fxml"));
        logoutButton.setOnAction(event -> logout());
    }

    private void loadView(String fxmlFile) {
        try {
            // Load the new FXML file
            Pane newPane = FXMLLoader.load(getClass().getResource(fxmlFile));
            // Replace the content of contentPane
            contentPane.getChildren().setAll(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        // Implement logout logic (e.g., redirect to login page)
        System.out.println("Logging out...");
    }
}
