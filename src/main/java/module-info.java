/**
 *
 */
module com.mycompany.brankobank {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.mycompany.brankobank to javafx.fxml;
    exports com.mycompany.brankobank;
}
