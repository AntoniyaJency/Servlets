import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  

public class SProg6 extends HttpServlet  
{  
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String found = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c : cookies){
                if("userin".equals(c.getName())){
                    found = c.getValue();
                    break;
                }
            }
        }
        out.print("<html><body>");
        out.print(found == null ? "No cookie named 'userin'" : ("Cookie 'userin' value: " + found));
        out.print("</body></html>");
        out.close();
    }  
}

