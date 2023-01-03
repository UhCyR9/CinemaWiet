package pl.edu.agh.to.cinemawiet.view.controller;

import com.sun.javafx.scene.control.IntegerField;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.hall.controller.HallController;
import pl.edu.agh.to.cinemawiet.hall.model.HallRequest;


@Controller
public class HallViewController {

    @FXML
    private TextField nameField;

    @FXML
    private IntegerField seatAmountField;

    private final HallController hallController;


    public HallViewController(HallController hallController) {
        this.hallController = hallController;
    }

    @FXML
    public void addHall() {
        HallRequest hallRequest = createHallRequest();
        hallController.addHall(hallRequest);
    }

    @FXML
    public void mainView() throws Exception{
        ApplicationUI.setScene(getClass().getResource("/view/MainView.fxml"));
    }

    private HallRequest createHallRequest() {
        return new HallRequest(nameField.getText(), seatAmountField.getValue());
    }
}
