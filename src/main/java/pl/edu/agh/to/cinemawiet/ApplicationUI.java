package pl.edu.agh.to.cinemawiet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.net.URL;


public class ApplicationUI extends Application {

    private static ConfigurableApplicationContext context;

    public static Stage stage;

    @Override
    public void init() throws Exception {
        super.init();
        context = SpringApplication.run(CinemaWIETApplication.class);
    }

    @Override
    public void start(javafx.stage.Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.setTitle("Cinema WIET");
        setScene(getClass().getResource("/view/LoginView.fxml"));
    }

    public static void main(String[] args) {
        launch(args);
    }


    public static void setScene(URL fxmlPath) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlPath);
        fxmlLoader.setControllerFactory(context::getBean);
        Pane pane = fxmlLoader.load();
        stage.setScene(new javafx.scene.Scene(pane));
        stage.show();
    }
}

