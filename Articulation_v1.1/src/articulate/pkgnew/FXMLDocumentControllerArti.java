
package articulate.pkgnew;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import nasofx.PlotProbability;

/**
 *
 * @author Pankaj Shramah,IITG,Articulate+ Project,Sponsored by MHRD,DBT,Govt. of India
 */
public class FXMLDocumentControllerArti implements Initializable {
    ArticulateNew  arti=new ArticulateNew ();
    @FXML
     final Tooltip playtip = new Tooltip();
     final Tooltip pausetip = new Tooltip();
     final Tooltip forwardtip = new Tooltip();
     final Tooltip rewindtip = new Tooltip();
     final Tooltip stoptip = new Tooltip();
     final Tooltip recordtip = new Tooltip();
     public  Media pick;  
     public MediaPlayer player ;
    @FXML
    private ToolBar toolbar;
    @FXML
    private AnchorPane recordPane;
    @FXML
    private Button playbtn;
    @FXML
    private Button stopbtn;
    @FXML
    private Button file;

    @FXML
    private Button forwardbtn;
    
    @FXML
    private Button recordbtn;

    @FXML
    private Button rewindbtn;
    
    @FXML
    private HBox Imagedisplay;
    @FXML
    private Label first_image;

    @FXML
    private Label second_image;
    public static int fileplay=0;
    @FXML
    public void onclickHindiInitial(ActionEvent event) throws FileNotFoundException{
        fileplay=1;
        file.setVisible(true);
        playbtn.setVisible(true);
        stopbtn.setVisible(true);
        forwardbtn.setVisible(true);
        rewindbtn.setVisible(true);
        recordbtn.setVisible(true);
        Imagedisplay.getChildren().clear();
        String current = System.getProperty("user.dir");
        String folder=current +"\\Articulate+\\Hindi\\aa\\initial";
        String image1=folder+"\\aag.jpeg";
        String image2=folder+"\\aa.png";
        System.out.println("path"+folder);
        FileInputStream input1 = new FileInputStream(image1);
        FileInputStream input2 = new FileInputStream(image2);
        Image image11 = new Image(input1);
        Image image22 = new Image(input2);
        ImageView imageView1 = new ImageView(image11);
        ImageView imageView2 = new ImageView(image22);
        Imagedisplay.getChildren().addAll(imageView1,imageView2);
     
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
             first_image.setText("aag");//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             second_image.setText("Lateral view");
            }
        });
    
        
    
    }
    
        @FXML
    public void onclickHindiFinal(ActionEvent event) throws FileNotFoundException{
       
        fileplay=2;
        file.setVisible(true);
        playbtn.setVisible(true);
        stopbtn.setVisible(true);
        forwardbtn.setVisible(true);
        rewindbtn.setVisible(true);
        recordbtn.setVisible(true);
        Imagedisplay.getChildren().clear();
        String current = System.getProperty("user.dir");
        String folder=current +"\\Articulate+\\Hindi\\aa\\final";
        String image1=folder+"\\kutta.jpeg";
        String image2=folder+"\\aa.png";
        System.out.println("path"+folder);
        FileInputStream input1 = new FileInputStream(image1);
        FileInputStream input2 = new FileInputStream(image2);
        Image image11 = new Image(input1);
        Image image22 = new Image(input2);
        ImageView imageView1 = new ImageView(image11);
        ImageView imageView2 = new ImageView(image22);
        Imagedisplay.getChildren().addAll(imageView1,imageView2);
     
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
             first_image.setText("kutta");//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             second_image.setText("Lateral view");
            }
        });
    
    }
    
        @FXML
    public void onclickHindiMedial(ActionEvent event) throws FileNotFoundException{
      
        fileplay=3;
        file.setVisible(true);
        playbtn.setVisible(true);
        stopbtn.setVisible(true);
        forwardbtn.setVisible(true);
        rewindbtn.setVisible(true);
        recordbtn.setVisible(true);
        Imagedisplay.getChildren().clear();
        String current = System.getProperty("user.dir");
        String folder=current +"\\Articulate+\\Hindi\\aa\\medial";
        String image1=folder+"\\kaala.jpg";
        String image2=folder+"\\aa.png";
        System.out.println("path"+folder);
        FileInputStream input1 = new FileInputStream(image1);
         FileInputStream input2 = new FileInputStream(image2);
        Image image11 = new Image(input1);
        Image image22 = new Image(input2);
        ImageView imageView1 = new ImageView(image11);
        ImageView imageView2 = new ImageView(image22);
        Imagedisplay.getChildren().addAll(imageView1,imageView2);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
             first_image.setText("kaala");//   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             second_image.setText("Lateral view");
            }
        });
        
        
        
    
    }
     @FXML
    public void onclickEnglish(ActionEvent event) throws FileNotFoundException{
       
        Imagedisplay.getChildren().clear();
        String current = System.getProperty("user.dir");
        String folder=current +"\\Articulate+\\"+"English\\"+"photo2.jpg";
        System.out.println("path"+folder);
        FileInputStream input = new FileInputStream(folder);
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Imagedisplay.getChildren().add(imageView);
    
    
    }
    @FXML
    public void closerecordwindow(ActionEvent event)
    {
 
        recordPane.setTranslateX(700);
    
    }    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        file.setVisible(false);
        playbtn.setVisible(false);
        stopbtn.setVisible(false);
        forwardbtn.setVisible(false);
        rewindbtn.setVisible(false);
        recordbtn.setVisible(false);
        recordPane.setTranslateX(784);
        

    }    
 
    @FXML
    void forwardSound(ActionEvent event) {
    
        player.seek(player.getCurrentTime().multiply(.5));
    }
    
    
    @FXML
    void pauseSound(ActionEvent event) {
     
        MediaPlayer.Status status = player.getStatus();
                 
         if (status == MediaPlayer.Status.PLAYING )
         {
          //    Image playimage = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\play-arrow-(1).png",15,17,false,false);
              player.stop();
            //  trans.stop();
             // resetmedia();
              playtip.setText("play");
              playbtn.setTooltip(playtip);
             // playbtn.setGraphic(new ImageView(playimage));
              
         }

    }

    @FXML
    void playSound(ActionEvent event) throws MalformedURLException {
       
        
        
        if(fileplay==1){
        String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\medial\\kaala.wav";
      //  String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\medial\\gagan_s4.wav";
        
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        }else if(fileplay==2){
        String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\initial\\aag.wav";
      //  String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\final\\sahana_s1.wav";
        
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        }
        else if(fileplay==3){
        String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\final\\kutta.wav";
       // String filename="C:\\Users\\AMW Design PC 4\\Documents\\NetBeansProjects\\Articulate+New\\Articulate+\\Hindi\\aa\\initial\\hemanth_s6.wav";
        
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());          
        }
        
        
        player = new MediaPlayer(pick);
        MediaPlayer.Status status = player.getStatus();
                if(!(status == MediaPlayer.Status.PLAYING) && !(status == MediaPlayer.Status.PAUSED)) {
                player.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                ///later remove comment                      resetmedia();
                playtip.setText("play");
                playbtn.setTooltip(playtip);
               
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
        // trans.pause();
         playtip.setText("play");
         playbtn.setTooltip(playtip);
   //      playbtn.setGraphic(new ImageView(pause));
      //   playbtn.setGraphic(new ImageView(playimage));
         
         }
         else if(status == MediaPlayer.Status.PAUSED){
                player.play();
       /*
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
         Duration oldValue, Duration newValue) {
       
         double a = player.getCurrentTime().toMillis();
      int aa = (int)a;
       
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
              
       //   milisec.setText(mm);
      //    sec.setText(ss);
          
           
   }
        });
          */
            //    trans.play();
                
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
          //      playbtn.setGraphic(new ImageView(pause));
               player.setOnEndOfMedia(new Runnable() {
               @Override public void run() {
                //later remove comment                       resetmedia();
                System.out.println("M ON");
                playtip.setText("play");
                playbtn.setTooltip(playtip);
   //             playbtn.setGraphic(new ImageView(playimage));
            }
                 });
                 }
         
     }
    
        
        
        
        

    

    @FXML
    void rewindSound(ActionEvent event) {
        
       player.seek(player.getCurrentTime().divide(1.5));
    }

    @FXML
    void record(ActionEvent event){
   
       TranslateTransition openNav=new TranslateTransition(new Duration(350), recordPane);
       openNav.setToX(0);
       openNav.play();
    
    
    }
   
    @FXML
    void redirect(ActionEvent event) throws IOException {
           Stage stage = arti.getStage();
           Platform.runLater(new Runnable(){
           @Override
           public void run() {
           //Parent root = null;
         FXMLLoader  primaryLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
               try {
             Parent   textAreaHolder = (Parent) primaryLoader.load();
              Scene scene = new Scene(textAreaHolder);
              stage.setScene(scene);
              stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(PlotProbability.class.getName()).log(Level.SEVERE, null, ex);
               }
       
        //,530,735, true, SceneAntialiasing.BALANCED);
       

       // scene.getStylesheets().add("myStyleSheet.css");
        
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
        
      // arti.show();

    }
    
    
}
