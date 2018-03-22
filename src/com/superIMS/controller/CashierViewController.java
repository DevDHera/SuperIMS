/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.superIMS.model.BillItems;
import com.superIMS.model.Complaint;
import com.superIMS.model.ConnectionManager;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class CashierViewController implements Initializable {

    @FXML
    private JFXButton btnSideMenu;

    @FXML
    private JFXButton btnClose;

    @FXML
    private JFXButton btnMin;
    
    @FXML
    private Pane pnlSideMenu;
    
     @FXML
    private JFXButton btnLogOut;
     
    @FXML
    private ImageView staffPic;

    @FXML
    private Label lblURole;

    @FXML
    private Label lblUName;
    
    @FXML
    private Pane pnlView;

    @FXML
    private Label lblWelcomeMsg;

    @FXML
    private JFXButton btnBilling;
    
    @FXML
    private JFXButton btnNewBilling;
    
    @FXML
    private JFXButton btnDashBoard;
    
    @FXML
    private Pane pnlBilling;
    
    @FXML
    private Pane pnlDashbord;
    
    @FXML
    private JFXButton btnNextItem;

    @FXML
    private JFXButton btnCal;

    @FXML
    private JFXComboBox<String> cbxItems;

    @FXML
    private JFXTextField txtQty;
    
    @FXML
    private TableView<BillItems> tableItemCart;

    @FXML
    private TableColumn<BillItems, String> colItemName;

    @FXML
    private TableColumn<BillItems, String> colQty;

    @FXML
    private TableColumn<BillItems, String> colItemTot;

    
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
    
    
    //Bill Details
    private int BILLID = 0;
    private float priceperu = 0;
    private String itemId = null;
    private float remaninQty = 0;
    private float totalBill = 0;
    
    //OBList
    private ObservableList<String>data;
    private ObservableList<BillItems>data1;
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
        btnMin.setStyle(IDLE_BUTTON_STYLE);
        btnMin.setOnMouseEntered(e -> btnMin.setStyle(HOVERED_BUTTON_STYLE_MIN));
        btnMin.setOnMouseExited(e -> btnMin.setStyle(IDLE_BUTTON_STYLE));
        
        pnlDashbord.setVisible(true);
        pnlBilling.setVisible(false);
        pnlDashbord.toFront();
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

    }
    
    public void setUserCredentials(String id, String name, String des, Timestamp t, String p){
        this.SID = id;
        this.FName = name;
        this.Desi = des;
        this.loginTime = t;
        this.spic = p;
        
        setStaffDetails();
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
    
     @FXML
    void logOutAction(ActionEvent event) throws IOException {
        logout();
    }
    
    private void setStaffDetails(){
        File file = new File("src/com/superIMS/assests/imgs/spics/"+spic);
        Image image = new Image(file.toURI().toString());
        staffPic.setImage(image);
        
        lblURole.setText(Desi);
        lblUName.setText(FName);
        lblWelcomeMsg.setText("Hi "+FName+"!, Keep Up the Good Work :)");
    }
    
     @FXML
    void billingAction(ActionEvent event) {
        //lblWelcomeMsg.setVisible(false);
        //btnBilling.setVisible(false);
        pnlDashbord.setVisible(false);
        pnlBilling.setVisible(true);
        pnlBilling.toFront();
        getBillID();
        loadItemCombo();
    }
    
    private void getBillID(){
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String loginStatusSql = "SELECT BILLID FROM bill ORDER BY BILLID DESC LIMIT 1";
            
            rs = stmt.executeQuery(loginStatusSql);
            
            while(rs.next()){
                BILLID = rs.getInt("BILLID");
            }
            System.out.println(BILLID);
            
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
    
    private void loadItemCombo(){
        con = ConnectionManager.getConnection();
        data = FXCollections.observableArrayList();
        
        try{
            stmt = con.createStatement();

            String itemSql = "SELECT ITEMID,ITEMDES FROM items";            
            
            rs = stmt.executeQuery(itemSql);

            while(rs.next()){
                //data2.add(new Department(rs.getString("DEPTID"), rs.getString("DESIGNATION"), rs.getString("DESCRIPTION")));
                data.add(rs.getString("ITEMDES"));
            }

            cbxItems.getItems().addAll(data);  
            
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
    void showDashbord(ActionEvent event) {
        pnlDashbord.setVisible(true);
        pnlBilling.setVisible(false);
        pnlDashbord.toFront();
    }
    
    @FXML
    void calculateAction(ActionEvent event) {
        con = ConnectionManager.getConnection();
        int id = BILLID;
        id++;
        try{
            stmt = con.createStatement();
            
            String totalBillSql = "SELECT SUM(PRICE) FROM billitems GROUP BY BILLID HAVING BILLID="+id;
            
            rs = stmt.executeQuery(totalBillSql);
            
            while(rs.next()){
                totalBill = rs.getFloat(1);
            }
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bill");
            alert.setHeaderText("Total Bill Amount");
            alert.setContentText("Rs. "+totalBill);
            alert.showAndWait();
            
            updateBill();
            
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
    
    private void updateBill(){
        Date dt = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String currentDay = sdf1.format(dt);
        String currentTime = sdf2.format(dt);
        
        con = ConnectionManager.getConnection();
        int id = BILLID;
        id++;
        try{
            stmt = con.createStatement();
            
            String upBillSql = "INSERT INTO bill(DATE,TIME,AMOUNT,SID,PMETHOD,INVOICEID) VALUES('"+currentDay+"','"+currentTime+"',"+totalBill+",'"+SID+"','Cash','"+id+"')";
            
            int c = stmt.executeUpdate(upBillSql);
            
            if(c>0){
                Notifications noti = Notifications.create()
                            .title("Inventory & Sales Updates")
                            .text("Bill : "+id+" Entered the DB")
                            .graphic(null)
                            .hideAfter(Duration.seconds(20))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println("Close Noti");
                            }
                            });
                
                    noti.showInformation();
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
    
    private boolean checkQty(){
        boolean status = false;
        //float remaninQty = 0;
        float threshold = 0;
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String qtySql = "SELECT QTY,THRESHOLD,PRICEPERUNIT,ITEMID FROM items WHERE ITEMDES='"+cbxItems.getValue()+"'";
            
            rs = stmt.executeQuery(qtySql);
            
            while(rs.next()){
                remaninQty = rs.getFloat("QTY");
                threshold = rs.getFloat("THRESHOLD");
                priceperu = rs.getFloat("PRICEPERUNIT");
                itemId = rs.getString("ITEMID");
            }
            //System.out.println(BILLID);
            if(remaninQty-Float.parseFloat(txtQty.getText())>=0){
                status=true;
                if(remaninQty-Float.parseFloat(txtQty.getText())<threshold){
                    Notifications noti = Notifications.create()
                            .title("Items Running Low")
                            .text("Item : "+cbxItems.getValue()+" quantity is runing low. Hurry order!!!")
                            .graphic(null)
                            .hideAfter(Duration.seconds(20))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println("Close Noti");
                            }
                            });
                
                    noti.showWarning();
                }
            }
            else{
                status=false;
                Notifications noti = Notifications.create()
                            .title("Not Enough Stock")
                            .text("Item : "+cbxItems.getValue()+" quantity is less than asked")
                            .graphic(null)
                            .hideAfter(Duration.seconds(20))
                            .position(Pos.TOP_RIGHT)
                            .onAction(new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent event){
                                System.out.println("Close Noti");
                            }
                            });
                
                    noti.showError();
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
        
        return status;
    }
    
    @FXML
    void nextItemAction(ActionEvent event) {
        if(checkQty()){
            //System.out.println("Devin is Awesome");
            updateBillItems();
        }
    }
    
    private void updateBillItems(){
        int bid = BILLID;
        bid++;
        float price = Float.parseFloat(txtQty.getText())*priceperu;
        
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String billItemsSql = "INSERT INTO billitems VALUES("+bid+",'"+itemId+"',"+txtQty.getText()+","+price+")";
            
            int c = stmt.executeUpdate(billItemsSql);
            
            if(c>0){
                //System.out.println("Awesome");
                updateQty();
                populateTableCart();
            }
            //System.out.println(BILLID);
            
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
    
    private void updateQty(){
        float newQty = remaninQty-Float.parseFloat(txtQty.getText());
        
        con = ConnectionManager.getConnection();
        try{
            stmt = con.createStatement();
            
            String upQtySql = "UPDATE items SET QTY="+newQty+" WHERE ITEMID='"+itemId+"'";
            
            int c = stmt.executeUpdate(upQtySql);
            
            if(c>0){
                System.out.println("Awesome");
                //updateQty();
                //populateTableCart();
            }
            //System.out.println(BILLID);
            
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
    
    private void populateTableCart(){
        con = ConnectionManager.getConnection();
        data1 = FXCollections.observableArrayList();

        try{
            stmt = con.createStatement();
            
            String cartSql = "SELECT * FROM billitems WHERE BILLID="+(BILLID+1);            
            
            rs = stmt.executeQuery(cartSql);

            while(rs.next()){
                data1.add(new BillItems(rs.getString("BILLID"), rs.getString("ITEMID"), rs.getString("QTY"), rs.getString("PRICE"), Float.toString(priceperu), cbxItems.getValue()));
            }

            colItemName.setCellValueFactory(new PropertyValueFactory<>("ITEMID"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("QTY"));
            //colPricePU.setCellValueFactory(new PropertyValueFactory<>("PRICEPERUNIT"));
            colItemTot.setCellValueFactory(new PropertyValueFactory<>("PRICE"));
            
            tableItemCart.setItems(data1);

            
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
