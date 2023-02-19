package estm.dsic.webby.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        route_request(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        route_request(request, response);
    }

    private void route_request(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String uri    = request.getServletPath();
        String method = request.getMethod();

        if (uri.equals("/")) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/index.jsp");  
            rd.forward(request, response);
            return;
        } else if (uri.equals("/auth")) {
            if (method.equals("GET")) {
                AuthService.get(this, request, response);
                return;
            } else if (method.equals("POST")) {
                AuthService.post(this, request, response);
                return;
            }
        } else if (uri.equals("/admin")) {
            if (method.equals("GET")) {
                AdminService.get(this, request, response);
                return;
            }
        } else if (uri.equals("/dashboard")) {
            if (method.equals("GET")) {
                UserService.get(this, request, response);
                return;
            } else if (method.equals("POST")) {
                UserService.post(this, request, response);
                return;
            }
        } else if (uri.equals("/dashboard/recent")) {
            if (method.equals("GET")) {
                UserService.get(this, request, response);
                return;
            } else if (method.equals("POST")) {
                UserService.post(this, request, response);
                return;
            }
        } else if (uri.equals("/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            
            response.sendRedirect(this.getServletContext().getContextPath() + "/auth");
            return;
        } else if (uri.startsWith("/css/")
                || uri.startsWith("/js/")) {
            InputStream in = request.getServletContext().getResourceAsStream(request.getServletPath());
            byte[] buffer  = new byte[4096];
            int numBytesRead;
            OutputStream out = response.getOutputStream();
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
            return;
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/404.jsp");  
        rd.forward(request, response);
    }
}
