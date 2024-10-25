module com.flightDomain.flightbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.flightDomain.flightbook.controllers to javafx.fxml;
    exports com.flightDomain.flightbook;
}