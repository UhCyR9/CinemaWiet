package pl.edu.agh.to.cinemawiet.film.service;


import org.springframework.stereotype.Service;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.film.model.FilmRequest;
import pl.edu.agh.to.cinemawiet.film.repository.FilmRepository;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;


    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film addFilm(FilmRequest filmRequest) {
        Film filmToAdd = new Film();

        return filmRepository.save(filmToAdd);
    }
}
