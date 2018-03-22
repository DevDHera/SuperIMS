/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.superIMS.model.ConnectionManager;
import com.superIMS.controller.LoginController;
import com.superIMS.model.Complaint;
import com.superIMS.model.Department;
import com.superIMS.model.Supplier;
import com.superIMS.model.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AdminViewController implements Initializable {

    @FXML
    private Pane pnlSideMenu;
    
    @FXML
    private Pane pnlDivider;

    @FXML
    private JFXButton btnSideMenu;
    
    @FXML
    private JFXButton btnDashbord;
    
    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnMinmize;
    
     @FXML
    private JFXButton btnLogOut;
     
    @FXML
    private ImageView staffPic;
    
    @FXML
    private Label lblURole;

    @FXML
    private Label lblUName;
    
    @FXML
    private JFXButton btnRegUser;
    
     @FXML
    private Pane pnlView;

    @FXML
    private Pane pnlDashboard;

    @FXML
    private Pane pnlRegUser;
    
    @FXML
    private Pane pnlDashLoginStatus;

    @FXML
    private JFXButton btnLoginStatusMore;

    @FXML
    private Pane pnlDashUserSummery;

    @FXML
    private Pane pnlDashReportSummery;

    @FXML
    private Pane pnlDashSupplierSummery;
    
    @FXML
    private Label lblLastLoginTime;

    @FXML
    private Label lblLastLogoutTime;
    
    @FXML
    private Label lblNumberEmp;

    @FXML
    private Label lblComplaints;

    @FXML
    private JFXButton btnUserSumMore;
    
     @FXML
    private Label lblNumberSales;

    @FXML
    private Label lblSaleAmount;

    @FXML
    private JFXButton btnReportSumMore;
    
    @FXML
    private Label lblSupPending;

    @FXML
    private Label lblSupOuts;

    @FXML
    private JFXButton btnSupplierSumMore;
    
     @FXML
    private JFXButton btnReports;

    @FXML
    private JFXButton btnSupplier;
    
    @FXML
    private Pane pnlReports;

    @FXML
    private Pane pnlSuppliers;
    
    @FXML
    private Pane pnlStaffDetails;

    @FXML
    private TableView<User> tableStaffDetails;

    @FXML
    private TableColumn<User, String> colSID;

    @FXML
    private TableColumn<User, String> colSFNAME;

    @FXML
    private TableColumn<User, String> colSLNAME;

    @FXML
    private TableColumn<User, String> colSADDRESS;

    @FXML
    private TableColumn<User, String> colSPHNO;
    
    @FXML
    private JFXButton btnSeeComp;

    @FXML
    private Pane pnlComplaints;

    @FXML
    private TableView<Complaint> tableComplaints;

    @FXML
    private TableColumn<Complaint, String> colCOMPID;

    @FXML
    private TableColumn<Complaint, String> colCOMPSID;

    @FXML
    private TableColumn<Complaint, String> colComplaint;

    @FXML
    private JFXButton btnComplaintBack;
    
    @FXML
    private JFXTextField txtFName;

    @FXML
    private JFXTextField txtLName;

    @FXML
    private JFXRadioButton radMale;

    @FXML
    private JFXRadioButton radFemale;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtTel;
    
    @FXML
    private JFXComboBox<String> cbxDept;

    @FXML
    private JFXComboBox<String> cbxDesi;
    
    @FXML
    private JFXComboBox<String> cbxBranch;

    @FXML
    private JFXButton btnRegNext;

    @FXML
    private JFXTextField txtSID;

    @FXML
    private JFXTextField txtPWD;
    
    @FXML
    private JFXButton btnPic;
    
    @FXML
    private JFXButton btnGenSID;

    @FXML
    private JFXButton btnGenPWD;
    
    @FXML
    private LineChart<String, Float> dailySalesChart;

    @FXML
    private JFXButton btnGenChart;
    
    @FXML
    private TableView<Supplier> tableSupplierDetails;

    @FXML
    private TableColumn<Supplier, String> colSUPID;

    @FXML
    private TableColumn<Supplier, String> colSUPNAME;

    @FXML
    private TableColumn<Supplier, String> colSUPADD;

    @FXML
    private TableColumn<Supplier, String> colSUPTEL;

    @FXML
    private JFXButton btnSupDetailsTable;

    @FXML
    private JFXButton btnSupReg;

    @FXML
    private JFXButton btnSupplies;
    
    @FXML
    private Pane pnlSupplierRegistration;

    
    //Hover Close | Minimize Buttons
    private static final String IDLE_BUTTON_STYLE = "-fx-background-color: transparent; -fx-border-color:white;";
    private static final String HOVERED_BUTTON_STYLE_CLOSE = "-fx-background-color:red; -fx-border-color:white;";
    private static final String HOVERED_BUTTON_STYLE_MIN = "-fx-background-color:blue; -fx-border-color:white;";
    
    //Connection Objects
    private Connection con = null;
    private Statement stmt = null;
    private Statement stmt2 = null;
    private ResultSet rs = null;
    private ResultSet rs2 = null;
    
    //Set user Credentilas
    private String SID = null;
    private String FName = null;
    private String Desi = null;
    private Timestamp loginTime = null;
    private String spic = null;
    
    //OBList
    private ObservableList<User>data;
    private ObservableList<Complaint>data1;
    private ObservableList<String>data2;
    private ObservableList<String>data3;
    private ObservableList<String>data4;
    private ObservableList<Supplier>data5;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        prepareSideMenuAnimation();
        //Hover Close Button
        btnClose.setStyle(IDLE_BUTTON_STYLE);
        btnClose.setOnMouseEntered(e -> btnClose.setStyle(HOVERED_BUTTON_STYLE_CLOSE));
        btnClose.setOnMouseExited(e -> btnClose.setStyle(IDLE_BUTTON_STYLE));
        //Hover Minimize Button
        btnMinmize.setStyle(IDLE_BUTTON_STYLE);
        btnMinmize.setOnMouseEntered(e -> btnMinmize.setStyle(HOVERED_BUTTON_STYLE_MIN));
        btnMinmize.setOnMouseExited(e -> btnMinmize.setStyle(IDLE_BUTTON_STYLE));
        
        pnlDashboard.setVisible(true);
        pnlRegUser.setVisible(false);
        pnlReports.setVisible(false);
        pnlSuppliers.setVisible(false);
        pnlStaffDetails.setVisible(false);
        pnlComplaints.setVisible(false);
        pnlSupplierRegistration.setVisible(false);
        pnlDashboard.toFront();
    }    
    
    private void prepareSideMenuAnimation() {
        TranslateTransition openNav=new TranslateTransition(new Duration(350), pnlSideMenu);
        openNav.setToX(0);
        TranslateTransition closeNav=new TranslateTransition(new Duration(350), pnlSideMenu);
        btnSideMenu.setOnAction((ActionEvent evt)->{
            if(pnlSideMenu.getTranslateX()!=0){
                openNav.play();                
                
            }else{
                closeNav.setToX(-(pnlSideMenu.getWidth()));
                closeNav.play();                
            }
        });
    }
    
    @FXML
    void closeViewAction(ActionEvent event) throws IOException {
        //System.exit(0);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(FName+", you need to logout First");
        alert.setResizable(false);
        alert.setContentText("Select OK to Logout");

        Optional<ButtonType> result = alert.showAndWait();
        ButtonType button = result.orElse(ButtonType.CANCEL);

        if (button == ButtonType.OK) {
            logout();
        }
    }

    @FXML
    void minAction(ActionEvent event) {
        //System.out.println("src/com/superIMS/assests/imgs/spics/"+spic);
    }
    
    @FXML
    void logOutAction(ActionEvent event) throws IOException {
        logout();
    }
    
    @FXML
    void handleSideMenuActions(ActionEvent event) {
        if(event.getSource()==btnDashbord){
            pnlDashboard.setVisible(true);
            pnlReports.setVisible(false);
            pnlRegUser.setVisible(false);            
            pnlSuppliers.setVisible(false);            
            pnlStaffDetails.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlSupplierRegistration.setVisible(false);
            pnlDashboard.toFront();
        }else if(event.getSource()==btnRegUser){
            pnlRegUser.setVisible(true);
            pnlReports.setVisible(false);
            pnlDashboard.setVisible(false);            
            pnlSuppliers.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlSupplierRegistration.setVisible(false);
            pnlRegUser.toFront();
            
            /*ObservableList<String> options = FXCollections.observableArrayList(
                    "1",
                    "2",
                    "3"
            );
            cbxDesi = new JFXComboBox<String>(options);
            cbxDesi.setItems(options);*/
            //cbxDesi.getItems().addAll("1","2","3");
            
            
            setRegistration();
        }else if(event.getSource()==btnReports){
            pnlReports.setVisible(true);
            pnlDashboard.setVisible(false);
            pnlRegUser.setVisible(false);            
            pnlSuppliers.setVisible(false); 
            pnlComplaints.setVisible(false);
            pnlSupplierRegistration.setVisible(false);
            pnlReports.toFront();
        }else if(event.getSource()==btnSupplier){
            pnlSuppliers.setVisible(true);
            pnlReports.setVisible(false);
            pnlRegUser.setVisible(false);            
            pnlDashboard.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlSupplierRegistration.setVisible(false);
            pnlSuppliers.toFront();
            setSupplierTable();
        }
    }
    
    @FXML
    void handleDashPanelButtons(ActionEvent event) {
        if(event.getSource()==btnLoginStatusMore){
            System.out.println("Login Status More");
        }else if(event.getSource()==btnReportSumMore){
            pnlSuppliers.setVisible(false);
            pnlReports.setVisible(true);
            pnlRegUser.setVisible(false);            
            pnlDashboard.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlStaffDetails.setVisible(false);
            pnlSupplierRegistration.setVisible(false);
            pnlReports.toFront();
            chartCreationGeneral();
        }else if(event.getSource()==btnUserSumMore){
            pnlSuppliers.setVisible(false);
            pnlReports.setVisible(false);
            pnlRegUser.setVisible(false);            
            pnlDashboard.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlStaffDetails.setVisible(true);
            pnlSupplierRegistration.setVisible(false);
            pnlStaffDetails.toFront();
            
            con = ConnectionManager.getConnection();
            data = FXCollections.observableArrayList();
            
            try{
            stmt = con.createStatement();
            
            String userSql = "SELECT * FROM staff";
            
            rs = stmt.executeQuery(userSql);
            
            while(rs.next()){
                data.add(new User(rs.getString("SID"), rs.getString("SFNAME"), rs.getString("SLNAME"), rs.getString("GENDER"), rs.getString("SADDRESS"), rs.getString("SPHNO"), rs.getString("DEPTID"), rs.getString("DESIGNATION")));
            }
            
            colSID.setCellValueFactory(new PropertyValueFactory<>("SID"));
            colSFNAME.setCellValueFactory(new PropertyValueFactory<>("SFNAME"));
            colSLNAME.setCellValueFactory(new PropertyValueFactory<>("SLNAME"));
            colSADDRESS.setCellValueFactory(new PropertyValueFactory<>("SADDRESS"));
            colSPHNO.setCellValueFactory(new PropertyValueFactory<>("SPHNO"));
            
            tableStaffDetails.setItems(data);
            
            
        }catch(SQLException ex){
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
            
        }else if(event.getSource()==btnSupplierSumMore){
            setSupplierTable();
        }        
    }
    
    //Initializing user Data using controller
    public void setUserCredentials(String id, String name, String des, Timestamp t, String p){
        this.SID = id;
        this.FName = name;
        this.Desi = des;
        this.loginTime = t;
        this.spic = p;
        
        setStaffDetails();
        setLoginStatus();
        setSupplierStatus();
        setUserSumStatus();
        setReportStatus();
    }    
    
    
    private void setStaffDetails(){
        File file = new File("src/com/superIMS/assests/imgs/spics/"+spic);
        Image image = new Image(file.toURI().toString());
        staffPic.setImage(image);
        
        lblURole.setText(Desi);
        lblUName.setText(FName);
    }
    
    private void setLoginStatus(){
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String loginStatusSql = "SELECT LOGIN,LOGOUT FROM userLOG WHERE SID='"+SID+"' ORDER BY SINDEX DESC LIMIT 1,1";
            
            rs = stmt.executeQuery(loginStatusSql);
            
            while(rs.next()){
                lblLastLoginTime.setText(rs.getString(1));
                lblLastLogoutTime.setText(rs.getString(2));
            }
            
        }catch(SQLException ex){
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
        
    }
    
    private void logout() throws IOException{
        con = ConnectionManager.getConnection();
        try {
            stmt = con.createStatement();
                    
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            
            String logOutTimeSql = "UPDATE userLOG SET LOGOUT ='"+timestamp+"' WHERE SID='"+SID+"' and LOGIN='"+loginTime+"'";
            stmt.executeUpdate(logOutTimeSql);
            
            //BackTo Login Window
            btnLogOut.getScene().getWindow().hide();
            Stage login = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/superIMS/view/Login.fxml"));
            login.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
            login.setScene(scene);
            login.show();
                    
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
    }
    
    private void setSupplierStatus(){
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String suppliesStatusSql = "SELECT DSTATUS,PSTATUS,BATCHID FROM suppliesStatus";
            
            rs = stmt.executeQuery(suppliesStatusSql);
            
            int pending = 0;
            float outs = 0;
            
            while(rs.next()){
                if(rs.getString(1).equals("No")){
                    pending++;
                }
                if(rs.getString(2).equals("No") && rs.getString(1).equals("Yes")){
                    stmt2 = con.createStatement();
                    String outstandingSql = "SELECT AMOUNT FROM pendingSupplies WHERE BATCHID='"+rs.getString(3)+"'";
                    rs2 = stmt2.executeQuery(outstandingSql);
                    while(rs2.next()){
                        float amo = rs2.getFloat(1);
                        outs+=amo;
                    }
                }
            }
            
            lblSupPending.setText(Integer.toString(pending));
            lblSupOuts.setText(Float.toString(outs));
            
        }catch(SQLException ex){
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

    }
    
    private void setUserSumStatus(){
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String userNumberSql = "SELECT * from staff";
            
            rs = stmt.executeQuery(userNumberSql);
            
            int numU = 0;
            int comp = 0;
            
            while(rs.next()){
                numU++;
            }
            
            lblNumberEmp.setText(Integer.toString(numU));
            
            String compNumberSql = "SELECT * from staffComplaints";
            
            rs = stmt.executeQuery(compNumberSql);
            
            while(rs.next()){
                comp++;
            }
            
            lblComplaints.setText(Integer.toString(comp));
            
        }catch(SQLException ex){
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
    }
    
    private void setReportStatus(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDay = sdf.format(dt);
        
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String numberOfSalesSql = "SELECT * from Bill";
            
            rs = stmt.executeQuery(numberOfSalesSql);
            
            int numSales = 0;
            float amo = 0;
            
            while(rs.next()){
                if(rs.getString("DATE").equals(currentDay)){
                    numSales++;
                    amo+=rs.getFloat("AMOUNT");
                }
            }
            lblNumberSales.setText(Integer.toString(numSales));
            lblSaleAmount.setText(Float.toString(amo));
            
        }catch(SQLException ex){
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
    }
    
      @FXML
    void complaintAction(ActionEvent event) {
        pnlSuppliers.setVisible(false);
        pnlReports.setVisible(false);
        pnlRegUser.setVisible(false);            
        pnlDashboard.setVisible(false);
        pnlStaffDetails.setVisible(false);
        pnlComplaints.setVisible(true);
        pnlSupplierRegistration.setVisible(false);
        pnlComplaints.toFront();

        con = ConnectionManager.getConnection();
        data1 = FXCollections.observableArrayList();

        try{
            stmt = con.createStatement();

            String compSql = "SELECT * FROM staffComplaints";

            rs = stmt.executeQuery(compSql);

            while(rs.next()){
                data1.add(new Complaint(rs.getString("COMPID"), rs.getString("SID"), rs.getString("COMPLAINT")));
            }

            colCOMPID.setCellValueFactory(new PropertyValueFactory<>("COMPID"));
            colCOMPSID.setCellValueFactory(new PropertyValueFactory<>("SID"));
            colComplaint.setCellValueFactory(new PropertyValueFactory<>("COMPLAINT"));
            
            tableComplaints.setItems(data1);

            
        }catch(SQLException ex){
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
    }

    @FXML
    void complaintBackAction(ActionEvent event) {
        pnlSuppliers.setVisible(false);
        pnlReports.setVisible(false);
        pnlRegUser.setVisible(false);            
        pnlDashboard.setVisible(false);
        pnlStaffDetails.setVisible(true);
        pnlComplaints.setVisible(false);
        pnlSupplierRegistration.setVisible(false);
        pnlStaffDetails.toFront();
    }
    
    @FXML
    void handleRegPanelButtons(ActionEvent event) {
        if(event.getSource()==btnGenSID){
            String genSID = null;
            if(cbxDesi.getValue().equals("Admin")){
                genSID = "KUADMIN"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Manager")){
                genSID = "KUMANAG"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Accountant")){
                genSID = "KUACCOU"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("MLT")){
                genSID = "KUMLTEC"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Butcher")){
                genSID = "KUBUTCH"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Cashier")){
                genSID = "KUCASHI"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Customer Help/F")){
                genSID = "KUCUHPF"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Customer Help/")){
                genSID = "KUCUHPP"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Cleaning")){
                genSID = "KUCLEAN"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Security")){
                genSID = "KUSECUR"+cbxBranch.getValue();
            }else if(cbxDesi.getValue().equals("Welcomer")){
                genSID = "KUWELCO"+cbxBranch.getValue();
            }
            txtSID.setText(genSID);
        }
        else if(event.getSource()==btnGenPWD){
            txtPWD.setText(txtFName.getText()+"112233");
        }else if(event.getSource()==btnRegNext){
            String gender = null;
            if(radMale.isSelected()){
                gender = "M";
            }else if(radFemale.isSelected()){
                gender = "F";
            }
            
            //Insert User
            con = ConnectionManager.getConnection();
            try{
                stmt = con.createStatement();

                String userInsSql = "INSERT INTO staff(SID,SFNAME,SLNAME,GENDER,SADDRESS,SPHNO,DEPTID,DESIGNATION,SPWD) VALUES('"+txtSID.getText()+"','"+txtFName.getText()+"','"+txtLName.getText()+"','"+gender+"','"+txtAddress.getText()+"',"+txtTel.getText()+",'"+cbxDept.getValue()+"','"+cbxDesi.getValue()+"','"+txtPWD.getText()+"')";

                int status = stmt.executeUpdate(userInsSql);
                
                if(status>0){
                    Notifications noti = Notifications.create()
                            .title("Registartion Success")
                            .text("User "+txtFName.getText()+" added succesfully")
                            .graphic(null)
                            .hideAfter(Duration.seconds(20))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println("Close Noti");
                            }
                            });
                
                    noti.showConfirm();
                    
                    txtFName.setText(null);
                    txtLName.setText(null);
                    txtAddress.setText(null);
                    txtPWD.setText(null);
                    txtSID.setText(null);
                    txtTel.setText(null);
                    
                }
                
                

            }catch(SQLException ex){
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
        }
    }
    
    private void setRegistration(){
        con = ConnectionManager.getConnection();
        data2 = FXCollections.observableArrayList();
        data3 = FXCollections.observableArrayList();
        data4 = FXCollections.observableArrayList();
        
        

        try{
            stmt = con.createStatement();

            String deptidSql = "SELECT DISTINCT DEPTID FROM department";
            
            //Role bases Designation Selection
            String desiSql = null;
            if(this.Desi.equals("Super Admin")){
                desiSql = "SELECT DISTINCT DESIGNATION FROM department WHERE DESIGNATION !='Super Admin'";
            }else if(this.Desi.equals("Admin")){
                desiSql = "SELECT DISTINCT DESIGNATION FROM department WHERE DESIGNATION !='Super Admin' AND DESIGNATION != 'Admin'";
            }
            String branchSql = "SELECT BRANCHID FROM branches";
            

            rs = stmt.executeQuery(deptidSql);

            while(rs.next()){
                //data2.add(new Department(rs.getString("DEPTID"), rs.getString("DESIGNATION"), rs.getString("DESCRIPTION")));
                data2.add(rs.getString("DEPTID"));
            }

            cbxDept.getItems().addAll(data2);
            
            rs = stmt.executeQuery(desiSql);
            
            while(rs.next()){
                data3.add(rs.getString("DESIGNATION"));
            }
            
            cbxDesi.getItems().addAll(data3);
            
            rs = stmt.executeQuery(branchSql);
            
            while(rs.next()){
                data4.add(rs.getString(1));
            }
            
            cbxBranch.getItems().addAll(data4);
            
        }catch(SQLException ex){
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
    }
    
     @FXML
    void selectPic(ActionEvent event) {
        //Path to;
        //Path from;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select A Picture");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Images", "*.*"),
            new FileChooser.ExtensionFilter("JPG", "*.jpg"),
            new FileChooser.ExtensionFilter("GIF", "*.gif"),
            new FileChooser.ExtensionFilter("BMP", "*.bmp"),
            new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(new Stage());
        //Files.copy(file.toPath(), "src/com/superIMS/assests/imgs/spics/");
        if (file != null) {
            //from = Paths.get(file.toURI().toString());
            //to = Paths.get("Your destination path" + file.getName());
            //Files.copy(from.toFile(), to.toFile(),REPLACE_EXISTING, COPY_ATTRIBUTES,NOFOLLOW_LINKS);
        }
    }
    
    @FXML
    void chartCreation(ActionEvent event) {
        chartCreationGeneral();
    }
    
    private void chartCreationGeneral(){
        dailySalesChart.getData().clear();
        XYChart.Series<String,Float> series = new XYChart.Series<>();
        /*series.getData().add(new XYChart.Data<String, Float>("2018-03-20", 15000.0F));
        series.getData().add(new XYChart.Data<String, Float>("2018-03-21", 5000.0F));
        series.getData().add(new XYChart.Data<String, Float>("2018-03-22", 1000.0F));
        series.setName("Daily Sales");*/
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();

            String chartSql = "SELECT DATE,sum(AMOUNT) FROM bill GROUP BY DATE";
            
            rs = stmt.executeQuery(chartSql);

            while(rs.next()){
                series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getFloat(2)));
            }
            series.setName("Daily Sales");
            dailySalesChart.getData().add(series);
                        
        }catch(SQLException ex){
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
        
    }
    
    @FXML
    void handleSupplierPanelAction(ActionEvent event) {
        if(event.getSource()==btnSupDetailsTable){
            setSupplierTable();
        }else if (event.getSource()==btnSupReg){
            pnlSuppliers.setVisible(false);
            pnlReports.setVisible(false);
            pnlRegUser.setVisible(false);            
            pnlDashboard.setVisible(false);
            pnlComplaints.setVisible(false);
            pnlStaffDetails.setVisible(false);
            pnlSupplierRegistration.setVisible(true);
            pnlSupplierRegistration.toFront();
        }
    }
    
    private void setSupplierTable(){
        pnlSuppliers.setVisible(true);
        pnlReports.setVisible(false);
        pnlRegUser.setVisible(false);            
        pnlDashboard.setVisible(false);
        pnlComplaints.setVisible(false);
        pnlStaffDetails.setVisible(false);
        pnlSupplierRegistration.setVisible(false);
        pnlSuppliers.toFront();

        con = ConnectionManager.getConnection();
        data5 = FXCollections.observableArrayList();

        try{
            stmt = con.createStatement();

            String supSql = "SELECT * FROM supplier";

            rs = stmt.executeQuery(supSql);

            while(rs.next()){
                data5.add(new Supplier(rs.getString("SUPID"), rs.getString("SUPNAME"), rs.getString("SUPADDRESS"), rs.getString("SUPTEL")));
            }

            colSUPID.setCellValueFactory(new PropertyValueFactory<>("SUPID"));
            colSUPNAME.setCellValueFactory(new PropertyValueFactory<>("SUPNAME"));
            colSUPADD.setCellValueFactory(new PropertyValueFactory<>("SUPADDRESS"));
            colSUPTEL.setCellValueFactory(new PropertyValueFactory<>("SUPTEL"));
            

            tableSupplierDetails.setItems(data5);


        }catch(SQLException ex){
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
    }
}
