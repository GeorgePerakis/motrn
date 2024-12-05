package shiven.Controllers;

import java.util.concurrent.Flow;

import org.w3c.dom.UserDataHandler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import shiven.App;
import shiven.DB.DAOS.UserDAO;
import shiven.DB.User;
import shiven.DB.Subscription;
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
    private TableView<Subscription> SubsTable;

    @FXML
    private TableColumn<Subscription, String> TrainersCol;

    @FXML
    private TableColumn<Subscription, String> crossfitCol;

    @FXML
    private TableColumn<Subscription, String> kickboxingCol;

    @FXML
    private TableColumn<Subscription, String> pilatesCol;

    @FXML
    private TableColumn<String, String> trxCol;

    @FXML
    private Text textTotal;

    @FXML
    public void initialize() {
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        trxCol.setCellValueFactory(new PropertyValueFactory<>("trx"));
        crossfitCol.setCellValueFactory(new PropertyValueFactory<>("crossfit"));
        kickboxingCol.setCellValueFactory(new PropertyValueFactory<>("kickboxing"));
        pilatesCol.setCellValueFactory(new PropertyValueFactory<>("pilates"));
        textTotal.setText("Your total amount is: " + getTotal().toString()+"€");
    }

    public void populateTableView() {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();

        ObservableList<User> users = FXCollections.observableArrayList(
                userDAO.getUser(username)
        );

        UserTable.setItems(users);
    }

    public void populateSubTableView() {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();

        ObservableList<Subscription> subscriptions = FXCollections.observableArrayList(
                userDAO.getUserGroup(username)
        );

        SubsTable.setItems(subscriptions);
    }

    @FXML
    void editUserGroup(ActionEvent event) {
        UserDAO userDAO = new UserDAO();

        boolean trx = trx_button.isSelected();
        boolean crossfit = crossfit_button.isSelected();
        boolean kickboxing = kickboxing_button.isSelected();
        boolean pilates = pilates_button.isSelected();
        String username = UserSession.getInstance().getUsername();

        userDAO.editUserGroup(username, trx, crossfit, kickboxing, pilates);

        Tools.Make_Success_Alert("Success", "User edited successfully", "Redirecting to HomeScreen");

        try {
            App.setRoot("HomeScreen");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();
        userDAO.addDeletedUser(username);
        userDAO.deleteUser(username);
        Tools.Make_Success_Alert("Success", "User Deleted successfully", "Redirecting to login screen");
        try {
            App.setRoot("Register");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Integer getTotal() {
        UserDAO userDAO = new UserDAO();
        String username = UserSession.getInstance().getUsername();
        return userDAO.getUserTotal(username);
    }
}
