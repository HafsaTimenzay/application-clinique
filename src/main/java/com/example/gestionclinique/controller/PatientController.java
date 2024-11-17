package com.example.gestionclinique.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PatientController {

    @FXML
    private Pane contentPane;

    @FXML
    private Label pageTitle;

    /**
     * Load the Dashboard view into the contentPane.
     */
    @FXML
    private void loadDashboard() {
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
    protected void loadProfilePage() {
        loadPage("profil-patient.fxml", "/ Profiel");
    }


    /**
     * Logout logic.
     */
    @FXML
    private void logout() {
        // Implement your logout logic here
        System.out.println("Logout clicked");
    }

    /**
     * Loads an FXML page into the contentPane and updates the page title.
     *
     * @param fxmlFile the FXML file to load
     * @param title    the title to display
     */
    private void loadPage(String fxmlFile, String title) {
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
