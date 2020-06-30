
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

@WebServlet(urlPatterns = {"/update"})
public class Update extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse res) throws IOException, ServletException {

        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();

            String flightRegNo = request.getParameter("flregno");
            String flightName = request.getParameter("flname");
            String flightSource = request.getParameter("flsource");
            String flightDestination = request.getParameter("fldestination");
            String flightDepature = request.getParameter("fldepature");
            String flightArrival = request.getParameter("flarrival");
            String flightClass = request.getParameter("flclass");
            String flightCharge = request.getParameter("flcharge");
            String flightSeates = request.getParameter("flseates");
            
            int regNo = Integer.parseInt(flightRegNo);
            int charge = Integer.parseInt(flightCharge);
            int seates = Integer.parseInt(flightSeates);

            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            String statement = ("update FLIGHTS set AIRNAME=?, AIRSOURCE=?, "
                    + "AIRDESTINATION=?, AIRDEPATURE=?, AIRARRIVAL=?, AIRCLASS=?, AIRCHARGE=?, AIRSEATES=? where AIRREGNO=?");
            PreparedStatement ps = con.prepareStatement(statement);
  
            ps.setString(1, flightName);
            ps.setString(2, flightSource);
            ps.setString(3, flightDestination);
            ps.setString(4, flightDepature);
            ps.setString(5, flightArrival);
            ps.setString(6, flightClass);
            ps.setInt(7, charge);
            ps.setInt(8, seates);
            ps.setInt(9, regNo);

            ps.executeUpdate();
            ps.close();

            int i = ps.executeUpdate();
            if (i > 0) {
                //out.println("You are sucessfully register");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully updated Aircraft...');");
                out.println("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("Adminpg.jsp");
                rd.forward(request, res);
            } else {
                //out.println("email or data type missing");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Faild to update aircraft...');");
                out.println("</script>");
                RequestDispatcher rd = request.getRequestDispatcher("Adminpg.jsp");
                rd.forward(request, res);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegisterFlight.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegisterFlight.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher rd = request.getRequestDispatcher("Adminpg.jsp");
        rd.forward(request, res);
    }

    /*@Override
  public String getServletInfo(){
  return "Name : RegisterServlet ; Version: 1.0";*/
}
