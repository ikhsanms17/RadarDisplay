module com.example.radarapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.radarapp to javafx.fxml;
    exports com.example.radarapp;
}