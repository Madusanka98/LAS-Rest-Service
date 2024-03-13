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
import static resources.DataAccess.DoctorDBUtils.DB_URL;
import static resources.DataAccess.DoctorDBUtils.PASS;
import static resources.DataAccess.DoctorDBUtils.USER;
import static resources.DataAccess.TechnicianDBUtils.DB_URL;
import static resources.DataAccess.TechnicianDBUtils.PASS;
import static resources.DataAccess.TechnicianDBUtils.USER;
import resources.Models.Doctor;
import resources.Models.Patient;
import resources.Models.Technician;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class PatientDBUtils {
    
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public PatientDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public boolean addPatient(Patient st,String uniqueId,int userId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
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
        Patient st = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
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
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
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
