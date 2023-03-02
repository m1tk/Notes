package estm.dsic.webby.Controllers;

import java.io.IOException;

import estm.dsic.webby.Models.Account;
import estm.dsic.webby.Models.Recent;
import estm.dsic.webby.Services.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController {
    public static void get(HttpServlet ctx, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc;
        if ((acc = (Account)session.getAttribute("account")) != null) {

            if (request.getServletPath().equals("/dashboard/recent")) {
                request.setAttribute("recent", true);
            }

            if (request.getParameter("show") != null) {
                request.setAttribute("show", User.note(acc, request.getParameter("show")));
            } else if (request.getParameter("edit") != null) {
                request.setAttribute("edit", User.note(acc, request.getParameter("edit")));
            } else if (request.getParameter("delete") != null) {
                User.delete_note(acc, (Recent)session.getAttribute("recent"),
                        request.getParameter("delete"));
                request.setAttribute("ok", "Note successfully deleted");
            }

            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/client/index.jsp");  
            rd.forward(request, response);
            return;
        } else {
            response.sendRedirect(ctx.getServletContext().getContextPath() + "/auth");
        }
    }

    public static void post(HttpServlet ctx, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc;
        if ((acc = (Account)session.getAttribute("account")) != null) {
            
            if (request.getRequestURI().equals(ctx.getServletContext().getContextPath() + "/dashboard/recent")) {
                request.setAttribute("recent", true);
            }

            if (request.getParameter("edit-note") != null) {
                User.edit_note(acc, (Recent)session.getAttribute("recent"),
                        request.getParameter("edit-note-id"),
                        request.getParameter("edit-note-title"),
                        request.getParameter("edit-note-body"));

                request.setAttribute("ok", "Note successfully edited");
            } else if (request.getParameter("add-note") != null) {
                User.add_note(acc, (Recent)session.getAttribute("recent"),
                        request.getParameter("add-note-title"),
                        request.getParameter("add-note-body"));

                request.setAttribute("ok", "Note successfully added");
            }

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/client/index.jsp");  
            rd.forward(request, response);
            return;
        } else {
            response.sendRedirect(ctx.getServletContext().getContextPath() + "/auth");
        }
    }
}
