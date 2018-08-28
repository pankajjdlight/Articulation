/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulate;

//import static articulate.FirstpageController.classStage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.stage.Stage;

/**
 *
 * @author AMW Design PC 2
 */
public class Articulate extends Application {
      Scene scene;
      public static Stage classStage = new Stage();
     FXMLLoader primaryLoader;
     String path="",wavename="";
     public  Media pick; 
     public  TranslateTransition trans;
   //  HBox hbox=new HBox();
  //   SecondpageController second=new SecondpageController(); 
     
     public void setPath(String Path){
       path=Path;  
     }
     public String getPath()
     {
         return path;
     }
     
    public void setstageStage(Stage stage){
    classStage=stage;
    } 
    
    public Stage getstageStage(){
    return classStage;
    } 
     Media getpick(){
     return pick;
     }
     void setpick(String file) throws MalformedURLException{
         System.out.println("bbbb"+file);
        
         pick = new Media(new File(file).toURI().toURL().toExternalForm());
         trans= new TranslateTransition();
     }
    @Override
    public void start(Stage stage) throws Exception {
      classStage=stage;
      primaryLoader = new FXMLLoader(getClass().getResource("Firstpage.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      stage.setResizable(false);
      stage.setMaxWidth(663);
      stage.setMaxHeight(629);
      stage.setTitle(" Articulate+");
      scene = new Scene(textAreaHolder);
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();
    
    }

    
    
   void secondpageloader() throws IOException
   {
       
       String currentDir = System.getProperty("user.dir");
       String cexedir = currentDir + "\\Articulate+\\";
       System.out.println("cexedir"+cexedir);
       primaryLoader = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       classStage.setResizable(false);
       classStage.setMaxWidth(700);
       classStage.setMaxHeight(725);
       classStage.setTitle(" Articulate+");
       scene = new Scene(textAreaHolder);
       classStage.setScene(scene);
       classStage.centerOnScreen();
       classStage.show();
       
       
   }
    
   void setwavefilename(String name){
    wavename=name;   
   }
   
   String getwavefilename(){
    return wavename;   
   }
   
   
   
   
   void evaluate() throws IOException
   {
   
   
       primaryLoader = new FXMLLoader(getClass().getResource("Thirdpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       classStage.setResizable(false);
       classStage.setMaxWidth(632);
       classStage.setMaxHeight(685);
       classStage.setTitle(" Articulate+");
       scene = new Scene(textAreaHolder);
       classStage.setScene(scene);
       classStage.centerOnScreen();
       classStage.show();
   
   
   
   }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
    
}
