import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

public class SProg6 extends HttpServlet  
{  
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        
        String username = null;
        String theme = null;
        String capsuleType = null;
        
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                switch(c.getName()){
                    case "username":
                        username = c.getValue();
                        break;
                    case "theme":
                        theme = c.getValue();
                        break;
                    case "capsuleType":
                        capsuleType = c.getValue();
                        break;
                }
            }
        }
        
        out.print("<html><head><title>Capsulink - Cookie Data</title>");
        out.print("<style>");
        out.print("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #ffecd2 0%, #fcb69f 100%); }");
        out.print(".container { background: rgba(255,255,255,0.9); padding: 30px; border-radius: 15px; max-width: 600px; margin: 0 auto; }");
        out.print("h1 { color: #fcb69f; text-align: center; }");
        out.print(".cookie-info { background: rgba(255,255,255,0.5); padding: 20px; border-radius: 10px; margin: 20px 0; }");
        out.print(".not-found { color: #e53e3e; font-style: italic; }");
        out.print("a { color: #fcb69f; text-decoration: none; font-weight: bold; }");
        out.print("a:hover { text-decoration: underline; }");
        out.print("</style></head><body>");
        out.print("<div class='container'>");
        out.print("<h1>📖 Cookie Data Retrieved</h1>");
        out.print("<div class='cookie-info'>");
        out.print("<h3>🍪 Your Stored Preferences:</h3>");
        out.print("<p><strong>👤 Username:</strong> " + (username != null && !username.isEmpty() ? username : "<span class='not-found'>Not found</span>") + "</p>");
        out.print("<p><strong>🎨 Theme:</strong> " + (theme != null && !theme.isEmpty() ? theme : "<span class='not-found'>Not found</span>") + "</p>");
        out.print("<p><strong>📦 Capsule Type:</strong> " + (capsuleType != null && !capsuleType.isEmpty() ? capsuleType : "<span class='not-found'>Not found</span>") + "</p>");
        out.print("</div>");
        out.print("<p style='text-align: center; margin-top: 30px;'>");
        out.print("<a href='" + request.getContextPath() + "/ex7a-cookies.html'>🍪 Set More Cookies</a> | ");
        out.print("<a href='" + request.getContextPath() + "/ex7-session-mgmt.html'>← Session Management</a>");
        out.print("</p>");
        out.print("</div></body></html>");
        out.close();
    }  
}

