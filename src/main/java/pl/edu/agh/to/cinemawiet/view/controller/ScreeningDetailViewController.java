package pl.edu.agh.to.cinemawiet.view.controller;

import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.screening.model.Screening;


@Controller
public class ScreeningDetailViewController {

    private static Screening screening;


    public static void setScreening(Screening screening) {
        ScreeningDetailViewController.screening = screening;
    }
}
