module com.example.newpaketstation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.newpaketstation to javafx.fxml;
    exports com.example.newpaketstation;
}