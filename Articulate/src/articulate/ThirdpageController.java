package articulate;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ThirdpageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginbtn;

    @FXML
    private CheckBox sub;

    @FXML
    private CheckBox om;

    @FXML
    private CheckBox dis;

    @FXML
    private CheckBox ad;
    
    
    
    public static Stage classStage = new Stage();
     String path="",wavename="";
     public  Media pick; 
     public  TranslateTransition trans;
     public MediaPlayer player;
    
    
    
    @FXML
    void generatepdf(ActionEvent event) {
        
         // Stage newstage=plot.getstageStage();
        
          //Stage stage = (Stage) Print.getScene().getWindow();
         // stage.close();
     
       //   Prob object = new Prob();
        //  double xxx = object.getProb();
        //  double PRO=1-xxx;
       
           
       //   String print=fname+"\t"+mname+"\t"+lname+"\n"+dobirth+"\n"+address;
        Stage stage = (Stage) loginbtn.getScene().getWindow();
         stage.close();
         
          Platform.runLater(new Runnable(){
           @Override
           public void run() {
            
           Document doc=new Document();
        
               try {
                  
              PdfWriter.getInstance(doc, new FileOutputStream(saveLocation()));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               }
          doc.open();
              try {
                   Font blue = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
                   Font black = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
                   Font black1 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
                   Font red = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
                   Font yellow = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.YELLOW);
                   Font green = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN);
                   String tittle="Evaluation Result";
                   Paragraph Tittle=new Paragraph(22,tittle,black);
                   Tittle.setSpacingAfter(100);
                   doc.add(Tittle);
                   String result;
                    
                        if(sub.isSelected()){
  // write login-username to config file
  result="Error   "+"Substitution";
            Paragraph reesult=new Paragraph(22,result,black1);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
                 
} else {
   //clear the config file
}
           if(om.isSelected()){
  // write login-username to config file
   result="Error   "+"Ommission";
            Paragraph reesult=new Paragraph(22,result,black1);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
} else {
   //clear the config file
}
           if(dis.isSelected()){
                result="Error   "+"Distrotion";
            Paragraph reesult=new Paragraph(22,result,black1);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
  // write login-username to config file
} else {
   //clear the config file
}
           if(ad.isSelected()){
                result="Error   "+"Addition";
            Paragraph reesult=new Paragraph(22,result,black1);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
  // write login-username to config file
} else {
   //clear the config file
}
          
                   
                   
      
        
                   String patientDetails="Patient Details:";
                   Paragraph patient=new Paragraph(18,patientDetails,black1);
                   patient.setSpacingAfter(25);
                   String Name="Name :  "+"First name"+"   "+"Middle name"+"    "+"last name";
                   Paragraph paraName=new Paragraph(18,Name,black1);
                   paraName.setSpacingAfter(10);
                   String DOB="Date-Of-Birth:"+"   "+"Date-Of-Birth";
                   Paragraph paraDOB=new Paragraph(18,DOB,black1);
                   paraDOB.setSpacingAfter(10);
                   String ADD="Address:\t"+"   "+"Address";
                   Paragraph paraADD=new Paragraph(18,ADD,black1);
                   paraADD.setSpacingAfter(10);
                   String re="Remarks:\t"+"   "+"Remarks";
                   Paragraph parare=new Paragraph(18,re,black1);
                   String author="Articulation Assessments are made possible"+
                            "by AIISH and IITG with the help of kind funding by IMPRINT";
                   Paragraph p2=new Paragraph(22,author,blue);
                   p2.setSpacingBefore(100);
                   doc.add(patient);
                   doc.add(paraName);
                   doc.add(paraDOB);
                   doc.add(paraADD);
                   doc.add(parare);
                   doc.add(p2);
                 } catch (DocumentException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               }
                   doc.close();               
        
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("File is saved successfully.");
alert.showAndWait();
               }
       });

    }
    
          public String saveLocation() throws IOException
    {        
       
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pdf files","*.pdf"));
    
    File selectedfile=fc.showSaveDialog(classStage);
   if(selectedfile!=null)
   {
     
   }
       
   return selectedfile.getAbsolutePath();
    
   }
    
    @FXML
    void initialize() {
        assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert sub != null : "fx:id=\"sub\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert om != null : "fx:id=\"om\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert dis != null : "fx:id=\"dis\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert ad != null : "fx:id=\"ad\" was not injected: check your FXML file 'Thirdpage.fxml'.";

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
    
    
    void resetmedia() throws MalformedURLException{
         Media pick=this.getpick();
         player = new MediaPlayer(pick);
       
    }
}
