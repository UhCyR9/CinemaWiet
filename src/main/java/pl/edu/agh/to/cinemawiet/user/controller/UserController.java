package pl.edu.agh.to.cinemawiet.user.controller;

import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.user.model.UserRequest;
import pl.edu.agh.to.cinemawiet.user.service.UserService;

import java.util.List;
import java.util.Optional;


@Controller
public class UserController {


    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }


    public User addUser(UserRequest userRequest) {
        return userService.addUser(userRequest);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public Optional<User> getUserByAuth(String mail, String password) {
        return userService.getUserByAuth(mail, password);
    }

}
