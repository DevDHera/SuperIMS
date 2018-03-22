/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.model;

import java.sql.*;
import javafx.scene.control.Alert;


/**
 *
 * @author USER
 */
public class ConnectionManager {
    private static String url = "jdbc:mysql://localhost:3308/superIMS?useSSL=false";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String userName = "root";
    private static String password = "";
    private static Connection con;
    
    public static Connection getConnection(){
       try{
           Class.forName(driverName);
           try{
               con = DriverManager.getConnection(url, userName, password);
           }catch(SQLException ex){
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Connection Error");
               alert.setHeaderText("SQLException");
               alert.setContentText(ex.toString());
               alert.showAndWait();
           }
       }catch(ClassNotFoundException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Databse Error");
            alert.setHeaderText("ClassNotFoundException");
            alert.setContentText(ex.toString());
            alert.showAndWait();
       } 
       return con;
    }
}
