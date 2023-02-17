package pl.edu.agh.to.cinemawiet.screening.controller;

import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;
import pl.edu.agh.to.cinemawiet.screening.service.ScreeningService;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    public Screening addScreening(ScreeningRequest screening) {
        return screeningService.addScreening(screening);
    }

    public List<Screening> getAllScreenings() {
        return screeningService.getAllScreenings();
    }

    public long isOccupied(Timestamp beginnigDate, Timestamp endingDate, long hallId){
        return screeningService.isOccupied(beginnigDate,endingDate,hallId);}

    public Time getDuration(long filmId){
        return screeningService.getDuration(filmId);
    }
}

