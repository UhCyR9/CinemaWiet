package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.user.context.UserContext;
import pl.edu.agh.to.cinemawiet.user.controller.UserController;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.user.model.UserRequest;
import pl.edu.agh.to.cinemawiet.user.model.UserRole;
import pl.edu.agh.to.cinemawiet.utils.exception.InputValidationException;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;

@Component
public class UserViewController {

    @FXML
    private Button returnButton;
    @FXML
    ListView<User> usersList = new ListView<>();

    @FXML
    private TextField nameField;

    @FXML
    private TextField secondNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private RadioButton adminRadio;

    @FXML
    private RadioButton managerRadio;

    @FXML
    private RadioButton employeeRadio;

    private final ToggleGroup roleGroup = new ToggleGroup();

    private final UserController userController;

    private final PasswordEncoder encoder;


    public UserViewController(UserController userController, PasswordEncoder encoder) {
        this.userController = userController;
        this.encoder = encoder;
    }

    @FXML
    public void initialize() {
        initializeUsersList();
        initializeRadioButtons();

        ObservableList<User> users = FXCollections.observableArrayList(userController.getAllUsers());
        usersList.setItems(users);
    }

    @FXML
    public void addUser() {
        UserRequest userRequest = createUserRequest();
        try {
            User addedUser = userController.addUser(userRequest);
            usersList.getItems().add(addedUser);
        } catch (InputValidationException ex) {
            Prompts.userError(ex.getMessage());
        }
    }

    private void initializeUsersList() {
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
    }

    private void initializeRadioButtons() {
        adminRadio.setToggleGroup(roleGroup);
        adminRadio.setUserData(UserRole.ADMIN);
        managerRadio.setToggleGroup(roleGroup);
        managerRadio.setUserData(UserRole.MANAGER);
        employeeRadio.setToggleGroup(roleGroup);
        employeeRadio.setUserData(UserRole.EMPLOYEE);
        employeeRadio.setSelected(true);
    }

    private UserRequest createUserRequest() {
        return new UserRequest(nameField.getText(), secondNameField.getText(),
                emailField.getText(), encoder.encode(passwordField.getText()),
                (UserRole) roleGroup.getSelectedToggle().getUserData());
    }
    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }
}
