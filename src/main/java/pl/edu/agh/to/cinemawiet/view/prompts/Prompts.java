package pl.edu.agh.to.cinemawiet.view.prompts;

import javafx.scene.control.Alert;

public class Prompts {

    public static void userError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while adding user");
        alert.setHeaderText("Possible reasons:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void loginFailed() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login error");
        alert.setContentText("Wrong authentication data!");
        alert.showAndWait();
    }
}
