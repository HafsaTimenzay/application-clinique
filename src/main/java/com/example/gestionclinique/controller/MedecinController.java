package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.*;
import com.example.gestionclinique.model.DAO.ConsultationDAO;
import com.example.gestionclinique.model.DAO.DisponibiliteDAO;
import com.example.gestionclinique.model.DAO.MedecinDAO;
import com.example.gestionclinique.model.DAO.PrescriptionDAO;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MedecinController {
    @FXML private CheckBox wednesdayCheckBox;
    @FXML private TextField wednesdayStartTime;
    @FXML private TextField wednesdayEndTime;
    @FXML private CheckBox thursdayCheckBox;
    @FXML private TextField thursdayStartTime;
    @FXML private TextField thursdayEndTime;
    @FXML private CheckBox fridayCheckBox;
    @FXML private TextField fridayStartTime;
    @FXML private TextField fridayEndTime;
    @FXML private CheckBox saturdayCheckBox;
    @FXML private TextField saturdayStartTime;
    @FXML private TextField saturdayEndTime;
    @FXML private ComboBox<String> weekComboBox;
    @FXML private Button saveButton;
    @FXML private Pane contentPane;
    @FXML private Label pageTitle;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private DatePicker birthDatePicker;
    @FXML private TextField addressField;
    @FXML private ComboBox<Specialite> departComboBox;
    @FXML private TableView<RendezVous> tableRendezVous;
    @FXML private TableColumn<RendezVous, String> colDate;
    @FXML private TableColumn<RendezVous, String> colSpecialite;
    @FXML private TableColumn<RendezVous, String> colDoctor;
    @FXML private TableColumn<RendezVous, String> colDiagnosis;
    @FXML private TableColumn<RendezVous, Button> colAction;
    @FXML private ComboBox<String> specialites;
    @FXML private TextField symptomsField;
    @FXML private TextField diagnosisField;
    @FXML private TextField notesField;
    @FXML private DatePicker datePicker;
    @FXML private TextField medicationField;
    @FXML private TextField dosageField;
    @FXML private TextField durationField;
    @FXML private TextField instructionsField;
    @FXML private CheckBox sundayCheckBox;
    @FXML private TextField sundayStartTime;
    @FXML private TextField sundayEndTime;
    @FXML private CheckBox mondayCheckBox;
    @FXML private TextField mondayStartTime;
    @FXML private TextField mondayEndTime;
    @FXML private CheckBox tuesdayCheckBox;
    @FXML private TextField tuesdayStartTime;
    @FXML private TextField tuesdayEndTime;

    private Medecin medecinCt; // Global medecin variable
    private final MedecinDAO medecinDAO;
    private final DisponibiliteDAO disponibiliteDAO;

    public MedecinController() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        this.medecinDAO = new MedecinDAO(connection);
        this.disponibiliteDAO = new DisponibiliteDAO(connection);
    }

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


    @FXML
    private void saveAvailability() {
        String selectedWeek = weekComboBox.getValue();
//        if (selectedWeek == null || selectedWeek.isEmpty()) {
//            System.out.println("Please select a week.");
//            return;
//        }

        try {
            // ID du médecin connecté (à remplacer dynamiquement)
            int doctorId = 1;
            System.out.println("id medecin for jour "+ medecinCt.getIdMedecin());
            // Dimanche
            if (sundayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Sunday", sundayStartTime.getText(), sundayEndTime.getText(), 1);
            }
            // Lundi
            if (mondayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Monday", mondayStartTime.getText(), mondayEndTime.getText(), 1);
            }
            // Mardi
            if (tuesdayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Tuesday", tuesdayStartTime.getText(), tuesdayEndTime.getText(), 1);
            }
            // Mercredi
            if (wednesdayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Wednesday", wednesdayStartTime.getText(), wednesdayEndTime.getText(), 1);
            }
            // Jeudi
            if (thursdayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Thursday", thursdayStartTime.getText(), thursdayEndTime.getText(), 1);
            }
            // Vendredi
            if (fridayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Friday", fridayStartTime.getText(), fridayEndTime.getText(), 1);
            }
            // Samedi
            if (saturdayCheckBox.isSelected()) {
                disponibiliteDAO.insertAvailability((int) medecinCt.getIdMedecin(),"Saturday", saturdayStartTime.getText(), saturdayEndTime.getText(), 1);
            }

            System.out.println("Doctor availability saved successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error saving doctor availability: " + e.getMessage());
        }
    }


    @FXML
    public void initialize() {
        // Bind columns to RendezVous properties
//        if (colDate == null) {
//            System.err.println("colDate is null!");
//            return;
//        }
//        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
//        colDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
//        colSpecialite.setCellValueFactory(cellData -> cellData.getValue().specialiteProperty());
//        colDoctor.setCellValueFactory(cellData -> cellData.getValue().doctorProperty());
//        colDiagnosis.setCellValueFactory(cellData -> cellData.getValue().diagnosisProperty());
//
//        // Populate the TableView with data
//        try {
//            RendezVousDAO rendezVousDAO = new RendezVousDAO(ConnectionUtil.getConnection());
//            List<RendezVous> rendezVousList = rendezVousDAO.getRendezVousBymedecinId(1); // Replace with dynamic medecin ID
//            tableRendezVous.setItems(FXCollections.observableArrayList(rendezVousList));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        colAction.setCellValueFactory(cellData -> {
//            Button actionButton = new Button("Consult");
//            actionButton.setOnAction(e -> navigateToConsultation(cellData.getValue()));
//            return new SimpleObjectProperty<>(actionButton);
//        });


        //  Initialize gender ComboBox
        if (departComboBox != null) {
            // This method should return a list of Specialty objects (id, name)
            ObservableList<Specialite> specialties = FXCollections.observableArrayList();

            // Query the database and populate the list (you would replace this part with actual DB access code)
            specialties.addAll(
                    new Specialite(1, "Cardiology"),
                    new Specialite(2, "Neurology"),
                    new Specialite(3, "Orthopedics"),
                    new Specialite(4, "Pediatrics"),
                    new Specialite(5, "Dermatology"),
                    new Specialite(6, "Oncology"),
                    new Specialite(7, "Gynecology"),
                    new Specialite(8, "Psychiatry"),
                    new Specialite(9, "Endocrinology"),
                    new Specialite(10, "Gastroenterology")
            );

            departComboBox.setItems(specialties);
        }

        setDefaultTimesOnCheck(sundayCheckBox, sundayStartTime, sundayEndTime);
        setDefaultTimesOnCheck(mondayCheckBox, mondayStartTime, mondayEndTime);
        setDefaultTimesOnCheck(tuesdayCheckBox, tuesdayStartTime, tuesdayEndTime);
        setDefaultTimesOnCheck(wednesdayCheckBox, wednesdayStartTime, wednesdayEndTime);
        setDefaultTimesOnCheck(thursdayCheckBox, thursdayStartTime, thursdayEndTime);
        setDefaultTimesOnCheck(fridayCheckBox, fridayStartTime, fridayEndTime);
        setDefaultTimesOnCheck(saturdayCheckBox, saturdayStartTime, saturdayEndTime);

    }


    private void setDefaultTimesOnCheck(CheckBox checkBox, TextField startTime, TextField endTime) {
        System.out.println(checkBox);
        if (checkBox != null){
            checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) { // When checkbox is checked
                    startTime.setText("09:00");
                    endTime.setText("16:00");
                } else { // When checkbox is unchecked
                    startTime.clear();
                    endTime.clear();
                }
            });
        }

    }

//    private void navigateToConsultation(RendezVous rendezVous) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/consultation-medecin.fxml"));
//            Parent root = loader.load();
//
//            ConsultationController consultationController = loader.getController();
//            consultationController.setRendezVous(rendezVous); // Pass selected rendezvous to the next controller
//
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));
//            stage.setTitle("Consultation & Prescription");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }




    public void ProfileSave(Medecin medecin) {
        if (medecin == null) {
            throw new IllegalArgumentException("medecin object cannot be null!");
        }

        this.medecinCt = medecin; // Update global medecin variable with the new medecin that has the ID set
        System.out.println("Loaded medecin: " + medecinCt);
        if (firstNameField == null || departComboBox == null) {
            System.err.println("FXML fields are not initialized. Check your FXML file and its controller.");
            return;
        }

        String dateFromDb = medecin.getDateNaissance(); // Récupérer la date depuis l'objet medecin
        if (dateFromDb != null && !dateFromDb.isEmpty()) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(dateFromDb, formatter);
                birthDatePicker.setValue(localDate); // Affecter la valeur au DatePicker
            } catch (DateTimeParseException e) {
                System.err.println("Invalid date format in database: " + dateFromDb);
            }
        }

        firstNameField.setText(medecin.getNom());
        lastNameField.setText(medecin.getPrenom());
        addressField.setText(medecin.getAdresse());
//        departComboBox.getSelectionModel().select(medecin.get);

    }

    public void updateProfile() throws SQLException {
        if (medecinCt == null || medecinCt.getIdMedecin() == 0) {
            throw new IllegalArgumentException("No medecin loaded to update!");
        }
        System.out.println(medecinCt);
        System.out.println(medecinCt.getIdMedecin());

        medecinCt.setIdMedecin(medecinCt.getIdMedecin());

        // Validate fields
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String address = addressField.getText();

        medecinCt.setNom(firstName);
        medecinCt.setPrenom(lastName);
        medecinCt.setAdresse(address);

        Specialite selectedSpecialty = departComboBox.getSelectionModel().getSelectedItem();
        if (selectedSpecialty != null) {
            int selectedId = selectedSpecialty.getId();
            System.out.println("Selected Specialty ID: " + selectedId);
            medecinCt.setSpecialiteId(selectedId);
        }

        LocalDate date = birthDatePicker.getValue();
        if (date != null) {
            // Format the date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = date.format(formatter);

            // Use the formatted date string
            System.out.println("Selected date: " + formattedDate);
        }
        System.out.println("date : "+ date);

        medecinCt.setDateNaissance(date.toString());

        // Update in database
        boolean isUpdated = medecinDAO.updateMedecin(medecinCt);
        if (isUpdated) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("the information is saved");
            alert.showAndWait();
        } else {
            throw new SQLException("Failed to update medecin in the database.");
        }

    }



    public void loadPage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/medecin/" + fxmlFile));
            Node page = loader.load();
            MedecinController medecinController = loader.getController();

            // Pass the medecin data
            medecinController.ProfileSave(medecinCt);

            // Replace content
            contentPane.getChildren().clear();
            contentPane.getChildren().add(page);

            // Update title
            pageTitle.setText(title);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the Dashboard view into the contentPane.
     */
    @FXML
    public void loadDashboard() {
        loadPage("dashboard-medecin.fxml", "/ Dashboard");
    }

    /**
     * Load the Appointments view into the contentPane.
     */
    @FXML
    private void loadDisponbilty() {
        loadPage("disponible-medecin.fxml", "/ Disponibility");
    }

    /**
     * Load the Appointments view into the contentPane.
     */
    @FXML
    private void loadAppointments() {
        loadPage("rendezvous-medecin.fxml", "/ Appointment");
    }

    /**
     * Load the Appointments view into the contentPane.
     */
    @FXML
    private void loadConsultation() {
        loadPage("consultation-medecin.fxml", "/ Consultation");
    }

    /**
     * Load the Profile view into the contentPane.
     */
    @FXML
    public void loadProfilePageMe() {
        loadPage("profil-medecin.fxml", "/ Profiel");
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

}


    





