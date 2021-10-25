module dev.tebbe.PaketstationFX {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.tebbe.PaketstationFX to javafx.fxml;
    exports dev.tebbe.PaketstationFX;
}