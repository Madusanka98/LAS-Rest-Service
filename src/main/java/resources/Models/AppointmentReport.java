/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Models;

import java.sql.Date;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentReport {
    private Date createDateTime;
    private Date preferredDate;
    private String preferredTime;
    private String appointmentType;
    private String technicians;
    private String technicianName;
    private String patientsName;
    
    public AppointmentReport(){
        
    }

    public AppointmentReport(Date createDateTime, Date preferredDate, String preferredTime, String appointmentType, String technicians, String technicianName, String patientsName) {
        this.createDateTime = createDateTime;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
        this.appointmentType = appointmentType;
        this.technicians = technicians;
        this.technicianName = technicianName;
        this.patientsName = patientsName;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Date getPreferredDate() {
        return preferredDate;
    }

    public void setPreferredDate(Date preferredDate) {
        this.preferredDate = preferredDate;
    }

    public String getPreferredTime() {
        return preferredTime;
    }

    public void setPreferredTime(String preferredTime) {
        this.preferredTime = preferredTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getTechnicians() {
        return technicians;
    }

    public void setTechnicians(String technicians) {
        this.technicians = technicians;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getPatientsName() {
        return patientsName;
    }

    public void setPatientsName(String patientsName) {
        this.patientsName = patientsName;
    }

    
    
}
