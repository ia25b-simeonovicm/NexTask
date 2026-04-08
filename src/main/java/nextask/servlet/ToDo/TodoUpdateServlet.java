package nextask.servlet.ToDo;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import nextask.dao.KategorieDAO;
import nextask.dao.ToDoDAO;
import nextask.model.ToDo;
import nextask.model.User;

import java.io.IOException;

@WebServlet("/todoUpdate")
public class TodoUpdateServlet extends HttpServlet {
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
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

        String idString = request.getParameter("id");

        if (idString != null && !idString.isEmpty()) {
            try {
                int todoId = Integer.parseInt(idString);
                ToDoDAO todoDao = new ToDoDAO();
                ToDo selectedTodo = todoDao.searchToDoById(todoId);

                request.setAttribute("todo", selectedTodo);

                request.getRequestDispatcher("/sites/updateTodo.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                response.sendRedirect(request.getContextPath() + "/sites/task.jsp");
            }
        }
    }

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

        String todoIdStr = request.getParameter("toDoID");
        String kategorieIDStr = request.getParameter("kategorieID");
        String duedateStr = request.getParameter("ablaufdatum");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        boolean isDone = request.getParameter("isDone") != null;

        if (todoIdStr != null && !todoIdStr.isEmpty()) {
            int todoId = Integer.parseInt(todoIdStr);

            ToDoDAO todoDao = new ToDoDAO();
            KategorieDAO katDao = new KategorieDAO();

            ToDo todo = todoDao.searchToDoById(todoId);

            if (todo != null) {
                todo.setTitle(title);
                todo.setDescription(description);
                todo.setDone(isDone);

                if (duedateStr != null && !duedateStr.isEmpty()) {
                    todo.setAblaufdatum(java.time.LocalDate.parse(duedateStr));
                } else {
                    todo.setAblaufdatum(null);
                }

                // Kategorie aktualisieren
                if (kategorieIDStr != null && !kategorieIDStr.isEmpty()) {
                    int katId = Integer.parseInt(kategorieIDStr);
                    todo.setKategorie(katDao.searchKategorieById(katId));
                } else {
                    todo.setKategorie(null);
                }

                todoDao.updateToDo(todo);
            }
        }
        response.sendRedirect(request.getContextPath() + "/sites/task.jsp");
    }
}
