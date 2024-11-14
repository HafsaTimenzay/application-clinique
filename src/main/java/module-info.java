module com.example.gestionclinique {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.gestionclinique to javafx.fxml;
    exports com.example.gestionclinique;
}