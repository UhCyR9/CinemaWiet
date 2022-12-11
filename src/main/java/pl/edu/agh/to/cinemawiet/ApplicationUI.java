package pl.edu.agh.to.cinemawiet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ApplicationUI extends Application {
    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        super.init();
        context = SpringApplication.run(CinemaWIETApplication.class);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/UserView.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        Pane pane = fxmlLoader.load();
        primaryStage.setScene(new javafx.scene.Scene(pane));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

