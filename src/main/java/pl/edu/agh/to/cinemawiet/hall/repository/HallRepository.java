package pl.edu.agh.to.cinemawiet.hall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.hall.model.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall,Integer> {
}
