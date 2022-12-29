package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.film.model.FilmRequest;

import java.sql.Date;
import java.sql.Time;


@Controller
public class FilmViewController {

    private final FilmController filmController;

    @FXML
    private Button addFilmButton;

    @FXML
    private FlowPane filmFlowPane;

    public FilmViewController(FilmController filmController) {
        this.filmController = filmController;
    }

    @FXML
    public void initialize() {
        ObservableList<Film> films = FXCollections.observableArrayList(filmController.getAllFilms());

        films.forEach(this::addNewFilm);

        filmFlowPane.setHgap(10);
        filmFlowPane.setVgap(10);

        addFilmButton.setOnAction(event -> {

//            SET FORM

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Dodaj film");

            ButtonType okButtonType = new ButtonType("Dodaj", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Anuluj", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));


            TextField titleField = new TextField();
            titleField.setPromptText("Nazwa");

            DatePicker dateFrom = new DatePicker();
            dateFrom.setPromptText("Data od");

            DatePicker dateTo = new DatePicker();
            dateTo.setPromptText("Data do");

            TextField durationField = new TextField();
            durationField.setPromptText("Czas trwania");

            TextField categoryField = new TextField();
            categoryField.setPromptText("Kategoria");

            TextField imageUrlField = new TextField();
            imageUrlField.setPromptText("URL obrazka");


            grid.add(new Label("Nazwa:"), 0, 0);
            grid.add(titleField, 1, 0);

            grid.add(new Label("Data od:"), 0, 1);
            grid.add(dateFrom, 1, 1);

            grid.add(new Label("Data do:"), 0, 2);
            grid.add(dateTo, 1, 2);

            grid.add(new Label("Czas trwania:"), 0, 3);
            grid.add(durationField, 1, 3);
            grid.add(new Label("Format - hh:mm:ss"), 2, 3);

            grid.add(new Label("Kategoria:"), 0, 4);
            grid.add(categoryField, 1, 4);

            grid.add(new Label("URL obrazka:"), 0, 5);
            grid.add(imageUrlField, 1, 5);

            dialog.getDialogPane().setContent(grid);

//            END OF SET FORM

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == okButtonType) {
                    FilmRequest filmRequest = new FilmRequest(titleField.getText(),
                            Date.valueOf(dateFrom.getValue()), Date.valueOf(dateTo.getValue()), Time.valueOf(durationField.getText()),
                            categoryField.getText(), imageUrlField.getText());

                    Film film = filmController.addFilm(filmRequest);
                    films.add(film);
                    addNewFilm(film);
                }
                return null;
            });

            dialog.showAndWait();
        });
    }

    private void addNewFilm(Film film) {
        Image img = new Image(film.getImageUrl());
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(270);
        imgView.setFitWidth(180);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().add(imgView);
        vBox.getChildren().add(new Label(film.getName()));
        vBox.getChildren().add(new Label(film.getDuration().toString()));
        vBox.getChildren().add(new Label(film.getCategory()));
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        filmFlowPane.getChildren().add(vBox);
    }
}