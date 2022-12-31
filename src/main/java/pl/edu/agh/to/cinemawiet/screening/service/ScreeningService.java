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

    public Screening addScreening(ScreeningRequest screeningRequest) {
        Screening screening = new Screening();
        screening.setFilmId(screeningRequest.filmId());
        screening.setHallId(screeningRequest.hallId());
        screening.setScreeningDate(screeningRequest.screeningDate());

        return screeningRepository.save(screening);
    }

    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }


}
