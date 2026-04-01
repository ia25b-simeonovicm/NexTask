package org.example.nextask.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            request.setAttribute("error_signUp", "Please fill all the fields");
        }

        UserDAO dao =  new UserDAO();
        if (dao.searchUserByUsername(username) != null) {
            request.setAttribute("error_signUp", "Username is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        if (dao.searchUserByEmail(email) != null) {
            request.setAttribute("error_signUp", "Email is already in use");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        dao.createUser(user);


        request.getRequestDispatcher("sites/dashboard.jsp").forward(request, response);
    }
}
