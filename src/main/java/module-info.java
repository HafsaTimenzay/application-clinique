module com.example.gestionclinique {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestionclinique to javafx.fxml;
    exports com.example.gestionclinique;
}