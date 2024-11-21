package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class ProfilController {

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

    private BorderPane mainPane;
    private Long compteId;



    private final PatientDAO patientDAO;

    public void setCompteId(Long compteId) {
        this.compteId = compteId;
    }

    public ProfilController() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        this.patientDAO = new PatientDAO(connection);
    }

    public void updateProfile() throws SQLException {
        Patient patient = patientDAO.getPatientById(130);
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String birthDate = String.valueOf(birthDatePicker.getValue());
        String cin = cinField.getText();
        String gsm = gsmField.getText();
        String address = addressField.getText();
        Double weight = Double.parseDouble(weightField.getText());
        Double height = Double.parseDouble(heightField.getText());
        String gender = genderComboBox.getSelectionModel().getSelectedItem().toString();

        // condition regExp

        if (patient != null) {
            patient.setNom(firstName);
            patient.setPrenom(lastName);
            patient.setCIN(cin);
            patient.setGSM(gsm);
            patient.setAdresse(address);
            patient.setTaille(height);
            patient.setPoids(weight);
            patient.setDateNaissance(birthDate);
            patient.setSexe(gender);

            patientDAO.updatePatient(patient);
        } else {
            throw new IllegalArgumentException("Patient not found!");
        }
    }

    // Method to insert a patient during signup
    //public void signup() throws SQLException {
     //   Patient patient = new Patient();
    //    patient.setNom(firstNameField.getText());
    //    patient.setPrenom(lastNameField.getSelectedText());// GSM can temporarily hold the email in this example
    //    patient.setCompteId(compteId);

    //    patientDAO.InsertPatient(patient);
    //}

    // Method to update the profile after completing additional details


    // Method to retrieve a patient's information for display (e.g., in the profile page)
    public Patient getPatientProfile(int patientId) throws SQLException {
        return patientDAO.getPatientById(patientId);
    }


}
