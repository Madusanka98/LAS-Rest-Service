/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import resources.Models.Admin;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AdminDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public AdminDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public Admin getAdminbyUserId(int id) throws SQLException {
        Admin st = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE userId="+ id);) {
            while (rs.next()) {
                st = new Admin();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setNic(rs.getString("nic"));
                st.setEmail(rs.getString("email"));
                st.setMobileNum(rs.getString("mobileNum"));
                st.setAddress(rs.getString("address"));
                st.setStartDate(rs.getDate("startDate"));
                st.setImage(rs.getString("image"));
                st.setuId(rs.getInt("userId"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return st;
    }
    
    
    
}
