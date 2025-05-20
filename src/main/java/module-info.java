module com.diti.ebank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires jbcrypt;
    requires java.desktop;
    requires TrayNotification;


    opens com.diti.ebank to javafx.fxml;
    exports com.diti.ebank;
    exports com.diti.ebank.controllers;
    opens com.diti.ebank.controllers to javafx.fxml;
}