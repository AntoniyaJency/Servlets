
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
public class p12_db3 extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        response.setContentType("text/html");
        Connection conn=null;
        Statement stmt=null;
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Capsulink - Department Data</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; max-width: 800px; margin: 0 auto; }");
        out.println("h1 { color: #ffd700; text-align: center; }");
        out.println(".result { background: rgba(255,255,255,0.2); padding: 15px; border-radius: 10px; margin: 10px 0; }");
        out.println("a { color: #ffd700; text-decoration: none; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h1>📊 Department Data</h1>");
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root", "");
            stmt = conn.createStatement();
            
            //select data from table
            String sql = "SELECT * FROM department";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            out.println("<h3>📋 All Departments</h3>");
            
            boolean hasResults = false;
            // Extract data from result set
            while(rs.next())
            {
                hasResults = true;
                //Retrieve by column name
                String did = rs.getString("DeptID");
                String dname = rs.getString("DeptName");
                int dnos = rs.getInt("NOS");
                
                //Display values
                out.println("<div class='result'>");
                out.println("<p><strong>DeptID:</strong> " + did + "<br>");
                out.println("<strong>DeptName:</strong> " + dname + "<br>");
                out.println("<strong>Number of Students:</strong> " + dnos + "</p>");
                out.println("</div>");
            }
            
            if (!hasResults) {
                out.println("<p>No departments found in database.</p>");
            }
            
            out.println("</body></html>");
            // Clean-up environment
            rs.close();
            pstmt.close();
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
        response.setContentType("text/html");
        Connection conn=null;
        Statement stmt=null;
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Capsulink - Database Updated</title>");
        out.println("<style>");
        out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
        out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; max-width: 800px; margin: 0 auto; }");
        out.println("h1 { color: #ffd700; text-align: center; }");
        out.println(".success { color: #90EE90; font-weight: bold; text-align: center; margin: 20px 0; }");
        out.println(".result { background: rgba(255,255,255,0.2); padding: 15px; border-radius: 10px; margin: 10px 0; }");
        out.println("a { color: #ffd700; text-decoration: none; font-weight: bold; }");
        out.println("a:hover { text-decoration: underline; }");
        out.println("</style></head><body>");
        out.println("<div class='container'>");
        out.println("<h1>✏️ Database Update</h1>");
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root", "");
            stmt = conn.createStatement();
            
            // Get form parameters
            String depid = request.getParameter("depid");
            String depname = request.getParameter("depname");
            String depnos = request.getParameter("depnos");
            
            if (depid != null && depname != null && depnos != null && 
                !depid.trim().isEmpty() && !depname.trim().isEmpty() && !depnos.trim().isEmpty()) {
                
                PreparedStatement pstmt = conn.prepareStatement("insert into department values(?, ?, ?)");    
                pstmt.setString(1, depid);
                pstmt.setString(2, depname);
                pstmt.setInt(3, Integer.valueOf(depnos));
                
                // Execute the insert command using executeUpdate()
                pstmt.executeUpdate();
               
                out.println("<div class='success'>✅ Database Updated Successfully!</div>");
                out.println("<p><strong>Added Department:</strong></p>");
                out.println("<div class='result'>");
                out.println("<p><strong>DeptID:</strong> " + depid + "<br>");
                out.println("<strong>DeptName:</strong> " + depname + "<br>");
                out.println("<strong>Number of Students:</strong> " + depnos + "</p>");
                out.println("</div>");
                
                pstmt.close();
            } else {
                out.println("<div class='result' style='background: rgba(255,165,0,0.3);'>");
                out.println("<p><strong>Warning:</strong> Please fill in all required fields.</p>");
                out.println("</div>");
            }
            
            // Show all departments after update
            out.println("<h3>📋 All Departments</h3>");
            String sql = "SELECT * FROM department";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            
            boolean hasResults = false;
            while(rs.next())
            {
                hasResults = true;
                String did = rs.getString("DeptID");
                String dname = rs.getString("DeptName");
                int dnos = rs.getInt("NOS");
                
                out.println("<div class='result'>");
                out.println("<p><strong>DeptID:</strong> " + did + "<br>");
                out.println("<strong>DeptName:</strong> " + dname + "<br>");
                out.println("<strong>Number of Students:</strong> " + dnos + "</p>");
                out.println("</div>");
            }
            
            if (!hasResults) {
                out.println("<p>No departments found in database.</p>");
            }
            
            // Clean-up environment
            rs.close();
            pstmt.close();
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
}