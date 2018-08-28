/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulate.pkgnew;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nasofx.PlotProbability;

/**
 *
 * @author AMW Design PC 4
 */
public class ArticulateNew extends Application {
    Stage classstage =new Stage();
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        classstage=stage;
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void setStage(Stage stage) {
       classstage=stage;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public Stage getStage() {
        return classstage;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     /*
    public void show() throws IOException{
         Stage classstage = this.getStage();
           Platform.runLater(new Runnable(){
           @Override
           public void run() {
           //Parent root = null;
         FXMLLoader  primaryLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
               try {
             Parent   textAreaHolder = (Parent) primaryLoader.load();
              Scene scene = new Scene(textAreaHolder);
              classstage.setScene(scene);
              classstage.show();
               } catch (IOException ex) {
                   Logger.getLogger(PlotProbability.class.getName()).log(Level.SEVERE, null, ex);
               }
       
        //,530,735, true, SceneAntialiasing.BALANCED);
       

       // scene.getStylesheets().add("myStyleSheet.css");
        
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
    }
     */
     
     
     
     
     
}
