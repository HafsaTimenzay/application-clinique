package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.Compte;
import com.example.gestionclinique.model.DAO.CompteDAO;
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

    public AuthController() {
        try {
            Connection connection = ConnectionUtil.getConnection();
            compteDAO = new CompteDAO(connection);
            patientDAO = new PatientDAO(connection);
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
            if (compteDAO.authenticate(emailField.getText(), passwordField.getText())) {
                String userType = compteDAO.getUserType(emailField.getText(), passwordField.getText());
                FXMLLoader loader;
                Parent root;

                if ("Patient".equals(userType)) {
                    loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/main-patient.fxml"));
                    root = loader.load();

                    PatientController patientController = loader.getController();
                    patientController.loadDashboard(); // Charge le tableau de bord
                } else if ("Medecin".equals(userType)) {
                    loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/main-medecin.fxml"));
                    root = loader.load();

                    MedecinController medecinController = loader.getController();
                    medecinController.loadDashboard(); // Charge le tableau de bord
                } else {
                    showAlert("Error", "Unknown user type.");
                    return;
                }

                // Affiche la nouvelle scène
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
                        System.out.println(patientDAO.InsertPatientSignUp(patient));
                        if (patientDAO.InsertPatientSignUp(patient)) {
                            System.out.println("id avec patient : "+patient.getCompteId());
                            // Charge l'email après l'inscription
                            String emailPatient = compte.getEmail();
                            openPatientProfile(event, patient, emailPatient);
                        } else {
                            showAlert("Error", "Error while creating patient.");
                        }
                    } else if ("Medecin".equals(typeUtilisateur)) {
                        openMedecinProfile(event);
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



    private void openMedecinProfile(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/main-medecin.fxml"));
        Parent root = loader.load();

        MedecinController medecinController = loader.getController();
        medecinController.loadProfilePageMe();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.setMaximized(true);
        currentStage.setTitle("Medecin Profile");
        currentStage.show();
    }
}
