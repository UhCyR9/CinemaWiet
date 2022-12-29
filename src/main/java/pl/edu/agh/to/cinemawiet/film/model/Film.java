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

    @Column(name = "DURATION")
    private Time duration;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "IMAGEURL")
    private String imageUrl;

    public boolean isRecommended() {
        return recommended;
    }

    public void setRecommended(boolean recommended) {
        this.recommended = recommended;
    }

    @Column(name = "RECOMMENDED")
    private boolean recommended;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
