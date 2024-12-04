package shiven.Controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shiven.App;
import shiven.DB.DAOS.UserDAO;
import shiven.Utility.Authorization;
import shiven.Utility.Tools;

public class RegisterController{
    @FXML
    private CheckBox trainerCheck;
    
    @FXML
    private PasswordField RegisterPassword;

    @FXML
    private TextField RegisterUsername;

    @FXML
    void InitializeRegister(ActionEvent event) {
        String Username = RegisterUsername.getText();
        String Password = RegisterPassword.getText();

        if(Username.isBlank() || Password.isBlank())
        {
            Tools.Make_Error_Alert("Invadlid Username or Password", "Please enter username and password", "Username or Password is empty");
            return;
        }

        boolean User_Exists = Authorization.check_username(Username);

        if (!User_Exists)
        {
            UserDAO userdao = new UserDAO();
            userdao.addUser(Username, Password);
            
            if(trainerCheck.isSelected())
            {
               userdao.addTrainer(Username); 
            }
            
            Tools.Make_Success_Alert("Success", "User made successfully", "Redirecting to Login");

            try {
                App.setRoot("Login");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        else Tools.Make_Error_Alert("Invadlid Username", "Please enter a valid username", "User "+ Username + " already exists");
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Login");
    }
}