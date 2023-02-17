package pl.edu.agh.to.cinemawiet.film.model;

import java.sql.Date;
import java.sql.Time;

public record FilmRequest(String filmName, Date dateFrom, Date dateTo,
                          Time duration, String category, String imageUrl) {

}
