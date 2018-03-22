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
public class User {
    private final StringProperty SID;
    private final StringProperty SFNAME;
    private final StringProperty SLNAME;
    private final StringProperty GENDER;
    private final StringProperty SADDRESS;
    private final StringProperty SPHNO;
    private final StringProperty DEPTID;
    private final StringProperty DESIGNATION; 
    
    public User(String SID, String SFNAME, String SLNAME, String GENDER, String SADDRESS, String SPHNO, String DEPTID, String DESIGNATION){
        this.SID = new SimpleStringProperty(SID);        
        this.SFNAME = new SimpleStringProperty(SFNAME);        
        this.SLNAME = new SimpleStringProperty(SLNAME);        
        this.GENDER = new SimpleStringProperty(GENDER);        
        this.SADDRESS = new SimpleStringProperty(SADDRESS);        
        this.SPHNO = new SimpleStringProperty(SPHNO);        
        this.DEPTID = new SimpleStringProperty(DEPTID);        
        this.DESIGNATION = new SimpleStringProperty(DESIGNATION);                
    }

    /**
     * @return the SID
     */
    public String getSID() {
        return SID.get();
    }

    /**
     * @return the SFNAME
     */
    public String getSFNAME() {
        return SFNAME.get();
    }

    /**
     * @return the SLNAME
     */
    public String getSLNAME() {
        return SLNAME.get();
    }

    /**
     * @return the GENDER
     */
    public String getGENDER() {
        return GENDER.get();
    }

    /**
     * @return the SADDRESS
     */
    public String getSADDRESS() {
        return SADDRESS.get();
    }

    /**
     * @return the SPHNO
     */
    public String getSPHNO() {
        return SPHNO.get();
    }

    /**
     * @return the DEPTID
     */
    public String getDEPTID() {
        return DEPTID.get();
    }

    /**
     * @return the DESIGNATION
     */
    public String getDESIGNATION() {
        return DESIGNATION.get();
    }

    /**
     * @param SID the SID to set
     */
    public void setSID(String value) {
        SID.set(value);
    }

    /**
     * @param SFNAME the SFNAME to set
     */
    public void setSFNAME(String value) {
        SFNAME.set(value);
    }

    /**
     * @param SLNAME the SLNAME to set
     */
    public void setSLNAME(String value) {
        SLNAME.set(value);
    }

    /**
     * @param GENDER the GENDER to set
     */
    public void setGENDER(String value) {
        GENDER.set(value);
    }

    /**
     * @param SADDRESS the SADDRESS to set
     */
    public void setSADDRESS(String value) {
        SADDRESS.set(value);
    }

    /**
     * @param SPHNO the SPHNO to set
     */
    public void setSPHNO(String value) {
        SPHNO.set(value);
    }

    /**
     * @param DEPTID the DEPTID to set
     */
    public void setDEPTID(String value) {
        DEPTID.set(value);
    }

    /**
     * @param DESIGNATION the DESIGNATION to set
     */
    public void setDESIGNATION(String value) {
        DESIGNATION.set(value);
    }
    
    public StringProperty sidProperty(){
        return SID;
    }
    public StringProperty sfnameProperty(){
        return SFNAME;
    }
    public StringProperty slnameProperty(){
        return SLNAME;
    }
    public StringProperty saddressProperty(){
        return SADDRESS;
    }
    public StringProperty genderProperty(){
        return GENDER;
    }
    public StringProperty sphnoProperty(){
        return SPHNO;
    }
    public StringProperty deptidProperty(){
        return DEPTID;
    }
    public StringProperty designationProperty(){
        return DESIGNATION;
    }
}
