module shiven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens shiven to javafx.fxml;
    exports shiven;
}
