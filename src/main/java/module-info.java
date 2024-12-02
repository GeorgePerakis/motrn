module shiven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires ojdbc8;
    opens shiven to javafx.fxml;
    exports shiven;
    exports shiven.Controllers;
    opens shiven.Controllers to javafx.fxml;
    opens shiven.DB to javafx.base;
}

