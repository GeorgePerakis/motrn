package shiven.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import shiven.App;
import shiven.DB.DAOS.UserDAO;

public class LoginController {
    UserDAO userDAO = new UserDAO();

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Register");
    }

}
