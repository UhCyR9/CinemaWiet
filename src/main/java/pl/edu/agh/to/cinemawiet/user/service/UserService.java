package pl.edu.agh.to.cinemawiet.user.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.user.model.UserRequest;
import pl.edu.agh.to.cinemawiet.user.repository.UserRepository;
import pl.edu.agh.to.cinemawiet.utils.InputValidationService;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final InputValidationService inputValidationService;

    public UserService(UserRepository userRepository, InputValidationService inputValidationService) {
        this.userRepository = userRepository;
        this.inputValidationService = inputValidationService;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(UserRequest userRequest) {
        String name = inputValidationService.validateName(userRequest.name());
        String surname = inputValidationService.validateSecondName(userRequest.secondName());
        String mail = inputValidationService.validateMail(userRequest.email());
        User userToAdd = new User(name, surname, mail, userRequest.role());
        return userRepository.save(userToAdd);
    }
}
