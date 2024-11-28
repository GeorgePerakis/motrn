package shiven.Utility;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
}
