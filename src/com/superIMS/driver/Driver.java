/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.superIMS.driver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author USER
 */
public class Driver extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/superIMS/view/Login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);        
        primaryStage.show();
    }
    
    public static void main(String args[]){
        launch(args);
    }
}
