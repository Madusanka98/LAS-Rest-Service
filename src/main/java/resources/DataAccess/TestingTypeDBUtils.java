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
import resources.Models.TestingType;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class TestingTypeDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public TestingTypeDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public TestingType getTestingType(int id) throws SQLException {
        TestingType type = null;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM testingtype WHERE id="+ id);) {
            while (rs.next()) {
                type = new TestingType();
                type.setId( rs.getInt("id"));
                type.setName(rs.getString("name"));
                type.setPrice(rs.getDouble("price"));
                type.setActive(rs.getInt("active"));
                break;
            }
        } catch (SQLException e) {
            System.err.print(e);
            throw e;
        }
        return type;
    }
    
    public List<TestingType> getTestingTypes() {
        List<TestingType> types = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM testingtype");) {
            while (rs.next()) {
                TestingType type = new TestingType();
                type.setId( rs.getInt("id"));
                type.setName(rs.getString("name"));
                type.setPrice(rs.getDouble("price"));
                type.setActive(rs.getInt("active"));
                types.add(type);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return types;
    }
}
