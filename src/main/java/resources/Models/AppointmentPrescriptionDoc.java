/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Models;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class AppointmentPrescriptionDoc {
    private int id;
    private String fileName;
    private String fileType;
    private String document;
    private int active;
    private int aPrescriptionId;
    
    public AppointmentPrescriptionDoc(){
        
    }

    public AppointmentPrescriptionDoc(int id, String fileName, String fileType, String document, int active, int aPrescriptionId) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.document = document;
        this.active = active;
        this.aPrescriptionId = aPrescriptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getaPrescriptionId() {
        return aPrescriptionId;
    }

    public void setaPrescriptionId(int aPrescriptionId) {
        this.aPrescriptionId = aPrescriptionId;
    }
    
}
