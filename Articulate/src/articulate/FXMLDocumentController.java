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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author AMW Design PC 2
 */
 

public class FXMLDocumentController implements Initializable {
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
   
       public void listFilesForFolder(final File folder) {
    for (final File fileEntry : folder.listFiles()) {
        if (fileEntry.isDirectory()) {
            listFilesForFolder(fileEntry);
        } else {
            System.out.println(fileEntry.getName());
            filename.add(fileEntry.getName());
        }
    }
}
       Collection getAllFilesThatMatchFilenameExtension(String directoryName)
{
    File directory = new File(directoryName);
    return FileUtils.listFiles(directory, new WildcardFileFilter("*.png *.jpeg"), null);
}
       
       
       
   @FXML
    void H_uu_Initial(ActionEvent event) throws IOException
    {
       Stage stageStage = arti.getstageStage();
       stageStage.close();
      
       
       
       String pathlabel="Language/Hindi/uu/Initial";
       
       primaryLoader = new FXMLLoader(getClass().getResource("Secondpage.fxml"));  
       Parent textAreaHolder = (Parent) primaryLoader.load();
       SecondpageController second=primaryLoader.getController();
        
       second.displaypath(pathlabel);
       
       //////////////////////////////png filename////////////////////////// 
                File dir = new File(Hindi_cexedir+"uu\\"+"Initial");
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
        //////////////////////tif filename //////////////////////////
              
		String[] exten = new String[] { "gif" };
		
		List<File> fi = (List<File>) FileUtils.listFiles(dir, exten, true);
		for (File file : fi) {
			System.out.println("gif image file: " + file.getCanonicalPath());
       second.gifimagedisplay(file.getCanonicalPath());
               }
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
       classStage.setMaxWidth(652);
       classStage.setMaxHeight(725);
       classStage.setTitle(" Articulate+");
       scene = new Scene(textAreaHolder);
       classStage.setScene(scene);
       classStage.centerOnScreen();
       classStage.show();
               
       
    }
    @FXML
    void H_uu_Medial(ActionEvent event) throws IOException {
       
  
   
    }
    
    @FXML
    void H_uu_Final(ActionEvent event) throws IOException {
       
  
   
      
          }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
