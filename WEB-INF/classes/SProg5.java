import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

public class SProg5 extends HttpServlet  
{  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        // Get form parameters
        String username = request.getParameter("username");
        String theme = request.getParameter("theme");
        String capsuleType = request.getParameter("capsuleType");

        // Create cookies for each preference
        Cookie usernameCookie = new Cookie("username", username == null ? "" : username);
        Cookie themeCookie = new Cookie("theme", theme == null ? "" : theme);
        Cookie capsuleTypeCookie = new Cookie("capsuleType", capsuleType == null ? "" : capsuleType);
        
        // Set cookie properties
        usernameCookie.setPath(request.getContextPath());
        usernameCookie.setMaxAge(60*60); // 1 hour
        themeCookie.setPath(request.getContextPath());
        themeCookie.setMaxAge(60*60);
        capsuleTypeCookie.setPath(request.getContextPath());
        capsuleTypeCookie.setMaxAge(60*60);
        
        response.addCookie(usernameCookie);
        response.addCookie(themeCookie);
        response.addCookie(capsuleTypeCookie);

        out.print("<html><head><title>Capsulink - Cookies Set</title>");
        out.print("<style>");
        out.print("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #ffecd2 0%, #fcb69f 100%); }");
        out.print(".container { background: rgba(255,255,255,0.9); padding: 30px; border-radius: 15px; max-width: 600px; margin: 0 auto; }");
        out.print("h1 { color: #fcb69f; text-align: center; }");
        out.print(".success { color: #48bb78; font-weight: bold; text-align: center; margin: 20px 0; }");
        out.print("a { color: #fcb69f; text-decoration: none; font-weight: bold; }");
        out.print("a:hover { text-decoration: underline; }");
        out.print("</style></head><body>");
        out.print("<div class='container'>");
        out.print("<h1>🍪 Cookies Set Successfully!</h1>");
        out.print("<div class='success'>✅ Your time capsule preferences have been saved as cookies!</div>");
        out.print("<p><strong>Username:</strong> " + (username != null ? username : "Not set") + "</p>");
        out.print("<p><strong>Theme:</strong> " + (theme != null ? theme : "Not set") + "</p>");
        out.print("<p><strong>Capsule Type:</strong> " + (capsuleType != null ? capsuleType : "Not set") + "</p>");
        out.print("<p style='text-align: center; margin-top: 30px;'>");
        out.print("<a href='" + request.getContextPath() + "/SP6'>📖 Read Cookies</a> | ");
        out.print("<a href='" + request.getContextPath() + "/ex7a-cookies.html'>← Back to Form</a>");
        out.print("</p>");
        out.print("</div></body></html>");
        out.close();
    }  
}

