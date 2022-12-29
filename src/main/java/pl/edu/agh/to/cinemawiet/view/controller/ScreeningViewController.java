package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.screening.controller.ScreeningController;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;

import java.sql.Timestamp;


@Component
public class ScreeningViewController {

    // TODO add hallsController

    private final FilmController filmController;
    private final ScreeningController screeningController;

    @FXML
    ListView<Film> filmsList;

    @FXML
    ListView<Integer> hallsList;

    @FXML
    DatePicker selectedDate;

    @FXML
    TextField selectedHour;

    public ScreeningViewController(FilmController filmController, ScreeningController screeningController) {
        this.filmController = filmController;
        this.screeningController = screeningController;
    }
    @FXML
    public void initialize() {

        ObservableList<Film> films = FXCollections.observableArrayList(filmController.getAllFilms());
        filmsList.setItems(films);

        // TODO initialize hallsList

        filmsList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Film item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null || item.getName() == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        ObservableList<Integer> halls = FXCollections.observableArrayList(1,2,3);

        hallsList.setItems(halls);

    }

    @FXML
    public void  addScreening() {
        //TODO Validate if hall is available

        if (filmsList.getSelectionModel().getSelectedItem() == null || hallsList.getSelectionModel().getSelectedItem() == null || selectedDate.getValue() == null || selectedHour.getText().isEmpty()) {
            // TODO show error

        }
        else {
            Film film = filmsList.getSelectionModel().getSelectedItem();
            // TODO change Hall to real Halls
            Integer hall = hallsList.getSelectionModel().getSelectedItem();
            String date = selectedDate.getValue().toString();
            String time = selectedHour.getText();
            Timestamp timestamp = Timestamp.valueOf(date + " " + time + ":00");

            ScreeningRequest screeningRequest = new ScreeningRequest((int)film.getId(), hall, timestamp);
            screeningController.addScrenning(screeningRequest);
        }
    }
}
