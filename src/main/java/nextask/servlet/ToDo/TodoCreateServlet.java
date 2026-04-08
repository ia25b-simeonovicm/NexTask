package nextask.servlet.ToDo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import nextask.dao.KategorieDAO;
import nextask.dao.ToDoDAO;
import nextask.model.Kategorie;
import nextask.model.ToDo;
import nextask.model.User;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/todoCreate")
public class TodoCreateServlet extends HttpServlet {
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
        todo.setCreatedAt(LocalDate.now());
        todo.setUser(user);

        Tododao.createToDo(todo);

        request.getRequestDispatcher("/sites/task.jsp").forward(request, response);
    }
}
