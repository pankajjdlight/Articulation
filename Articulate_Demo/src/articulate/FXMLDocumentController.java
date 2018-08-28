/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulate;

import static articulate.Articulate.classStage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author AMW Design PC 2
 */
 

public class FXMLDocumentController implements Initializable {
    
   
     @FXML
    private AnchorPane anchor;
    
    @FXML
    private MenuItem H_aa_Initial;
    Articulate arti=new Articulate();
    Scene scene;
   public static Stage classStage = new Stage();
     FXMLLoader primaryLoader;
      String currentDir = System.getProperty("user.dir");
       String Hindi_cexedir = currentDir + "\\Articulate+\\"+"Hindi\\";
       String English_cexedir = currentDir + "\\Articulate+\\"+"English\\";
      // String[] filename=new String[5];
       ArrayList<String> filename = new ArrayList<String>();
      
   @FXML
    void H_uu_Initial(ActionEvent event) throws IOException
    {
      
       
       String pathlabel="Language/Hindi/uu/initial";
       String path=Hindi_cexedir+"uu\\"+"initial";
       SecondPageLoader(pathlabel,path);
   
    }
    @FXML
    void H_uu_Medial(ActionEvent event) throws IOException 
    {
          String pathlabel="Language/Hindi/uu/medial";
          String path=Hindi_cexedir+"uu\\"+"medial";
           SecondPageLoader(pathlabel,path);
   
    }
    
    @FXML
    void H_uu_Final(ActionEvent event) throws IOException {
       
         String pathlabel="Language/Hindi/uu/final";
          String path=Hindi_cexedir+"uu\\"+"final";
           SecondPageLoader(pathlabel,path);
        
 
          }
    @FXML
  void H_e_initial(ActionEvent event) throws IOException {
       String pathlabel="Language/Hindi/e/initial";
       String path=Hindi_cexedir+"e\\"+"initial";
       SecondPageLoader(pathlabel,path);
   
          
    }
@FXML
  void H_e_medial(ActionEvent event) throws IOException {
       String pathlabel="Language/Hindi/e/medial";
       String path=Hindi_cexedir+"e\\"+"medial";
       SecondPageLoader(pathlabel,path);
   
        
    }
  @FXML
     void H_e_final(ActionEvent event) throws IOException {
          String pathlabel="Language/Hindi/e/final";
       String path=Hindi_cexedir+"e\\"+"final";
       SecondPageLoader(pathlabel,path);
    }
     @FXML
     void H_n_initial(ActionEvent event) throws IOException {
       String pathlabel="Language/Hindi/n/initial";
       String path=Hindi_cexedir+"n\\"+"initial";
       SecondPageLoader(pathlabel,path);
    }
     @FXML
     void H_n_medial(ActionEvent event) throws IOException {
          String pathlabel="Language/Hindi/n/medial";
       String path=Hindi_cexedir+"n\\"+"medial";
       SecondPageLoader(pathlabel,path);
        
    }
     @FXML
     void H_n_final(ActionEvent event) throws IOException {
          String pathlabel="Language/Hindi/n/final";
       String path=Hindi_cexedir+"n\\"+"final";
       SecondPageLoader(pathlabel,path);
        
    }
    @FXML
     void H_g_initial(ActionEvent event) throws IOException {
         String pathlabel="Language/Hindi/g/initial";
       String path=Hindi_cexedir+"g\\"+"initial";
       SecondPageLoader(pathlabel,path);
    }
     @FXML
     void H_g_medial(ActionEvent event) throws IOException {
         String pathlabel="Language/Hindi/g/medial";
       String path=Hindi_cexedir+"g\\"+"medial";
       SecondPageLoader(pathlabel,path);
        
    }
     @FXML
     void H_g_final(ActionEvent event) throws IOException {
         String pathlabel="Language/Hindi/g/final";
       String path=Hindi_cexedir+"g\\"+"final";
       SecondPageLoader(pathlabel,path);
        
    }
    
    void SecondPageLoader(String pathlabel,String path) throws IOException{
        
        
       primaryLoader = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       SecondpageController second=primaryLoader.getController();
       second.displaypath(pathlabel);
       
       //////////////////////////////png filename////////////////////////// 
                File dir = new File(path);/////////////////////////////////hast to be replaced with path
		String[] extensions = new String[] { "png" };
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			System.out.println("png image file: " + file.getCanonicalPath());
       second.pngimagedisplay(file.getCanonicalPath());
               }
        ////////////////////png filename/////////////////////////////        
              
       //////////////////////jpeg filename //////////////////////////
              
		String[] extensionn = new String[] { "jpg" };
		
		List<File> filess = (List<File>) FileUtils.listFiles(dir, extensionn, true);
		for (File file : filess) {
			System.out.println("jpeg image file: " + file.getCanonicalPath());
       second.jpegimagedisplay(file.getCanonicalPath());
               }
       ///////////////////////////end fof jpeg filename///////////
           ///////////////////////////end fof jpeg filename///////////
       
       
      
       
       ////////////wave filename///////////////////////
       String[] extension = new String[] { "wav" };
		List<File> file = (List<File>) FileUtils.listFiles(dir, extension, true);
		for (File filename : file) {
			System.out.println("wave file: " + filename.getCanonicalPath());
                        second.setwavefilename(filename.getCanonicalPath());
                        second.setpick(filename.getCanonicalPath());//////////////////////////////////////For audio play
		}
        ///////////////////////end of wave file name////////////////
       
       
       classStage.setResizable(false);
       classStage.setMaxWidth(800);
       classStage.setMaxHeight(600);
       classStage.setTitle(" Articulate+");
       scene = new Scene(textAreaHolder);
       classStage.setScene(scene);
       classStage.centerOnScreen();
       classStage.show();
               
       
         
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       BackgroundImage myBI= new BackgroundImage(new Image("file:C:\\Users\\AMW Design PC 2\\Documents\\NetBeansProjects\\Articulate_Demo\\src\\Icons\\Artboard 1.png",800,570,false,true),
      BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
         BackgroundSize.DEFAULT);
//then you set to your node
    anchor.setBackground(new Background(myBI));
    }    
    
}
