package pl.edu.agh.to.cinemawiet.models;

import javafx.beans.property.*;

import javax.persistence.*;
import java.io.*;

@Entity
@Table(name = "users")
public class User implements Externalizable {
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

    public final void setId(int id) {
        this.id.set(id);
    }


    public final String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public final void setName(String firstName) {
        this.name.set(firstName);
    }


    public String getSecondName() {
        return secondName.get();
    }

    public final StringProperty secondNameProperty() {
        return secondName;
    }

    public final void setSecondName(String lastName) {
        this.secondName.set(lastName);
    }


    public final String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public final void setEmail(String email) {
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


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(id.get());
        out.writeObject(name.get());
        out.writeObject(secondName.get());
        out.writeObject(email.get());
        out.writeObject(role.get());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        id.set(in.readInt());
        name.set((String) in.readObject());
        secondName.set((String) in.readObject());
        email.set((String) in.readObject());
        role.set((UserRole) in.readObject());
    }
}
