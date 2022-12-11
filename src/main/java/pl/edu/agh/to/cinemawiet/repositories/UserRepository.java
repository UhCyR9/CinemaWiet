package pl.edu.agh.to.cinemawiet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
