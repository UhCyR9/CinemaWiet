package pl.edu.agh.to.cinemawiet.screening.repository;

import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.film.model.Film;

import java.sql.Time;
import java.sql.Timestamp;


@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    @Query("Select count(s) from Screening  s where s.hallId=:hallId and(:beginningDate >= s.screeningDate and :beginningDate <= s.screeningEnd) or ( :endingDate   >=  s.screeningDate and  :endingDate <= s.screeningEnd) ")
    long isOccupied(@Param("beginningDate") Timestamp beginningDate, @Param("endingDate") Timestamp endingDate, @Param("hallId") long hallId);

    @Query("select f.duration from Film f where f.id=:filmId")
    Time getDuration(@Param("filmId") long filmId);
}

