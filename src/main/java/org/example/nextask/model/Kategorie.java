package org.example.nextask.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Kategorie")
public class Kategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategorieID")
    private int KategorieID;

    @Column(name = "Name", unique = true, nullable = false, length = 50)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @OneToMany(mappedBy = "Kategorie")
    private List<ToDo> ToDo = new ArrayList<>();

    @Column(name = "color")
    private String color;

    public Kategorie() {
    }

    public Kategorie(int kategorieID, String name, User user, List<ToDo> toDo, String color) {
        this.KategorieID = kategorieID;
        this.Name = name;
        this.User = user;
        this.ToDo = toDo;
        this.color = color;
    }

    public int getKategorieID() {
        return KategorieID;
    }

    public void setKategorieID(int KategorieID) {
        this.KategorieID = KategorieID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public List<ToDo> getToDo() {
        return ToDo;
    }

    public void setToDo(List<ToDo> toDo) {
        ToDo = toDo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
