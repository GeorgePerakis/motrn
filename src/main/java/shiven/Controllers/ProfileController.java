package shiven.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shiven.App;
import shiven.DB.DAOS.UserDAO;
import shiven.DB.User;
import shiven.Utility.Tools;
import shiven.Utility.UserSession;

public class ProfileController {

    @FXML
    private Button apply_button;

    @FXML
    private CheckBox crossfit_button;

    @FXML
    private CheckBox kickboxing_button;

    @FXML
    private CheckBox pilates_button;

    @FXML
    private CheckBox trx_button;

    @FXML
    private TableView<User> UserTable;

    @FXML
    private TableColumn<User, String> UsernameCol;

    @FXML
    private TableView<String> SubsTable;

    @FXML
    private TableColumn<String, String> TrainersCol;

    @FXML
    private TableColumn<String, String> crossfitCol;

    @FXML
    private TableColumn<String, String> kickboxingCol;

    @FXML
    private TableColumn<String, String> pilatesCol;

    @FXML
    private TableColumn<String, String> trxCol;

    @FXML
    public void initialize() {
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        trxCol.setCellValueFactory(new PropertyValueFactory<>("trx"));
        crossfitCol.setCellValueFactory(new PropertyValueFactory<>("crossfit"));
        kickboxingCol.setCellValueFactory(new PropertyValueFactory<>("kickboxing"));
        pilatesCol.setCellValueFactory(new PropertyValueFactory<>("pilates"));
    }

    public void populateTableView() {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();
        userDAO.getUser(username);
        
        ObservableList<User> users = FXCollections.observableArrayList(
                userDAO.getUser(username)
        );

        UserTable.setItems(users);
    }

    @FXML
    void editUserGroup(ActionEvent event) {
        UserDAO userDAO = new UserDAO();

        boolean trx = trx_button.isSelected();
        boolean crossfit = crossfit_button.isSelected();
        boolean kickboxing = kickboxing_button.isSelected();
        boolean pilates = pilates_button.isSelected();
        String username = UserSession.getInstance().getUsername();

        userDAO.editUserGroup(username,trx,crossfit,kickboxing,pilates);

        Tools.Make_Success_Alert("Success", "User edited successfully", "Changed subscription values");
    }

    @FXML
    void deleteUser(ActionEvent event) {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();
        userDAO.deleteUser(username);
        Tools.Make_Success_Alert("Success","User Deleted successfully" ,"Redirecting to login screen" );
        try {
                App.setRoot("Register");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
