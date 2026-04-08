package org.example.nextask.servlet.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.nextask.dao.UserDAO;
import org.example.nextask.model.User;

import java.io.IOException;

@WebServlet("/delete")
public class DeleteAccount extends HttpServlet {
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }
        String action = request.getParameter("action");

        if ("deleteAccount".equals(action)) {
            UserDAO userDao = new UserDAO();
            userDao.deleteUser(sessionUser.getUserID());
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
