package shiven.Controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import shiven.Utility.Tools;

public class HomeScreenController {
    
    @FXML
    private BorderPane borderPane;

    @FXML
    private void switchToHome() throws IOException {
        Tools.loadView("/shiven/HomeImage.fxml", borderPane);
    }

    @FXML
    private void switchToProfile() throws IOException {
        ProfileController thisProfileController = Tools.loadView("/shiven/ProfileScreen.fxml", borderPane);
        thisProfileController.populateTableView();
    }
    
    @FXML
    private void switchToUsers() throws IOException {
        UsersController thisUsersController = Tools.loadView("/shiven/UsersScreen.fxml", borderPane);
        thisUsersController.populateMembersTableView();
    }
    
    
}
