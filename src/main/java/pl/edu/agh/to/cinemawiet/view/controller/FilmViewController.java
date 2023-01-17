package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.email.EmailSenderService;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.film.model.FilmRequest;
import pl.edu.agh.to.cinemawiet.user.model.User;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


@Component
public class FilmViewController {

    private final FilmController filmController;
    private ObservableList<Film> films;

    private ObservableList<Film> recommendedFilms = FXCollections.observableArrayList();

    @FXML
    private Button addFilmButton;

    @FXML
    private CheckBox showRecommendedCheckBox;

    @FXML
    private FlowPane filmFlowPane;

    @FXML
    private Button emailButton;

    private final EmailSenderService emailSenderService;

    public FilmViewController(FilmController filmController, EmailSenderService emailSenderService) {
        this.filmController = filmController;
        this.emailSenderService = emailSenderService;
    }

    @FXML
    public void initialize() {
        films = FXCollections.observableArrayList(filmController.getAllFilms());
        films.forEach(film -> {
            addNewFilm(film);
            if (film.isRecommended()) {
                recommendedFilms.add(film);
            }
        });

        films.addListener((ListChangeListener<Film>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    c.getAddedSubList().forEach(this::addNewFilm);
                }
            }
        });

        recommendedFilms.addListener((ListChangeListener<Film>) c -> {
            while (c.next()) {
                if (c.wasRemoved() && showRecommendedCheckBox.isSelected()) {
                    filmFlowPane.getChildren().clear();
                    recommendedFilms.forEach(this::addNewFilm);
                }
            }
        });

        filmFlowPane.setHgap(10);
        filmFlowPane.setVgap(10);

        initializeAddFilmButton();
        initializeShowRecommendedCheckBox();
        initializeEmailButton();

    }

    private void initializeShowRecommendedCheckBox()
    {
        showRecommendedCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                filmFlowPane.getChildren().clear();
                recommendedFilms.forEach(this::addNewFilm);
            } else {
                filmFlowPane.getChildren().clear();
                films.forEach(this::addNewFilm);
            }
        });
    }

    private void initializeAddFilmButton()
    {
        addFilmButton.setOnAction(event -> {

//            SET FORM

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Add film");

            ButtonType okButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));


            TextField titleField = new TextField();
            titleField.setPromptText("Name");

            DatePicker dateFrom = new DatePicker();
            dateFrom.setPromptText("Date from");

            DatePicker dateTo = new DatePicker();
            dateTo.setPromptText("Date to");

            TextField durationField = new TextField();
            durationField.setPromptText("Duration Format - HH:MM:SS");

            TextField categoryField = new TextField();
            categoryField.setPromptText("Category");

            TextField imageUrlField = new TextField();
            imageUrlField.setPromptText("URL of poster");


            grid.add(new Label("Name:"), 0, 0);
            grid.add(titleField, 1, 0);

            grid.add(new Label("Date from:"), 0, 1);
            grid.add(dateFrom, 1, 1);

            grid.add(new Label("Date to:"), 0, 2);
            grid.add(dateTo, 1, 2);

            grid.add(new Label("Duration:"), 0, 3);
            grid.add(durationField, 1, 3);

            grid.add(new Label("Category:"), 0, 4);
            grid.add(categoryField, 1, 4);

            grid.add(new Label("URL of poster:"), 0, 5);
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
                }
                return null;
            });

            dialog.showAndWait();
        });
    }

    private void addNewFilm(Film film) {
        VBox vBox = initializeFilmBox(film);

        vBox.setOnMouseClicked(event -> {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Recommendation");
            if (film.isRecommended()) {
                dialog.setContentText("Do you want to delete " + film.getName() + " from recommended?");

                ButtonType okButtonType = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButtonType) {
                        filmController.removeFilmFromRecommended(film);
                        recommendedFilms.remove(film);
                    }
                    return null;
                });
            }
            else {
                dialog.setContentText("Do you want to add " + film.getName() + " to recommended?");

                ButtonType okButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialog.getDialogPane().getButtonTypes().addAll(okButtonType, cancelButtonType);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == okButtonType) {
                        filmController.addFilmToRecommended(film);
                        recommendedFilms.add(film);
                    }
                    return null;
                });
            }

            dialog.showAndWait();
        });

        filmFlowPane.getChildren().add(vBox);
    }

    private VBox initializeFilmBox(Film film) {
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

        return vBox;
    }
    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }
    private void initializeEmailButton(){
        emailButton.setOnAction(event -> {sendMail();});
    }
    public void sendMail(){
        StringBuilder message=new StringBuilder();
        message.append("Hello, these are new movies we are promoting now:\n\n");
        for (Film recommendedFilm : recommendedFilms) {
            message.append(recommendedFilm.getName()).append(",\n");
        }
        List<User> users = filmController.getAllUsers();

        StringBuilder prompt=new StringBuilder();
        for (User user: users){
            emailSenderService.sendEmail(user.getEmail(),"New movies to promote",message.toString());
            prompt.append("Mail send to: ").append(user.getEmail()).append("\n");
        }
        Prompts.mailSuccess(prompt.toString());
    }
}