package estm.dsic.webby.web;

import java.io.IOException;

import estm.dsic.webby.Models.Account;
import estm.dsic.webby.Models.Recent;
import estm.dsic.webby.Services.Auth;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthService {
    public static void get(HttpServlet ctx, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc;
        if ((acc = (Account)session.getAttribute("account")) == null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/auth.jsp");  
            rd.forward(request, response);
        } else {
            redirect(ctx, response, acc);
        }
    }

    public static void post(HttpServlet ctx, HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        HttpSession session = request.getSession();

        Account acc;
        if ((acc = (Account)session.getAttribute("account")) == null) {

            if (request.getParameter("logsubmit") != null) {
                acc = Auth.log_email_pass(
                    request.getParameter("log_email"),
                    request.getParameter("log_pass"));
                
                session.setAttribute("account", acc);
                session.setAttribute("recent", new Recent());

                redirect(ctx, response, acc);
            } else if (request.getParameter("signsubmit") != null) {
                Auth.signup_email_pass(
                    request.getParameter("sign_email"),
                    request.getParameter("sign_pass"),
                    request.getParameter("sign_pass1"));

                request.setAttribute("sign_ok", "Account created. Log in now.");

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/auth.jsp");  
                rd.forward(request, response);
            }
        } else {
            redirect(ctx, response, acc);
        }
    }

    private static void redirect(HttpServlet ctx, HttpServletResponse response, Account acc) throws IOException {
        if (acc.is_admin) {
            response.sendRedirect(ctx.getServletContext().getContextPath() + "/admin");
        } else {
            response.sendRedirect(ctx.getServletContext().getContextPath() + "/dashboard");
        }
    }
}
