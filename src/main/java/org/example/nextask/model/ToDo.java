package org.example.nextask.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ToDo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ToDoID")
    private int ToDoID;

    @Column(name = "Title",unique = true, nullable = false, length = 50)
    private String Title;

    @Column(name = "Description", length = 350)
    private String Description;

    @Column(name = "IsDone", nullable = false)
    private Boolean IsDone;

    @Column(name = "CreatedAt")
    private Date CreatedAt;

    @Column(name = "Ablaufdatum")
    private Date Ablaufdatum;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @ManyToMany(mappedBy = "TodoKategorie")
    private List<Kategorie> Kategorie = new ArrayList<>();

    public ToDo (){}
    public ToDo(int toDoID, String title, String description, Boolean isDone, Date createdAt, Date ablaufdatum, User user, List<Kategorie> kategorie) {
        ToDoID = toDoID;
        Title = title;
        Description = description;
        IsDone = isDone;
        CreatedAt = createdAt;
        Ablaufdatum = ablaufdatum;
        User = user;
        Kategorie = kategorie;
    }

    public int getToDoID() {
        return ToDoID;
    }

    public void setToDoID(int toDoID) {
        ToDoID = toDoID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Boolean getDone() {
        return IsDone;
    }

    public void setDone(Boolean done) {
        IsDone = done;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getAblaufdatum() {
        return Ablaufdatum;
    }

    public void setAblaufdatum(Date ablaufdatum) {
        Ablaufdatum = ablaufdatum;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public List<Kategorie> getKategorie() {
        return Kategorie;
    }

    public void setKategorie(List<Kategorie> kategorie) {
        Kategorie = kategorie;
    }
}
