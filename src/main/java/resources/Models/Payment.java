/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.Models;

/**
 *
 * @author Madusanka(MadusankaB
 */
public class Payment {
    private int id;
    private int appId;
    private boolean isPaied;

    public  Payment (){
    }

    public Payment(int id, int appId, boolean isPaied) {
        this.id = id;
        this.appId = appId;
        this.isPaied = isPaied;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppId() {
        return appId;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public boolean isIsPaied() {
        return isPaied;
    }

    public void setIsPaied(boolean isPaied) {
        this.isPaied = isPaied;
    }
    
    
}
