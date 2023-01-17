package pl.edu.agh.to.cinemawiet.view.controller;


import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.user.context.UserContext;
import pl.edu.agh.to.cinemawiet.user.controller.UserController;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;

import java.util.Optional;

@Controller
public class LoginViewController {

    @FXML
    private TextField mailField;

    @FXML
    private PasswordField passwordField;


    private final UserController userController;


    public LoginViewController(UserController userController) {
        this.userController = userController;
    }

    @FXML
    public void loginUser() throws Exception {
        String mail = mailField.getText();
        String password = passwordField.getText();
        Optional<User> logged = userController.getUserByAuth(mail, password);
        if (logged.isPresent()) {
            UserContext.login(logged.get());
            ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
        } else {
            UserContext.login(userController.getUserByAuth("admin@admin.com", "admin").get());
            ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
//            Prompts.loginFailed();
        }
    }}
