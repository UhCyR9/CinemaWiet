package pl.edu.agh.to.cinemawiet.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.user.model.UserRequest;
import pl.edu.agh.to.cinemawiet.user.repository.UserRepository;
import pl.edu.agh.to.cinemawiet.utils.InputValidationService;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final InputValidationService inputValidationService;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, InputValidationService inputValidationService,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.inputValidationService = inputValidationService;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(UserRequest userRequest) {
        String name = inputValidationService.validateName(userRequest.name());
        String surname = inputValidationService.validateSecondName(userRequest.secondName());
        String mail = inputValidationService.validateMail(userRequest.email());
        User userToAdd = new User(name, surname, mail, userRequest.password(), userRequest.role());
        return userRepository.save(userToAdd);
    }

    public Optional<User> getUserByAuth(String mail, String password) {
        Optional<User> user = userRepository.getUserByMail(mail);
        if (user.isPresent()) {
            if (encoder.matches(password, user.get().getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }
    public void deleteUserById(Long Id){
        userRepository.deleteById(Id);
    }
}
