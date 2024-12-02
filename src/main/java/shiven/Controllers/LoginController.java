package shiven.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shiven.App;
import shiven.Utility.Authorization;
import shiven.Utility.Tools;
import shiven.Utility.UserSession;

public class LoginController {

    @FXML
    private CheckBox trainerCheck;

    @FXML
    private PasswordField LoginPassword;

    @FXML
    private TextField LoginUsername;

    @FXML
    void InitializeLogin(ActionEvent event) {
        String Username = LoginUsername.getText();
        String Password = LoginPassword.getText();

        if (Username.isBlank() || Password.isBlank()) {
            Tools.Make_Error_Alert("Invadlid Username or Password", "Please enter username and password", "Username or Password is empty");
            return;
        }

        boolean User_Exists = Authorization.check_username(Username);
        boolean Pass_Exists = Authorization.check_password(Username, Password);

        if (User_Exists) {
            if (Pass_Exists) {

                Tools.Make_Success_Alert("Success", "User logged in successfully", "Redirecting to Home Page");
                UserSession.getInstance().setUsername(Username);
                try {
                    App.setRoot("HomeScreen");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Tools.Make_Error_Alert("Invadlid Password", "Please enter a valid Password", "Password is not correct");
            }

        } else {
            Tools.Make_Error_Alert("Invadlid Username", "Please enter a valid username", "User " + Username + " does not exist");
        }

    }

    @FXML
    private void switchToRegister() throws IOException {
        App.setRoot("Register");
    }

}
