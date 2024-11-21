package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.DAO.RendezVousDAO;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.RendezVous;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

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
    private TextField addressField;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField cinField;
    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TableView<RendezVous> tableRendezVous;
    @FXML
    private TableColumn<RendezVous, String> colDate;
    @FXML
    private TableColumn<RendezVous, String> colSpecialite;
    @FXML
    private TableColumn<RendezVous, String> colDoctor;
    @FXML
    private TableColumn<RendezVous, String> colDiagnosis;
    @FXML
    private TableColumn<RendezVous, Button> colAction;

    private Patient patientCt; // Global patient variable
    private final PatientDAO patientDAO;

    public PatientController() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        this.patientDAO = new PatientDAO(connection);
    }

    @FXML
    public void initialize() {
         // Bind columns to RendezVous properties
        if (colDate == null) {
            System.err.println("colDate is null!");
            return;
        }
        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        colSpecialite.setCellValueFactory(cellData -> cellData.getValue().specialiteProperty());
        colDoctor.setCellValueFactory(cellData -> cellData.getValue().doctorProperty());
        colDiagnosis.setCellValueFactory(cellData -> cellData.getValue().diagnosisProperty());

        // Populate the TableView with data
        try {
            RendezVousDAO rendezVousDAO = new RendezVousDAO(ConnectionUtil.getConnection());
            List<RendezVous> rendezVousList = rendezVousDAO.getRendezVousByPatientId(1); // Replace with dynamic patient ID
            tableRendezVous.setItems(FXCollections.observableArrayList(rendezVousList));
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        colAction.setCellValueFactory(cellData -> {
//            Button actionButton = new Button("Consult");
//            actionButton.setOnAction(e -> navigateToConsultation(cellData.getValue()));
//            return new SimpleObjectProperty<>(actionButton);
//        });


        //  Initialize gender ComboBox
        if (genderComboBox != null) {
            ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
            genderComboBox.setItems(genders);
        }
    }

    private void navigateToConsultation(RendezVous rendezVous) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/consultation-patient.fxml"));
            Parent root = loader.load();

            ConsultationController consultationController = loader.getController();
            consultationController.setRendezVous(rendezVous); // Pass selected rendezvous to the next controller

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Consultation & Prescription");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/Authentification/login-view.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) contentPane.getScene().getWindow();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.setTitle("Login");
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadDashboard() {
        loadPage("historique-patient.fxml", "/ Dashboard");
    }

    @FXML
    private void loadAppointments() {
        loadPage("rendezvous-patient.fxml", "/ Appointment");
    }

    @FXML
    public void loadProfilePage() {
        loadPage("profil-patient.fxml", "/ Profile");
    }

    public void ProfileSave(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient object cannot be null!");
        }

        this.patientCt = patient; // Update global patient variable with the new patient that has the ID set
        System.out.println("Loaded patient: " + patientCt);
        if (firstNameField == null || genderComboBox == null) {
            System.err.println("FXML fields are not initialized. Check your FXML file and its controller.");
            return;
        }

        String dateFromDb = patient.getDateNaissance(); // Récupérer la date depuis l'objet Patient
        if (dateFromDb != null && !dateFromDb.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateFromDb, formatter);
                birthDatePicker.setValue(localDate); // Affecter la valeur au DatePicker
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format in database: " + dateFromDb);
            }
        }

        genderComboBox.getSelectionModel().select(patient.getSexe());
        firstNameField.setText(patient.getNom());
        lastNameField.setText(patient.getPrenom());
        cinField.setText(patient.getCIN());
        gsmField.setText(patient.getGSM());
        addressField.setText(patient.getAdresse());
        weightField.setText(String.valueOf(patient.getPoids()));
        heightField.setText(String.valueOf(patient.getTaille()));

    }

    public void updateProfile() throws SQLException {
        if (patientCt == null || patientCt.getIdPatient() == 0) {
            throw new IllegalArgumentException("No patient loaded to update!");
        }
        System.out.println(patientCt);
        System.out.println(patientCt.getIdPatient());

        patientCt.setIdPatient(patientCt.getIdPatient());

        // Validate fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String cin = cinField.getText();
        String gsm = gsmField.getText();
        String address = addressField.getText();
        String gender = genderComboBox.getSelectionModel().getSelectedItem();
        Double weight, height;

        LocalDate date = birthDatePicker.getValue();

        if (date != null) {
            // Format the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = date.format(formatter);

            // Use the formatted date string
            System.out.println("Selected date: " + formattedDate);
        }
        System.out.println("date : "+ date);

        try {
            weight = Double.parseDouble(weightField.getText());
            height = Double.parseDouble(heightField.getText());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Weight and height must be numeric values!");
        }

            // Update patient object
            patientCt.setNom(firstName);
            patientCt.setPrenom(lastName);
            patientCt.setCIN(cin);
            patientCt.setGSM(gsm);
            patientCt.setAdresse(address);
            patientCt.setPoids(weight);
            patientCt.setTaille(height);
            patientCt.setDateNaissance(date.toString());
            patientCt.setSexe(gender);

            // Update in database
            boolean isUpdated = patientDAO.updatePatient(patientCt);
            if (isUpdated) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setContentText("the information is saved");
                alert.showAndWait();
            } else {
                throw new SQLException("Failed to update patient in the database.");
            }

    }



    public void loadPage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/" + fxmlFile));
            Node page = loader.load();
            PatientController patientController = loader.getController();

            // Pass the patient data
            patientController.ProfileSave(patientCt);

            // Replace content
            contentPane.getChildren().clear();
            contentPane.getChildren().add(page);

            // Update title
            pageTitle.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
