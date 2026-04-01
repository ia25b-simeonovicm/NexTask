package org.example.nextask.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

import org.example.nextask.dao.UserDAO;
import org.example.nextask.model.User;

@SuppressWarnings("D")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    HttpSession session = request.getSession(false);
                    if (session != null) {
                        request.getRequestDispatcher("sites/dashboard.jsp").forward(request, response);
                        return;
                    }
                }
            }
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username);

        UserDAO dao = new UserDAO();
        User user = null;

        if (username == null || password == null) {
            return;
        }

        if (username.contains("@")) {
            try {
                user = dao.searchUserByEmail(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                user = dao.searchUserByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (user == null) {
            request.setAttribute("error_signIn", "Benutzer nicht gefunden.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        if (password.equals(user.getPassword())) {
            Cookie cookie = new Cookie("username", username );
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            request.getRequestDispatcher("sites/dashboard.jsp").forward(request, response);
        } else {
            request.setAttribute("error_signIn", "Falsches Passwort.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
