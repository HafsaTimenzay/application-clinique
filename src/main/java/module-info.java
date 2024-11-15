module com.example.gestionclinique {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    // Open the com.example.gestionclinique package to javafx.fxml for reflection
    opens com.example.gestionclinique to javafx.fxml;

    // Export the controller package to javafx.fxml
    opens com.example.gestionclinique.controller to javafx.fxml; // Add this line

    // Export the main package
    exports com.example.gestionclinique;
}
