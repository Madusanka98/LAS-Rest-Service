/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import resources.Models.Technician;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class TechnicianDBUtils  {
    
    public TechnicianDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } 
        catch (Exception e) {
             System.err.print(e);
        }
    }
    
    public Technician getTechnician(int id){
        Technician st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection();
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
        } 
        catch (SQLException e) {
            System.err.print(e);
        }
        return st;
    }
    
    public Technician getTechnicianbyUserId(int id) {
        Technician st = null;
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
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
        } 
        catch (SQLException e) {
            System.err.print(e);
        }
        return st;
    }
    
    public List<Technician> getTechnicians() {
        List<Technician> Technicians = new ArrayList<>();
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection(); 
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
        } 
        catch (SQLException e) {
            System.err.print(e);
        }
        return Technicians;
    }
    
    public boolean addTechnician(Technician st,int uId) {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection();
                Statement stmt = conn.createStatement();) 
        {
            stmt.executeUpdate("INSERT INTO technicians (id, name, age, city, email, mobileNum, address, startDate,image,userId) "
                    + "VALUES ('"+ st.getId()+"', '"+ st.getName() +"','"+ st.getAge()+"','"+ st.getCity()+"','"+ st.getEmail() +"','"+ st.getMobileNum()+"','"+ st.getAddress()+"', '"+ st.getStartDate()+"', '"+ st.getImage()+"', '"+ uId+"');");
            return true;    
        } 
        catch (SQLException e) {
            System.err.print(e);
        }
        return false;
    }
   
    public boolean updateTechnician(Technician st) {
        try {
            DatabaseConfig dbconfig = DatabaseConfig.getInstance();
            try (Connection conn = dbconfig.getConnection(); 
                    Statement stmt = conn.createStatement();) 
            {
                stmt.executeUpdate("UPDATE technicians SET name = '" +st.getName()+ "', age = '" + st.getAge()+ "' , city = '" + st.getCity()+ "', email = '" + st.getEmail()+ "' , mobileNum = '" + st.getMobileNum()+ "' , address = '" + st.getAddress()+ "' , startDate = '" + st.getStartDate()+ "', image = '" + st.getImage()+ "', userId = '" + st.getuId()+ "' WHERE (id = '" + st.getId() +"');");
                return true;
            } 
            catch (SQLException e) {
                System.err.print(e);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteTechnician(int id) {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        try (Connection conn = dbconfig.getConnection();
                Statement stmt = conn.createStatement();) 
        {
            stmt.executeUpdate("DELETE FROM technicians WHERE (id = '"+ id + "');");
            return true;
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
