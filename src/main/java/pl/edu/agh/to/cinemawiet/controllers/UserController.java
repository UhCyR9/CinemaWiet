package pl.edu.agh.to.cinemawiet.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.models.User;
import pl.edu.agh.to.cinemawiet.services.UserService;


@Controller
public class UserController {

    @FXML
    ListView<User> usersList = new ListView<>();

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void initialize() {

        usersList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName()+" "+item.getSecondName()+" "+item.getEmail()+" "+item.getRole());
                }
            }
        });

        ObservableList<User> users = FXCollections.observableArrayList(userService.getAllUsers());
        usersList.setItems(users);
    }

}
