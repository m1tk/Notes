package estm.dsic.webby.web;

import java.io.IOException;

import estm.dsic.webby.Models.Account;
import estm.dsic.webby.Services.Admin;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminService {
    public static void get(HttpServlet ctx, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc;
        if ((acc = (Account)session.getAttribute("account")) != null) {
            if (!acc.is_admin) {
                response.sendRedirect(ctx.getServletContext().getContextPath() + "/dashboard");
                return;
            }

            if (request.getParameter("type") != null) {
                switch (request.getParameter("type")) {
                    case "edit":
                        Admin.update_user(request.getParameter("id"), request.getParameter("new"));
                        request.setAttribute("ok", "User "+request.getParameter("new")+" successfully edited");
                        break;
                    case "delete":
                        Admin.delete_user(request.getParameter("id"));
                        request.setAttribute("ok", "User with id "+request.getParameter("id")+" successfully removed");
                        break;
                }
            }
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/index.jsp");  
            rd.forward(request, response);
            return;
        } else {
            response.sendRedirect(ctx.getServletContext().getContextPath() + "/auth");
        }
    }
}
