
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isuru
 */
public class ValidateAdmin {
    
    public static boolean checkUser(String email, String pass) {
        boolean st = false;
        try {
//loading driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
//creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
            PreparedStatement ps = con.prepareStatement("select * from ADMIN where ADMINEMAIL=? and ADMINPASSWORD=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            st = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }
    
}
