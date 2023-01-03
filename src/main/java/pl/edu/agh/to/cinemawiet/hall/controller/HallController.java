package pl.edu.agh.to.cinemawiet.hall.controller;

import org.springframework.stereotype.Controller;
import pl.edu.agh.to.cinemawiet.hall.model.Hall;
import pl.edu.agh.to.cinemawiet.hall.service.HallService;

import java.util.List;

@Controller
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService){this.hallService=hallService;}

    public List<Hall> getAllHalls(){return hallService.getAllHalls();}
}
