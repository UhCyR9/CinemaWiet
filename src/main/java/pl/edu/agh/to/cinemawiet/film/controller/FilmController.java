package pl.edu.agh.to.cinemawiet.film.controller;

import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.film.model.FilmRequest;
import pl.edu.agh.to.cinemawiet.film.service.FilmService;

import java.util.List;

@Controller
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    public void addFilm(FilmRequest filmRequest) {
        filmService.addFilm(filmRequest);
    }

    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }



}
