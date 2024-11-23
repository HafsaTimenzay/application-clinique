package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.DAO.MedecinDAO;
import com.example.gestionclinique.model.DAO.PatientDAO;
import com.example.gestionclinique.model.DAO.RendezVousDAO;
import com.example.gestionclinique.model.Medecin;
import com.example.gestionclinique.model.Patient;
import com.example.gestionclinique.model.RendezVous;
import com.example.gestionclinique.model.Specialite;
import com.example.gestionclinique.model.util.ConnectionUtil;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

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

    @FXML
    private ComboBox<Specialite> departComboBox;
    @FXML
    private ComboBox<String> specialites;
    @FXML
    private ComboBox<Medecin> MedecinComboBox;
    @FXML
    private ComboBox<String> doctors;

    private Patient patientCt; // Global patient variable
    private final PatientDAO patientDAO;


    private Connection connection; // Connexion à initialiser
    private MedecinDAO medecinDAO;


    public void loadMedecins(int specialiteId) {
        if (MedecinComboBox != null) {
            // Récupérer les médecins par spécialité depuis la base de données
            ObservableList<Medecin> medecins = medecinDAO.getAllMedecinsBySpecialite(specialiteId);
            MedecinComboBox.setItems(medecins);
        }
    }

    public PatientController() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        this.patientDAO = new PatientDAO(connection);
        this.medecinDAO = new MedecinDAO(connection);
    }

    @FXML
    public void initialize() {
        // Bind columns to RendezVous properties
//        colAction.setCellValueFactory(cellData -> {
//            Button actionButton = new Button("Consult");
//            actionButton.setOnAction(e -> navigateToConsultation(cellData.getValue()));
//            return new SimpleObjectProperty<>(actionButton);
//        });


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
//            List<RendezVous> rendezVousList = rendezVousDAO.getRendezVousByPatientId(1); // Replace with dynamic patient ID
//            tableRendezVous.setItems(FXCollections.observableArrayList(rendezVousList));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
//        colSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
//        colDoctor.setCellValueFactory(new PropertyValueFactory<>("doctor"));
//        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
//
//        // Create data for the table
//        ObservableList<RendezVous> data = FXCollections.observableArrayList(
//                new RendezVous("2024-11-20", "Cardiology", "Dr. Smith", "High blood pressure"),
//                new RendezVous("2024-11-18", "Dermatology", "Dr. Brown", "Skin rash"),
//                new RendezVous("2024-11-15", "Orthopedics", "Dr. Taylor", "Fractured arm"),
//                new RendezVous("2024-11-10", "Pediatrics", "Dr. Green", "Fever and cold"),
//                new RendezVous("2024-11-08", "Neurology", "Dr. Adams", "Migraine"),
//                new RendezVous("2024-11-05", "General Medicine", "Dr. Wilson", "Routine checkup")
//        );
//
//        // Set data to the TableView
//        tableRendezVous.setItems(data);

        // Set the cell factory for the Action column (to show buttons)


        //  Initialize gender ComboBox
        if (genderComboBox != null) {
            ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female");
            genderComboBox.setItems(genders);
        }

        System.out.println("departCom"+ departComboBox);
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
        if(departComboBox != null){
            Specialite selectedSpecialty = departComboBox.getSelectionModel().getSelectedItem();
            if (selectedSpecialty != null) {
                int selectedId = selectedSpecialty.getId();
                System.out.println("Selected Specialty ID: " + selectedId);
                if (MedecinComboBox != null) {
                    // Récupérer les médecins par spécialité depuis la base de données
                    ObservableList<Medecin> medecins = medecinDAO.getAllMedecinsBySpecialite(selectedId);
                    MedecinComboBox.setItems(medecins);
                }
                loadMedecins(selectedId);
        }

        }

        if (MedecinComboBox != null) {
            // This method should return a list of Specialty objects (id, name)
            ObservableList<Medecin> medecins = FXCollections.observableArrayList();

            Medecin medecin1 = new Medecin();
            medecin1.setIdMedecin(1);
            medecin1.setNom("Dr. Hassan Kad");
            Medecin medecin2 = new Medecin();
            medecin2.setIdMedecin(1);
            medecin2.setNom("Dr. Halima Eds");
            // Query the database and populate the list (you would replace this part with actual DB access code)
            medecins.addAll(
                    medecin1,
                    medecin2
            );

            MedecinComboBox.setItems(medecins);
        }
//

    }


    private Callback<TableColumn.CellDataFeatures<ObservableList<String>, String>, javafx.beans.value.ObservableValue<String>> createCellValue(TableColumn.CellDataFeatures<ObservableList<String>, String> data, int index) {
        return new Callback<>() {
            @Override
            public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList<String>, String> param) {
                if (param.getValue().size() > index) {
                    return new javafx.beans.property.SimpleStringProperty(param.getValue().get(index));
                }
                return new javafx.beans.property.SimpleStringProperty("");
            }
        };
    }


    @FXML
    private void saveRendezVous() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Rendez-vous saved");
        alert.showAndWait();


//        specialites.valueProperty().addListener((observable, oldValue, newValue) -> {
//            if (newValue != null) {
//                // Supposons que `newValue` contient l'ID de la spécialité ou que vous pouvez le mapper
//                int specialiteId = getSpecialiteIdByName(newValue); // Implémentez cette méthode si nécessaire
//
//            }
//        });
//        try {
//            // Récupérer les données de l'interface
//            int specialiteId = getSelectedSpecialiteId();
//            int doctorId = getSelectedDoctorId();
//            String details = digonist.getText();
//            String date = getSelectedDate();
//            String time = getSelectedTime();
//
//            // Insérer dans la base de données
//            boolean success = rendezVousDAO.insertRendezVous(specialiteId, doctorId, date, time, details);
//            if (success) {
//                System.out.println("Rendez-vous ajouté avec succès !");
//            } else {
//                System.out.println("Erreur lors de l'ajout du rendez-vous.");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


//    private void navigateToConsultation(RendezVous rendezVous) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestionclinique/view/patient/consultation-patient.fxml"));
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
