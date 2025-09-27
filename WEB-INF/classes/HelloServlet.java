import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Welcome to Capsulink</h1>");
        out.println("<p>Basic servlet invoked from HTML (Ex6).</p>");
        out.println("</body></html>");
        out.close();
    }
}

