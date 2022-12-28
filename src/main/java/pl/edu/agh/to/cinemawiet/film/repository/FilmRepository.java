package pl.edu.agh.to.cinemawiet.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.film.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

}
