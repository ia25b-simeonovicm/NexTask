package org.example.nextask.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.nextask.dao.KategorieDAO;
import org.example.nextask.dao.ToDoDAO;
import org.example.nextask.dao.UserDAO;
import org.example.nextask.model.Kategorie;
import org.example.nextask.model.ToDo;
import org.example.nextask.model.User;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/todo")
public class TodoServlet extends HttpServlet {
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
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        KategorieDAO catdao = new KategorieDAO();
        ToDoDAO Tododao = new ToDoDAO();
        ToDo todo = new ToDo();

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String duedateStr = request.getParameter("duedate");
        LocalDate date;
        if (duedateStr == null || duedateStr.isEmpty()) {
            date = LocalDate.now();
        } else {
            date = LocalDate.parse(duedateStr);
        }

        String kategorieStr = request.getParameter("kategorie");
        if (kategorieStr == null || kategorieStr.trim().isEmpty()) {
            request.setAttribute("error", "Bitte wähle eine Kategorie aus.");
            request.getRequestDispatcher("/sites/addTodo.jsp").forward(request, response);
            return;
        }

        int categoryId = Integer.parseInt(kategorieStr);
        Kategorie category = catdao.searchKategorieById(categoryId);

        if (category == null) {
            request.setAttribute("error", "Can't find category");
            request.getRequestDispatcher("/sites/addTodo.jsp").forward(request, response);
            return;
        }

        todo.setTitle(title);
        todo.setDescription(description);
        todo.setAblaufdatum(date);
        todo.setKategorie(category);
        todo.setDone(false);
        todo.setUser(user);

        Tododao.createToDo(todo);

        request.getRequestDispatcher("/sites/task.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
