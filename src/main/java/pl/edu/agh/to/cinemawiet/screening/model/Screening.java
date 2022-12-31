package pl.edu.agh.to.cinemawiet.screening.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "screening")
public class Screening {
    @Id
    @GeneratedValue
    @Column(name = "SCREENINGID")
    private long screeningId;

    @Column(name = "FILMID")
    private long filmId;

    @Column(name = "HALLID")
    private long hallId;

    @Column(name = "SCREENINGDATE")
    private Timestamp screeningDate;

    public long getScreeningId() {
        return screeningId;
    }

    public void setScreeningId(long screeningId) {
        this.screeningId = screeningId;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public long getHallId() {
        return hallId;
    }

    public void setHallId(long hallId) {
        this.hallId = hallId;
    }

    public Timestamp getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Timestamp screeningDate) {
        this.screeningDate = screeningDate;
    }
}
