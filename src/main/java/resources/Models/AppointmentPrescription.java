/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Models;

import java.util.List;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentPrescription {
    private int id;
    private String testResults;
    private String technicians;
    private String comment;
    private int appointmentId;
    private List<AppointmentPrescriptionDoc> approintmentDocList;

    public AppointmentPrescription(){
        
    }
    
    public AppointmentPrescription(int id, String testResults, String technicians, String comment, int appointmentId, List<AppointmentPrescriptionDoc> approintmentDocList) {
        this.id = id;
        this.testResults = testResults;
        this.technicians = technicians;
        this.comment = comment;
        this.approintmentDocList = approintmentDocList;
        this.appointmentId = appointmentId;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
    }

    public String getTechnicians() {
        return technicians;
    }

    public void setTechnicians(String technicians) {
        this.technicians = technicians;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public List<AppointmentPrescriptionDoc> getApprointmentDocList() {
        return approintmentDocList;
    }

    public void setApprointmentDocList(List<AppointmentPrescriptionDoc> approintmentDocList) {
        this.approintmentDocList = approintmentDocList;
    }
    
    
}
