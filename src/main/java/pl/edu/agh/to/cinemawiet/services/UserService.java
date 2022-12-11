package pl.edu.agh.to.cinemawiet.services;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.models.User;
import pl.edu.agh.to.cinemawiet.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }
}
