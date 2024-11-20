package com.example.gestionclinique.controller;


import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;


public class PatientController {

    @FXML
    private Pane contentPane;

    @FXML
    private Label pageTitle;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;


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
    // Assuming you're using @FXML to inject this from FXML

    // This is your initialization method, where UI elements are guaranteed to be initialized.
    @FXML
    public void initialize() {
        System.out.println("firstNameField : " + firstNameField);

        // Check if the firstNameField TextField is set, otherwise just debug it
        System.out.println("condition to prSave : " + (firstNameField != null));

    }
    private Patient patientCt;
    // Now ensure you call ProfileSave() correctly when you load the patient data.
    public void ProfileSave(Patient patient) {
        patientCt = patient;
        // Ensure the firstNameField is not null
        if (firstNameField != null) {
            // Print the patient's name to the console
            System.out.println("Patient's name: " + patientCt.getNom());

            // Set the firstNameField's text to the patient's name
            firstNameField.setText(patient.getNom());  // Setting the name from the patient object
        } else {
            System.out.println("firstNameField is null in ProfileSave()");
        }
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

            // Load the FXML file and get the controller instance
            Node page = loader.load();
            PatientController patientController = loader.getController();  // Correct controller injection

            // Pass the patient data to the controller
            System.out.println("firstNameField from controller: " + patientController.firstNameField);

            patientController.ProfileSave(patientCt);
            // Make sure the TextField is properly injected before calling ProfileSave
//            if (patientController.firstNameField != null) {
//                System.out.println("Injecting patient data into firstNameField...");
//                patientController.firstNameField.setText("HAfsa"); // Example of setting text
//            } else {
//                System.out.println("firstNameField is still null after FXML loading!");
//            }

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
