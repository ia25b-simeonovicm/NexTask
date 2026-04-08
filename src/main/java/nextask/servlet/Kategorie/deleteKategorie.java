package nextask.servlet.Kategorie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import nextask.dao.KategorieDAO;
import nextask.model.User;

import java.io.IOException;

@WebServlet("/deleteCategory")
public class deleteKategorie extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            request.setAttribute("error", "You are not logged in!");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        KategorieDAO dao = new KategorieDAO();
        String param = request.getParameter("kategorieId");
        if (param == null) {
            response.sendRedirect(request.getContextPath() + "/category");
            return;
        }
        int kategorieId = Integer.parseInt(param);
        dao.deleteKategorie(kategorieId);

        response.sendRedirect(request.getContextPath() + "/category");
    }
}
