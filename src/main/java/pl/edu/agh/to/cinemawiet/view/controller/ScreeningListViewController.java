package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.screening.controller.ScreeningController;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;



@Controller
public class ScreeningListViewController {

    @FXML
    ListView<Screening> screeningList = new ListView<>();

    private final ScreeningController screeningController;

    private final FilmController filmController;


    public ScreeningListViewController(ScreeningController screeningController, FilmController filmController) {
        this.screeningController = screeningController;
        this.filmController = filmController;
    }

    @FXML
    public void initialize() {
        initializeScreeningList();

        ObservableList<Screening> screenings = FXCollections.observableArrayList(screeningController.getAllScreenings());
        screeningList.setItems(screenings);
    }


    private void initializeScreeningList() {
        screeningList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Screening item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    String filmName = filmController.getFilmById(item.getFilmId()).get().getName();
                    setText(filmName+" "+item.getHallId()+" "+item.getScreeningDate());
                }
            }
        });
    }


    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }

    @FXML
    public void detailView() throws Exception{
        ScreeningDetailViewController.setScreening(screeningList.getSelectionModel().getSelectedItem());
        ApplicationUI.setScene(getClass().getResource("/view/ScreeningDetailView.fxml"));
    }
}
