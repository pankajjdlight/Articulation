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
    
   String i_position="initial",m_position="medial",f_position="final";
   String E_d="d",E_b="b",E_l="l",E_w="w",E_v="v";
   String H__uu="uu",H_n="n",H_g="g",H_e="e",H_a="a";
     @FXML
    private AnchorPane anchor;
      @FXML
    private MenuItem H_uu;
    @FXML
    private MenuItem H_aa_Initial;
    Articulate arti=new Articulate();
    Getfield gt=new Getfield();
    Scene scene;
    String Language="English";
   
   public static Stage classStage = new Stage();
     FXMLLoader primaryLoader;
      String currentDir = System.getProperty("user.dir");
       String Hindi_cexedir = currentDir + "\\Articulate+\\"+"Hindi\\";
       String English_cexedir = currentDir + "\\Articulate+\\"+"English\\";
       String Hindi_cexedi="Hindi\\";
       String English_cexedi="English\\";
      // String[] filename=new String[5];
       ArrayList<String> filename = new ArrayList<String>();
       
      
    ///////Modified//////////////   
      @FXML
    void H_e(ActionEvent event) throws IOException {
 Stage stageStage = arti.getstageStage();
stageStage.close();
gt.setPhoneme(H_e);
gt.setPosition(i_position);
String pathlabel=Hindi_cexedi+H_e+"\\"+i_position;
String path=Hindi_cexedir+H_e+"\\"+i_position;
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
    }

    @FXML
    void H_g(ActionEvent event) throws IOException {
       
Stage stageStage = arti.getstageStage();
stageStage.close();
gt.setPhoneme(H_g);
gt.setPosition(i_position);

String pathlabel=Hindi_cexedi+H_g+"\\"+i_position;
String path=Hindi_cexedir+H_g+"\\"+i_position;
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
    }

    @FXML
    void H_n(ActionEvent event) throws IOException {
Stage stageStage = arti.getstageStage();
stageStage.close();
gt.setPhoneme(H_n);
gt.setPosition(i_position);

String pathlabel=Hindi_cexedi+H_n+"\\"+i_position;
String path=Hindi_cexedir+H_n+"\\"+i_position;
//gt.setPath(path);
SecondPageLoader(pathlabel,path);

    }
      @FXML
    void E_d(ActionEvent event) throws IOException {
       
Stage stageStage = arti.getstageStage();
stageStage.close();
//tring path=System.getProperty("user.dir");
  String phoneme=E_d;
 File file = new File(English_cexedir+"\\"+"\\"+phoneme);
        String[] fileList = file.list();
        int i=0;
        for(;i<fileList.length;i++){
           // System.out.println(fileList[i]);
           if(i==0){
        gt.setEPosition_1(fileList[i]);
         gt.one=1;
         gt.two=0;
         gt.three=0;
          // break;
           }else{}
           
        if(i==1){
            gt.setEPosition_2(fileList[i]);
            gt.one=0;
            gt.two=1;
            gt.three=0;
        }else{}
        if(i==2){
        gt.setEPosition_3(fileList[i]);
        gt.one=0;
        gt.two=0;
        gt.three=1;
        }else{}
        }
gt.setPhoneme(E_d);
gt.setPosition(i_position);
gt.E_lang=1;
String pathlabel=English_cexedi+"\\"+phoneme+gt.getEPosition_1();
String path=English_cexedir+phoneme+"\\"+gt.getEPosition_1();
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
  
   }

    @FXML
    void E_b(ActionEvent event) throws IOException {
       
Stage stageStage = arti.getstageStage();
stageStage.close();
//tring path=System.getProperty("user.dir");
  String phoneme=E_b;
 File file = new File(English_cexedir+"\\"+"\\"+phoneme);
        String[] fileList = file.list();
        int i=0;
        for(;i<fileList.length;i++){
           // System.out.println(fileList[i]);
           if(i==0){
        gt.setEPosition_1(fileList[i]);
         gt.one=1;
         gt.two=0;
         gt.three=0;
          // break;
           }else{}
           
        if(i==1){
            gt.setEPosition_2(fileList[i]);
            gt.one=0;
            gt.two=1;
            gt.three=0;
        }else{}
        if(i==2){
        gt.setEPosition_3(fileList[i]);
        gt.one=0;
        gt.two=0;
        gt.three=1;
        }else{}
        }

        
gt.setPhoneme(E_b);
gt.setPosition(i_position);
gt.E_lang=1;
String pathlabel=English_cexedi+"\\"+phoneme+gt.getEPosition_1();
String path=English_cexedir+phoneme+"\\"+gt.getEPosition_1();
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
  
    }
      @FXML
    void E_l(ActionEvent event) throws IOException {
       
Stage stageStage = arti.getstageStage();
stageStage.close();
//tring path=System.getProperty("user.dir");
  String phoneme=E_l;
 File file = new File(English_cexedir+"\\"+"\\"+phoneme);
        String[] fileList = file.list();
        int i=0;
        for(;i<fileList.length;i++){
           // System.out.println(fileList[i]);
           if(i==0){
        gt.setEPosition_1(fileList[i]);
         gt.one=1;
         gt.two=0;
         gt.three=0;
          // break;
           }else{}
           
        if(i==1){
            gt.setEPosition_2(fileList[i]);
            gt.one=0;
            gt.two=1;
            gt.three=0;
        }else{}
        if(i==2){
        gt.setEPosition_3(fileList[i]);
        gt.one=0;
        gt.two=0;
        gt.three=1;
        }else{}
        }

        
gt.setPhoneme(E_l);
gt.setPosition(i_position);
gt.E_lang=1;
String pathlabel=English_cexedi+"\\"+phoneme+gt.getEPosition_1();
String path=English_cexedir+phoneme+"\\"+gt.getEPosition_1();
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
      }

    @FXML
    void E_w(ActionEvent event) throws IOException {
Stage stageStage = arti.getstageStage();
stageStage.close();
//tring path=System.getProperty("user.dir");
  String phoneme=E_w;
 File file = new File(English_cexedir+"\\"+"\\"+phoneme);
        String[] fileList = file.list();
        int i=0;
        for(;i<fileList.length;i++){
           // System.out.println(fileList[i]);
           if(i==0){
        gt.setEPosition_1(fileList[i]);
         gt.one=1;
         gt.two=0;
         gt.three=0;
          // break;
           }else{}
           
        if(i==1){
            gt.setEPosition_2(fileList[i]);
            gt.one=0;
            gt.two=1;
            gt.three=0;
        }else{}
        if(i==2){
        gt.setEPosition_3(fileList[i]);
        gt.one=0;
        gt.two=0;
        gt.three=1;
        }else{}
        }
gt.setPhoneme(E_w);
gt.setPosition(i_position);
gt.E_lang=1;
String pathlabel=English_cexedi+"\\"+phoneme+gt.getEPosition_1();
String path=English_cexedir+phoneme+"\\"+gt.getEPosition_1();
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
    }
     @FXML
    void E_v(ActionEvent event) throws IOException {
Stage stageStage = arti.getstageStage();
stageStage.close();
//tring path=System.getProperty("user.dir");
  String phoneme=E_v;
 File file = new File(English_cexedir+"\\"+"\\"+phoneme);
        String[] fileList = file.list();
        int i=0;
        for(;i<fileList.length;i++){
           // System.out.println(fileList[i]);
           if(i==0){
        gt.setEPosition_1(fileList[i]);
         gt.one=1;
         gt.two=0;
         gt.three=0;
          // break;
           }else{}
           
        if(i==1){
            gt.setEPosition_2(fileList[i]);
            gt.one=0;
            gt.two=1;
            gt.three=0;
        }else{}
        if(i==2){
        gt.setEPosition_3(fileList[i]);
        gt.one=0;
        gt.two=0;
        gt.three=1;
        }else{}
        }
gt.setPhoneme(E_v);
gt.setPosition(i_position);
gt.E_lang=1;
String pathlabel=English_cexedi+"\\"+phoneme+gt.getEPosition_1();
String path=English_cexedir+phoneme+"\\"+gt.getEPosition_1();
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
    }
   

    @FXML
    void H_uu(ActionEvent event) throws IOException {
        
Stage stageStage = arti.getstageStage();
stageStage.close();
gt.setPhoneme(H__uu);
gt.setPosition(i_position);
String pathlabel=Hindi_cexedi+H__uu+"\\"+i_position;
//String pathlabel="Language/Hindi/e/initial";
String path=Hindi_cexedir+H__uu+"\\"+i_position;
//gt.setPath(path);
SecondPageLoader(pathlabel,path);
//Stage stage = (Stage) arti.getstageStage().getScene().getWindow();
  //stage.close();
   }  
      @FXML
    void H_a(ActionEvent event) throws IOException {
Stage stageStage = arti.getstageStage();
stageStage.close();
gt.setPhoneme(H_a);
gt.setPosition(i_position);

String pathlabel=Hindi_cexedi+H_a+"\\"+i_position;
String path=Hindi_cexedir+H_a+"\\"+i_position;
//gt.setPath(path);
SecondPageLoader(pathlabel,path);

 //Stage stage = (Stage) arti.getstageStage().getScene().getWindow();
  //stage.close();
   } 
      
       
 
    
     
     /////////////////////////////////MASTEER FUNCTION//////////////////////////////////////////////////////////////////////
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
                        gt.setwavefilename(filename.getCanonicalPath());
                        second.setpick(filename.getCanonicalPath());//////////////////////////////////////For audio play
		}
        ///////////////////////end of wave file name////////////////
         
       classStage.setResizable(false);
       classStage.setMaxWidth(1000);
       classStage.setMaxHeight(750);
       classStage.setTitle(" Articulate+");
       scene = new Scene(textAreaHolder);
       classStage.setScene(scene);
       classStage.centerOnScreen();
       classStage.show();
               
         
    }
    
    ///////////////End of master function//////////////////////////////////
     void testfunction(){
         
         
         
     }
     
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         String currentDir = System.getProperty("user.dir");
        String file="file:"+currentDir+"\\Icons\\"+"Artboard 1.png";
       BackgroundImage myBI= new BackgroundImage(new Image(file,800,570,false,true),
      BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
         BackgroundSize.DEFAULT);
//then you set to your node
    anchor.setBackground(new Background(myBI));
    }    
    
    
    
    
}
