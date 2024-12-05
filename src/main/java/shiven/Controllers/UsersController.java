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
    private TableView<User> TrainersTable;

    @FXML
    private TableColumn<User, String> TrainersTableUsername;

    @FXML
    private TableView<User> membersTable;

    @FXML
    private TableColumn<User, String> membersNameCol;

    @FXML
    public void initialize() {
        membersNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TrainersTableUsername.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void populateMembersTableView() {
        UserDAO userDAO = new UserDAO();

        ObservableList<User> users = FXCollections.observableArrayList();

        users.addAll(userDAO.getAllUsers());

        membersTable.setItems(users);

        ObservableList<User> trainers = FXCollections.observableArrayList();

        trainers.addAll(userDAO.getAllTrainers());

        TrainersTable.setItems(trainers);
    }


}
