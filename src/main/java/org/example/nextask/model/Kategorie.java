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
    private int UserID;

    @Column(name = "Name",unique = true , nullable = false, length = 50)
    private String Username;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @ManyToMany(mappedBy = "TodoKategorie")
    private List<ToDo> ToDo = new ArrayList<>();

    public Kategorie() {}
    public Kategorie(int userID, String username, User user, List<ToDo> toDo) {
        UserID = userID;
        Username = username;
        User = user;
        ToDo = toDo;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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
