package pl.edu.agh.to.cinemawiet.screening.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.screening.repository.ScreeningRepository;

import java.util.List;

@Service
public class ScreeningService {

    private final ScreeningRepository screeningRepository;

    public ScreeningService(ScreeningRepository screeningRepository) {
        this.screeningRepository = screeningRepository;
    }

    public Screening addScrenning(ScreeningRequest screnningRequest) {
        Screening screening = new Screening();
        screening.setFilmId(screnningRequest.filmId());
        screening.setHallId(screnningRequest.hallId());
        screening.setScreeningDate(screnningRequest.screeningDate());

        return screeningRepository.save(screening);
    }

    public List<Screening> getAllScrennings() {
        return screeningRepository.findAll();
    }


}
