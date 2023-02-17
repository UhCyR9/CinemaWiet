package pl.edu.agh.to.cinemawiet.screening.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.screening.repository.ScreeningRepository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public Screening addScreening(ScreeningRequest screeningRequest) {
        Screening screening = new Screening();
        screening.setFilmId(screeningRequest.filmId());
        screening.setHallId(screeningRequest.hallId());
        screening.setScreeningDate(screeningRequest.screeningDate());
        screening.setScreeningEnd(screeningRequest.screeningEnd());

        return screeningRepository.save(screening);
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    //is this time occupied in screening
    public long isOccupied(Timestamp beginnigDate, Timestamp endingDate, long hallId){
        return screeningRepository.isOccupied(beginnigDate, endingDate, hallId);
    }
    public Time getDuration(long filmId){
        return screeningRepository.getDuration(filmId);
    }

}
