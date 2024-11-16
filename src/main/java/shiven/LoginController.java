package shiven;

import java.io.IOException;

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
    }
}
