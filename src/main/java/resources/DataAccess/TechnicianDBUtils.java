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
import java.util.ArrayList;
import java.util.List;
import resources.Models.Technician;
import resources.Models.UserInformation;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class TechnicianDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public TechnicianDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public Technician getTechnician(int id) throws SQLException {
        Technician st = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM technicians WHERE id="+ id);) {
            while (rs.next()) {
                st = new Technician();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAge(rs.getInt("age"));
                st.setCity(rs.getString("city"));
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
    
    public Technician getTechnicianbyUserId(int id) throws SQLException {
        Technician st = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM technicians WHERE userId="+ id);) {
            while (rs.next()) {
                st = new Technician();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAge(rs.getInt("age"));
                st.setCity(rs.getString("city"));
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
    
    public List<Technician> getTechnicians() {
        List<Technician> Technicians = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM technicians");) {
            while (rs.next()) {
                Technician st = new Technician();
                st.setId( rs.getInt("id"));
                st.setName(rs.getString("name"));
                st.setAge(rs.getInt("age"));
                st.setCity(rs.getString("city"));
                st.setEmail(rs.getString("email"));
                st.setMobileNum(rs.getString("mobileNum"));
                st.setAddress(rs.getString("address"));
                st.setStartDate(rs.getDate("startDate"));
                st.setuId(rs.getInt("userId"));
                Technicians.add(st);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return Technicians;
    }
    
    public boolean addTechnician(Technician st,int uId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("INSERT INTO technicians (id, name, age, city, email, mobileNum, address, startDate,image,userId) "
                    + "VALUES ('"+ st.getId()+"', '"+ st.getName() +"','"+ st.getAge()+"','"+ st.getCity()+"','"+ st.getEmail() +"','"+ st.getMobileNum()+"','"+ st.getAddress()+"', '"+ st.getStartDate()+"', '"+ st.getImage()+"', '"+ uId+"');");
            return true;    
        } catch (SQLException e) {
            System.err.print(e);
        }
        return false;
    }
    
    public boolean addUserInfo(UserInformation st) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("INSERT INTO userinformation (id,displayName,email,userType,password,active,createDate,modifiedDate,createdBy,modifiedBy)"
                    + "VALUES ( '"+ st.getId()+"','"+ st.getDisplayName() +"','"+ st.getEmail()+"','"+ st.getUserType()+"','"+ st.getPassword()+"','"+ st.isActive()+"','"+ st.getCreateDate()+"', '"+ st.getModifiedDate()+"','"+ st.getCreatedBy()+"','"+ st.getModifiedBy()+"');");
            return true;    
        } catch (SQLException e) {
            System.err.print(e);
        }
        return false;
    }
   
    public boolean updateTechnician(Technician st) {
        try {
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                    Statement stmt = conn.createStatement(); 
                ) 
            {
                stmt.executeUpdate("UPDATE technicians SET name = '" +st.getName()+ "', age = '" + st.getAge()+ "' , city = '" + st.getCity()+ "', email = '" + st.getEmail()+ "' , mobileNum = '" + st.getMobileNum()+ "' , address = '" + st.getAddress()+ "' , startDate = '" + st.getStartDate()+ "', image = '" + st.getImage()+ "', userId = '" + st.getuId()+ "' WHERE (id = '" + st.getId() +"');");
                return true;
            } catch (SQLException e) {
                System.err.print(e);
            }
        } catch (Exception e) {

        }
        return false;
    }
    
    public boolean deleteTechnician(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ) {
            stmt.executeUpdate("DELETE FROM technicians WHERE (id = '"+ id + "');");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
