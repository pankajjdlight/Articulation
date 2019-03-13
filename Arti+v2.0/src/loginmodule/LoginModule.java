/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginmodule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 *
 * @author IITG
 */
public class LoginModule extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        
        Connection conn = LoginModule.connect();
        System.out.println("connection successfull");
        Parent root = FXMLLoader.load(getClass().getResource("/loginmodule/LOGINFXML.fxml"));//    "/loginmodule/LOGINFXML.fxml"       //LOGINFXML  PatientRegistration
        
        Scene scene = new Scene(root,522,550, true, SceneAntialiasing.BALANCED);///522,550
       
        
        
   

       // scene.getStylesheets().add("myStyleSheet.css");
        stage.setScene(scene);
        stage.setTitle("Articulate+");
        stage.show();
    }
    public static Connection connect() {
      
        try {
             String currentDir = System.getProperty("user.dir");
             String database_cexedir = currentDir + "\\sqlite\\Nasospeech.sqlite";
            // db parameters
         //   String url = "jdbc:sqlite:C:\\sqlite\\Nasospeech.sqlite";
         
            String url = "jdbc:sqlite:"+database_cexedir;
            
            // create a connection to the database
         Connection conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to the Database has been established.");
           // JOptionPane.showMessageDialog(null,"Connection Successfull");
            
            return conn;
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        } 
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
