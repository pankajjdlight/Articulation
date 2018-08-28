/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;
//package nasofx;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
//import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import static nasofx.NasoFX.valuefromc;
//import static nasofx.FXMLDocumentController.valuefromc;
//import static nasofx.FXMLDocumentController.valuefromc;
/**
 *
 * @author DoD
 */
public class PasteNasoFx extends Application {
    private AnchorPane objectsLayer;
    public StreamBytes streamBytes;
    public int startSam, endSam;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength,startPix,endPix; 
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
      
    LineChart lineChart ;
    NumberAxis xAxis ;
  //  FXMLDocumentController fxmlobject =new FXMLDocumentController();
    double array[]={};
     @FXML 
    static  double  valuefromc;
     XYChart.Data<Double,Double> dd;

    public PasteNasoFx() {
        this.streamBytes = new StreamBytes();
    }
    
    
    
    
    
    
    
    
    @Override
    public void start(Stage stage) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        Scene scene;
        scene = new Scene(root);
     // scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
       // scene.getStylesheets().add("test.css");
        
      stage.setResizable(false);
       stage.setMaxWidth(1190);
       stage.setMaxHeight(630);
      //stage.setTitle(" NasoSpeech");
      
        stage.setScene(scene);
         stage.centerOnScreen();
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename,Tab tab1 ,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration,AudioInputStream audioInputStream,float frameSize,int actual_frames_per_pixel) throws Exception {
     //   System.out.println("duration as frame_per_pixel"+duration);
        System.out.println(" sam_freq duration"+sam_freq +"numsamples"+numsamples+"sam_freq"+sam_freq+"duration"+duration+"frameSize"+frameSize+"actual_frames_per_pixel"+actual_frames_per_pixel);
        xAxis= new NumberAxis("",0d,duration,0.05);
        NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
        lineChart= new LineChart(xAxis, yAxis);
        java.nio.file.Path p=Paths.get(filename);
      
       String substring= p.getFileName().toString();
       stage.setTitle(substring);

        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     
      
      
       for(int i=0;i<numsamples;i++){
      
         // data = new XYChart.Data<Integer,Double>( i, samples[i]);
         
           System.out.println("for loop"+i);
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         //dd.setNode(new HoveredThresholdNode(samples[i]));
         data.add(dd);
         // data.add(new XYChart.Data<>(i/sam_freq, samples[i]).setNode(new HoveredThresholdNode(samples[i])));
          
         
          //dataSeries1.getData().add(data);
         // System.out.println(samples[i]);
     
       }
     //dataSeries1.getData().add(data);
     XYChart.Series series = new XYChart.Series(data);
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
    xAxis.setSide(Side.TOP);
   
     lineChart.setCreateSymbols(false);
     
 //1 lineChart.getData().clear();
     //lineChart.getData().add(10, series);
    // int index=346/2;
    // lineChart.getData().add(150, series);
     lineChart.getData().add(series);  
     
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart.setLegendVisible(false);
       boolean verticalGridLinesVisible = lineChart.getVerticalGridLinesVisible();
      verticalGridLinesVisible=true;
      boolean horizontalGridLinesVisible = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible=true;
        
      lineChart.setCreateSymbols(false);
      lineChart.setAnimated(false);
     // lineChart.verticalGridLinesVisibleProperty(true){
    // public final void setVerticalGridLinesVisible(boolean value) { verticalGridLinesVisible.set(value); }
    
  //  }
      // xAxis.setTickUnit(factor);
     // lineChart.getYAxis().setTickLabelsVisible(false);
    //  lineChart.getYAxis().setTickMarkVisible(false);
    //lineChart.getXAxis().toString();
     //lineChart.getXAxis().setTickMarkVisible(false);  
     // lineChart.getXAxis().setTickLabelsVisible(false);
    //  lineChart.getXAxis().setOpacity(0);
      //lineChart.getYAxis().lookup(".axis-minor-tick-mark").setVisible(false);
     // lineChart.getXAxis().lookup(".axis-minor-tick-mark").setVisible(false);
    //  lineChart.getYAxis().setVisible(false);
     // lineChart.getYAxis().setTickLabelsVisible(false);
   //  lineChart.getYAxis().setOpacity(0);
        tab1.setText(substring);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     //lineChart.setPrefHeight(352);
     //lineChart.setMinHeight(352);
   
         double height = lineChart.getHeight();//System.out.println("linechartheight"+height);
     //lineChart.setMaxSize(1300, 402);
   // lineChart.setMinSize(1300,402);
//2    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     wavepane.setContent(lineChart);
     
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() 
     {  
      @Override public void handle(MouseEvent mouseEvent) {
          double xxx=(double)xAxis.getValueForDisplay(mouseEvent.getX());
          System.out.println("xaxis value print-->"+
          String.format(
            "x =.2f",xxx  
          )
        );
        //  xAxis.animatedProperty().asString();
          //lineChart.getXAxis().getLabel();
          
          
      // xAxis.setClip(new HoveredThresholdNode(xxx));
  //HoveredThresholdNode xxx=new HoveredThresholdNode();  
//xxx.
   //dd.setNode(new Label("xxx"));
        //  final Label label=new Label("xxxxx");
        //  lineChart.getEffect().notify();
//xAxis.getChildren().setAll(label);
//StackPane ss=new StackPane();
//ss.getChildren().add(ss);
// xAxis.getAnimated();
          //   xAxis.setCursor(Cursor.CROSSHAIR);
          // ss.toFront();
          // label.toFront();
         // boolean hover = xAxis.isHover();
// System.out.println("series data-->"+series.getData().toString());////////////////gives the data
   
      
     
      
      }
    });
   //  lineChart.setOnDragOver(new EventHandler<DragEvent>() {
  //  public void handle(DragEvent event) {
        /* data is dragged over the target */
        /* accept it only if it is not dragged from the same node 
         * and if it has a string data */
       /* if (event.getGestureSource() != target &&
                event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses 
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        */
    //  if(mousePosX1==0){
    //  mousePosX1=event.getX();
    //      System.out.println("position1"+mousePosX1);
    //  }
    //  else{
       //   mousePosX2=event.getX();
       //   System.out.println("position2"+mousePosX2);
      
    //  }
       
       
      
       
       
      //  event.consume();
    //}
//});

     
     lineChart.setOnMouseDragged(new EventHandler<MouseEvent>() 
    {  
        
      // lineChart.getData().clear();
        
      @Override public void handle(MouseEvent mouseEvent) {
          //double xxx=(double)xAxis.getValueForDisplay(mouseEvent.getX());
         // System.out.println("xaxis value print-->"+
         // String.format(
          //  "x =",xxx  
         // )
         
       if(mousePosX1==0){
     mousePosX1=mouseEvent.getX();
    // mouseEvent.consume();
         // System.out.println("position1"+mousePosX1);
     }
     else{
         mousePosX2=mouseEvent.getX();
        //  System.out.println("position2"+mousePosX2);
     
     }
       
          System.out.println("\nposition1->\t"+mousePosX1);
          System.out.println("\nposition2->\t"+mousePosX2);
          getSamplingPositions(mousePosX1,mousePosX2,(int)frameSize,actual_frames_per_pixel);
               int startSample = getStartSamples();
                            int endSample = getEndSamples();
String input="\nStarting MousePosition-->\n"+mousePosX1+"\nMouse_End_Position-->\n"+mousePosX2+"\nStartSample-->\n"+startSample+"\nendSample-->\n"+endSample;
System.out.print("\nStartSample\t"+startSample+"\nendSample\t"+endSample);
        //lineChart.getData().clear();
 
//////////////write to the file for articulation

 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "/cexe/";
                        String filename=cexedir+"Input_For_Articulation.txt";


 File file = new File(filename);
  
          try {
              //Create the file
              if (file.createNewFile()){
                  System.out.println("File is created!");
              }else{
                  System.out.println("File already exists.");
              }
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
           
          //Write Content
          FileWriter writer = null;
          try {
              writer = new FileWriter(file);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.write(input);
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
          try {
              writer.close();
              
          } catch (IOException ex) {
              Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
          }
              
  /////end of the file writer////////////////            
                  
              
// CutAudioWave cutW = new CutAudioWave();
 //cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

 //if (cutW.getresultByteArray() == null) {
   //  System.out.println("i have entered");
     //return;
// }
 //if (CutAudioInputStream.getCutWave() == null) {
   //  System.out.println("i have entered");
    // return;
 //}
          
   // String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                    //    String cexedir = currentDir + "/cexe/";
                    //    System.out.println("correct format"+cexedir);
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                        
                 
                        
                        String filelocName=cexedir+"temp.wav";
                        StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(),audioInputStream , filelocName);
                        CutAudioInputStream.setCutWave(null);
                        
                        /////////////hypernasality////////////
  //CheckHypernasality(filelocName);
                        //////////////hypernasality//////////
                       
 
          
          
          
      }
    });

      
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             
             //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         
     
     
     
     
     
     
     
     
     
     
     
     
     /*
    xAxis.setOnMouseMoved(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
          System.out.println("xaxis value print-->"+
          String.format(
            "x = %.2f",
            xAxis.getValueForDisplay(mouseEvent.getX())
          )
        );
      }
    });
     
  */   
     
     
     
     
     
 /*    
     lineChart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                mousePosX1=e.getX();
                mousePosY1=e.getY();
                
                
                
                
                
                
               getSamplingPositions(e.getX(),e.getY(),numsamples,duration);
               int startSample = getStartSamples();
                            int endSample = getEndSamples();
                            System.out.print("StartSample\t"+startSample+"endSample\t"+endSample);
                                CutAudioWave cutW = new CutAudioWave();
                                cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                                if (CutAudioInputStream.getCutWave() == null) {
                                     System.out.println("i have entered");
                                     return;
                                }
                //System.out.println("mousePosX1--->"+e.getX());
               // System.out.println("mousePosY1---->"+e.getY());
               // series.getData().add(new XYChart.Data(series.getData().size() * 10, 30 + 50 * new Random().nextDouble()));
                //Coords();
            }
        });*/
     // System.out.println("mousePosX1--->"+mousePosX1);
     //System.out.println("mousePosX1--->"+e.getX());
     //wavepane.setBackground(new Background(Array(new BackgroundFill(Color.DARKCYAN,new CornerRadii(0),Insets(0)))));
    // wavepane.setContent().removeAll(lineChart);
    /// wavepane.getChildren().add(lineChart);
     
     
     

//3    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
    /* scrollbar.valueProperty().addListener(new ChangeListener<Number>() {
        @Override
        public void changed(ObservableValue<? extends Number> ov,
            Number old_val, Number new_val) {
            
         //  double val= scrollbar.getValue();
            
          //  lineChart.setTranslateX((-val)*lineChart.getScaleX()*2);
            
          //  System.out.println(lineChart.getScaleX());
            
            double trans = lineChart.getBaselineOffset();
                lineChart.setTranslateX((-new_val.doubleValue()*20));
                System.out.println(trans);
                
        }
    });
   */  
     
     
     
     
   // lineChart.setScaleX(2.0);
    }
    
     public void paint() 
    { //System.out.println("i am here");
      //  Graphics2D g2 = (Graphics2D) g;
        
       // Draw Selection portion 
                      //  Color mouseDraggedbg = new Color(255, 255, 153);
                         
                        //java.awt.Color mouseDraggedbg = new java.awt.Color(51, 153, 255);
                        
                       // g2.setColor(mouseDraggedbg);
                        
       // objectsLayer = new AnchorPane();
      //  objectsLayer.prefHeightProperty().bind(heightProperty());
      ///  objectsLayer.prefWidthProperty().bind(widthProperty());
        
        
        
        
        
                         mousePosX1=0;
                         mousePosX2=1180;
                        int widthPos1 = (int) (mousePosX1 - mousePosX2);
                        int minValue1 = (int) (widthPos1 < 0 ? mousePosX1 : mousePosX2);
                        widthPos1 = (widthPos1 < 0 ? -1 * widthPos1 : widthPos1);
                        System.out.println("widthPos1\t"+widthPos1+"minValue1\t"+minValue1);
                        Rectangle r=new Rectangle((int) minValue1,0,1180,350);
                        //lineChart.setStyle("");
                        r.setFill(Color.AQUA);
                     //   objectsLayer.getChildren().remove(r);
                      //  objectsLayer.getChildren().add(r);
                      //  lineChart.getData().add(objectsLayer);
                      
                        if (minValue1 != 0) {
                            r.setFill(Color.CYAN);
                           // g2.fillRect((int) minValue1, 0, 1180, 350);/// 0 to 1180 x,y,width=1180,height=352
                            System.out.println("mousePosY1"+mousePosY1);
                            if (mousePosY1 > 195) {
                                try {
                                    BufferedImage image2 = ImageIO.read(new File("conf/img/L_01.jpg"));
                                  //  g2.drawImage(image2, (int) mousePosX2, (int) h - INFOPAD - 2, null);
                                  //  samplingGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                                } catch (IOException ex) {
                                  //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
    
    
    
    
    }
    
    public void saveas(AudioInputStream audioInputStream,int frameSize,int actual_frames_per_pixel)//audioinputstream,numsamples,duration
    {
    
          if (this.mousePosX1 != 0 && this.mousePosX2 != 0) 
                            {
                                this.getSamplingPositions(mousePosX1, mousePosX2, frameSize,actual_frames_per_pixel);//getSamplingPositions();
                                int startSample = this.getStartSamples();//getStartSamples();
                                int endSample = this.getEndSamples();//getEndSamples();
                                
                                
                                 byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
                                   if (audioBytes == null)
                                   {
                                        audioInputStream = null;
                                        return;
                                   }
                                 streamBytes.setCurrent(audioBytes);
                                   audioBytes = null;
                                  audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
                                 
                                 
                                System.out.print("StartSample in saveas\t"+startSample+"endSample in saveas\t"+endSample);
                                CutAudioWave cutW = new CutAudioWave();
                             //  streamBytes.setCurrent(audioBytes);
                                cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                                if (cutW.getresultByteArray() == null) 
                                {
                                     System.out.println("i have entered in saveas getresult");
                                     return;
                                }
                                if (CutAudioInputStream.getCutWave() == null)
                                {
                                     System.out.println("i have entered in saveas");
                                     return;
                                }
                                 //String currentDir = System.getProperty("user.dir");
                                 //System.out.println("cu");
                                 //String cexedir = currentDir + "\\cexe\\";
                                // createTempFile("wave",".wav"," C:\\Users\\user\\Documents\\NetBeansProjects\\HypernasalityFinal\\");
                                String filelocName =saveLocation();//"C:\\Users\\user\\Documents\\NetBeansProjects\\HypernasalityFinal\\temp.wav";//saveLocation(); //drawingComponent.this.ReturnFilename;saveLocation();
                                System.out.println("filelocName Saved\t"+filelocName);
                                StreamConverter.byteTowavefile(CutAudioInputStream.getCutWave(),audioInputStream, filelocName);

                                CutAudioInputStream.setCutWave(null);
         
         
         
         
         
     
                            }    
        

        
        
        
        
        
        
        
        
        
    
    }
    ///////////copy////////////////
    
            //Copy
   
public void copy(AudioInputStream audioInputStream,int frameSize,int actual_frames_per_pixel,float framerate)
{         
            if (this.mousePosX1 != 0 && this.mousePosX2 != 0 && audioInputStream != null) 
            {
                
                        try {
                            //  cutWave();
                            if (this.mousePosX1 == 0 && this.mousePosX2 == 0) {
                                return;
                            }
                            copy_from_ms = 0;
                            copy_to_ms = 0;
                                 this.getSamplingPositions(this.mousePosX1, this.mousePosX2, frameSize,actual_frames_per_pixel);//getSamplingPositions();////////////////correct frame_pre_pixel
                                 int startSample = this.getStartSamples();//getStartSamples();
                                 int endSample = this.getEndSamples();//getEndSamples();
                                 
                                 byte[] audioBytes = StreamConverter.streamTobyte(audioInputStream);
                                   if (audioBytes == null)
                                   {
                                        audioInputStream = null;
                                        return;
                                   }
                                 streamBytes.setCurrent(audioBytes);
                                   audioBytes = null;
                                  audioInputStream = StreamConverter.byteTostream(streamBytes.getCurrent(), audioInputStream);
                                 
                                 
                                 
                                 
                                 
                            System.out.println("Ref. StartSamples " + startSample + " EndSamples " + endSample + " FramesPerPix " + actual_frames_per_pixel+"framerate"+framerate);

                            CutAudioWave cutW = new CutAudioWave();
                            cutW.cutPortion(streamBytes.getCurrent(), startSample, endSample);

                         //   right.calculatePixcel();
                              if (this.mousePosX1 > this.mousePosX2) 
                              {
                                  startPix = (int) this.mousePosX2;
                                  endPix = (int) this.mousePosX1;
                              } else
                              {
                                  startPix = (int) this.mousePosX1;
                                  endPix = (int) this.mousePosX2;
                              }
                            
                            
                            
                            
                            
                            
                            
                            PixcelConversion pixConversion = new PixcelConversion();
                            copy_from_ms = pixConversion.pixcelToMillisecond(startPix, actual_frames_per_pixel, (int) framerate);////////frame_per correct it
                            copy_to_ms = pixConversion.pixcelToMillisecond(endPix, actual_frames_per_pixel, (int) framerate);
                            System.out.println("copy_from_ms\t"+copy_from_ms+"copy_to_ms\t"+copy_to_ms);

                            if (cutW.getresultByteArray() == null) {
                                System.out.println("\ncopyresultbytearry null");
                                return;
                            }



                        } 
                        catch (Exception ex) 
                        {
                        //    Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                        }
           }
}
            //End copy
        
       public void paste(AudioInputStream audioInputStream)
     { 
         
     
   if (CutAudioInputStream.getCutWave() != null && audioInputStream != null) {
               
                        if (CutAudioInputStream.getCutWave() != null) {
                            AudioInputStream newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), audioInputStream);

                            // FindAnnotated findAnnotation = new FindAnnotated(pWave.mainFrame.getConn(), pWave.fileName, copy_from_ms, copy_to_ms);
                          //  ArrayList ann_id = new FindAnnotated((Connection) pWave.mainFrame, Hash.getHashValue(StreamConverter.byteTostream(pWave.streamBytes.getCurrent(), pWave.audioInputStream)), copy_from_ms, copy_to_ms).getAnnotationID();

                            String newAnn_file = Hash.getHashValue(newInputStream);
                            newInputStream = StreamConverter.byteTostream(CutAudioInputStream.getCutWave(), audioInputStream);
                           // new InsertAnnotation(pWave.mainFrame.getConn()).insertCopySAnnotation(newAnn_file, copy_from_ms, 0, ann_id);
                          // pWave.mainFrame.createCopyPanel("Copy/Paste", newInputStream);
                           
                            CutAudioInputStream.setCutWave(null);
                        }
                    }
               
            
     }  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      public String saveLocation()
    {        
        
   String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
    File selectedfile=fc.showOpenDialog(null);
   
   return selectedfile.getAbsolutePath();  
   
   
   
}   
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void showHover(Float value)
    {  
    
        
        
        
        
    
    
    }
    
    public void getSamplingPositions(double mousePosX1,double mousePosX2,int frameSize,int actual_frames_per_pixel) {
        try {
         //   System.out.println("duration in sampling position"+duration);
            int width = 1180;//pWave.samplingGraph.getSize().width;
            int inPos = (int) mousePosX1, enPos = (int) mousePosX2;

            if (inPos < 1) {
                inPos = 1;
            }
            if (enPos > (width - 10)) {
                enPos = width - 10;
            }
            if (enPos < 1) {
                enPos = 1;
            }
            if (inPos > (width - 10)) {
                inPos = width - 10;
            }

            int startTime = (int)((inPos *actual_frames_per_pixel) * frameSize);////////////it is not the duration it has to be changed ask 
            int endTime = (int)((enPos *actual_frames_per_pixel) * frameSize);
            int startSample, endSample;
            if (startTime > endTime) {
                startSample = endTime;
                endSample = startTime;
            } else {
                startSample = startTime;
                endSample = endTime;
            }
          //  System.out.println("startSample inside"+startSample);
           // System.out.println("endSample inside"+endSample);
            setStartSamples(startSample);
            setEndSamples(endSample);
        } catch (Exception er) {
            System.err.println(er);
        }

    }
    
     private void setStartSamples(int start) {
        this.startSam = start;
    }

    private void setEndSamples(int end) {
        this.endSam = end;
    }
    public int getStartSamples() {
        return this.startSam;
    }

    public int getEndSamples() {
        return this.endSam;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public double[] normalize(double []samples, int numsamples){
        double norm_arr[] = new double[numsamples] ;
        double max = Math.abs(samples[0]);
        /// find the max value
        for(int itr=1;itr<numsamples;itr++){
            if(Math.abs(samples[itr])> max )
                max = Math.abs(samples[itr]);
        }
        
        /// normalize with max value
        for(int itr=0;itr<numsamples;itr++){
            norm_arr[itr] = samples[itr]/max;
        }
        
        System.out.println("max = "+max   + "samples 0 = " + samples[0]  + "norm 0 = "+ norm_arr[0]);
        
        
        return norm_arr;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
        
        
    }
    
    //////////////////////////select&CheckHypernasality////////////////////////////
       private double CheckHypernasality(String filelocName) 
       {
             
               try {        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "/cexe/";
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filelocName,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          System.out.println("fdgdfhdfhdfhdfhfdhdf");     
                          
                          p1.waitFor();
                         
                             
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                         //   System.out.println("The number of frames is AAAAAAA"+numFrames);
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                 // 
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      //System.out.println("i am here");
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                              // s p.waitFor();
                              int waitFor = p.waitFor();
                              p.isAlive();
                                      System.out.println("wait for value"+waitFor);
                                   //   BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      Scanner sc=new Scanner(p.getInputStream());
                                     valuefromc= Double.parseDouble(sc.next());
                                     
                                    // System.out.println("xxxxxxxxxxxxx---->"+br.readLine());
                                     //String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                      //valuefromc=Float.parseFloat(br.readLine());
                                    //System.out.println("float of xxxxxxxx----->"+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      //(float) 0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                     //System.out.println("%f i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
   
                                      // PlotProbability plot=new  PlotProbability();
                                      //plot.plotfunction();
///do it later                                     PlotProbability plot1=new  PlotProbability(this);
//                                       plot1.plotfunction();
                                    //  plot1.plotfunction1();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                 
                                  }
                                  catch (IOException ex)
                                  {
                                    //  Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex)
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         }
                return valuefromc;
         }
             
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void dozoom(Float value,ScrollPane wavepane,double duration){
  //double i=1;
    // while(i>0){
    //lineChart.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("zoom.css").toExternalForm());
   
    if(value<10)
    {
    this.lineChart.setMinWidth(1180);
    xAxis.setTickUnit(.05);
    
    //xAxis= new NumberAxis("", 0d, duration, .001);
    
    
    //scrollbar.setVisibleAmount(100);
    //scrollbar.setValue(50); 
      
 
    }
    else if(value>=10&&value<30){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.08);
     
     wavepane.setHvalue(.50);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=30&&value<50){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.05);
     
     wavepane.setHvalue(.40);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=50&&value<70){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.03);
     
     wavepane.setHvalue(.30);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>=70&&value<90){
     this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.02);
     
     wavepane.setHvalue(.20);
     
    // scrollbar.setVisibleAmount(100-value);
    // scrollbar.setValue(50);
    
    }
    else if(value>90)
            {
                this.lineChart.setMinWidth(1180+value*250);
    //xAxis.setTickUnit(value*.1);////////////////set the value
     xAxis.setTickUnit(.01);
     
     wavepane.setHvalue(.10);
    }
    }
   /* public void dozoomout(){
  //   double i=1;
    // while(i>0){
   
      this.lineChart.setScaleX(1);
       xAxis.setTickUnit(1);
      //i++;
     //} //this.lineChart.onZoomProperty();
    
    }
*/
    double[] getdata(double[] samples) {
        System.out.println("i am entering\n");
        this.array=samples;
        // for(int i=0;i<13780;i++)
       //System.out.println(samples[i]);
        return this.array;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setTab(Tab tab1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     private void setUpZooming(final Rectangle rect, final Node zoomingNode) {
        final ObjectProperty<Point2D> mouseAnchor = new SimpleObjectProperty<>();
        zoomingNode.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseAnchor.set(new Point2D(event.getX(), event.getY()));
                rect.setWidth(0);
                rect.setHeight(0);
            }
        });
        zoomingNode.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                rect.setX(Math.min(x, mouseAnchor.get().getX()));
                rect.setY(Math.min(y, mouseAnchor.get().getY()));
                rect.setWidth(Math.abs(x - mouseAnchor.get().getX()));
                rect.setHeight(Math.abs(y - mouseAnchor.get().getY()));
            }
        });
    }
    
     
     
     
        
            
            
            

            private void doZoom(Rectangle zoomRect, LineChart<Number, Number> chart) {
        Point2D zoomTopLeft = new Point2D(zoomRect.getX(), zoomRect.getY());
        Point2D zoomBottomRight = new Point2D(zoomRect.getX() + zoomRect.getWidth(), zoomRect.getY() + zoomRect.getHeight());
        final NumberAxis yAxis = (NumberAxis) chart.getYAxis();
        Point2D yAxisInScene = yAxis.localToScene(0, 0);
        final NumberAxis xAxis = (NumberAxis) chart.getXAxis();
        Point2D xAxisInScene = xAxis.localToScene(0, 0);
        double xOffset = zoomTopLeft.getX() - yAxisInScene.getX() ;
        double yOffset = zoomBottomRight.getY() - xAxisInScene.getY();
        double xAxisScale = xAxis.getScale();
        double yAxisScale = yAxis.getScale();
        xAxis.setLowerBound(xAxis.getLowerBound() + xOffset / xAxisScale);
        xAxis.setUpperBound(xAxis.getLowerBound() + zoomRect.getWidth() / xAxisScale);
        yAxis.setLowerBound(yAxis.getLowerBound() + yOffset / yAxisScale);
        yAxis.setUpperBound(yAxis.getLowerBound() - zoomRect.getHeight() / yAxisScale);
        System.out.println(yAxis.getLowerBound() + " " + yAxis.getUpperBound());
        zoomRect.setWidth(0);
        zoomRect.setHeight(0);
    }

            
 @FXML
 public double Hypernasality(String filename){
 
 
    
                        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "/cexe/";
                        System.out.println("correct format"+cexedir);
                       // JFrame jf = new JFrame("test");
                        //String name = JOptionPane.showInputDialog(jf,
                        //currentDir, null);
                      try {
                          Process p1;
                          //System.out.println("getting filename"+pWave.abbfilePath);
                          //filenamedummy = pWave.abbfilePath;
                         // System.out.println("filenamedummy"+filename);
                          ProcessBuilder pb1=new ProcessBuilder
                            (cexedir+"mfcc_final_version_working",
                                    filename,
                                    "1001",
                                    cexedir+"start.txt",
                                    cexedir+"end.txt",
                                    cexedir+"vunv.txt",
                                    cexedir+"spfr.txt",
                                    cexedir+"avg.txt",
                                    cexedir+"N.txt",
                                    cexedir+"F.txt",
                                    cexedir+"mfcc_output_13dim.txt"
                            );
                          
                          
                          p1 = pb1.start();
                          System.out.println("fdgdfhdfhdfhdfhfdhdf");     
                          
                          p1.waitFor();
                         
                             
                          LineNumberReader  lnr = new LineNumberReader(new FileReader(new File(cexedir+"mfcc_output_13dim.txt")));
                            lnr.skip(Long.MAX_VALUE);
                            System.out.println(lnr.getLineNumber() + 1); //Add 1 because line index starts at 0
                                    // Finally, the LineNumberReader object should be closed to prevent resource leak
                            int numFrames = lnr.getLineNumber();
                         //   System.out.println("The number of frames is AAAAAAA"+numFrames);
                            lnr.close();
                            System.out.println("num frames = "+numFrames);
                                  ProcessBuilder pb = new ProcessBuilder(cexedir+"posteriorcomputation" ,
                                          cexedir+"mfcc_output_13dim.txt",
                                          cexedir+"mean_norm.txt",
                                          cexedir+"var_norm.txt",
                                          cexedir+"weight_norm.txt",
                                          cexedir+"mean_clp.txt",
                                          cexedir+"var_clp.txt",
                                          cexedir+"weight_clp.txt",
                                          cexedir+"output_norm.txt",
                                          cexedir+"output_clp.txt", "16", "13", Integer.toString(numFrames));
                                 // 
                                  //  ProcessBuilder pb = new ProcessBuilder("tree");
                                  
                                  
                                  try {
                                      //System.out.println("i am here");
                                      Process p = pb.start();
                                      /*try {
                                      pb.wait(0);
                                      } catch (InterruptedException ex) {
                                      Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                      }
                                      */
                              // s p.waitFor();
                              int waitFor = p.waitFor();
                              p.isAlive();
                                      System.out.println("wait for value"+waitFor);
                                   //   BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                                      Scanner sc=new Scanner(p.getInputStream());
                                     valuefromc= Double.parseDouble(sc.next());
                                     
                                    // System.out.println("xxxxxxxxxxxxx---->"+br.readLine());
                                     //String st= br.readLine();
                                      //Double.parseDouble(br.readLine());
                                      //valuefromc=Float.parseFloat(br.readLine());
                                    //System.out.println("float of xxxxxxxx----->"+Double.parseDouble(br.readLine()));
                                    // valuefromc = Double.parseDouble(br.readLine());
                                      //(float) 0.27;//Double.parseDouble(br.readLine());//br.readLine();
                                     //System.out.println("%f i am  getting this value from c---->"+valuefromc);
                                      //br.readLine();
                                      
                                      //  System.out.println(" i am  getting this value from c---->");
                                      
                                      //System.out.println("value getting"+br);//br.readLine());
                                      // String probability =br.readLine();
                                      //double probnew = Double.parseDouble(probability);
                                      
                                                               
                                      //System.out.println(" i am  getting this value after converting from double---->"+valuefromc);
                                     // PlotProbability plot=new  PlotProbability();
                                      //plot.plotfunction();
/////do it later                                     PlotProbability plot1=new  PlotProbability(this);
    //                                   plot1.plotfunction();
                                    //  plot1.plotfunction1();
                                      //pWave.mainFrame.createIvectorInternalFrame("Speaker Identification", "word/Assamese/part2");
                                 
                                  }
                                  catch (IOException ex)
                                  {
                                    //  Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                                  }
                                  
                                  
                      }
                      catch (IOException ex) 
                         {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                         } catch (InterruptedException ex) {
                       // Logger.getLogger(RightClickEvent.class.getName()).log(Level.SEVERE, null, ex);
                    }

 
 
            
            
            
            
            return valuefromc;
            
            
            
            
            
            
            
		
}
   static double getvaluefromc()
    {
        return valuefromc;
    }
   
      public void selectAll(int numsamples,double duration)
              
{                    
    
                         
                         System.out.println("numsamples"+numsamples);
                         System.out.println("duration"+duration);
                 //////mouse drag paint////////        
            //     this.paint(g);
            
            
           
              
                try{
                           int inPos = 0;//(int) mousePosX1,
                           int enPos = 1180;//(int) ((pWave.samplingGraph.getSize().width) - 10);

                            if (inPos < 1) 
                            {
                                inPos = 1;
                            }

                            if (enPos < 1) 
                            {
                                enPos = 1;
                            }
                            if (inPos > (enPos - 10)) 
                            {
                                inPos = enPos - 10;
                            }
                            //double bytesPerSecond = pWave.audioInputStream.getFormat().getFrameSize() * (double) pWave.audioInputStream.getFormat().getFrameRate();
                            int startTime = (int)((inPos * duration) * numsamples);
                            int endTime = (int)((enPos * duration) * numsamples);
                           // System.out.println("CK " + ((inPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000 + " " + ((enPos * pWave.frames_per_pixel) / pWave.audioInputStream.getFormat().getFrameRate()) * 1000);


                           //mousePosX1 = startTime;
                          // mousePosX2 = endTime;
                            System.out.println("starttime\t"+startTime+" endtime\t"+endTime+" mousestart\t"+inPos +" mouseend\t"+enPos);
                          //  pWave.samplingGraph.repaint();
                          
                
                          
                }
                
                
                catch (Exception er) 
                {
                            System.err.println(er);
                        
                }
                this.paint();
}
   
   
   
   
      
   
   
   
   
   

    
    
}

      
      
    
      
      
    
