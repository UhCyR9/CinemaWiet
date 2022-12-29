package pl.edu.agh.to.cinemawiet.screening.controller;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.screening.service.ScreeningService;

import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    public Screening addScrenning(ScreeningRequest screnning) {
        return screeningService.addScrenning(screnning);
    }

    public List<Screening> getAllScrennings() {
        return screeningService.getAllScrennings();
    }
}

