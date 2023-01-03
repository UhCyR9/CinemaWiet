package pl.edu.agh.to.cinemawiet.hall.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.hall.model.Hall;
import pl.edu.agh.to.cinemawiet.hall.model.HallRequest;
import pl.edu.agh.to.cinemawiet.hall.repository.HallRepository;

import java.util.List;

@Service
public class HallService {
    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository){this.hallRepository=hallRepository;}

    public List<Hall> getAllHalls(){return hallRepository.findAll();}

    public void addHall(HallRequest request) {
        hallRepository.save(new Hall(request.name(), request.numberOfSeats()));
    }
}
