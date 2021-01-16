module ro.mta.se.lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires org.json;

    opens ro.mta.se.lab to javafx.fxml;
    exports ro.mta.se.lab;
    opens ro.mta.se.lab.controller to javafx.fxml;
    exports ro.mta.se.lab.controller;
}