package articulate;
import com.mathworks.engine.*;
import static articulate.Articulate.classStage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.layout.element.Cell; 
//import com.itextpdf.layout.element.Table;  
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.engine.MatlabSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
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
public static String PATHLABEL="",PATH="";
public static double valuefromc=0.0;


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
    public static String FOLDER_ID="";
     public static String i_error="";
     public static String m_error="";
     public static String f_error="";
    Stage classStage = arti.getstageStage();
    
    FXMLLoader primaryLoader;
      String currentDir = System.getProperty("user.dir");
       String Hindi_cexedir = currentDir + "\\Articulate+\\"+"Hindi\\";
       String English_cexedir = currentDir + "\\Articulate+\\"+"English\\";
    
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
        String query = "SELECT FName,MName,LName,Address,Folder_id FROM Patient_Register_Table WHERE Pid = ?";
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
            FOLDER_ID=rs.getString(5);
           //  System.out.println("xxx"+"Ariculate_Demo"+FOLDER_ID);
         
         }
         
        
        
        }
        catch (SQLException e){
        e.printStackTrace();
        
        
        }
        
        
   }
   
   
   
   /////////////////////inserting files into folder///////////////
   /*
   String folderId = "0BwwA4oUTeiV1TGRPeTVjaWRDY1E";
File fileMetadata = new File();
fileMetadata.setName("photo.jpg");
fileMetadata.setParents(Collections.singletonList(folderId));
java.io.File filePath = new java.io.File("files/photo.jpg");
FileContent mediaContent = new FileContent("image/jpeg", filePath);
File file = driveService.files().create(fileMetadata, mediaContent)
    .setFields("id, parents")
    .execute();
System.out.println("File ID: " + file.getId());
   
   
   
   
  */ 
   
    
   
 //////////////////////Inserting Files Into Folder///////////////   
    @FXML
    void next(ActionEvent event) throws IOException, InterruptedException, CancellationException, ExecutionException { 
        
      ///////////////For hindi language//////////////////////////
      
        if(gt.E_lang!=1){
     ///step to close window each time////   
       Stage stage = (Stage) playbtn.getScene().getWindow();
       stage.close(); 
       //////////////////////////////
       
        /*
        if(arti.uu_Initial==1)
        {
       PATHLABEL="Language/Hindi/uu/medial";
       PATH=Hindi_cexedir+"uu\\"+"medial";
        }
        if(arti.uu_Medial==2)
        {
       PATHLABEL="Language/Hindi/uu/final";
       PATH=Hindi_cexedir+"uu\\"+"final";
        }
        if(arti.uu_Final==3)
        {
       PATHLABEL="Language/Hindi/uu/initial";
       PATH=Hindi_cexedir+"uu\\"+"initial";
       }
        
        */
       arti.uu_Initial++;
       if(arti.uu_Initial==3){
/////////////////////////////////////Start of After Final Menu Tick response////////////////////////////////
            if(sub.isSelected()){
                            arti.fsubb++;
  
                 
} else {
   //clear the config file
}
           if(om.isSelected()){
               arti.fomm++;
  
} else {
   //clear the config file
}
           if(dis.isSelected()){
               arti.fdiss++;
             
} else {
   //clear the config file
}
           if(ad.isSelected()){
               arti.fadd++;
          
} else {
   //clear the config file
}
 
        //classStage.close();
     // classStage=stage;
   
         arti.fval=calculateSODA("Hindi","final",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
        
       generatepdfForAll();
      //t.E_lang=0;
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController();  
       classStage .setResizable(false);
       classStage .setMaxWidth(800);
       classStage .setMaxHeight(600);
       classStage .setTitle(" Articulate+");
       Scene scene = new Scene(textAreaHolder);
       classStage .setScene(scene);
       classStage .centerOnScreen();
       classStage .show();
       
//////////////////////////////////////////End of After Final menu tick response////////////////////////////////////       
       }
       else{
           
       
       if(arti.uu_Initial%2!=0){
/////////////////////////////////////////START of After initial menutick response/////////////////////////////////////////
       
       PATHLABEL="Language\\Hindi\\uu\\medial";
       PATH=Hindi_cexedir+gt.getPhoneme()+"\\"+"medial";//  org "uu/"
           
       
       gt.setPosition("medial");
                         
                        if(sub.isSelected()){
                            arti.isubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.iomm++;
  
} else {
   
}
           if(dis.isSelected()){
               arti.idiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.iadd++;
          
} else {
   
}
        
         arti.ival=calculateSODA("Hindi","initial",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
             
 //////////////////////////////////////////////////END of After initial menutick response////////////////////////////////
       
       
       }
       else{
   /////////////////////Start of After Medial MenuTick Response//////////////////////////////////////////////////////////////     
      
       
       PATHLABEL="Language\\Hindi\\uu\\final";
       PATH=Hindi_cexedir+gt.getPhoneme()+"\\"+"final";//  org "uu/"
           
       
       gt.setPosition("final");
       if(sub.isSelected()){
                            arti.msubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.momm++;
  
} else {
  
}
           if(dis.isSelected()){
               arti.mdiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.madd++;
          
} else {
   
}
         arti.mval=calculateSODA("Hindi","medial",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
          
   ///////////////END of After MedialMenu click response////////////////////////////////////////////////////////////////////    
       }
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController(); 
       first.SecondPageLoader(PATHLABEL,PATH);
       FXMLLoader   prima = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent text = (Parent) prima.load();
       SecondpageController second=prima.getController();
       second.displaypath(PATHLABEL);
       
       }
       
       
       
       ///////////////////End of hindi language//////////////////
    }
        else
        
        
        {
            
            
            
            
       
            
    ///////////////////////ENGLISH LANGUAGE///////////////////        
             
           if(gt.one==1){
               
               
           Stage stage = (Stage) playbtn.getScene().getWindow();
       stage.close(); 
        if(sub.isSelected()){
                            arti.fsubb++;
  
                 
} else {
   //clear the config file
}
           if(om.isSelected()){
               arti.fomm++;
  
} else {
   //clear the config file
}
           if(dis.isSelected()){
               arti.fdiss++;
             
} else {
   //clear the config file
}
           if(ad.isSelected()){
               arti.fadd++;
          
} else {
   //clear the config file
}
           
        arti.fval=calculateSODA("English","final",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");   
         generatepdfForAll();
      //t.E_lang=0;
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController();  
       classStage .setResizable(false);
       classStage .setMaxWidth(800);
       classStage .setMaxHeight(600);
       classStage .setTitle(" Articulate+");
       Scene scene = new Scene(textAreaHolder);
       classStage .setScene(scene);
       classStage .centerOnScreen();
       classStage .show();  
           
           
           
           
           
           
           
           
           
           }else{}
           if(gt.two==1){
           
               arti.count++;
                if(arti.count%2!=0){
/////////////////////////////////////////START of After initial menutick response/////////////////////////////////////////
       PATHLABEL="Language\\English\\"+gt.getPhoneme()+"\\"+gt.getEPosition_2();
       PATH=English_cexedir+gt.getPhoneme()+"\\"+gt.getEPosition_2();//  org "uu/"
           
       
       gt.setPosition(gt.getEPosition_2());
                         
                        if(sub.isSelected()){
                            arti.isubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.iomm++;
  
} else {
   
}
           if(dis.isSelected()){
               arti.idiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.iadd++;
          
} else {
   
}
          
         arti.ival=calculateSODA("English","initial",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
          primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController(); 
       first.SecondPageLoader(PATHLABEL,PATH);
       FXMLLoader   prima = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent text = (Parent) prima.load();
       SecondpageController second=prima.getController();
       second.displaypath(PATHLABEL);
             
 //////////////////////////////////////////////////END of After initial menutick response////////////////////////////////
       
       
       }
       else{
   /////////////////////Start of After Medial MenuTick Response//////////////////////////////////////////////////////////////     
      
      
       
       PATHLABEL="Language\\English\\"+gt.getPhoneme()+"\\"+gt.getEPosition_3();
       PATH=English_cexedir+gt.getPhoneme()+"\\"+gt.getEPosition_3();//  org "uu/"
      
           
       
       gt.setPosition("final");
       if(sub.isSelected()){
                            arti.msubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.momm++;
  
} else {
  
}
           if(dis.isSelected()){
               arti.mdiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.madd++;
          
} else {
   
}
        
          arti.fval=calculateSODA("English","final",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");   
         generatepdfForAll();
      //t.E_lang=0;
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController();  
       classStage .setResizable(false);
       classStage .setMaxWidth(800);
       classStage .setMaxHeight(600);
       classStage .setTitle(" Articulate+");
       Scene scene = new Scene(textAreaHolder);
       classStage .setScene(scene);
       classStage .centerOnScreen();
       classStage .show();  
         
   ///////////////END of After MedialMenu click response////////////////////////////////////////////////////////////////////    
       }
      
       
       
             
               
               
               
               
               
               
               
               
               
               
               
               
           
           
           
           
           }else{}
          
           
           
           
           
           
           if(gt.three==1){        
   ///step to close window each time////   
       Stage stage = (Stage) playbtn.getScene().getWindow();
       stage.close(); 
       //////////////////////////////
       
        /*
        if(arti.uu_Initial==1)
        {
       PATHLABEL="Language/Hindi/uu/medial";
       PATH=Hindi_cexedir+"uu\\"+"medial";
        }
        if(arti.uu_Medial==2)
        {
       PATHLABEL="Language/Hindi/uu/final";
       PATH=Hindi_cexedir+"uu\\"+"final";
        }
        if(arti.uu_Final==3)
        {
       PATHLABEL="Language/Hindi/uu/initial";
       PATH=Hindi_cexedir+"uu\\"+"initial";
       }
        
        */
       arti.COUNT++;
       if(arti.COUNT==3){
/////////////////////////////////////Start of After Final Menu Tick response////////////////////////////////
            if(sub.isSelected()){
                            arti.fsubb++;
  
                 
} else {
   //clear the config file
}
           if(om.isSelected()){
               arti.fomm++;
  
} else {
   //clear the config file
}
           if(dis.isSelected()){
               arti.fdiss++;
             
} else {
   //clear the config file
}
           if(ad.isSelected()){
               arti.fadd++;
          
} else {
   //clear the config file
}
 
        //classStage.close();
     // classStage=stage;
     
         arti.fval=calculateSODA("English","final",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
         
       generatepdfForAll();
      //t.E_lang=0;
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController();  
       classStage .setResizable(false);
       classStage .setMaxWidth(800);
       classStage .setMaxHeight(600);
       classStage .setTitle(" Articulate+");
       Scene scene = new Scene(textAreaHolder);
       classStage .setScene(scene);
       classStage .centerOnScreen();
       classStage .show();
       
//////////////////////////////////////////End of After Final menu tick response////////////////////////////////////       
       }
       else{
           
       
       if(arti.COUNT%2!=0){
/////////////////////////////////////////START of After initial menutick response/////////////////////////////////////////
       PATHLABEL="Language\\English\\"+gt.getPhoneme()+"\\"+gt.getEPosition_2();
       PATH=English_cexedir+gt.getPhoneme()+"\\"+gt.getEPosition_2();//  org "uu/"
           
       
       gt.setPosition(gt.getEPosition_2());
                         
                        if(sub.isSelected()){
                            arti.isubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.iomm++;
  
} else {
   
}
           if(dis.isSelected()){
               arti.idiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.iadd++;
          
} else {
   
}
          
         arti.ival=calculateSODA("English","initial",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
         
             
 //////////////////////////////////////////////////END of After initial menutick response////////////////////////////////
       
       
       }
       else{
   /////////////////////Start of After Medial MenuTick Response//////////////////////////////////////////////////////////////     
      
      
       
       PATHLABEL="Language\\English\\"+gt.getPhoneme()+"\\"+gt.getEPosition_3();
       PATH=English_cexedir+gt.getPhoneme()+"\\"+gt.getEPosition_3();//  org "uu/"
      
           
       
       gt.setPosition("final");
       if(sub.isSelected()){
                            arti.msubb++;
  
                 
} else {
   
}
           if(om.isSelected()){
               arti.momm++;
  
} else {
  
}
           if(dis.isSelected()){
               arti.mdiss++;
             
} else {
   
}
           if(ad.isSelected()){
               arti.madd++;
          
} else {
   
}
         arti.mval=calculateSODA("English","medial",gt.getPhoneme(),gt.getwavefilename(),"Templatepath");
         
          
   ///////////////END of After MedialMenu click response////////////////////////////////////////////////////////////////////    
       }
       primaryLoader = new FXMLLoader(getClass().getResource("/articulate/Firstpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       FXMLDocumentController first=primaryLoader.getController(); 
       first.SecondPageLoader(PATHLABEL,PATH);
       FXMLLoader   prima = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent text = (Parent) prima.load();
       SecondpageController second=prima.getController();
       second.displaypath(PATHLABEL);
       
       
             
        }
        }
        
       }
         
           
           
           
           
           
   
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    }
    
   String calculateSODA(String Langauge,String Position,String Target,String pathname,String Templatepath) throws  InterruptedException, CancellationException, ExecutionException{
    //   System.out.println("filename in matlab is"+pathname);
    /*
       MatlabEngine eng = MatlabEngine.startMatlab();
       eng.eval("cd '/home/nasospeech/Documents/Arti+'");
       Object feval = eng.feval("function_articulate",Langauge,Position,Target,pathname,"/home/nasospeech/Documents/Arti+/Hindi/uu/medial/kapoor.txt");
       System.out.println("value\t"+feval);
       eng.close();//check performence
       return feval.toString();
        //pathname=/home/nasospeech/Documents/Arti+/upar.wav
        //templaterpath=/home/nasospeech/Documents/Arti+/Hindi/uu/medial/kapoor.txt
      
     */
    
    
               try {      
                   String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                   pathname,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc.txt","k"
                            );
                          
                          
                          p1 = pb1.start();
                       
                          
                                  
                      }catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         }
         // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
               
               
         
         try {      
                   String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                          Process p2;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb=new ProcessBuilder
                            (cexedir+"dtw_new",
                                   "C:\\Users\\user\\Documents\\NetBeansProjects\\INTELLIGIBILITY_new\\MFCC_final_version_working_(END_POINT)_(INTELLIGI)\\mfcc.txt"," C:\\Users\\user\\Desktop\\india.txt",
                                    "C:\\Users\\user\\Desktop\\Intelligi_new\\out_new.txt","70","70","params.txt","debug_file.txt"
                            );
                          
                          
                          p2 = pb.start();
                       
                          
                                  
                      }catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         }
         
         
         
         
               
                return String.valueOf(valuefromc);
    
    
    
    
    
    
    
  //  return String.valueOf(1.011);
       
       
       
   }
    
     
   //////////////////////////modified Pdf /////////////////////////
    void generatepdfForAll(){
        
         if(arti.iomm==1){
        i_error="ommision";
    }else{}
    if(arti.idiss==1){
        i_error="distortion";
    }else{}
    if(arti.isubb==1){
        i_error="substitution";
    }else{}
    if(arti.iadd==1){
        i_error="addition";
    }else{}
    
   ///////medial////// 
   
    if(arti.momm==1){
        m_error="ommision";
    }else{}
    if(arti.mdiss==1){
        m_error="distortion";
    }else{}
    if(arti.msubb==1){
        m_error="substitution";
    }else{}
    if(arti.madd==1){
        m_error="addition";
    }else{}
    
    ////final//////
    
    if(arti.fomm==1){
        f_error="ommision";
    }else{}
    if(arti.fdiss==1){
        f_error="distortion";
    }else{}
    if(arti.fsubb==1){
        f_error="substitution";
    }else{}
    if(arti.fadd==1){
        f_error="addition";
    }else{}
    
    
          Platform.runLater(new Runnable(){
           @Override
           public void run() {
            
           Document doc=new Document();
        
               try {
                  //String pdflocation="C:\\Users\\AMW Design PC 2\\Documents\\NetBeansProjects\\Articulate_Demo\\cexe\\"+FNAME+".pdf";
                //  String pdflocation=saveLocation1()+"/"+FNAME+".pdf";
                  String pdflocation="C:\\Users\\user\\Documents\\NetBeansProjects\\Arti+v2.0\\CM_Ji.pdf";
              PdfWriter.getInstance(doc, new FileOutputStream(pdflocation/*saveLocation1())*/));//change here
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
                //   String path=arti.getfilename();
                   String patientDetails="Patient Details";
                   Paragraph patient=new Paragraph(18,patientDetails,black1);
                   patient.setSpacingAfter(25);
                 //  String Name="Name:  "+FNAME+"   "+MNAME+"    "+LNAME;
                   String Name="Name:  "+"";
                   Paragraph paraName=new Paragraph(18,Name,black1);
                   paraName.setSpacingAfter(10);
                   String DOB="Patient Id:"+"  ";
                   Paragraph paraDOB=new Paragraph(18,DOB,black1);
                   paraDOB.setSpacingAfter(10);
                   String ADD="Address:\t"+"   ";
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
                   
                   
                  
                    //Creating a table 
     // Table table = new Table(3);     

     PdfPTable table = new PdfPTable(new float[] { 2, 1,1, 2 });
	  table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
	  table.addCell("Phoneme");
          table.addCell("position");
          table.addCell("SODA error");
           table.addCell("Objective score");
	  table.setHeaderRows(1);
	  PdfPCell[] cells = table.getRow(0).getCells(); 
	  for (int j=0;j<cells.length;j++){
	     cells[j].setBackgroundColor(BaseColor.GRAY);
	  }
          
    	     table.addCell(gt.getPhoneme());
             table.addCell("initial");
             table.addCell(i_error);
             table.addCell(arti.ival);
             
             table.addCell(gt.getPhoneme());
             table.addCell("medial");
             table.addCell(m_error);
              table.addCell(arti.mval);
             
             table.addCell(gt.getPhoneme());
             table.addCell("final");
             table.addCell(f_error);
             table.addCell(arti.fval);
        
           //Adding Table to document  
      doc.add(table);
                 
      doc.add(p2);
                } catch (DocumentException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               }
                   doc.close();               
     ResetValue();  ///////////////reseting the value/////////////////////////////
                   
Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("File is saved successfully.");
alert.showAndWait();
               }
       });

          
          
//Stage classStage = (Stage) loginbtn.getScene().getWindow();     
 /*   
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
                     } catch (SQLException ex) {
                         Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
         }
     }); 

    
    /////////////correct shut down///////////////////
       
   
    */    
       
    
    
    
    
    
    
    
    
    }
    //////////////////////modified Pdf///////////////////////
    
    public void ResetValue(){
         arti.count=0;
        arti.COUNT=0;
        arti.uu_Initial=0;
        gt.E_lang=0;
       gt.eposition_i="";
       gt.eposition_m="";
       gt.eposition_f="";
       gt.eposition="";
       gt.ephoneme="";
       gt.one=0;
       gt.two=0;
     gt.three=0; 
     arti.ival="";
     arti.mval="";
      arti.fval="";
       arti.iadd=0;
       arti.isubb=0;
       arti.iomm=0;
       arti.idiss=0;
       arti.madd=0;
       arti.msubb=0;
       arti.momm=0;
       arti.mdiss=0;
       arti.fadd=0;
       arti.fsubb=0;
       arti.fomm=0;
       arti.fdiss=0;
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
                  //String pdflocation="C:\\Users\\AMW Design PC 2\\Documents\\NetBeansProjects\\Articulate_Demo\\cexe\\"+FNAME+".pdf";
                  String pdflocation=saveLocation1()+"\\"+FNAME+".pdf";
              PdfWriter.getInstance(doc, new FileOutputStream(pdflocation/*saveLocation1())*/));//change here
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (IOException ex) {
                   Logger.getLogger(ThirdpageController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (SQLException ex) {
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
                   
                   /*
                    //Creating a table 
      Table table = new Table(2);     

      //Adding cells to the table 
      table.addCell(new Cell().add("Name")); 
      table.addCell(new Cell().add("Raju")); 
      table.addCell(new Cell().add("Id")); 
      table.addCell(new Cell().add("1001")); 
      table.addCell(new Cell().add("Designation")); 
      table.addCell(new Cell().add("Programmer")); 

      //Adding Table to document  
      doc.add(table);
                   
                   */
                   
                   
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
                     } catch (SQLException ex) {
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
                 String image1="file:"+cexedi+"middle_pause.png";
                 String image2="file:"+cexedi+"middle_play.png";
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
   // String path="Language/Hindi/uu/final/evaluate";
   // pathlabel.setText(path);
        
          String currentDir = System.getProperty("user.dir");
        String file="file:"+currentDir+"\\Icons\\"+"Artboard 3.jpg";
         BackgroundImage myBI= new BackgroundImage(new Image(file,800,570,false,true),
     BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
     BackgroundSize.DEFAULT);
//then you set to your node
   anchor.setBackground(new Background(myBI));
             
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
        
public void shutdown() throws UnsupportedAudioFileException, IOException, InterruptedException, SQLException{
    
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
    
    
    
    
    
    
    
    

public void alertAction() throws UnsupportedAudioFileException, IOException, SQLException{

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
   public String AfterRecordSave() throws UnsupportedAudioFileException, IOException, SQLException{
        String currentDir = System.getProperty("user.dir");
        String newsavelocation="C:\\Users\\user\\Documents\\NetBeansProjects\\Arti+v2.0\\record_new.wav";//currentDir+"\\Articulate_Demo"+FOLDER_ID;
        
                 String cexedir = currentDir +"\\cexe\\";
                 String filename =cexedir+"record.wav";
        //String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\record.wav";
        String saveLocation =  arti.getfilename()+"\\"+FNAME+".wav";//newsavelocation;//saveLocation();////change here
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


       public String saveLocation1() throws IOException, SQLException
    {        
   /*     
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
*/   
return arti.getfilename();
   
   
   
}
}
