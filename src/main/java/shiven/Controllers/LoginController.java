package shiven.Controllers;

import java.io.IOException;

import shiven.App;
import shiven.DB.DAOS.UserDAO;
import shiven.Utility.Authorization;
import javafx.fxml.FXML;

public class LoginController {
    UserDAO userDAO = new UserDAO();

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Register");
    }

    @FXML
    private void WriteTest() throws IOException {
        userDAO.addUser("Test6","Pass6");
        Authorization.authenticate("null", "null");
    }
}
