package org.example.nextask.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Kategorie")
public class Kategorie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategorieID")
    private int KategorieID;

    @Column(name = "Name",unique = true , nullable = false, length = 50)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @ManyToMany(mappedBy = "kategorien")
    private List<ToDo> ToDo = new ArrayList<>();

    public Kategorie() {}
    public Kategorie(int KategorieID, String Name, User user, List<ToDo> toDo) {
        KategorieID = KategorieID;
        Name = Name;
        User = user;
        ToDo = toDo;
    }

    public int getKategorieID() {
        return KategorieID;
    }

    public void setKategorieID(int KategorieID) {
        KategorieID = KategorieID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        Name = Name;
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
}
