package org.example.nextask.servlet;

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
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    HttpSession session = request.getSession(false);
                    User user = (User) session.getAttribute("user");
                    request.getRequestDispatcher("sites/dashboard.jsp").forward(request, response);
                    return;
                }
            }
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("error_signUp", "Please fill all the fields");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        UserDAO dao = new UserDAO();
        if (dao.searchUserByUsername(username) != null) {
            request.setAttribute("error_signUp", "Username is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        if (dao.searchUserByEmail(email) != null) {
            request.setAttribute("error_signUp", "Email is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;

        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        dao.createUser(user);

        Cookie cookie = new Cookie("username", username );
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        request.getRequestDispatcher("sites/dashboard.jsp").forward(request, response);
    }
}
