/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static resources.Controllers.PatientResource.hashPassword;
import resources.Models.Login;
import resources.Models.UserInformation;
/**
 *
 * @author Madusanka(MadusankaB
 */
public class LoginDBUtils {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public LoginDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public UserInformation checkLogin(Login login) throws SQLException, JsonProcessingException, NoSuchAlgorithmException {
        UserInformation user = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); ){
                String email = login.getEmail();
                String password = login.getPassword();
                String query = "SELECT COUNT(*) AS count FROM abc_lab.userinformation WHERE email= '"+ email + "' and password = '"+ password +"' ;";
                ResultSet rs = stmt.executeQuery(query );
            //if (rs.next()) {
                //int count = rs.getInt("count");
                //boolean hasRecords = count > 0;
                String hashedEnteredPassword = hashPassword(password);
                String storedPassword = getPasswordFromDatabase(email);
                if (hashedEnteredPassword.equals(storedPassword)) {
                    user = new UserInformation();
                    try (Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS); 
                        Statement stmt1 = conn1.createStatement(); ){
                            String check = "SELECT * FROM abc_lab.userinformation WHERE email= '"+ email +"' ;";
                            ResultSet r = stmt1.executeQuery(check );
                            //infoMapper.setUserType( r.getString("userType"));
                            while (r.next()) {
                                user.setId( r.getInt("id"));
                                user.setUserType( r.getInt("userType"));
                                user.setDisplayName( r.getString("displayName"));
                                user.setEmail( r.getString("email"));
                            }
                    } catch (SQLException e) {
                        System.err.print(e);
                        throw e;
                    }
                }else {
                    System.out.println("Invalid Login");
                }
            //}
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return user;
    }
    
    public static String getPasswordFromDatabase(String username) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn  = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT password FROM userinformation WHERE email = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    public UserInformation getUserType(int id) throws SQLException{
        UserInformation user = null;
        try (Connection conn1 = DriverManager.getConnection(DB_URL, USER, PASS); 
            Statement stmt1 = conn1.createStatement(); ){
            user = new UserInformation();
                String query = "SELECT * FROM abc_lab.userinformation WHERE id= '"+ id + "' ;";
                ResultSet r = stmt1.executeQuery(query );
                //infoMapper.setUserType( r.getString("userType"));
                while (r.next()) {
                    user.setId(r.getInt("id"));
                    user.setUserType( r.getInt("userType"));
                }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return user;
    }
    
}
