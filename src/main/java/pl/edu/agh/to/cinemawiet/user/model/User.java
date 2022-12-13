package pl.edu.agh.to.cinemawiet.user.model;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "users")
public class User {
    @Serial
    private static final long serialVersionUID = 1L;
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty name = new SimpleStringProperty(this, "name");
    private final StringProperty secondName = new SimpleStringProperty(this, "secondName");
    private final StringProperty email = new SimpleStringProperty(this, "email");
    private final ObjectProperty<UserRole> role = new SimpleObjectProperty<>(this, "role");

    public User() {
    }

    public User(String name, String secondName, String email, UserRole userRole) {
        this.name.set(name);
        this.secondName.set(secondName);
        this.email.set(email);
        this.role.set(userRole);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String firstName) {
        this.name.set(firstName);
    }


    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String lastName) {
        this.secondName.set(lastName);
    }


    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


    public UserRole getRole() {
        return role.get();
    }

    public ObjectProperty<UserRole> roleProperty() {
        return role;
    }

    public void setRole(UserRole userRole) {
        this.role.set(userRole);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", secondName=" + secondName +
                ", email=" + email +
                ", role=" + role +
                '}';
    }

}
