package pl.edu.agh.to.cinemawiet.user.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.cinemawiet.user.model.UserRole;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.user.repository.UserRepository;

// FOR TESTING PURPOSES ONLY (GENERATES USERS)

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.count() == 0) {
                userRepository.save(new User("Kamil", "Kowalski", "test@gmail.com", "abc", UserRole.ADMIN));
            }
        };
    }
}
