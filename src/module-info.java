module sample {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.base;
    requires json.simple;
    opens sample.Model;
    opens sample ;
}