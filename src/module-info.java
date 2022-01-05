module P5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.company to javafx.fxml;
    exports com.company;
    opens com.company.controller to javafx.fxml;
    exports com.company.controller;
}