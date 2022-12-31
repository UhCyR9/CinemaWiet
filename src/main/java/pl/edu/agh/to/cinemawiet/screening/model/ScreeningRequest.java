package pl.edu.agh.to.cinemawiet.screening.model;

import java.sql.Timestamp;

public record ScreeningRequest(long filmId, long hallId, Timestamp screeningDate) {
}
