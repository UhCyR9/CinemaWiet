package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmViewController {

    private final FilmController filmController;

    public FilmViewController(FilmController filmController) {
        this.filmController = filmController;
    }

    @FXML
    public void initialize() {
        ObservableList<Film> films = FXCollections.observableArrayList(filmController.getAllFilms());
    }
}
