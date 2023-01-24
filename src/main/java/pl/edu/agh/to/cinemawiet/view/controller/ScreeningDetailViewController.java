package pl.edu.agh.to.cinemawiet.view.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;
import pl.edu.agh.to.cinemawiet.utils.JSONReader;
import pl.edu.agh.to.cinemawiet.utils.JSONWriter;
import pl.edu.agh.to.cinemawiet.view.elements.CustomRectangle;
import pl.edu.agh.to.cinemawiet.view.elements.Vector;
import pl.edu.agh.to.cinemawiet.view.prompts.Prompts;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Controller
public class ScreeningDetailViewController {

    @FXML
    private GridPane mainGrid;

    private final JSONReader jsonReader;

    private final JSONWriter jsonWriter;

    private static Screening screening;

    private final Set<Vector> reservedPlaces = new HashSet<>();

    private Map<String, Object> seatConfiguration = new HashMap<>();

    public ScreeningDetailViewController(JSONReader jsonReader, JSONWriter jsonWriter) {
        this.jsonReader = jsonReader;
        this.jsonWriter = jsonWriter;
    }

    public static void setScreening(Screening screening) {
        ScreeningDetailViewController.screening = screening;
    }

    @FXML
    public void initialize() throws FileNotFoundException {
        seatConfiguration = jsonReader.parseMap(screening.getScreeningId());
        mainGrid.setHgap(2);
        mainGrid.setVgap(2);
        seatConfiguration.forEach((key, value) -> {
            Map<String, Object> row = (Map<String, Object>) value;
            row.forEach((col, status) -> {
                CustomRectangle rectangle;
                if (status.equals("free")) {
                    rectangle = createFreeRect(key, col);
                } else {
                    rectangle = createReservedRect(key, col);
                }
                mainGrid.add(rectangle, Integer.parseInt(col), Integer.parseInt(key));
            });
        });
    }

    private CustomRectangle createReservedRect(String row, String col) {
        return createRect(Paint.valueOf("black"), Integer.parseInt(row), Integer.parseInt(col));
    }

    private CustomRectangle createFreeRect(String row, String col) {
        CustomRectangle rect = createRect(Paint.valueOf("white"), Integer.parseInt(row), Integer.parseInt(col));
        rect.setOnMouseClicked(event -> {
            if (rect.getFill().equals(Paint.valueOf("white"))) {
                rect.setFill(Paint.valueOf("orange"));
                reservedPlaces.add(rect.getGridPosition());
            } else {
                rect.setFill(Paint.valueOf("white"));
                reservedPlaces.remove(rect.getGridPosition());
            }
        });
        return rect;
    }

    private CustomRectangle createRect(Paint color, int row, int col) {
        CustomRectangle rect = new CustomRectangle(new Vector(row, col));
        rect.setHeight(50);
        rect.setWidth(50);
        rect.setFill(color);
        return rect;
    }

    @FXML
    public void mainView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }

    @FXML
    public void reserve() throws Exception {
        for (Vector vector : reservedPlaces) {
            Map<String, Object> row = (Map<String, Object>) seatConfiguration.get(String.valueOf(vector.row()));
            row.put(String.valueOf(vector.col()), "taken");
            seatConfiguration.put(String.valueOf(vector.row()), row);
        }
        jsonWriter.write(seatConfiguration, screening.getScreeningId());
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
        Prompts.reserved();
    }
}
