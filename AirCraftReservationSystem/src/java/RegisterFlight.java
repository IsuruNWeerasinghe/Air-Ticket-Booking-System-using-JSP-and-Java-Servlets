/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isuru
 */
public class RegisterFlight extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterFlight</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterFlight at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String flightName = request.getParameter("airname");
        String flightSource = request.getParameter("airsource");
        String flightDestination = request.getParameter("airdestination");
        String flightDepature = request.getParameter("airdepature");
        String flightArrival = request.getParameter("airarrival");
        String flightClass = request.getParameter("airclass");
        String flightCharge = request.getParameter("aircharge");
        String flightSeates = request.getParameter("airseates");

        int charge = Integer.parseInt(flightCharge);
        int seates = Integer.parseInt(flightSeates);
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            String statement = "insert into Flights(AIRNAME, AIRSOURCE, AIRDESTINATION, AIRDEPATURE, AIRARRIVAL, AIRCLASS, AIRCHARGE, AIRSEATES)"
                    + " values (?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(statement);

            ps.setString(1, flightName);
            ps.setString(2, flightSource);
            ps.setString(3, flightDestination);
            ps.setString(4, flightDepature);
            ps.setString(5, flightArrival);
            ps.setString(6, flightClass);
            ps.setInt(7, charge);
            ps.setInt(8, seates);

            int i = ps.executeUpdate();
            if (i > 0) {
                //out.println("<h3>Successfully added Aircraft...</h3>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully added Aircraft...');");
                out.println("</script>");
            } else {
                //out.println("<h3>Aircraft adding Failed...</h3>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Aircraft adding Failed...');");
                out.println("</script>");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
