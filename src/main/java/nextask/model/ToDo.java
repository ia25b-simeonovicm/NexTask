package nextask.model;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "ToDo")
public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ToDoID")
    private int ToDoID;

    @Column(name = "Title", unique = true, nullable = false, length = 50)
    private String Title;

    @Column(name = "Description", length = 350)
    private String Description;

    @Column(name = "IsDone", nullable = false)
    private Boolean IsDone;

    @Column(name = "CreatedAt")
    private LocalDate CreatedAt;

    @Column(name = "Ablaufdatum")
    private LocalDate Ablaufdatum;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @ManyToOne
    @JoinColumn(name = "KategorieID")
    private Kategorie Kategorie;

    public ToDo() {
    }

    public ToDo(int toDoID, String title, String description, Boolean isDone, LocalDate createdAt, LocalDate ablaufdatum, User user, Kategorie kategorie) {
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

    public LocalDate getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        CreatedAt = createdAt;
    }

    public LocalDate getAblaufdatum() {
        return Ablaufdatum;
    }

    public void setAblaufdatum(LocalDate ablaufdatum) {
        Ablaufdatum = ablaufdatum;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public Kategorie getKategorie() {
        return Kategorie;
    }
    
    public void setKategorie(Kategorie kategorie) {
        Kategorie = kategorie;
    }
}
