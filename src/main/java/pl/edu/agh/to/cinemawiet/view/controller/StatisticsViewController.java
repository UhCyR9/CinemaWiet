package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.HBox;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.hall.controller.HallController;
import pl.edu.agh.to.cinemawiet.screening.controller.ScreeningController;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import javafx.scene.control.Label;

import java.util.*;

@Controller
public class StatisticsViewController {

    private final FilmController filmController;
    private final HallController hallController;
    private final ScreeningController screeningController;


    public StatisticsViewController(FilmController filmController, HallController hallController, ScreeningController screeningController) {
        this.filmController = filmController;
        this.hallController = hallController;
        this.screeningController = screeningController;
    }

    @FXML
    private HBox chartHolderHBox;



    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }

    @FXML
    public void initialize() {
        initMostPopularCategoryChart();
        initMostPopularFilmsChart();

    }

    private void initMostPopularCategoryChart() {
        BarChart<String, Number> mostPopularCategories = new BarChart<>(new CategoryAxis(), new NumberAxis());
        List<Film> films = filmController.getAllFilms();
        HashMap<String,Number> categoriesByScreenings = new HashMap<>();

        for(Film film : films) {
            String category = film.getCategory();
            if(categoriesByScreenings.containsKey(category)) {
                categoriesByScreenings.put(category, categoriesByScreenings.get(category).intValue() + 1);
            } else {
                categoriesByScreenings.put(category, 1);
            }
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        categoriesByScreenings.forEach((categoryName,numberOfFilms) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>( (categoryName.length() < 15) ? categoryName : categoryName.substring(0,15) + "...", numberOfFilms);
            Label label = new Label(numberOfFilms.toString());
            // set label higher than bar
            label.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-text-alignment: center; -fx-alignment: center;");
            data.setNode(label);
            series.getData().add(data);
        });

        series.getData().sort(Comparator.comparingInt(o -> o.getYValue().intValue()));

        List<XYChart.Data<String, Number>> lastThreeData = new ArrayList<>(series.getData().subList(series.getData().size() - 3, series.getData().size()));
        series.getData().clear();
        series.getData().addAll(lastThreeData);

        mostPopularCategories.setTitle("Most popular categories");
        mostPopularCategories.getData().add(series);
        mostPopularCategories.setLegendVisible(false);
        mostPopularCategories.setAnimated(false);
        mostPopularCategories.setBarGap(5);
        mostPopularCategories.setCategoryGap(0);
        mostPopularCategories.setStyle("-fx-font-size: 15px;");
        mostPopularCategories.setPrefSize(400, 600);
        chartHolderHBox.getChildren().add(mostPopularCategories);
    }

    private void initMostPopularFilmsChart() {
        BarChart<String, Number> mostPopularFilms = new BarChart<>(new CategoryAxis(), new NumberAxis());
        List<Screening> screenings = screeningController.getAllScreenings();

        HashMap<String,Number> filmsByScreenings = new HashMap<>();

        screenings.forEach(screening -> {
            String filmName = filmController.getFilmById(screening.getFilmId()).get().getName();
            if(filmsByScreenings.containsKey(filmName)){
                filmsByScreenings.put(filmName,filmsByScreenings.get(filmName).intValue()+1);
            }else{
                filmsByScreenings.put(filmName,1);
            }
        });

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        filmsByScreenings.forEach((filmName,numberOfScreenings) -> {
            XYChart.Data<String, Number> data = new XYChart.Data<>((filmName.length() < 15) ? filmName: filmName.substring(0,15) + "...", numberOfScreenings);
            Label label = new Label(numberOfScreenings.toString());
            // set label higher than bar
            label.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-text-alignment: center; -fx-alignment: center;");
            data.setNode(label);
            series.getData().add(data);
        });

        series.getData().sort(Comparator.comparingInt(o -> o.getYValue().intValue()));

        mostPopularFilms.setTitle("Most popular films");
        mostPopularFilms.getData().add(series);
        mostPopularFilms.setLegendVisible(false);
        mostPopularFilms.setAnimated(false);
        mostPopularFilms.setBarGap(5);
        mostPopularFilms.setCategoryGap(0);
        mostPopularFilms.setStyle("-fx-font-size: 15px;");
        mostPopularFilms.setPrefSize(400, 600);
        chartHolderHBox.getChildren().add(mostPopularFilms);
    }
}
