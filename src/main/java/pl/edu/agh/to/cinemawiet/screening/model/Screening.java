package pl.edu.agh.to.cinemawiet.screening.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "screening")
public class Screening {
    @Id
    @GeneratedValue
    @Column(name = "SCREENINGID")
    private int screeningId;

    @Column(name = "FILMID")
    private int filmId;

    @Column(name = "HALLID")
    private int hallId;

    @Column(name = "SCREENINGDATE")
    private Timestamp screeningDate;

    public int getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(int screeningId) {
        this.screeningId = screeningId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Timestamp getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Timestamp screeningDate) {
        this.screeningDate = screeningDate;
    }
}
