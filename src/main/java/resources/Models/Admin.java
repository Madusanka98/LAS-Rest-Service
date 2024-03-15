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
public class Admin {
    private int id;
    private String name;
    private String nic;
    private String email;
    private String mobileNum;
    private String address;
    private Date startDate;
    private String image;
    private int uId;
    
    public Admin(){
        
    }

    public Admin(int id, String name, String nic, String email, String mobileNum, String address, Date startDate, String image, int uId) {
        this.id = id;
        this.name = name;
        this.nic = nic;
        this.email = email;
        this.mobileNum = mobileNum;
        this.address = address;
        this.startDate = startDate;
        this.image = image;
        this.uId = uId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }
    
    
    
}
