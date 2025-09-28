
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class p12_db2 extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        Connection conn=null;
        Statement stmt=null;
        PrintWriter out = response.getWriter();
        String department = request.getParameter("course");
        
        out.println("<html><head><title>Capsulink - Database Query Results</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; max-width: 800px; margin: 0 auto; }");
        out.println("h1 { color: #ffd700; text-align: center; }");
        out.println(".result { background: rgba(255,255,255,0.2); padding: 15px; border-radius: 10px; margin: 10px 0; }");
        out.println("a { color: #ffd700; text-decoration: none; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h1>🔍 Database Query Results</h1>");
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //create a database connection using jdbc , port no used here is 3306
            // database name is college and username is root , there is no password
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root", "");
            stmt = conn.createStatement();
            String sql;
            
            if (department != null && !department.trim().isEmpty()) {
                //select data from table where dept matches the value given by user in form
                sql = "SELECT * FROM marks where Dept = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, department);
                ResultSet rs = pstmt.executeQuery();
                
                out.println("<h3>📊 Students in Department: " + department + "</h3>");
                
                boolean hasResults = false;
                // Extract data from result set
                while(rs.next())
                {
                    hasResults = true;
                    //Retrieve by column name
                    String reg = rs.getString("RegNo");
                    String name = rs.getString("Name");
                    String dept = rs.getString("Dept");
                    Double cgpa = rs.getDouble("CGPA");
                    String sch = rs.getString("Scholarship");
                    //Display values
                    out.println("<div class='result'>");
                    out.println("<p><strong>RegNo:</strong> " + reg + "<br>");
                    out.println("<strong>Name:</strong> " + name + "<br>");
                    out.println("<strong>Department:</strong> " + dept + "<br>");
                    out.println("<strong>CGPA:</strong> " + cgpa + "<br>");
                    out.println("<strong>Availing Scholarship:</strong> " + sch + "</p>");
                    out.println("</div>");
                }
                
                if (!hasResults) {
                    out.println("<p>No students found in department: " + department + "</p>");
                }
                
                rs.close();
                pstmt.close();
            } else {
                // If no department specified, show all students
                sql = "SELECT * FROM marks";
                ResultSet rs = stmt.executeQuery(sql);
                
                out.println("<h3>📊 All Students</h3>");
                
                boolean hasResults = false;
                while(rs.next())
                {
                    hasResults = true;
                    String reg = rs.getString("RegNo");
                    String name = rs.getString("Name");
                    String dept = rs.getString("Dept");
                    Double cgpa = rs.getDouble("CGPA");
                    String sch = rs.getString("Scholarship");
                    
                    out.println("<div class='result'>");
                    out.println("<p><strong>RegNo:</strong> " + reg + "<br>");
                    out.println("<strong>Name:</strong> " + name + "<br>");
                    out.println("<strong>Department:</strong> " + dept + "<br>");
                    out.println("<strong>CGPA:</strong> " + cgpa + "<br>");
                    out.println("<strong>Availing Scholarship:</strong> " + sch + "</p>");
                    out.println("</div>");
                }
                
                if (!hasResults) {
                    out.println("<p>No students found in database.</p>");
                }
                
                rs.close();
            }
            
            // Clean-up environment
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            out.println("<div class='result' style='background: rgba(255,0,0,0.3);'>");
            out.println("<p><strong>Database Error:</strong> " + e.getMessage() + "</p>");
            out.println("</div>");
            System.out.print("Do not connect to DB - Error:"+e);
        }
        
        out.println("<p style='text-align: center; margin-top: 30px;'>");
        out.println("<a href='" + request.getContextPath() + "/ex9-database.html'>← Back to Database Forms</a> | ");
        out.println("<a href='" + request.getContextPath() + "/'>🏠 Home</a>");
        out.println("</p>");
        out.println("</div></body></html>");
        out.close();
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        // Call doGet to handle both GET and POST the same way
        doGet(request, response);
    }
}