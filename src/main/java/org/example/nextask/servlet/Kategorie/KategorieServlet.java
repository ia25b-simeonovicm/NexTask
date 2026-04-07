package org.example.nextask.servlet.Kategorie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.nextask.dao.KategorieDAO;
import org.example.nextask.model.Kategorie;
import org.example.nextask.model.User;

import java.io.IOException;

@WebServlet("/category")
public class KategorieServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String name = request.getParameter("name");
        String color = request.getParameter("color");

        KategorieDAO catdao = new KategorieDAO();
        Kategorie cat = new Kategorie();
        cat.setName(name);
        cat.setColor(color);
        cat.setUser(user);
        catdao.createKategorie(cat);

        response.sendRedirect(request.getContextPath() + "/category");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        KategorieDAO katdao = new KategorieDAO();
        request.setAttribute("categories", katdao.getAllKategorieByUser(user.getUserID()));
        request.getRequestDispatcher("/sites/addCategory.jsp").forward(request, response);
    }
}
