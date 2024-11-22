package com.example.gestionclinique.controller;

import com.example.gestionclinique.model.DAO.MedecinDAO;
import com.example.gestionclinique.model.Medecin;
import com.example.gestionclinique.model.RendezVous;
import com.example.gestionclinique.model.Specialite;
import com.example.gestionclinique.model.util.ConnectionUtil;
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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class MedecinController {

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
    private ComboBox<Specialite> departComboBox;

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
    private ComboBox<String> specialites;

    private Medecin medecinCt; // Global medecin variable

    private final MedecinDAO medecinDAO;

    public MedecinController() throws SQLException {
        Connection connection = ConnectionUtil.getConnection();
        this.medecinDAO = new MedecinDAO(connection);
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


    





