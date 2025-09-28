import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head><meta charset='UTF-8'><title>Capsulink - Time Capsule Created</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; max-width: 600px; margin: 0 auto; }");
        out.println("h1 { color: #ffd700; text-align: center; }");
        out.println(".capsule-info { background: rgba(255,255,255,0.2); padding: 20px; border-radius: 10px; margin: 20px 0; }");
        out.println("a { color: #ffd700; text-decoration: none; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h1>🔮 Welcome to Capsulink</h1>");
        out.println("<p><strong>Basic servlet invoked from HTML (Ex6).</strong></p>");
        out.println("<p>Your digital time capsule platform is ready!</p>");
        out.println("<p><a href='" + request.getContextPath() + "/ex6-basic-servlets.html'>← Back to Form</a></p>");
        out.println("<p><a href='" + request.getContextPath() + "/'>🏠 Home</a></p>");
        out.println("</div></body></html>");
        out.close();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // Get form parameters
        String sender = request.getParameter("sender");
        String recipient = request.getParameter("recipient");
        String openDate = request.getParameter("openDate");
        String message = request.getParameter("message");
        
        out.println("<html><head><meta charset='UTF-8'><title>Capsulink - Time Capsule Sealed</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; max-width: 700px; margin: 0 auto; }");
        out.println("h1 { color: #ffd700; text-align: center; }");
        out.println(".capsule-info { background: rgba(255,255,255,0.2); padding: 20px; border-radius: 10px; margin: 20px 0; }");
        out.println(".success { color: #90EE90; font-weight: bold; font-size: 1.2em; text-align: center; }");
        out.println("a { color: #ffd700; text-decoration: none; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h1>🔮 Time Capsule Sealed!</h1>");
        out.println("<div class='success'>✅ Your digital time capsule has been successfully created and sealed!</div>");
        out.println("<div class='capsule-info'>");
        out.println("<h3>📋 Capsule Details:</h3>");
        out.println("<p><strong>👤 From:</strong> " + (sender != null ? sender : "Anonymous") + "</p>");
        out.println("<p><strong>🎯 To:</strong> " + (recipient != null ? recipient : "Unknown") + "</p>");
        out.println("<p><strong>📅 Open Date:</strong> " + (openDate != null ? openDate : "Not specified") + "</p>");
        out.println("<p><strong>💌 Message:</strong></p>");
        out.println("<div style='background: rgba(255,255,255,0.1); padding: 15px; border-radius: 8px; margin: 10px 0;'>");
        out.println(message != null ? message.replace("\n", "<br>") : "No message provided");
        out.println("</div>");
        out.println("</div>");
        out.println("<p style='text-align: center;'><a href='" + request.getContextPath() + "/ex6-basic-servlets.html'>📝 Create Another Capsule</a></p>");
        out.println("<p style='text-align: center;'><a href='" + request.getContextPath() + "/'>🏠 Back to Home</a></p>");
        out.println("</div></body></html>");
        out.close();
    }
}

