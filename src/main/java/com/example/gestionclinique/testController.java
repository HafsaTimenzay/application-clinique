package com.example.gestionclinique;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.Parent;

import java.io.IOException;

public class testController {

    @FXML
    private BorderPane root; // Ensure this matches the fx:id in the FXML

    /**
     * Loads an FXML file into the center of the BorderPane.
     *
     * @param fxmlFile the FXML file to load
     */
    private void loadPage(String fxmlFile) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/" + fxmlFile));
            root.setCenter(page); // Ensure root is properly initialized
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadDashboard() {
        loadPage("com/example/gestionclinique/view/patient/main-patient.fxml");
    }

    @FXML
    private void loadHistorique() {
        loadPage("com/example/gestionclinique/view/patient/historique-patient.fxml");
    }

    @FXML
    private void loadProfil() {
        loadPage("com/example/gestionclinique/view/patient/profil-patient.fxml");
    }

    @FXML
    private void loadRendezVous() {
        loadPage("com/example/gestionclinique/view/patient/rendezvous-patient.fxml");
    }

    @FXML
    private void loadTest() {
        loadPage("com/example/gestionclinique/test.fxml");
    }
}
