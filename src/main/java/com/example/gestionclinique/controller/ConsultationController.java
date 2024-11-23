package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.Consultation;
import com.example.gestionclinique.model.DAO.ConsultationDAO;
import com.example.gestionclinique.model.DAO.PrescriptionDAO;
import com.example.gestionclinique.model.Prescription;
import javafx.scene.control.TextField;
import com.example.gestionclinique.model.RendezVous;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;

import javax.swing.text.TabExpander;
import java.awt.*;
import javafx.scene.control.TextField;

public class ConsultationController {

    @FXML
    private Button text;
    @FXML private TextField symptomsField;
    @FXML private TextField diagnosisField;
    @FXML private TextField notesField;
    @FXML private DatePicker datePicker;
    @FXML private TextField medicationField;
    @FXML private TextField dosageField;
    @FXML private TextField durationField;
    @FXML private TextField instructionsField;

    public void insertConslt(ActionEvent event) {
        try {
            // Créez une consultation
            Consultation consultation = new Consultation();
            consultation.setPatientId(230); // Vous pouvez récupérer l'ID dynamiquement
            consultation.setSymptoms(symptomsField.getText());
            consultation.setDiagnosis(diagnosisField.getText());
            consultation.setNotes(notesField.getText());
            consultation.setDate(datePicker.getValue().toString());

            if (ConsultationDAO.insertConsultation(consultation)) {
                // Créez une prescription associée
                Prescription prescription = new Prescription();
                prescription.setConsultationId(consultation.getIdConsultation());
                prescription.setMedication(medicationField.getText());
                prescription.setDosage(dosageField.getText());
                prescription.setDuration(durationField.getText());
                prescription.setInstructions(instructionsField.getText());

                if (PrescriptionDAO.insertPrescription(prescription)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Consultation and Prescription saved successfully!");
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save Prescription!");
                    alert.show();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to save Consultation!");
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
            alert.show();
        }
    }
}
