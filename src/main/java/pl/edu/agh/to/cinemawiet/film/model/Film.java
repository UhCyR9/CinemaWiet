package pl.edu.agh.to.cinemawiet.film.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue
    @Column(name = "FILMID")
    private long id;

    @Column(name = "FILMNAME")
    private String name;

    @Column(name = "DATEFROM")
    private Date dateFrom;

    @Column(name = "DATETO")
    private Date dateTo;

    @Column(name = "DUARATION")
    private Time duration;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "IMAGEURL")
    private String imageUrl;

}
