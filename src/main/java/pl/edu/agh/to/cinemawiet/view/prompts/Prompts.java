package pl.edu.agh.to.cinemawiet.view.prompts;

import javafx.scene.control.Alert;

public class Prompts {

    public static void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error while adding user");
        alert.setHeaderText("Possible reasons:");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
