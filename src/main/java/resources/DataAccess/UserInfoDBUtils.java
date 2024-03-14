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
import resources.Models.UserInformation;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class UserInfoDBUtils {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public UserInfoDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    
    public boolean addUserInfo(UserInformation st) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("INSERT INTO userinformation (id,displayName,email,userType,password,active,createDate,modifiedDate,createdBy,modifiedBy,image)"
                    + "VALUES ( '"+ st.getId()+"','"+ st.getDisplayName() +"','"+ st.getEmail()+"','"+ st.getUserType()+"','"+ st.getPassword()+"','"+ st.isActive()+"','"+ st.getCreateDate()+"', '"+ st.getModifiedDate()+"','"+ st.getCreatedBy()+"','"+ st.getModifiedBy()+"','"+ st.getImage()+"');");
            return true;    
        } catch (SQLException e) {
            System.err.print(e);
        }
        return false;
    }
    
    public UserInformation getUserId(String email) throws SQLException {
        int userId=0;
        UserInformation st = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM userinformation WHERE email='"+ email+"';");) {
            while (rs.next()) {
                st = new UserInformation();
                st.setId( rs.getInt("id"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return st;
    }
}
