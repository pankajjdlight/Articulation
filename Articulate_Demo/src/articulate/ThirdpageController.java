package articulate;

import static articulate.Articulate.classStage;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import loginmodule.LoginModule;

public class ThirdpageController implements Initializable{
     @FXML
    private AnchorPane anchor;
 Articulate arti=new Articulate();
 String wavefilename = arti.getwavefilename();
  Getfield gt=new Getfield();
private PreparedStatement pst;
    @FXML
    private Button loginbtn;
    @FXML
    private Label pathlabel;

    @FXML
    private CheckBox sub;

    @FXML
    private CheckBox om;

    @FXML
    private CheckBox dis;

    @FXML
    private CheckBox ad;
    public static String FNAME="";
    public static String MNAME="";
    public static String LNAME="";
    public static String ADDRESS="";
    
    
    //public static Stage classStage = new Stage();
     String path="",wavename="";
     public  Media pick; 
     public  TranslateTransition trans;
     public MediaPlayer player;
    @FXML
    private Button rewindbtn;
    @FXML
    private Button fwdbtn;
    @FXML
    private Button playbtn;
   
    @FXML
    private CheckBox ad1;
    void setwavefilename(String name){
    wavename=name;   
   }
   
   String getwavefilename(){
    return wavename;   
   }
    
   void getany(){
         String patientId = gt.getpatientId();
     Connection conn = LoginModule.connect();
        String query = "SELECT FName,MName,LName,Address FROM Patient_Register_Table WHERE Pid = ?";
        try {
            pst = conn.prepareStatement(query);
        } catch (SQLException ex) {
            //Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            pst.setString(1, gt.getpatientId());
        
      
        ResultSet rs = pst.executeQuery();
        int count =0;
         while(rs.next()){
          FNAME=rs.getString(1);
            MNAME=rs.getString(2);
            LNAME=rs.getString(3);
            ADDRESS=rs.getString(4);
       
         
         }
         
        
        
        }
        catch (SQLException e){
        e.printStackTrace();
        
        
        }
        
       
       
       
       
       
       
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
    @FXML
    void generatepdf(ActionEvent event) {
        
         // Stage newstage=plot.getstageStage();
        
          //Stage stage = (Stage) Print.getScene().getWindow();
         // stage.close();
     
       //   Prob object = new Prob();
        //  double xxx = object.getProb();
        //  double PRO=1-xxx;
       
           
       //   String print=fname+"\t"+mname+"\t"+lname+"\n"+dobirth+"\n"+address;
      //  Stage stage = (Stage) loginbtn.getScene().getWindow();
      //   stage.close();
          getany();
          Platform.runLater(new Runnable(){
           @Override
           public void run() {
            
           Document doc=new Document();
        
               try {
                  
              PdfWriter.getInstance(doc, new FileOutputStream(saveLocation1()));
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
                 
                   String path=arti.getfilename();
                   String patientDetails="Patient Details";
                   Paragraph patient=new Paragraph(18,patientDetails,black1);
                   patient.setSpacingAfter(25);
                   String Name="Name:  "+FNAME+"   "+MNAME+"    "+LNAME;
                   Paragraph paraName=new Paragraph(18,Name,black1);
                   paraName.setSpacingAfter(10);
                   String DOB="Patient Id:"+"   "+gt.getpatientId();
                   Paragraph paraDOB=new Paragraph(18,DOB,black1);
                   paraDOB.setSpacingAfter(10);
                   String ADD="Address:\t"+"   "+ADDRESS;
                   Paragraph paraADD=new Paragraph(18,ADD,black1);
                   paraADD.setSpacingAfter(10);
                  // String re="Remarks:\t"+"   "+"Remarks";
                   ///Paragraph parare=new Paragraph(18,re,black1);
                   String author="Articulation Assessments are made possible"+
                            "by AIISH and IITG with the help of kind funding by IMPRINT";
                   Paragraph p2=new Paragraph(22,author,blue);
                   p2.setSpacingBefore(100);
                   doc.add(patient);
                   doc.add(paraDOB);
                   doc.add(paraName);
                   doc.add(paraADD);
                 //  doc.add(parare);
                   doc.add(p2);
                 } catch (DocumentException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
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

Stage classStage = (Stage) loginbtn.getScene().getWindow();     
    
    ///////////////////correct shut down/////////////
    
       classStage.setOnHiding(new EventHandler<WindowEvent>() {
        
         @Override
         public void handle(WindowEvent event) {
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                     try {
                         // System.out.println("Application Closed by click to Close Button(X)");
                         // System.exit(0);
                            classStage.show();
                            shutdown();
                            Platform.setImplicitExit(false);
                            event.consume();
                     } catch (UnsupportedAudioFileException ex) {
                         Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
         }
     }); 

    
    /////////////correct shut down///////////////////
       
   
        

          
          
          
          
          
          
    }
    
   
    
    void initialize() {
        
       /* 
        
          assert loginbtn != null : "fx:id=\"loginbtn\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert sub != null : "fx:id=\"sub\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert om != null : "fx:id=\"om\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert dis != null : "fx:id=\"dis\" was not injected: check your FXML file 'Thirdpage.fxml'.";
        assert ad != null : "fx:id=\"ad\" was not injected: check your FXML file 'Thirdpage.fxml'.";
*/
    }
 Media getpick(){
     return pick;
     }
     void setpick(String file) throws MalformedURLException{
         System.out.println("bbbb"+file);
        
         pick = new Media(new File(file).toURI().toURL().toExternalForm());
         trans= new TranslateTransition();
     }
    




    @FXML
    void playsound(ActionEvent event) throws MalformedURLException {
          //this.setpick(wavefilename);
            String currentDir = System.getProperty("user.dir");
                 String cexedir = currentDir +"\\cexe\\";
                 String cexedi=currentDir+"\\Icons\\";
                 String image1="file:\\"+cexedi+"middle_pause.png";
                 String image2="file:\\"+cexedi+"middle_play.png";
               //  TP.getSelectionModel().select(tab1);
                 Image pause = new Image(image1,97,109,true,false);
                 Image play = new Image(image2,97,109,true,false);
                 ImageView pauseimage=new ImageView(pause);
                 pauseimage.setFitHeight(109);
                 pauseimage.setFitWidth(97);
                 pauseimage.setPreserveRatio(true);
                 pauseimage.setSmooth(true);
                  ImageView playimage=new ImageView(play);
                 playimage.setFitHeight(109);
                 playimage.setFitWidth(97);
                 playimage.setPreserveRatio(true);
                 playimage.setSmooth(true);
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
        });      playbtn.setGraphic(pauseimage);
                 player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       playbtn.setGraphic(playimage);
                  } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
         playbtn.setGraphic(pauseimage);
         playbtn.setGraphic(playimage);
         
        }
         else if(status == MediaPlayer.Status.PAUSED){
          player.play();
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
         
   }
        });
          playbtn.setGraphic(pauseimage);
          player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                        playbtn.setGraphic(playimage);
                      } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
                 }
        
        
        
        
    }




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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    String path="Language/Hindi/uu/final/evaluate";
    pathlabel.setText(path);
        
        
         BackgroundImage myBI= new BackgroundImage(new Image("file:C:\\Users\\AMW Design PC 2\\Documents\\NetBeansProjects\\Articulate\\src\\Icons\\Artboard 3.jpg",800,570,false,true),
     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
     BackgroundSize.DEFAULT);
//then you set to your node
   anchor.setBackground(new Background(myBI));
             
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
        
public void shutdown() throws UnsupportedAudioFileException, IOException, InterruptedException{
    
///////////////////////show alert to save for recording as sir said///////////////////          
// System.out.println("inside shutdown"+ Is_record);

if(arti.Is_record==1 && arti.is_SaveAs_done==0)
         {
         
          alertAction();
          
         }
         else
            
         {
             Platform.exit();
             

        }
}
    
    
    
    
    
    
    
    

public void alertAction() throws UnsupportedAudioFileException, IOException{

Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog ");
alert.setHeaderText("Do you want to save untitled.wav?");
alert.setContentText("Choose your option.");

ButtonType buttonTypeDont = new ButtonType("Don't");

ButtonType buttonTypeSave = new ButtonType("Save as");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

alert.getButtonTypes().setAll(buttonTypeDont, buttonTypeSave, buttonTypeCancel);
//alert.setDialogPane(new FixedOrderButtonDialog());
Optional<ButtonType> result = alert.showAndWait();
if (result.get() == buttonTypeDont){
    Platform.exit();
} else if (result.get() == buttonTypeSave) {
    
    //////////////////////////////////////do//////////////
  //nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.showAndWait();
  AfterRecordSave();
   
  Platform.exit();
    
   
    
    
    ///////////////save the file/////////////////////
} else 
{
  alert.close();
 // nasofx.FXMLDocumentController.classStage.setScene(scene);
 // nasofx.FXMLDocumentController.classStage.show();
  
  
 
}
}

    
    
    
    
    
//////savefile after record//////
   public String AfterRecordSave() throws UnsupportedAudioFileException, IOException{
        String currentDir = System.getProperty("user.dir");
                 String cexedir = currentDir +"\\cexe\\";
                 String filename =cexedir+"record.wav";
        //String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\record.wav";
        String saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        arti.is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
       return saveLocation;
    
   
   
   
   }
    
   ///////////////////////////////////// 
      
      public String saveLocation()
    {        
        
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
    File selectedfile=fc.showSaveDialog(classStage);
   if(selectedfile!=null)
   {
     System.out.println("file name"+selectedfile.getName());
   }
   
   return selectedfile.getAbsolutePath();  
   
   
   
}   


       public String saveLocation1() throws IOException
    {        
        
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Pdf files","*.pdf"));
    
    File selectedfile=fc.showSaveDialog(classStage);
   if(selectedfile!=null)
   {
     System.out.println("file name"+selectedfile.getName());
   }
        System.out.println("getPath\t"+selectedfile.getPath());
        System.out.println("canonicalpath\t"+selectedfile.getCanonicalPath());
        System.out.println("absolutePath\t"+selectedfile.getAbsolutePath());
   return selectedfile.getAbsolutePath();
   
   
   
}
}
