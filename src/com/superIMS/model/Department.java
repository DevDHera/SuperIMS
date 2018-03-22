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
public class Department {
    private final StringProperty DEPTID;
    private final StringProperty DESIGNATION;
    private final StringProperty DESCRIPTION;
    
    public Department(String DEPTID, String DESIGNATION, String DESCRIPTION){
        this.DEPTID = new SimpleStringProperty(DEPTID);
        this.DESIGNATION = new SimpleStringProperty(DESIGNATION);
        this.DESCRIPTION = new SimpleStringProperty(DESCRIPTION);
    }
    
    
    public String getDEPTID(){
        return DEPTID.get();
    }
    public String getDESIGNATION(){
        return DESIGNATION.get();
    }
    public String getDESCRIPTION(){
        return DESCRIPTION.get();
    }
    
    public void setDEPTID(String value){
        DEPTID.set(value);
    }
    public void setDESIGNATION(String value){
        DESIGNATION.set(value);
    }
    public void setDESCRIPTION(String value){
        DESCRIPTION.set(value);
    }
    
    public StringProperty deptidProperty(){
        return DEPTID;
    }
    public StringProperty designationProperty(){
        return DESIGNATION;
    }
    public StringProperty descriptionProperty(){
        return DESCRIPTION;
    }
}
