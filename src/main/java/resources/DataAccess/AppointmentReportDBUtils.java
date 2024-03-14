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
import static resources.DataAccess.DoctorDBUtils.DB_URL;
import static resources.DataAccess.DoctorDBUtils.PASS;
import static resources.DataAccess.DoctorDBUtils.USER;
import resources.Models.AppointmentReport;
import resources.Models.Doctor;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentReportDBUtils {
    static final String DB_URL = "jdbc:mysql://localhost:3306/abc_lab";
    static final String USER = "root";
    static final String PASS = "";
    
    public AppointmentReportDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public List<AppointmentReport> getAppointmentReports() {
        List<AppointmentReport> AppointmentReportdata = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("SELECT * FROM appointmentreportview");) {
            while (rs.next()) {
                AppointmentReport ar = new AppointmentReport();
                ar.setCreateDateTime(rs.getDate("createDateTime"));
                ar.setPreferredDate(rs.getDate("preferredDate"));
                ar.setPreferredTime(rs.getString("preferredTime"));
                ar.setAppointmentType(rs.getString("appointmentType"));
                ar.setTechnicians(rs.getString("technicians"));
                ar.setTechnicianName(rs.getString("technicianName"));
                ar.setPatientsName(rs.getString("patientsName"));
                AppointmentReportdata.add(ar);
            }
        } catch (SQLException e) {
            System.err.print(e);
        }
        return AppointmentReportdata;
    }
}
