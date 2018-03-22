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
public class Supplier {
    private final StringProperty SUPID;
    private final StringProperty SUPNAME;
    private final StringProperty SUPADDRESS;
    private final StringProperty SUPTEL;
    
    public Supplier(String SUPID, String SUPNAME, String SUPADDRESS, String SUPTEL){
        this.SUPID = new SimpleStringProperty(SUPID);
        this.SUPNAME = new SimpleStringProperty(SUPNAME);
        this.SUPADDRESS = new SimpleStringProperty(SUPADDRESS);
        this.SUPTEL = new SimpleStringProperty(SUPTEL);
    }
    
    public String getSUPID(){
        return SUPID.get();
    }
    public String getSUPNAME(){
        return SUPNAME.get();
    }
    public String getSUPADDRESS(){
        return SUPADDRESS.get();
    }
    public String getSUPTEL(){
        return SUPTEL.get();
    }
    
    public void setSUPID(String value){
        SUPID.set(value);
    }
    public void setSUPNAME(String value){
        SUPNAME.set(value);
    }
    public void setSUPADDRESS(String value){
        SUPADDRESS.set(value);
    }
    public void setSUPTEL(String value){
        SUPTEL.set(value);
    }
    
    public StringProperty supidProperty(){
        return SUPID;
    }
    public StringProperty supnameProperty(){
        return SUPNAME;
    }
    public StringProperty supaddressProperty(){
        return SUPADDRESS;
    }
    public StringProperty suptelProperty(){
        return SUPTEL;
    }
}
