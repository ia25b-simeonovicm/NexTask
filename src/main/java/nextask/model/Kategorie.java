package nextask.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Kategorie", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserID", "Name"})})
public class Kategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KategorieID")
    private int KategorieID;

    @Column(name = "Name", nullable = false, length = 50)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User User;

    @OneToMany(mappedBy = "Kategorie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDo> Todo;

    @Column(name = "color")
    private String Color;

    public Kategorie() {
    }

    public Kategorie(int kategorieID, String name, User user, List<ToDo> todo, String color) {
        KategorieID = kategorieID;
        Name = name;
        User = user;
        Todo = todo;
        Color = color;
    }

    public int getKategorieID() {
        return KategorieID;
    }

    public void setKategorieID(int kategorieID) {
        KategorieID = kategorieID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public List<ToDo> getTodo() {
        return Todo;
    }

    public void setTodo(List<ToDo> todo) {
        Todo = todo;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }
}
