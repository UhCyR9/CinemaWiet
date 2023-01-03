package pl.edu.agh.to.cinemawiet.hall.model;

import javax.persistence.*;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue
    @Column(name = "HALLID")
    private long id;

    @Column(name = "NUMBEROFSEATS")
    private long numberOfSeats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(long numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
