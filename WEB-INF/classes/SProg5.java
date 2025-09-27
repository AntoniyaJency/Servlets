import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

public class SProg5 extends HttpServlet  
{  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String value = request.getParameter("userin");

        Cookie cookie = new Cookie("userin", value == null ? "" : value);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(60*60); 
        response.addCookie(cookie);

        out.print("<html><body>");
        out.print("Cookie set. <a href='" + request.getContextPath() + "/SP6'>View cookie</a>");
        out.print("</body></html>");
        out.close();
    }  
}

