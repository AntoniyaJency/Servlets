import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HitCountGlobal extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        ServletContext ctx = getServletContext();
        synchronized (ctx) {
            Integer count = (Integer) ctx.getAttribute("globalCount");
            if (count == null) {
                count = 0;
            }
            count = count + 1;
            ctx.setAttribute("globalCount", count);
            
            out.println("<html><head><title>Global Hit Counter</title></head>");
            out.println("<body>");
            out.println("<h2>Global Hit Count: " + count + "</h2>");
            out.println("<p>This counter increases with every request to this servlet.</p>");
            out.println("<p><a href='" + request.getContextPath() + "/hit-global'>Refresh</a></p>");
            out.println("<p><a href='" + request.getContextPath() + "/'>Back to Home</a></p>");
            out.println("</body></html>");
        }
        out.close();
    }
}

