package org.example.nextask.servlet.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.nextask.dao.UserDAO;
import org.example.nextask.model.User;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        //Kontrollieren ob alle input felder inhalt haben
        if (username == null || password == null || email == null ||
                username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("error_signUp", "Please fill all the fields");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        //kontrollieren ob password mindestens 6 zeichen lang ist
        if (password.length() < 6) {
            request.setAttribute("error_signUp", "Password must be at least 6 characters");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        //kontrollieren ob Username schon benutzt wird
        UserDAO dao = new UserDAO();
        if (dao.searchUserByUsername(username) != null) {
            request.setAttribute("error_signUp", "Username is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        //kontrollieren ob Email schon benutzt wird
        if (dao.searchUserByEmail(email) != null) {
            request.setAttribute("error_signUp", "Email is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;

        }

        //benutzer erstellen
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        //benutzer in datenbank einfügen
        dao.createUser(user);


        Cookie cookie = new Cookie("username", username );
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.getRequestDispatcher("sites/task.jsp").forward(request, response);
    }
}
