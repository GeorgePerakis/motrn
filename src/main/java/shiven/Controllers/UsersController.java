package shiven.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shiven.DB.DAOS.UserDAO;
import shiven.DB.User;

public class UsersController {

    @FXML
    private TableView<User> membersTable;

    @FXML
    private TableColumn<User, String> membersNameCol;

    @FXML
    private TableColumn<User, String> membersTrainerCol;

    @FXML
    public void initialize() {
        membersNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        membersTrainerCol.setCellValueFactory(new PropertyValueFactory<>("trainer"));
    }

    public void populateMembersTableView() {
        UserDAO userDAO = new UserDAO();

        ObservableList<User> users = FXCollections.observableArrayList();

        users.addAll(userDAO.getAllUsers());

        membersTable.setItems(users);
    }

}
