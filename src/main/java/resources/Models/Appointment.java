/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Models;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class Appointment {
    private int id;
    private Date preferredDate;
    private String preferredTime;
    private String appointmentReason;
    private int appointmentType;
    private String referredDoctor;
    private String emgContactPer;
    private String emgMobileNum;
    private int userId;
    private List<ApprointmentDoc> approintmentDocList;
    private TestingType testingType;
    
    public Appointment(){
        
    }

    public Appointment(int id, Date preferredDate, String preferredTime, String appointmentReason, int appointmentType, String referredDoctor, String emgContactPer, String emgMobileNum, int userId, List<ApprointmentDoc> approintmentDocList, TestingType testingType) {
        this.id = id;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
        this.appointmentReason = appointmentReason;
        this.appointmentType = appointmentType;
        this.referredDoctor = referredDoctor;
        this.emgContactPer = emgContactPer;
        this.emgMobileNum = emgMobileNum;
        this.userId = userId;
        this.approintmentDocList = approintmentDocList;
        this.testingType = testingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAppointmentReason() {
        return appointmentReason;
    }

    public void setAppointmentReason(String appointmentReason) {
        this.appointmentReason = appointmentReason;
    }

    public int getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(int appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getReferredDoctor() {
        return referredDoctor;
    }

    public void setReferredDoctor(String referredDoctor) {
        this.referredDoctor = referredDoctor;
    }

    public String getEmgContactPer() {
        return emgContactPer;
    }

    public void setEmgContactPer(String emgContactPer) {
        this.emgContactPer = emgContactPer;
    }

    public String getEmgMobileNum() {
        return emgMobileNum;
    }

    public void setEmgMobileNum(String emgMobileNum) {
        this.emgMobileNum = emgMobileNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ApprointmentDoc> getApprointmentDocList() {
        return approintmentDocList;
    }

    public void setApprointmentDocList(List<ApprointmentDoc> approintmentDocList) {
        this.approintmentDocList = approintmentDocList;
    }

    public TestingType getTestingType() {
        return testingType;
    }

    public void setTestingType(TestingType testingType) {
        this.testingType = testingType;
    }

    
    
    
    
}
