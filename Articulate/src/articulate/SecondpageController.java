package articulate;

import static articulate.Articulate.classStage;
import java.io.File;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SecondpageController {
    static int count=0;
    private AudioFormat format,format1; 
    Articulate arti=new Articulate();
     Capture cap = new Capture();
    String path="",wavename="";
     public  Media pick; 
     public  TranslateTransition trans;
    @FXML
    private Button loginbtn;

    @FXML
    private Button rewindbtn;

    @FXML
    private Button fwdbtn;

    @FXML
    private Button playbtn;

    @FXML
    private Button stopbtn;

    @FXML
    private Button recordbtn;
     @FXML
    private ImageView png;

    @FXML
    private ImageView jpeg;
      @FXML
    private Label pathlabel;
        @FXML
    private ImageView tif;

    
    @FXML
    private Pane pane;
    Scene scene;
    FXMLLoader primaryLoader;
String wavefilename = arti.getwavefilename();
public MediaPlayer player;
    @FXML
    void eavluate_result(ActionEvent event) throws IOException {
        
        
        
        
       Stage stage = (Stage) loginbtn.getScene().getWindow();
         stage.close();
         
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
     Media getpick(){
     return pick;
     }
     void setpick(String file) throws MalformedURLException{
         System.out.println("bbbb"+file);
        
         pick = new Media(new File(file).toURI().toURL().toExternalForm());
         trans= new TranslateTransition();
     }
     void setwavefilename(String name){
    wavename=name;   
   }
   
   String getwavefilename(){
    return wavename;   
   }
   

    @FXML
    void playsound(ActionEvent event) throws MalformedURLException {
          Media pick = this.getpick();
          player = new MediaPlayer(pick);
          MediaPlayer.Status status = player.getStatus();
          double d = pick.getDuration().toMillis();
          if(!(status == MediaPlayer.Status.PLAYING) && !(status == MediaPlayer.Status.PAUSED)) {
                player.play();
                 player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
   
   }
        });
                 player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                  } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
        }
         else if(status == MediaPlayer.Status.PAUSED){
          player.play();
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
         
   }
        });   player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                      } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
                 }
        
        
        
        
    }

    @FXML
    void recordsound(ActionEvent event) throws InterruptedException, UnsupportedAudioFileException, IOException {
        
                 String currentDir = System.getProperty("user.dir");
                 String cexedir = currentDir +"\\cexe\\";
                 String cexedi=currentDir+"\\Icons\\";
                 String image1="file:\\"+cexedi+"pausebtn.png";
                 String image2="file:\\"+cexedi+"record_1.png";
               //  TP.getSelectionModel().select(tab1);
                 Image pause = new Image(image1,27,27,false,false);
                 Image record = new Image(image2,27,27,false,false);
            
                  if(count%2==0){
         
                  recordbtn.setGraphic(new ImageView(pause));
                  count++;
        
                                 }
                  else
                {
         /////record stop////////////////    
                   cap.stop();
                   String filename =cexedir+"record.wav";
                   pick = new Media(new File(filename).toURI().toURL().toExternalForm());
                   player = new MediaPlayer(pick);
                   trans= new TranslateTransition();
                   //sendfilename(filename);
                   this.setwavefilename(filename);
          
                    recordbtn.setGraphic(new ImageView(record));
                      count++;
                    return;
         } 
                  String filename=cexedir+"sahana_s1.wav";
                  AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
                  format= audioInputStream.getFormat();
                  float freq = format.getSampleRate();
                  cap.start();
                     
                 
 /////////////////////////////end of the plotwave/////////////////               
    
    }

    @FXML
    void stopsound(ActionEvent event) throws MalformedURLException {
     MediaPlayer.Status status = player.getStatus();
                 
         if (status == MediaPlayer.Status.PLAYING )
         {    player.stop();
               resetmedia();
             
         }

    }
     @FXML
    void forwardseek(ActionEvent event) {
        
       player.seek(player.getCurrentTime().multiply(1.5));
        
    }
    
   
    @FXML
    void rewindseek(ActionEvent event) {
        player.seek(player.getCurrentTime().divide(1.5));
     }

    void initialize() {
      
        assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
        assert rewindbtn != null : "fx:id=\"rewindbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
        assert fwdbtn != null : "fx:id=\"fwdbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
        assert playbtn != null : "fx:id=\"playbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
        assert stopbtn != null : "fx:id=\"stopbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
        assert recordbtn != null : "fx:id=\"recordbtn\" was not injected: check your FXML file 'Secondpage.fxml'.";
         
    }
    
    void display(Image image)
    { 
    }

    void pngimagedisplay(String get) {
        // Image image=new Image("file:\\C:\\Users\\AMW Design PC 2\\Documents\\NetBeansProjects\\Articulate\\Articulate+\\Hindi\\aa\\final\\kutta.jpeg",200,200,false,false);
      
         Image image =new Image("file:\\"+get);
          Platform.runLater(new Runnable() {
             @Override
             public void run() {
                png.setImage(image);
                // pane.getChildren().add(iv2);  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
         
//System.out.println(get);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void jpegimagedisplay(String canonicalPath) {
       Image image =new Image("file:\\"+canonicalPath);
          Platform.runLater(new Runnable() {
             @Override
             public void run() {
                jpeg.setImage(image);
                // pane.getChildren().add(iv2);  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     void gifimagedisplay(String canonicalPath) {
       Image image =new Image("file:\\"+canonicalPath);
          Platform.runLater(new Runnable() {
             @Override
             public void run() {
                tif.setImage(image);
                // pane.getChildren().add(iv2);  //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
             }
         });
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

void displaypath(String path){
    Platform.runLater(new Runnable() {
             @Override
             public void run() {
                 pathlabel.setText(path);
               }
         });
}

  void resetmedia() throws MalformedURLException{
         Media pick=this.getpick();
         player = new MediaPlayer(pick);
       
    }

   


}


