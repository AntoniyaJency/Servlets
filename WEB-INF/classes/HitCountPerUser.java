import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HitCountPerUser extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Identify the user via a persistent cookie so refresh does NOT increase count
        String userId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("capsulink_uid".equals(c.getName())) {
                    userId = c.getValue();
                    break;
                }
            }
        }
        
        boolean isNewUser = false;
        if (userId == null || userId.trim().length() == 0) {
            userId = UUID.randomUUID().toString();
            Cookie idCookie = new Cookie("capsulink_uid", userId);
            idCookie.setPath(request.getContextPath());
            idCookie.setMaxAge(60 * 60 * 24 * 365); // ~1 year
            response.addCookie(idCookie);
            isNewUser = true;
        }

        // Use application context to keep a set of unique users and a unique counter
        ServletContext ctx = getServletContext();
        synchronized (ctx) {
            @SuppressWarnings("unchecked")
            Set<String> uniqueUsers = (Set<String>) ctx.getAttribute("uniqueUsers");
            if (uniqueUsers == null) {
                uniqueUsers = new HashSet<String>();
                ctx.setAttribute("uniqueUsers", uniqueUsers);
            }
            Integer uniqueCount = (Integer) ctx.getAttribute("uniqueCount");
            if (uniqueCount == null) {
                uniqueCount = 0;
            }
            
            // Only increment when we see a new userId
            if (isNewUser || !uniqueUsers.contains(userId)) {
                uniqueUsers.add(userId);
                uniqueCount = uniqueCount + 1;
                ctx.setAttribute("uniqueCount", uniqueCount);
            }

            out.println("<html><head><title>Per-User Hit Counter</title></head>");
            out.println("<body>");
            out.println("<h2>Unique Users Count: " + uniqueCount + "</h2>");
            out.println("<p>This counter tracks unique visitors. Each user is counted only once.</p>");
            out.println("<p>Your User ID: " + userId.substring(0, 8) + "...</p>");
            out.println("<p><a href='" + request.getContextPath() + "/hit-user'>Refresh</a></p>");
            out.println("<p><a href='" + request.getContextPath() + "/'>Back to Home</a></p>");
            out.println("</body></html>");
        }
        out.close();
    }
}

