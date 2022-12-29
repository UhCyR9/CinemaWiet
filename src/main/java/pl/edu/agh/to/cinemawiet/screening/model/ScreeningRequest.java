package pl.edu.agh.to.cinemawiet.screening.model;

import java.sql.Timestamp;

public record ScreeningRequest(int filmId, int hallId, Timestamp screeningDate) {
}
