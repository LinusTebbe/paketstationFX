module com.example.newpaketstation {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.tebbe.PaketstationFX to javafx.fxml;
    exports dev.tebbe.PaketstationFX;
}