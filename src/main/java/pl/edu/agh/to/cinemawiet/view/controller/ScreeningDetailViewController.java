package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.utils.JSONReader;

import java.io.FileNotFoundException;
import java.util.Map;


@Controller
public class ScreeningDetailViewController {

    private static Screening screening;

    private final JSONReader jsonReader;

    public ScreeningDetailViewController(JSONReader jsonReader) {
        this.jsonReader = jsonReader;
    }

    public static void setScreening(Screening screening) {
        ScreeningDetailViewController.screening = screening;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        Map<String, Object> seatConfiguration = jsonReader.parse(screening.getScreeningId());
    }
}
