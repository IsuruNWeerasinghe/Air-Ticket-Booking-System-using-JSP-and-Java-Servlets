
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter(); // PrintWriter is object.

            String reg = req.getParameter("regNo");

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            PreparedStatement ps = con.prepareStatement("delete from FLIGHTS where AIRREGNO = ?");
            ps.setString(1, reg);
            ps.executeUpdate();

            int i = ps.executeUpdate();

            if (i > 0) {
                //out.println("You are sucessfully register");
                RequestDispatcher rd = req.getRequestDispatcher("Adminpg.jsp");
                rd.forward(req, res);
                
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully Deleted Aircraft...');");
                out.println("</script>");
                
            } else {
                //out.println("email or data type missing");
                RequestDispatcher rd = req.getRequestDispatcher("Adminpg.jsp");
                rd.forward(req, res);
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Faild to delete Aircraft...');");
                out.println("</script>");
                
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterFlight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher rd = req.getRequestDispatcher("Adminpg.jsp");
        rd.forward(req, res);
    }

    /*@Override
  public String getServletInfo(){
  return "Name : RegisterServlet ; Version: 1.0";*/
}
