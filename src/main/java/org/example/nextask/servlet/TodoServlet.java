package org.example.nextask.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.nextask.dao.KategorieDAO;
import org.example.nextask.dao.ToDoDAO;
import org.example.nextask.dao.UserDAO;
import org.example.nextask.model.Kategorie;
import org.example.nextask.model.ToDo;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        KategorieDAO catdao = new KategorieDAO();
        ToDoDAO Tododao = new ToDoDAO();
        UserDAO userdao = new UserDAO();
        ToDo todo = new ToDo();

        String cookieUsername = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("username")) {
                    cookieUsername = c.getValue();
                    break;
                }
            }
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        LocalDate date = LocalDate.parse(request.getParameter("duedate"));
        String categoryRequest = request.getParameter("category");
        Kategorie category = catdao.getAllKategorieByUserAndName(userdao.searchUserByUsername(cookieUsername).getUserID(), categoryRequest);

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setAblaufdatum(date);
        todo.setKategorie(category);
        todo.setDone(false);

    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
