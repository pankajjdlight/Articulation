/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmodule;

import java.awt.Color;
import static java.awt.Color.RED;
import static java.awt.Color.blue;
import static java.awt.Color.red;
import java.io.IOException;
//import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.BorderFactory;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


/**
 *
 * @author IITG
 */
public class FXMLDocumentController implements Initializable {
    
    
    ObservableList<String> genderitems = FXCollections.observableArrayList("Male","Female","Others");
    
    @FXML
    private Label label;
    
    @FXML
    private ComboBox<String> gcombox;
    
      @FXML
    private TextField fnametxt;

    @FXML
    private TextField mnametxt;

    @FXML
    private TextField lnametxt;

   

    @FXML
    private TextField unametxt;

    @FXML
    private PasswordField pwd;

    @FXML
    private PasswordField pwd1;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField phnumtxt;

    @FXML
    private TextField orgtxt;

    @FXML
    private TextField desigtxt;

     @FXML
    private TextArea addtxt;
     @FXML
    private Button reg;
     
     
      @FXML
    private Label fnamelbl;

    @FXML
    private Label lnamelbl;
    
    @FXML
    private Label genderlbl;


    @FXML
    private Label unamelbl;

    @FXML
    private Label pwdlbl;

    @FXML
    private Label pwd1lbl;

    @FXML
    private Label emaillbl;

    @FXML
    private Label phnumlbl;

    @FXML
    private Label orglbl;

    @FXML
    private Label desiglbl;

    @FXML
    private Label addlbl;

    Border bred = BorderFactory.createLineBorder(Color.RED,1);
    Border bblue = BorderFactory.createLineBorder(Color.BLUE,1);
    Border bgrey = BorderFactory.createLineBorder(Color.GRAY,1);
    
    
    private PreparedStatement pst;
    private PreparedStatement pst1;
    private PreparedStatement pst2;
    
    @FXML
    public void mnameclick(MouseEvent event) {
        if(fnametxt.getText().equals("")){
            fnametxt.setStyle("-fx-border-color: red;");
            fnamelbl.setStyle("-fx-text-fill: red;");
            fnamelbl.setText("You cant leave this empty!");
        
        
        }

    }
    
      @FXML
       public void fnameclick(MouseEvent event) {
            fnametxt.setStyle("-fx-border-color: transparent;");
             fnamelbl.setText(" ");
    }
       
     @FXML
    public void lnameclick(MouseEvent event) {
        
        lnametxt.setStyle("-fx-border-color: transparent;");
        lnamelbl.setText(" ");
        if(fnametxt.getText().equals("")){
            fnametxt.setStyle("-fx-border-color: red;");
            fnamelbl.setStyle("-fx-text-fill: red;");
            fnamelbl.setText("You cant leave this empty!");
        
        
        }

    }
     @FXML
    void gcomclick(MouseEvent event) {
        gcombox.setStyle("-fx-border-color: transparent;");
        genderlbl.setText(" ");
        if(lnametxt.getText().equals("")){
            lnametxt.setStyle("-fx-border-color: red;");
            lnamelbl.setStyle("-fx-text-fill: red;");
            lnamelbl.setText("You cant leave this empty!");
        
        
        }

    }
    
    @FXML
    void unameclick(MouseEvent event) {
        unametxt.setStyle("-fx-border-color: transparent;");
        unamelbl.setText(" ");
        if(lnametxt.getText().equals("")){
            lnametxt.setStyle("-fx-border-color: red;");
            lnamelbl.setStyle("-fx-text-fill: red;");
            lnamelbl.setText("You cant leave this empty!");
        
        
        }

    }
    
    @FXML
    void pwdclick(MouseEvent event) {
        pwd.setStyle("-fx-border-color: transparent;");
        pwdlbl.setText(" ");
        if(unametxt.getText().equals("")){
            unametxt.setStyle("-fx-border-color: red;");
            unamelbl.setStyle("-fx-text-fill: red;");
            unamelbl.setText("You cant leave this empty!");
            }
        else{
        Connection conn = LoginModule.connect();
        String query = "SELECT * FROM Therapist_Login_Table WHERE Username = ?";
        try {
            pst = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            pst.setString(1, unametxt.getText());
        
      
        ResultSet rs = pst.executeQuery();
        int count =0;
         while(rs.next()){
            count++;
        }
         if(count==1){
              
           unametxt.setStyle("-fx-border-color: red;");
           unamelbl.setStyle("-fx-text-fill: red;");
           unamelbl.setText("Username already taken");
          
          }
          else{
           unametxt.setStyle("-fx-border-color: transparent;");
           unamelbl.setStyle("-fx-text-fill: blue;");
           unamelbl.setText("Username available");
         
          }
        
        
        
        }
        catch (SQLException e){
        e.printStackTrace();
        
        
        }
        
        }
        
        

    }
    
    @FXML
    void pwd1click(MouseEvent event) {
        pwd1.setStyle("-fx-border-color: transparent;");
        pwd1lbl.setText(" ");
        if(pwd.getText().equals("")){
            pwd.setStyle("-fx-border-color: red;");
            pwdlbl.setStyle("-fx-text-fill: red;");
            pwdlbl.setText("You cant leave this empty!");
        
        
        }

    }
    
      @FXML
    void emailclick(MouseEvent event) {
        emailtxt.setStyle("-fx-border-color: transparent;");
        emaillbl.setText(" ");
        if(pwd1.getText().equals("")){
            pwd1.setStyle("-fx-border-color: red;");
            pwd1lbl.setStyle("-fx-text-fill: red;");
            pwd1lbl.setText("You cant leave this empty!");
        }
        else if(!(pwd.getText().equals(pwd1.getText()))){
        
            pwd1lbl.setStyle("-fx-text-fill: red;");
            pwd1lbl.setText("*Passwords do not match!");
        }

    }
    
      @FXML
    void phnumclick(MouseEvent event) {
        phnumtxt.setStyle("-fx-border-color: transparent;");
        phnumlbl.setText(" ");
        if(emailtxt.getText().equals("")){
            emailtxt.setStyle("-fx-border-color: red;");
            emaillbl.setStyle("-fx-text-fill: red;");
            emaillbl.setText("You cant leave this empty!");
        
        
        }
        

    }
    
     @FXML
    void orgclick(MouseEvent event) {
        orgtxt.setStyle("-fx-border-color: transparent;");
        orglbl.setText(" ");
        if(phnumtxt.getText().equals("")){
            phnumtxt.setStyle("-fx-border-color: red;");
            phnumlbl.setStyle("-fx-text-fill: red;");
            phnumlbl.setText("You cant leave this empty!");
        
        
        }
        

    }
    
     @FXML
    void desigclick(MouseEvent event) {
        desigtxt.setStyle("-fx-border-color: transparent;");
        desiglbl.setText(" ");
        if(orgtxt.getText().equals("")){
            orgtxt.setStyle("-fx-border-color: red;");
            orglbl.setStyle("-fx-text-fill: red;");
            orglbl.setText("You cant leave this empty!");
        
        
        }

    }
    
      @FXML
    void addclick(MouseEvent event) {
       addtxt.setStyle("-fx-border-color: transparent;");
       addlbl.setText(" ");
        if(desigtxt.getText().equals("")){
            desigtxt.setStyle("-fx-border-color: red;");
            desiglbl.setStyle("-fx-text-fill: red;");
            desiglbl.setText("You cant leave this empty!");
        
        
        }

    }

     @FXML
    void resetaction(ActionEvent event) {
        fnametxt.setText("");
        mnametxt.setText("");
        lnametxt.setText("");
        unametxt.setText("");
        pwd.setText("");
        pwd1.setText("");
        emailtxt.setText("");
        phnumtxt.setText("");
        orgtxt.setText("");
        desigtxt.setText("");
        addtxt.setText("");
        gcombox.getSelectionModel().selectFirst();
        
        fnamelbl.setText("");
        lnamelbl.setText("");
        unamelbl.setText("");
        pwdlbl.setText("");
        pwd1lbl.setText("");
        emaillbl.setText("");
        phnumlbl.setText("");
        orglbl.setText("");
        desiglbl.setText("");
        addlbl.setText("");
        genderlbl.setText("");
        
        fnametxt.setStyle("-fx-border-color: transparent;");
        lnametxt.setStyle("-fx-border-color: transparent;");
        unametxt.setStyle("-fx-border-color: transparent;");
        pwd.setStyle("-fx-border-color: transparent;");
        pwd1.setStyle("-fx-border-color: transparent;");
        emailtxt.setStyle("-fx-border-color: transparent;");
        phnumtxt.setStyle("-fx-border-color: transparent;");
        orgtxt.setStyle("-fx-border-color: transparent;");
        desigtxt.setStyle("-fx-border-color: transparent;");
        addtxt.setStyle("-fx-border-color: transparent;");

    }
    


    
      @FXML
    void initialize(ActionEvent event) throws IOException {
          Connection conn = LoginModule.connect();
      
          if(addtxt.getText().equals("")){
            addtxt.setStyle("-fx-border-color: red;");
            addlbl.setStyle("-fx-text-fill: red;");
            addlbl.setText("You cant leave this empty!");
           }
          
          
          else{ 
          try{
           // conn = SqliteJDBCconnection.connect();
        String query = "INSERT INTO Therapist_Login_Table(Username,Password) values(?,?)";
         pst = conn.prepareStatement(query);
        pst.setString(1, unametxt.getText());
        pst.setString(2, pwd.getText());
        
        String query1 = "INSERT INTO Therapist_Info_Table(FName,MName,LName,email,Phone_Num,Address,Gender,Profile,Org_Name) values(?,?,?,?,?,?,?,?,?)";
        pst1 = conn.prepareStatement(query1);
        pst1.setString(1,fnametxt.getText());
        pst1.setString(2,mnametxt.getText());
        pst1.setString(3,lnametxt.getText());
        pst1.setString(4,emailtxt.getText());
        pst1.setString(5,phnumtxt.getText());
        pst1.setString(6,addtxt.getText());
        pst1.setString(7, gcombox.getValue());
        pst1.setString(8,desigtxt.getText());
        pst1.setString(9,orgtxt.getText());
        
        
        pst1.execute();
        pst.execute();
        String query2 = "INSERT INTO Therapist_Master_Table(FName,MName,LName,Username,Password,Email,PhoneNum,Address,Gender,Profile,Org_Name) values(?,?,?,?,?,?,?,?,?,?,?)";
        pst2 = conn.prepareStatement(query2);
        pst2.setString(1,fnametxt.getText());
        pst2.setString(2,mnametxt.getText());
        pst2.setString(3,lnametxt.getText());
        pst2.setString(4,unametxt.getText());
        pst2.setString(5,pwd.getText());
        pst2.setString(6,emailtxt.getText());
        pst2.setString(7, phnumtxt.getText());
        pst2.setString(8,addtxt.getText());
        pst2.setString(9,gcombox.getValue());
        pst2.setString(10,desigtxt.getText());
        pst2.setString(11,orgtxt.getText());
        
        pst2.execute();
        
        
        
      FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("/loginmodule/LOGINFXML.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      Stage classStage = new Stage();
      classStage.setResizable(false);
      classStage.setMaxWidth(522);
      classStage.setMaxHeight(550);
      classStage.setTitle(" Articulate+");
      Scene scene = new Scene(textAreaHolder);
      classStage.setScene(scene);
      classStage.centerOnScreen();
      classStage.show();
              
        
        
        
        
        
        
        
        
        
        
             
        }
        catch (SQLException e){
        e.printStackTrace();
        }
          } 

    }
   
    

    


    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //gcombox.setValue("Male");
       gcombox.setItems(genderitems);
       gcombox.getSelectionModel().selectFirst();
      // System.setProperty("prism.lcdtext", "false");
    
        
        
    }    
    
}
