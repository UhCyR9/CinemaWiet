package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.film.controller.FilmController;
import pl.edu.agh.to.cinemawiet.film.model.Film;
import pl.edu.agh.to.cinemawiet.hall.controller.HallController;
import pl.edu.agh.to.cinemawiet.hall.model.Hall;
import pl.edu.agh.to.cinemawiet.screening.controller.ScreeningController;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.screening.model.ScreeningRequest;
import pl.edu.agh.to.cinemawiet.utils.JSONWriter;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;


@Component
public class ScreeningViewController {

    private final FilmController filmController;
    private final ScreeningController screeningController;
    private final HallController hallController;
    private final JSONWriter jsonWriter;

    @FXML
    ListView<Film> filmsList;
    @FXML
    ListView<Hall> hallsList;
    @FXML
    DatePicker selectedDate;
    @FXML
    TextField selectedHour;


    public ScreeningViewController(FilmController filmController, ScreeningController screeningController, HallController hallController, JSONWriter jsonWriter) {
        this.filmController = filmController;
        this.screeningController = screeningController;
        this.hallController=hallController;
        this.jsonWriter = jsonWriter;
    }
    @FXML
    public void initialize() {

        ObservableList<Film> films = FXCollections.observableArrayList(filmController.getAllFilms());
        filmsList.setItems(films);


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

        ObservableList<Hall> halls = FXCollections.observableArrayList(hallController.getAllHalls());
        hallsList.setItems(halls);
        hallsList.setCellFactory(param -> new ListCell<>(){
            @Override
            protected void updateItem(Hall item,boolean empty){
                super.updateItem(item,empty);
                if(empty || item == null){
                    setText(null);
                }else{
                    setText(Long.toString(item.getId()));
                }
            }
        });

    }

    @FXML
    public void  addScreening() throws IOException {

        if (filmsList.getSelectionModel().getSelectedItem() == null || hallsList.getSelectionModel().getSelectedItem() == null || selectedDate.getValue() == null || selectedHour.getText().isEmpty()) {
            Prompts.screeningFailed("You have not completed the entire form");
        }
        else {
            Film film = filmsList.getSelectionModel().getSelectedItem();
            Hall hall = hallsList.getSelectionModel().getSelectedItem();
            String date = selectedDate.getValue().toString();
            String time = selectedHour.getText();

            //validate if hour is okay
            if(!Pattern.matches("[0-2][0-9]+:+[0-5][0-9]",time)){
                Prompts.screeningFailed("You have not inputted the proper hour");
                return;
            }

            String toConvert = date + " " + time + ":00";
            Timestamp beginningDate = Timestamp.valueOf(toConvert);

            LocalTime lt1 = LocalTime.parse(time + ":00", DateTimeFormatter.ofPattern("HH:mm:ss"));
            LocalTime lt2 = LocalTime.parse(film.getDuration().toString(), DateTimeFormatter.ofPattern("HH:mm:ss"));
            lt1=lt1.plusHours(lt2.getHour()).plusMinutes(lt2.getMinute()).plusSeconds(lt2.getSecond());

            toConvert = date + " " + lt1.toString()+ ":00";
            Timestamp endingDate = Timestamp.valueOf(toConvert);

            //validate if film is displayed between
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date toCompareDate;
            try {
                toCompareDate = sdf.parse(date);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            int a= film.getDateFrom().compareTo(toCompareDate);
            int b = film.getDateTo().compareTo(toCompareDate);
            if((a>=0) || (b<=0)){
                Prompts.screeningFailed("This film is not screening in this date");
                return;
            }

                //validation if hall is available in that time
            if (screeningController.isOccupied(beginningDate,endingDate,hall.getId())!=0){
               Prompts.screeningFailed("This time is already occupied");
            }
            //adding new screening
            else{
                ScreeningRequest screeningRequest = new ScreeningRequest(film.getId(), hall.getId(), beginningDate,endingDate);
                Screening screening = screeningController.addScreening(screeningRequest);
                jsonWriter.writeToNewFile(screening.getScreeningId(), screening.getHallId());
                Prompts.addSuccess();
            }
        }
    }
    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }


}
