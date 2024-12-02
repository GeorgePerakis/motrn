package shiven.Utility;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import shiven.Controllers.ProfileController;

public class Tools {
    public static void Make_Error_Alert(String Title, String Header, String Content)
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(Content);
        alert.showAndWait();
    }

    public static void Make_Success_Alert(String Title, String Header, String Content)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.setHeaderText(Header);
        alert.setContentText(Content);
        alert.showAndWait();
    }

    public static <T> T loadView(String fxmlFile, BorderPane borderPane) {
        try {
            FXMLLoader loader = new FXMLLoader(Tools.class.getResource(fxmlFile));
            Node root = loader.load();
    
            borderPane.setCenter(root);

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if an exception occurs
        }
    }
}
