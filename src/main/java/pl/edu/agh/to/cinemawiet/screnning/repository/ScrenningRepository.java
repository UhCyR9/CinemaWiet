package pl.edu.agh.to.cinemawiet.screnning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.screnning.model.Screnning;

@Repository
public interface ScrenningRepository extends JpaRepository<Screnning, Long> {

}

