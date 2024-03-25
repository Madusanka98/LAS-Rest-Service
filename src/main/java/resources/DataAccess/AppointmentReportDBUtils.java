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
import resources.Models.AppointmentReport;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentReportDBUtils {
    
    public AppointmentReportDBUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            
        }
    }
    
    public List<AppointmentReport> getAppointmentReports() {
        DatabaseConfig dbconfig = DatabaseConfig.getInstance();
        List<AppointmentReport> AppointmentReportdata = new ArrayList<>();
        try (Connection conn = dbconfig.getConnection(); 
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
