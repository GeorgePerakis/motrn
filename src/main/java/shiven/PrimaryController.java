package shiven;

import java.io.IOException;

import javafx.fxml.FXML;

public class PrimaryController {
    UserDAO userDAO = new UserDAO();
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void WriteTest() throws IOException {
        userDAO.addUser("Test3","Pass3");
    }
}
