package pl.edu.agh.to.cinemawiet.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
