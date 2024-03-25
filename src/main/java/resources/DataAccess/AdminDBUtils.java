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
    
    public AdminDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public Admin getAdminbyUserId(int id) throws SQLException {
        Admin st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
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
