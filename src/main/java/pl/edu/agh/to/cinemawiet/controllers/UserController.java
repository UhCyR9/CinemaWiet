package pl.edu.agh.to.cinemawiet.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.models.User;
import pl.edu.agh.to.cinemawiet.models.UserRole;
import pl.edu.agh.to.cinemawiet.services.UserService;


@Controller
public class UserController {

    @FXML
    ListView<User> usersList = new ListView<>();

    @FXML
    private TextField nameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField emailField;

    @FXML
    private RadioButton adminRadio;

    @FXML
    private RadioButton managerRadio;

    @FXML
    private RadioButton employeeRadio;

    private final ToggleGroup roleGroup = new ToggleGroup();


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

        adminRadio.setToggleGroup(roleGroup);
        adminRadio.setUserData(UserRole.ADMIN);
        managerRadio.setToggleGroup(roleGroup);
        managerRadio.setUserData(UserRole.MANAGER);
        employeeRadio.setToggleGroup(roleGroup);
        employeeRadio.setUserData(UserRole.EMPLOYEE);
        employeeRadio.setSelected(true);
    }

    @FXML
    public void addUser() {
        User user = new User(nameField.getText(), secondNameField.getText(), emailField.getText(), (UserRole) roleGroup.getSelectedToggle().getUserData());
        userService.addUser(user);
        usersList.getItems().add(user);
    }

}
