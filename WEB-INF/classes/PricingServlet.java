import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PricingServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Capsulink Pricing</title></head><body>");
        out.println("<h1>Simple, transparent pricing</h1>");
        out.println("<ul>");
        out.println("<li><b>Starter</b>: Free — 3 capsules, 100MB storage</li>");
        out.println("<li><b>Pro</b>: ₹199/mo — Unlimited capsules, 10GB storage</li>");
        out.println("<li><b>Team</b>: ₹499/mo — Shared capsules, role-based access</li>");
        out.println("</ul>");
        out.println("</body></html>");
        out.close();
    }
}

