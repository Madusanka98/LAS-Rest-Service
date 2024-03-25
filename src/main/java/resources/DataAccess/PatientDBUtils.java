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
import resources.Models.Patient;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class PatientDBUtils {
    
    public PatientDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public boolean addPatient(Patient st,String uniqueId,int userId) {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection();  
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("INSERT INTO patients ( name, gender,mobileNum , email, dob, address,image,uniqueId,userId) "
                    + "VALUES ('"+ st.getName() +"','"+ st.getGender()+"','"+ st.getMobileNum()+"','"+ st.getEmail() +"','"+ st.getDob()+"','"+ st.getAddress()+"','"+ st.getImage()+"','"+ uniqueId+"', '"+ userId+"');");
            return true;    
        } catch (SQLException e) {
            System.err.print(e);
        }
        return false;
    }
    
    public Patient getPatient(int id) throws SQLException {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        Patient st = null;
        try (Connection conn = dbconfig.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients WHERE id="+ id);) {
            while (rs.next()) {
                st = new Patient();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setEmail(rs.getString("email"));
                st.setMobileNum(rs.getString("mobileNum"));
                st.setDob(rs.getDate("dob"));
                st.setGender(rs.getString("gender"));
                st.setAddress(rs.getString("address"));
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
    
    public Patient getPatientbyUserId(int id) throws SQLException {
        Patient st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM patients WHERE userId="+ id);) {
            while (rs.next()) {
                st = new Patient();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setEmail(rs.getString("email"));
                st.setMobileNum(rs.getString("mobileNum"));
                st.setDob(rs.getDate("dob"));
                st.setGender(rs.getString("gender"));
                st.setAddress(rs.getString("address"));
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
