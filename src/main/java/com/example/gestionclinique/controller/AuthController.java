package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.Compte;
import com.example.gestionclinique.model.DAO.CompteDAO;
import com.example.gestionclinique.model.DAO.MedecinDAO;
import com.example.gestionclinique.model.Medecin;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AuthController {

    @FXML
    private TextField emailField; // Email field
    @FXML
    private PasswordField passwordField; // Password field
    @FXML
    private PasswordField password;
    @FXML
    private RadioButton medecinRadio;
    @FXML
    private RadioButton patientRadio;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    private CompteDAO compteDAO;
    private PatientDAO patientDAO;
    private MedecinDAO medecinDAO;

    public AuthController() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            compteDAO = new CompteDAO(connection);
            patientDAO = new PatientDAO(connection);
            medecinDAO = new MedecinDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateCredentials(String email, String password) {
        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            showAlert("Invalid Email", "Please enter a valid email address.");
            return false;
        }
        if (password == null || password.isEmpty()) {
            showAlert("Invalid Password", "Password cannot be empty.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleLoginClick(ActionEvent event) {
        try {
            String email = emailField.getText();
            String password = passwordField.getText();

            if (compteDAO.authenticate(email, password)) {
                String userType = compteDAO.getUserType(email, password);
                FXMLLoader loader;
                Parent root;

                if ("Patient".equals(userType)) {
                    // Fetch the patient object
                    Patient patient = patientDAO.getPatientByEmail(email); // New method to fetch Patient by email

                    if (patient == null) {
                        throw new IllegalArgumentException("Patient object cannot be null!");
                    }

                    loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
                    root = loader.load();

                    // Pass the patient object to the controller
                    PatientController patientController = loader.getController();
                    patientController.ProfileSave(patient); // Load patient data
                    patientController.loadDashboard();

                } else if ("Medecin".equals(userType)) {

                    Medecin medecin = medecinDAO.getMedecinByEmail(email); // New method to fetch Patient by email

                    if (medecin == null) {
                        throw new IllegalArgumentException("medecin object cannot be null!");
                    }

                    loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/main-medecin.fxml"));
                    root = loader.load();

                    // Pass the patient object to the controller
                    MedecinController medecinController = loader.getController();
                    medecinController.ProfileSave(medecin); // Load patient data
                    medecinController.loadDashboard();

                } else {
                    showAlert("Error", "Unknown user type.");
                    return;
                }

                // Show the new scene
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                currentStage.setScene(scene);
                currentStage.setMaximized(true);
                currentStage.setTitle(userType + " Dashboard");
                currentStage.show();

            } else {
                showAlert("Login Failed", "Invalid email or password.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while processing your request.");
        }
    }


    @FXML
    private void handleSignupClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/Authentification/signup-view.fxml"));
            Parent root = loader.load();

//            AuthController signupController = loader.getController();
//            signupController.firstName.setText("hafsa");
//            System.out.println("click called");
//            System.out.println("firstName: " + firstName);
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Sign-Up");
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSignupSubmit(ActionEvent event) {
        ToggleGroup userTypeGroup = new ToggleGroup();
        medecinRadio.setToggleGroup(userTypeGroup);
        patientRadio.setToggleGroup(userTypeGroup);

        RadioButton selectedButton = (RadioButton) userTypeGroup.getSelectedToggle();
        String typeUtilisateur = selectedButton != null ? selectedButton.getText() : null;

        if (email.getText().isEmpty() || password.getText().isEmpty() || firstName.getText().isEmpty() ||
                lastName.getText().isEmpty() || typeUtilisateur == null || typeUtilisateur.isEmpty()) {
            showAlert("Error", "Please fill all the fields.");
        } else if (validateCredentials(email.getText(), password.getText())) {
            try {
                Compte compte = new Compte();
                compte.setEmail(email.getText());
                compte.setFirstName(firstName.getText());
                compte.setLastName(lastName.getText());
                compte.setPassword(password.getText());
                compte.setTypeUtilisateur(typeUtilisateur);

                int idCompte = compteDAO.createCompte(compte);
                if (idCompte != -1) {
                    System.out.println("id :"+idCompte+".");
                    System.out.println("Patient".equals(typeUtilisateur));
                    if ("Patient".equals(typeUtilisateur)) {
                        Patient patient = new Patient();
                        patient.setNom(firstName.getText());
                        patient.setPrenom(lastName.getText());
                        patient.setCompteId(idCompte);

                        // Insert the patient and retrieve the generated ID
                        if (patientDAO.InsertPatientSignUp(patient)) {
                            System.out.println("id avec patient : " + patient.getCompteId());

                            // Ensure the patient object now has the correct ID (assuming the InsertPatientSignUp method handles ID generation correctly)
                            int patientId = patientDAO.getPatientIdByCompteId((int) patient.getCompteId()); // Assuming this method exists to fetch patient ID

                            patient.setIdPatient(patientId);  // Set the correct ID to the patient object

                            // Load the profile page with the updated patient object
                            String emailPatient = compte.getEmail();
                            openPatientProfile(event, patient, emailPatient);
                        } else {
                            showAlert("Error", "Error while creating patient.");
                        }
                    }
                    else if ("Medecin".equals(typeUtilisateur)) {
                        Medecin medecin = new Medecin();
                        medecin.setNom(firstName.getText());
                        medecin.setPrenom(lastName.getText());
                        medecin.setCompteId(idCompte);

                        // Insert the medecin and retrieve the generated ID
                        if (medecinDAO.InsertMedecinSignUp(medecin)) {
                            System.out.println("id avec medecin : " + medecin.getCompteId());

                            // Ensure the medecin object now has the correct ID (assuming the InsertmedecinSignUp method handles ID generation correctly)
                            int medecinId = medecinDAO.getMedecinIdByCompteId((int) medecin.getCompteId()); // Assuming this method exists to fetch medecin ID

                            medecin.setIdMedecin(medecinId);  // Set the correct ID to the medecin object

                            // Load the profile page with the updated medecin object
                            String emailmedecin = compte.getEmail();
                            openMedecinProfile(event, medecin, emailmedecin);
                        } else {
                            showAlert("Error", "Error while creating medecin.");
                        }
                    }
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                showAlert("Error", "An error occurred during signup.");
            }
        }
    }

    private void openPatientProfile(ActionEvent event, Patient patient, String email) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
        Parent root = loader.load();  // Load the FXML file and automatically inject the controller

        // Retrieve the controller from the FXMLLoader
        PatientController patientController = loader.getController();


        // Call ProfileSave() to pass the patient data to the controller and print the name
        patientController.ProfileSave(patient);
        patientController.loadProfilePage();

        // Show the new scene
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setMaximized(true);
        currentStage.setTitle("Patient Profile");
        currentStage.show();
    }



    private void openMedecinProfile(ActionEvent event, Medecin medecin, String email) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/main-medecin.fxml"));
        Parent root = loader.load();

        // Ensure that the controller is properly set by FXMLLoader
        MedecinController medecinController = loader.getController();

        if (medecinController != null) {
            medecinController.ProfileSave(medecin);  // Pass the Medecin object to the controller
            medecinController.loadProfilePageMe();   // Load the profile page for the medecin
        } else {
            System.err.println("MedecinController is null!");
        }

        // Set up the new scene and window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setMaximized(true);
        currentStage.setTitle("Medecin Profile");
        currentStage.show();
    }

}
