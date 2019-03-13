/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmodule;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IITG
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   
    @FXML
    private Button signupbtn;

    @FXML
    void signupclick(ActionEvent event) throws IOException {
    
      signupbtn.setCursor(Cursor.HAND);
        
     Stage stage1 = new Stage();
     Parent myPane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
    Scene scene = new Scene(myPane);
    stage1.setScene(scene);
    stage1.show();
    }
    
     private PreparedStatement pst;
     
     @FXML
    private JFXTextField unametxt;

   @FXML
    private JFXPasswordField pwdtxt;
    
       @FXML
    private Button loginbtn;
       
  
   @FXML
    void sme1(MouseEvent event) {
        signupbtn.setCursor(Cursor.HAND);

    }
    
      @FXML
    void lme2(MouseEvent event) {
        loginbtn.setCursor(Cursor.HAND);
    }

    @FXML
    void loginclick(ActionEvent event) {
        
          
         String query = "SELECT * FROM Therapist_Login_Table WHERE Username = ? and Password = ?";
        try {
            Connection conn = LoginModule.connect();
            pst = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
           // conn = SqliteJDBCconnection.connect();
        
        pst.setString(1, unametxt.getText());
        pst.setString(2, pwdtxt.getText());
      
        ResultSet rs = pst.executeQuery();
        int count =0;
        while(rs.next()){
            count++;
        }
          if(count==1){
          JOptionPane.showMessageDialog(null, "Login Successfull");
          }
          else{
          JOptionPane.showMessageDialog(null, "Invalid Login");
          }
        }
        catch (SQLException e){
        e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}