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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author isuru
 */
public class RegisterMember extends HttpServlet {

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
            out.println("<title>Servlet RegisterMember</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterMember at " + request.getContextPath() + "</h1>");
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

        String userName = request.getParameter("membname");
        String userEmail = request.getParameter("membemail");
        String userPhone = request.getParameter("membphone");
        String userPassword = request.getParameter("membpassword");
        String userRePassword = request.getParameter("membrepassword");

        int phone = Integer.parseInt(userPhone);

        if (userPassword.equals(userRePassword)) {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
                Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                String statement = "insert into USERS(USERNAME, USEREMAIL, USERPHONE, USERPASSWORD) values (?,?,?,?)";

                PreparedStatement ps = con.prepareStatement(statement);

                ps.setString(1, userName);
                ps.setString(2, userEmail);
                ps.setInt(3, phone);
                ps.setString(4, userPassword);

                int i = ps.executeUpdate();
                if (i > 0) {
                    //out.println("Successfully Registered User...");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Successfully Registered User...');");
                    out.println("</script>");
                    RequestDispatcher rs = request.getRequestDispatcher("index.html");
                    rs.include(request, response);
                } else {
                    //out.println("User Registration Failed...");
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('User Registration Failed...');");
                    out.println("</script>");
                    RequestDispatcher rs = request.getRequestDispatcher("RegisterMember.jsp");
                    rs.include(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            out.println("Passwords didn't match...");
            RequestDispatcher rs = request.getRequestDispatcher("RegisterMember.jsp");
            rs.include(request, response);
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
