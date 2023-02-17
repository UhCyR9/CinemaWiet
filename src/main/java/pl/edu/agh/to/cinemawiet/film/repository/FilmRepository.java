package pl.edu.agh.to.cinemawiet.film.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.user.model.User;

import java.util.Optional;
import pl.edu.agh.to.cinemawiet.user.model.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT film FROM Film film WHERE film.id = ?1")
    Optional<Film> getFilmById(Long id);

    @Query("Select u from User u")
    List<User> allUsers();
}
