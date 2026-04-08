package nextask.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int UserID;

    @Column(name = "Email", unique = true, nullable = false, length = 50)
    private String Email;

    @Column(name = "Username", unique = true, nullable = false, length = 50)
    private String Username;

    @Column(name = "Password", nullable = false, length = 50)
    private String Password;

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Kategorie> Kategorie = new ArrayList<>();

    @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ToDo> ToDo = new ArrayList<>();

    public User() {
    }

    public User(int userID, String email, String username, String password, List<Kategorie> kategorie, List<ToDo> toDo) {
        UserID = userID;
        Email = email;
        Username = username;
        Password = password;
        Kategorie = kategorie;
        ToDo = toDo;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public List<Kategorie> getKategorie() {
        return Kategorie;
    }

    public void setKategorie(List<Kategorie> kategorie) {
        Kategorie = kategorie;
    }

    public List<ToDo> getToDo() {
        return ToDo;
    }

    public void setToDo(List<ToDo> toDo) {
        ToDo = toDo;
    }
}
