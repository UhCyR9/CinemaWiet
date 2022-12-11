package pl.edu.agh.to.cinemawiet.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.agh.to.cinemawiet.models.UserRole;
import pl.edu.agh.to.cinemawiet.models.User;
import pl.edu.agh.to.cinemawiet.repositories.UserRepository;

// FOR TESTING PURPOSES ONLY (GENERATES USERS)

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            if(userRepository.count() == 0) {
                userRepository.save(new User("Kamil", "Kowalski", "test@gmail.com", UserRole.ADMIN));
            }
        };
    }
}
