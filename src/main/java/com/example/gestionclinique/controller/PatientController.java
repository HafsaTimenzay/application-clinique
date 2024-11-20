package com.example.gestionclinique.controller;


import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    private TextField lastNameField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private TextField gsmField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField cinField;
    @FXML
    private ComboBox genderComboBox;


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
    public void loadProfilePage() {loadPage("profil-patient.fxml", "/ Profiel");}

//    @FXML
//    public void initialize() {
//        System.out.println("firstNameField : " + firstNameField);
//
//        // Check if the firstNameField TextField is set, otherwise just debug it
//        System.out.println("condition to prSave : " + (firstNameField != null));
//
//    }
    private Patient patientCt;


    public void ProfileSave(Patient patient) {
        patientCt = patient;
        if (firstNameField != null) {
            System.out.println("Patient's name: " + patientCt.getEmail());

            firstNameField.setText(patient.getNom());
            lastNameField.setText(patient.getPrenom());

            emailField.setText(patient.getEmail());

        } else {
            System.out.println("firstNameField is null in ProfileSave()");
        }
    }

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

            contentPane.getChildren().clear();
            contentPane.getChildren().add(page);

            // Update the page title
            pageTitle.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
