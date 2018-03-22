/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author USER
 */
public class Complaint {
    private final StringProperty COMPID;
    private final StringProperty SID;
    private final StringProperty COMPLAINT;
    
    public Complaint(String COMPID, String SID, String COMPLAINT){
        this.COMPID = new SimpleStringProperty(COMPID);
        this.SID = new SimpleStringProperty(SID);
        this.COMPLAINT = new SimpleStringProperty(COMPLAINT);
    }
    
    public String getCOMPID(){
        return COMPID.get();
    }
    public String getSID(){
        return SID.get();
    }
    public String getCOMPLAINT(){
        return COMPLAINT.get();
    }
    
    public void setCOMPID(String value){
        COMPID.set(value);
    }
    public void setSID(String value){
        SID.set(value);
    }
    public void setCOMPLAINT(String value){
        COMPLAINT.set(value);
    }
    
    public StringProperty compidProperty(){
        return COMPID;
    } 
    public StringProperty sidProperty(){
        return SID;
    } 
    public StringProperty complaintProperty(){
        return COMPLAINT;
    } 
}
