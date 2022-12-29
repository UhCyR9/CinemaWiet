package pl.edu.agh.to.cinemawiet.screnning.model;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "screnning")
public class Screnning {
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
}
