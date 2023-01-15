package pl.edu.agh.to.cinemawiet.hall.model;

import javax.persistence.*;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue
    @Column(name = "HALLID")
    private long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NUMBEROFSEATS")
    private int numberOfSeats;


    public Hall(String name, int numberOfSeats) {
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    public Hall() {

    }

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

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
