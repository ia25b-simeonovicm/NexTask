package nextask.servlet.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import nextask.dao.UserDAO;
import nextask.model.User;

import java.io.IOException;

@WebServlet("/update")
public class UpdateLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User sessionUser = (User) session.getAttribute("user");
        String action = request.getParameter("action");
        UserDAO dao = new UserDAO();

        if ("profile".equals(action)) {
            String newUsername = request.getParameter("username");
            String newEmail = request.getParameter("email");

            User existingByUsername = dao.searchUserByUsername(newUsername);
            if (existingByUsername != null && existingByUsername.getUserID() != sessionUser.getUserID()) {
                request.setAttribute("error", "Username ist bereits vergeben.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }

            User existingByEmail = dao.searchUserByEmail(newEmail);
            if (existingByEmail != null && existingByEmail.getUserID() != sessionUser.getUserID()) {
                request.setAttribute("error", "E-Mail ist bereits vergeben.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }

            sessionUser.setUsername(newUsername);
            sessionUser.setEmail(newEmail);
            dao.updateUser(sessionUser);
            session.setAttribute("user", sessionUser);

        } else if ("password".equals(action)) {
            String oldPassword = request.getParameter("old_password");
            String newPassword = request.getParameter("newpassword");

            if (oldPassword == null || newPassword == null || oldPassword.isEmpty() || newPassword.isEmpty()) {
                request.setAttribute("error", "Bitte alle Passwort-Felder ausfüllen.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }
            if (!oldPassword.equals(sessionUser.getPassword())) {
                request.setAttribute("error", "Altes Passwort ist falsch.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }
            if (newPassword.equals(oldPassword)) {
                request.setAttribute("error", "Das neue Passwort darf nicht mit dem alten übereinstimmen.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }
            if (newPassword.length() < 6) {
                request.setAttribute("error", "Passwort muss mindestens 6 Zeichen lang sein.");
                request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
                return;
            }

            sessionUser.setPassword(newPassword);
            dao.updateUser(sessionUser);
            session.setAttribute("user", sessionUser);
        }

        request.setAttribute("success", "Einstellungen gespeichert.");
        request.getRequestDispatcher("/sites/settings.jsp").forward(request, response);
    }
}
