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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import loginmodule.LoginModule;

/**
 *
 * @author AMW Design PC 2
 */
public class Articulate extends Application {
     public static String filenamere="";
      Scene scene;
      public static Stage classStage = new Stage();
     FXMLLoader primaryLoader;
     String path="";
     static String wavename="";
     public  Media pick; 
     public  TranslateTransition trans;
     public static double Is_record=0;
  public static double is_SaveAs_done=0;
  Getfield gt =new Getfield();
  public static int uu_Initial=0,uu_Medial=0,uu_Final=0;
    public static int Initial=0,Medial=0,Final=0;
  public static int iadd=0,isubb=0,iomm=0,idiss=0,madd=0,msubb=0,momm=0,mdiss=0,fadd=0,fsubb=0,fomm=0,fdiss=0;
  public static String ival="",mval="",fval="";
  public static int COUNT=0,count=0;
   //  HBox hbox=new HBox();
  //   SecondpageController second=new SecondpageController(); 
     
     public void setPath(String Path){
       path=Path;  
     }
     public String getPath()
 
 
     {
         return path;
     }
       public void setfilename(String file){
        String fill=file;
        String dirName =System.getProperty("user.dir");
        filenamere=dirName+fill;
        
        
        
        
        
         //System.out.println("xxxx"+filenamere);
     }
     public String getfilename() throws SQLException{
        String dirName =System.getProperty("user.dir");
        Connection conn = LoginModule.connect();
        String folder="";
        PreparedStatement ps;
        ps = conn.prepareStatement( "SELECT * FROM Patient_Register_Table WHERE Pid = ?" );
                ps.setString(1, gt.getpatientId());
                ResultSet result = ps.executeQuery();
                while( result.next() ) {
                   folder=result.getString("Folder_id");
                }
        filenamere=dirName+folder;   
   
   
         return filenamere;
         
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
     // Stage classStage = new Stage();
      classStage=stage;
      
      FXMLLoader primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
      Parent textAreaHolder = (Parent) primaryLoader.load();
      stage.setResizable(false);
      stage.setMaxWidth(800);
      stage.setMaxHeight(600);
      stage.setTitle(" Articulate+");
      Scene scene = new Scene(textAreaHolder);
      
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.show();
    
    }

    
    
   void secondpageloader() throws IOException
   {
       
       String currentDir = System.getProperty("user.dir");
       String cexedir = currentDir + "\\Articulate+\\";
       System.out.println("cexedir"+cexedir);
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Secondpage.fxml"));  
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
   
   
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Thirdpage.fxml"));  
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
