package shiven.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import shiven.App;

public class RegisterController {

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
}