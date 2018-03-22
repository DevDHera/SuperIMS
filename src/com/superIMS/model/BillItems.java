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
public class BillItems {
    private final StringProperty BILLID;
    private final StringProperty ITEMID;
    private final StringProperty QTY;
    private final StringProperty PRICE;
    private final StringProperty PRICEPERUNIT;
    private final StringProperty ITEMDES;
    
    
    public BillItems(String BILLID, String ITEMID, String QTY, String PRICE, String PRICEPERUNIT, String ITEMDES){
        this.BILLID = new SimpleStringProperty(BILLID);
        this.ITEMID = new SimpleStringProperty(ITEMID);
        this.QTY = new SimpleStringProperty(QTY);
        this.PRICE = new SimpleStringProperty(PRICE);
        this.PRICEPERUNIT = new SimpleStringProperty(PRICEPERUNIT);
        this.ITEMDES = new SimpleStringProperty(ITEMDES);
        
    }
    
    public String getBILLID(){
        return BILLID.get();
    }
    public String getITEMID(){
        return ITEMID.get();
    }
    public String getQTY(){
        return QTY.get();
    }
    public String getPRICE(){
        return PRICE.get();
    }
    public String getPRICEPERUNIT(){
        return PRICEPERUNIT.get();
    }
    public String getITEMDES(){
        return ITEMDES.get();
    }
   
    
    public void setBILLID(String value){
        BILLID.set(value);
    }
    public void setITEMID(String value){
        ITEMID.set(value);
    }
    public void setQTY(String value){
        QTY.set(value);
    }
    public void setPRICE(String value){
        PRICE.set(value);
    }
    public void setPRICEPERUNIT(String value){
        PRICEPERUNIT.set(value);
    }
    public void setITEMDES(String value){
        ITEMDES.set(value);
    }
   
    
    public StringProperty billidProperty(){
        return BILLID;
    }
    public StringProperty itemidProperty(){
        return ITEMID;
    }
    public StringProperty qtyProperty(){
        return QTY;
    }
    public StringProperty priceProperty(){
        return PRICE;
    }
    public StringProperty priceperunitProperty(){
        return PRICEPERUNIT;
    }
    public StringProperty itemdesProperty(){
        return ITEMDES;
    }
    
}
