package pl.edu.agh.to.cinemawiet;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaWIETApplication {

	public static void main(String[] args) {
		Application.launch(ApplicationUI.class, args);
	}

}
