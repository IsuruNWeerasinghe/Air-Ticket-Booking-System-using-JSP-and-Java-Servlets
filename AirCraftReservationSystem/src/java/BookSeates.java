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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isuru
 */
public class BookSeates extends HttpServlet {

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
            out.println("<title>Servlet BookSeates</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookSeates at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String customer = request.getParameter("bookname");
        String regNo = request.getParameter("bookregno");
        String numofSeates = request.getParameter("bookseates");

        int reg = Integer.parseInt(regNo);
        int seates = Integer.parseInt(numofSeates);
        int flightSeates = 0;
        int restofSeates = 0;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            String statement = "select AIRSEATES from Flights where AIRREGNO=?";

            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, reg);

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            while (rs.next()) {
                //out.print("<h1>" + rsmd.getColumnName(1) + "</h1>");
                //out.print("<h1>" + rs.getString(1) + "</h1>");
                flightSeates = Integer.parseInt(rs.getString(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }

        restofSeates = flightSeates - seates;

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            //String statement = "insert into Flights(AIRSEATES) values(?) where AIRREGNO=?";
            String statement = "UPDATE Flights SET AIRSEATES=?, BOOKINGS=? WHERE AIRREGNO=?";

            PreparedStatement ps = con.prepareStatement(statement);
            ps.setInt(1, restofSeates);
            ps.setInt(2, seates);
            ps.setInt(3, reg);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Successfully booked Ticket...</h3>");
                /*out.println("<script type=\"text/javascript\">");
                out.println("alert('Successfully booked Ticket...');");
                out.println("</script>");
                */
            } else {
                out.println("<h3>Ticket booking Failed...</h3>");
                /*
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Ticket booking Failed...');");
                out.println("</script>");
                */
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

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

        processRequest(request, response);

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
