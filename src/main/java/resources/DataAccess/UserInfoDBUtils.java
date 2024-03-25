/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import resources.Models.UserInformation;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class UserInfoDBUtils {
    
    public UserInfoDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.err.print(e);
        }
    }
    
    public boolean addUserInfo(UserInformation st) {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("INSERT INTO userinformation (id,displayName,email,userType,password,active,createDate,modifiedDate,createdBy,modifiedBy,image)"
                    + "VALUES ( '"+ st.getId()+"','"+ st.getDisplayName() +"','"+ st.getEmail()+"','"+ st.getUserType()+"','"+ st.getPassword()+"','"+ st.isActive()+"','"+ st.getCreateDate()+"', '"+ st.getModifiedDate()+"','"+ st.getCreatedBy()+"','"+ st.getModifiedBy()+"','"+ st.getImage()+"');");
            return true;    
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public UserInformation getUserId(String email) throws SQLException {
        UserInformation st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM userinformation WHERE email='"+ email+"';");) {
            while (rs.next()) {
                st = new UserInformation();
                st.setId( rs.getInt("id"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
    
    public UserInformation getUserInfo(int id) throws SQLException {
        UserInformation st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM userinformation WHERE id='"+ id+"';");) {
            while (rs.next()) {
                st = new UserInformation();
                st.setEmail( rs.getString("email"));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return st;
    }
    
}
