/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.superIMS.model.ConnectionManager;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class LoginController implements Initializable {

     @FXML
    private JFXTextField txtUName;

    @FXML
    private JFXPasswordField txtPWD;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXCheckBox cbxRemember;

    @FXML
    private JFXButton btnClose;
    
    //Hover Close Button
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-border-color:white;";
    private static final String HOVERED_BUTTON_STYLE = "-fx-background-color:red; -fx-border-color:white;";

    //Connection Objects
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    //User Details
    private String SID = null;
    private String FName = null;
    private String Desi = null;
    private Timestamp timestamp = null;
    private String spic = null;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUName.setStyle("-fx-text-inner-color: #a0a2ab");
        txtPWD.setStyle("-fx-text-inner-color: #a0a2ab");
        btnClose.setStyle(IDLE_BUTTON_STYLE);
        btnClose.setOnMouseEntered(e -> btnClose.setStyle(HOVERED_BUTTON_STYLE));
        btnClose.setOnMouseExited(e -> btnClose.setStyle(IDLE_BUTTON_STYLE));
    }    
    
    @FXML
    public void closeAction(ActionEvent event) {       
        System.exit(0);        
    }
    
    @FXML
    public void loginAction(ActionEvent event) throws IOException {
        con = ConnectionManager.getConnection();
         try {
             stmt = con.createStatement();
             
             String sql = "SELECT * FROM staff WHERE SFNAME='"+txtUName.getText()+"' AND SPWD='"+txtPWD.getText()+"'";
             
             rs = stmt.executeQuery(sql);
             
             if(rs.next()){
                
                
                //User Details initiation
                rs = stmt.executeQuery(sql);
                while(rs.next()){                    
                    this.SID = rs.getString("SID");
                    this.FName = rs.getString("SFNAME");
                    this.Desi = rs.getString("DESIGNATION");  
                    this.spic = rs.getString("SPIC");
                }
                
                //Login Timestamp
                timestamp = new Timestamp(System.currentTimeMillis());
                String timeStampSql = "INSERT INTO userLOG(SID,SFNAME,DESIGNATION,LOGIN) VALUES('"+getSID()+"','"+getFName()+"','"+getDesi()+"','"+getTimestamp()+"')";
                stmt.executeUpdate(timeStampSql);
                
                //AdminViewController user Data initilization  
                if(getDesi().equals("Super Admin") || getDesi().equals("Admin")){
                    btnLogin.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    //Parent root = FXMLLoader.load(getClass().getResource("/com/superIMS/view/AdminView.fxml"));
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/superIMS/view/AdminView.fxml"));
                    stage.initStyle(StageStyle.UNDECORATED);                
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);

                    AdminViewController controller = loader.getController();
                    controller.setUserCredentials(SID, FName, Desi, timestamp, getSpic());

                    stage.show();
                }else if(getDesi().equals("Cashier")){
                    btnLogin.getScene().getWindow().hide();
                    Stage stage = new Stage();
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/superIMS/view/CashierView.fxml"));
                    stage.initStyle(StageStyle.UNDECORATED);                
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);

                    CashierViewController controller = loader.getController();
                    controller.setUserCredentials(SID, FName, Desi, timestamp, getSpic());
                    
                    stage.show();
                }
                                
             }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Status");
                alert.setHeaderText("Login Failed");
                alert.setContentText(null);
                alert.showAndWait(); 
             }
             
         } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("SQL Error");
            alert.setHeaderText("SQLException");
            alert.setContentText(ex.toString());
            alert.showAndWait();
         } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("SQL Error");
                alert.setHeaderText("SQLException");
                alert.setContentText(ex.toString());
                alert.showAndWait();
            }
         }
        
        //if(txtUName.getText().equals("admin") && txtPWD.getText().equals("1234")){
            /*Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Status");
            alert.setHeaderText("Login Success");
            alert.setContentText("Admin You Are loggend in");
            alert.showAndWait();*/
            /*btnLogin.getScene().getWindow().hide();
            Stage admin = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/superIMS/view/AdminView.fxml"));
            Scene scene = new Scene(root);
            admin.setScene(scene);
            admin.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Status");
            alert.setHeaderText("Login Failed");
            alert.setContentText(null);
            alert.showAndWait();
        }*/
            
    }

    /**
     * @return the SID
     */
    public String getSID() {
        return SID;
    }

    /**
     * @return the FName
     */
    public String getFName() {
        return FName;
    }

    /**
     * @return the Desi
     */
    public String getDesi() {
        return Desi;
    }

    /**
     * @return the timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * @return the spic
     */
    public String getSpic() {
        return spic;
    }    
    
}
