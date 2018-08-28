/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static nasofx.FXMLDocumentController.classStage;

/**
 * FXML Controller class
 *
 * @author Naso
 */
public class PreviewController extends Application {

    @FXML
    private TextField fn;

    @FXML
    private TextField mn;

    @FXML
    private TextField ln;

    @FXML
    private TextField dob;

    @FXML
    private TextArea add;

    @FXML
    private Button Print;
    @FXML
    String fname;
    String mname;
    String lname;
    
    String dobirth;
    String address;
    Stage classstage=new Stage();
    NasoFX nfx=new NasoFX();
    PdfWriter writer;
    PdfContentByte cb;
    PlotProbability plot;

    @FXML
    private Label name;
    @FXML
    private Label date;
    @FXML
    private Label addressfordis;
    @FXML
    private Label caseAndvalue;

    /**
     * Initializes the controller class.
     */
       
        @FXML
    void generatePdf(ActionEvent event) throws FileNotFoundException, DocumentException 
    { 
        
        System.out.println("jhjkdhfdhklvhdklfvlkfdkvkdfvldzdv");
        /*   
        Prob object = new Prob();
      //  Prob object1=new Prob(pWave);
        double xxx = object.getProb();
        double PRO=1-xxx;
    // drawingComponent DC = new drawingComponent(); 
  //  System.out.println("generate probality\t"+PRO); 
    fname=fn.getText().toString();
    mname=mn.getText().toString();
    lname=ln.getText().toString();
    
    dobirth=dob.getText().toString();
    address=add.getText().toString();
        String print=fname+"\t"+mname+"\t"+lname+"\n"+dobirth+"\n"+address;
         System.out.println(""+print);
         
         Platform.runLater(new Runnable(){
           @Override
           public void run() {
            
         Document doc=new Document();
        
               try {
                  
              PdfWriter.getInstance(doc, new FileOutputStream(saveLocation()));
            // cb = writer.getDirectContent(); 
            
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
               }
        doc.open();
        //doc.add(new Paragraph(print));
       // doc.addTitle("Hypernasality result");
        //doc.addCreationDate();
               try {
                   Font blue = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
                   Font black = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, BaseColor.BLACK);
                   Font black1 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);
                   Font red = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.RED);
                   Font yellow = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.YELLOW);
                   Font green = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.GREEN);
                  

//Chunk blueText = new Chunk("Hypernasality Result", black);
                   String tittle="Hypernasality Result";
                   // doc.addTitle(blueText.toString());
                    // Paragraph p1=new Paragraph(print).setFont(blue);
                   Paragraph Tittle=new Paragraph(22,tittle,black);
                   Tittle.setSpacingAfter(100);
                   doc.add(Tittle);
                   String result;
                   //="  "+PRO;
                    
        if(PRO<=0.25)
         
            
        {
            result="Normal    "+String.valueOf(PRO);
            Paragraph reesult=new Paragraph(22,result,green);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
                 
        }else
        if(0.25<PRO & PRO<=0.50)
        {
            result="Mild       "+String.valueOf(PRO);
            Paragraph reesult=new Paragraph(22,result,blue);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
                 
        }else
        if(0.50<PRO& PRO<=0.75)
        {   
            result="Moderate   "+String.valueOf(PRO);
            Paragraph reesult=new Paragraph(22,result,yellow);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
                 
        } else
        if(0.75<PRO& PRO<=1.0)
        {  // cb.saveState();
            //cb.setColorStroke(Color);
           // cb.rectangle(10,20,10,20);
           // cb.setColorFill(BaseColor.RED);
           // cb.stroke();
           // cb.restoreState();
            result="SEVERE      "+String.valueOf(PRO);
                   Paragraph reesult=new Paragraph(22,result,red);
                   reesult.setSpacingAfter(25);
                   doc.add(reesult);
                 
        }
                     String patientDetails="Patient Details:";
                   Paragraph patient=new Paragraph(18,patientDetails,black1);
                   patient.setSpacingAfter(25);
                   String Name="Name :  "+fname+"   "+mname+"    "+lname;
                   Paragraph paraName=new Paragraph(18,Name,black1);
                   paraName.setSpacingAfter(10);
                   String DOB="Date-Of-Birth:"+"   "+dobirth;
                   Paragraph paraDOB=new Paragraph(18,DOB,black1);
                   paraDOB.setSpacingAfter(10);
                   String ADD="Address:\t"+"   "+address;
                   Paragraph paraADD=new Paragraph(18,ADD,black1);
            //       paraName.setSpacingAfter(100);
                  
                   //Paragraph ptittle=new Paragraph(18,print,blue);
                  // Paragraph p1=new Paragraph(18,print,blue);
                  // p1.setSpacingBefore(50);
                  // p1.setSpacingAfter(50);
                 
                   String author="Hypernasality Assessments are made possible"+
                            "by AIISH and IITG with the help of kind funding by MHRD";
                   Paragraph p2=new Paragraph(22,author,blue);
                   p2.setSpacingBefore(100);
                   //p2.setSpacingAfter(50);
                  
               //  doc.add(new Paragraph(print));
                 //doc.add(p1);
                 
                 doc.add(patient);
                 doc.add(paraName);
                 doc.add(paraDOB);
                 doc.add(paraADD);
                 doc.add(p2);
                 //doc.add(p2);
                 
                 
                 
                 
               //doc.add(new Paragraph(author));
                  // paragraph.setMultipliedLeading(leadingValue);
               } catch (DocumentException ex) {
                   Logger.getLogger(PatientController.class.getName()).log(Level.SEVERE, null, ex);
               }
        doc.close();               
        
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
     // classstage.close();
   */   
    }
    @FXML
          public String saveLocation()
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
   
   return selectedfile.getAbsolutePath();  
   
   
   
}
          
          void generate() throws IOException{
              plot=new PlotProbability(nfx);
           Stage newstage=plot.getstageStage();
          
              System.out.println("jhjkhfkvjdfhvjfdjkvhkjsdfv");
          new Thread(()->{ 
              Platform.runLater(new Runnable(){

                  @Override
                  public void run() {
                      name.setText("hjhdfjkdvd");//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                  }
              });}).start();
              
         //     name.setText("hjhdfjkdvd"); // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
              
          
             // do what you have to do
    //Stage stage = (Stage) Print.getScene().getWindow();
    //     stage.close();
             Platform.runLater(new Runnable(){
           @Override
           public void run() {
           //Parent root = null;
         FXMLLoader  primaryLoader = new FXMLLoader(getClass().getResource("Preview.fxml"));  
               try {
             Parent   textAreaHolder = (Parent) primaryLoader.load();
              Scene scene = new Scene(textAreaHolder);
              newstage.setScene(scene);
              newstage.show();
               } catch (IOException ex) {
                   Logger.getLogger(PlotProbability.class.getName()).log(Level.SEVERE, null, ex);
               }
       
        //,530,735, true, SceneAntialiasing.BALANCED);
       

       // scene.getStylesheets().add("myStyleSheet.css");
        
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
       });
    
          
          
          
          
          }
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
          
      @Override
    public void start(Stage primaryStage) {
        classstage=primaryStage;
        
       /* Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
    public static void main(String[] args) {
        launch(args);
    } 
     
}
