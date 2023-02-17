package pl.edu.agh.to.cinemawiet.view.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.ApplicationUI;
import pl.edu.agh.to.cinemawiet.user.context.UserContext;
import pl.edu.agh.to.cinemawiet.user.model.UserRole;

@Controller
public class MainViewController {

    @FXML
    private Button userViewButton;

    @FXML
    private Button screeningViewButton;

    @FXML
    private Button filmViewButton;

    @FXML
    private Button hallViewButton;

    @FXML
    private Button screeningListViewButton;

    @FXML
    private Button statisticsViewButton;

    @FXML
    public void initialize() {
        UserRole role = getLoggedUserRole();
        switch (role) {
            case MANAGER -> screeningViewButton.setDisable(true);
            case EMPLOYEE -> {
                userViewButton.setDisable(true);
                filmViewButton.setDisable(true);
                hallViewButton.setDisable(true);
                screeningListViewButton.setDisable(true);
            }
        }
    }


    @FXML
    public void userView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/UserView.fxml"));
    }

    @FXML
    public void filmView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/FilmView.fxml"));
    }

    @FXML
    public void screeningView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/ScreeningView.fxml"));
    }

    @FXML
    public void hallView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/HallView.fxml"));
    }

    @FXML
    public void screeningListView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/ScreeningListView.fxml"));
    }

    @FXML
    public void statisticsView() throws Exception {
        ApplicationUI.setScene(getClass().getResource("/view/StatisticsView.fxml"));
    }

    @FXML
    public void logout() throws Exception {
        UserContext.logout();
        ApplicationUI.setScene(getClass().getResource("/view/LoginView.fxml"));
    }


    private UserRole getLoggedUserRole() {
        return UserContext.getInstance().getUser().getRole();
    }
}
