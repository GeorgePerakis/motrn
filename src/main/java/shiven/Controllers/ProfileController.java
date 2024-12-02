package shiven.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shiven.DB.DAOS.UserDAO;
import shiven.DB.User;
import shiven.Utility.UserSession;

public class ProfileController {

    @FXML
    private TableView<User> UserTable;

    @FXML
    private TableColumn<User, String> SubCol;

    @FXML
    private TableColumn<User, String> TrainersCol;

    @FXML
    private TableColumn<User, String> UsernameCol;

    @FXML
    public void initialize() {
        UsernameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TrainersCol.setCellValueFactory(new PropertyValueFactory<>("trainer"));
        SubCol.setCellValueFactory(new PropertyValueFactory<>("subscription"));
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

}
