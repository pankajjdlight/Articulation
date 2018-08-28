/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Random;
//import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
//import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import static nasofx.FXMLDocumentController.classStage;
//import static nasofx.FXMLDocumentController.valuefromc;
//import static nasofx.FXMLDocumentController.valuefromc;
/**
 *
 * @author IITG
 */
public class NasoFX extends Application {
    VBox vb=new VBox();
    Rectangle rect;
    SimpleDoubleProperty rectinitX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectinitY = new SimpleDoubleProperty();
    SimpleDoubleProperty rectX = new SimpleDoubleProperty();
    SimpleDoubleProperty rectY = new SimpleDoubleProperty();
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    
   
    
    
    public int getrecordvalue=0;
    public boolean called=false;
    public PasteNasoFx pastenfx;
     private AudioFormat format;  
/**
     *
     */
        private AnchorPane objectsLayer;
    public StreamBytes streamBytes;
    public int startSam, endSam,startSample,endSample,totalSample;
    private int copy_from_ms = 0, copy_to_ms = 0, ann_fLength, ann_oldfLength,startPix,endPix; 
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    public SignalProc sigProc;
    LineChart lineChart ;
    NumberAxis xAxis ;
//  FXMLDocumentController fxmlobject =new FXMLDocumentController();
   static int Copied_Samples;
    
    
      public static int is_SaveAs_done=0;
      double array[]={};
      int numSamples;
      float frameRate;
      int frameSize;
      int actual_frames_per_pixel;
      public  double frames_per_pixel;
     
      public int Is_record_done=0;
     
      public static int Is_record=0;
     
      
    public static  double  valuefromc;
    public double hypernasality_value=0;
     XYChart.Data<Double,Double> dd;
     Plotwave plot;
// GridPane pane = new GridPane();
     Scene scene;
     Scene scene1;
     FXMLLoader primaryLoader;
     Capture cap = new Capture();
     public Button btn_to_pass=new Button();
     
     
    public NasoFX() 
    {
        this.plot = new Plotwave();
        this.pastenfx = new PasteNasoFx();
        this.streamBytes = new StreamBytes();
           
    }
   
    
    @Override
    public void start(Stage stage) throws Exception  {
        primaryLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
        Parent textAreaHolder = (Parent) primaryLoader.load();
   // Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));     
        
       
     // scene.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
       // scene.getStylesheets().add("test.css");
        
       stage.setResizable(false);
       stage.setMaxWidth(1190);
       stage.setMaxHeight(630);
       stage.setTitle(" NasoSpeech");
//        gp=new Group();
        
        
        rect = getNewRectangle();
        rect.widthProperty().bind(rectX.subtract(rectinitX));
        rect.heightProperty().bind(rectY.subtract(rectinitY));
        vb.getChildren().add(textAreaHolder);
        vb.getChildren().add(rect);
        
        scene = new Scene(vb);
      //  scene1 = new Scene(textAreaHolder);
      //  scene.setOnMouseDragged(mouseHandler);
      //  scene.setOnMousePressed(mouseHandler);
      // scene.setOnMouseReleased(mouseHandler);
        
        stage.setScene(scene);
        //      gp.getChildren().add(rect);
       // lineChart.getData().add(rect);
    //    scene =new Scene(gp);  
          
    stage.centerOnScreen();
    stage.show();
    
    
     /*   stage.setOnHidden(e->{try {
            
        shutdown();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        });   
        */
    /*  stage.setOnCloseRequest(e->{try {
            shutdown();
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
            }
});*/
    
    
    ///////////////////correct shut down/////////////
    
   stage.setOnHiding(new EventHandler<WindowEvent>() {
        
         @Override
         public void handle(WindowEvent event) {
             Platform.runLater(new Runnable() {

                 @Override
                 public void run() {
                     try {
                         // System.out.println("Application Closed by click to Close Button(X)");
                         // System.exit(0);
                            stage.show();
                            shutdown();
                            Platform.setImplicitExit(false);
                            event.consume();
                     } catch (UnsupportedAudioFileException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(NasoFX.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }
             });
         }
     }); 
 
    
    /////////////correct shut down///////////////////
    
   
    }
    
public void shutdown() throws UnsupportedAudioFileException, IOException, InterruptedException{
    
///////////////////////show alert to save for recording as sir said///////////////////          
 System.out.println("inside shutdown"+ Is_record);

if(Is_record==1 && is_SaveAs_done==0)
         {
         
          alertAction();
          
         }
         else
            
         {
             Platform.exit();
             

        }
}
 ///////////alert button action/////////// 
/*private static class FixedOrderButtonDialog extends DialogPane {
    @Override
    protected Node createButtonBar() {
        ButtonBar node = (ButtonBar) super.createButtonBar();
        node.setButtonOrder(ButtonBar.BUTTON_ORDER_NONE);
        return node;
    }
} 
*/




public void alertAction() throws UnsupportedAudioFileException, IOException{

Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog ");
alert.setHeaderText("Do you want to save untitled.wav?");
alert.setContentText("Choose your option.");

ButtonType buttonTypeDont = new ButtonType("Don't");

ButtonType buttonTypeSave = new ButtonType("Save as");
ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

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




          //////////////////////////////////////   
   
///////savefile after record//////
   public String AfterRecordSave() throws UnsupportedAudioFileException, IOException{
    String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                     //   String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                        String cexedi=currentDir+"\\cexe\\";
                  
        String filename=cexedi+"record.wav";
        String saveLocation = saveLocation();
        AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
         is_SaveAs_done=1;
        //AudioSystem.write(audioInputStream, , saveLocation);
        int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
        System.out.println("size of the arrary"+size);
        StreamConverter.streamTowavefile(saveLocation, audioInputStream);
       return saveLocation;
    
   
   
   
   }
    
   ///////////////////////////////////////////////////// 
    
 ////////////////////rect for dragging/////////////////   
    private Rectangle getNewRectangle() {
        Rectangle r = new Rectangle();
        r.setFill(Color.web("blue", 0.1));
       // r.setStroke(Color.BLUE);
       // r.setArcHeight(40);
       // r.setArcWidth(40);
        return r;
    }
    
    ////////////////////mousehandler////////////////////////////
 EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {

            if (mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                rect.setX(mouseEvent.getX());
                rect.setY(mouseEvent.getY());
                rectinitX.set(mouseEvent.getX());
                rectinitY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED) {
                System.out.println("mouse is dragged inside eventhandler");
                rectX.set(mouseEvent.getX());
                rectY.set(mouseEvent.getY());
            } else if (mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED) {
                // Clone the rectangle
                Rectangle r = getNewRectangle();
                r.setX(rect.getX());
                r.setY(rect.getY());
                r.setWidth(rect.getWidth());
                r.setHeight(rect.getHeight());
  //modify here pane.getChildren().add(r);
                vb.getChildren().add(r);

                // Hide the rectangle
                rectX.set(0);
                rectY.set(0);
            }
        }
    };
    
    /////////////////////////mousehandler////////////////////
    
 //////////////////////////
 
 
 ///////////////////
    
    public void startforplotwave(Stage stage,double[] samples,int numsamples,String filename,Tab tab1 ,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration,AudioInputStream audioInputStream,float frameSize,int actual_frames_per_pixel) throws Exception 
    {
     //   System.out.println("duration as frame_per_pixel"+duration);
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
     
     lineChart.getData().clear();
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
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);

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
                startSample = getStartSamples();
                            endSample = getEndSamples();
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
     
     
     

    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());
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
    public void tempplot(Stage stage,String filename,double[] samples,int numsamples,Tab tab1,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration )throws Exception{
        
        TP.getSelectionModel().select(tab1);
        getrecordvalue=1;
        called=true;
        
        xAxis= new NumberAxis("",0d,duration,0.05);
    //    xAxis.setAutoRanging(true);//("",0d,duration,0.05);
   //     xAxis.setForceZeroInRange(false);
       
        NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
        
        lineChart= new LineChart(xAxis, yAxis);
        lineChart.setMaxHeight(330);
        lineChart.setMinHeight(330);
        java.nio.file.Path p=Paths.get(filename);
      
        String substring= p.getFileName().toString();
       // stage.setTitle(substring);
        tab1.setText(substring);  
        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     
      
      
       for(int i=0;i<numsamples;i++){
      
         // data = new XYChart.Data<Integer,Double>( i, samples[i]);
         
         
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
     
     lineChart.getData().clear();
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
//        tab1.setText(substring);
     TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     //lineChart.setPrefHeight(352);
     //lineChart.setMinHeight(352);
   
         double height = lineChart.getHeight();//System.out.println("linechartheight"+height);
     //lineChart.setMaxSize(1300, 402);
   // lineChart.setMinSize(1300,402);
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
   // wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
    // wavepane.setPannable(true);
    // wavepane.setStyle(".scroll-pane > .viewport{-fx-background-color:#232323 ;\n" +
     //"-fx-control-inner-background: transparent;}");
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);
   //  wavepane.setContent(lineChart);
     //wavepane.setContent(rect);
 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        rect = getNewRectangle();
        rect.widthProperty().bind(rectX.subtract(rectinitX));
        rect.heightProperty().bind(rectX.subtract(rectinitY));
        Group group=new Group();
        group.getChildren().addAll(lineChart,rect);
        wavepane.setContent(group);
        wavepane.setPannable(false);
   
 
     wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

/////////////////////// start of the final  MouseListener//////////////////////////////////////
Rectangle r = getNewRectangle();
lineChart.addEventHandler(MouseEvent.MOUSE_PRESSED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
             rect.setX(mouseEvent.getX());
             //   rect.setY(mouseEvent.getY());
             rectinitX.set(mouseEvent.getX());
             // rectinitY.set(mouseEvent.getY());
            
             rectX.set(0);
             rectY.set(0);
            // rect.setHeight(320);
                 
            mousePosX1=mouseEvent.getX();
          
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            
            int width=1135;
            double  StartSecond= ((duration/width)*mouseEvent.getX());
     
        //   System.out.println("Startsecondduration------------------>\t"+StartSecond);
           plot.setStartSecond(StartSecond);
     
            
            
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"start_time"+mousePosX1+"startsample"+d);
            
            }
        });
lineChart.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
               rectX.set(mouseEvent.getX());
               rectY.set(mouseEvent.getY());
//orgSceneX = mouseEvent.getSceneX();
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               // double offsetX = event.getSceneX()- orgSceneX;
        
                
                r.setX(rect.getX());
                r.setY(rect.getY());
                r.setWidth(rect.getWidth());
                r.setHeight(rect.getHeight());
            
            mousePosX2=event.getX();
            //  mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
           // double lastduration=doubleValue/duration;
           
             double width=1135;
          double factor=(duration*1000)/width;
                System.out.println("factor"+factor);
          double movefactor=event.getX()*factor;////calculate duration
         int milli=(int)movefactor%1000;
         String mm=String.valueOf(milli);
          
      int second=(int)movefactor/1000;
        String ss=String.valueOf(second);    
         
        
      //  double duration = plot.getduration();
   double  LastSecond= ((duration/width)*event.getX());
     
   //  System.out.println("lastsecondduration-------------------->\t"+LastSecond);
  
        
        plot.setLastSecond(LastSecond);
        
          
            System.out.println("lastduration"+movefactor+"\t"+ss+"\t"+mm+"duration\n"+duration+"\n");
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println(""+input+"\nCopied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
      //     generate_text_file(input);
                



// Hide the rectangle
              // rectX.set(0);
             //  rectY.set(0);// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

//////////////////////////END Of the final mouselistener///////////////////////////////////

     
     
     
     
     
     
   /* lineChart.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) 
            {  mousePosX1=event.getX();
               mousePosX2=0;           
               setStartSample(mousePosX1);
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    } 
    );
        
   */ 
   
//pane.setHgap(10);
//pane.setVgap(10);
//VBox root = new VBox();
//root.getChildren().add(pane);
//scene = new Scene(root);
//lineChart.setOnMouseDragged(mouseHandler);
//lineChart.setOnMousePressed(mouseHandler);
//lineChart.setOnMouseReleased(mouseHandler);
//rect = getNewRectangle();
//rect.widthProperty().bind(rectX.subtract(rectinitX));
//rect.heightProperty().bind(rectY.subtract(rectinitY));
//vb.getChildren().add(rect);
//lineChart.getData().add(rect);
//wavepane.setContent(rect);///////add the rect 





/*
lineChart.addEventHandler(MouseEvent.DRAG_DETECTED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            mousePosX1=e.getX();
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"startsample"+d);
          // Rectangle r=new Rectangle(0,0,1180,350);
          // wavepane.setContent(r);
         //  scene =new Scene(r);
         //  stage.setScene(scene);
         //  stage.show();
            
        }
});
   */
  /* lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) 
        {
            mousePosX2=e.getX();
            mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           Copied_Samples=plot.getEndSample()-plot.getStartSample();
           System.out.println("Copied_Samples"+Copied_Samples);    
           SeT_No_Of_Samples_Copied(Copied_Samples);
           generate_text_file(input);
           
        }

            

           
       });
     
     */   
    
  } 
    
    
    
    
    
    LineChart lineChart1;
    
     public void tempplot1(Stage stage,String filename,double[] samples,int numsamples,Tab tab1,TabPane TP,ScrollPane wavepane,Double sam_freq,double duration, int check)throws Exception{
        TP.getSelectionModel().select(tab1);
        xAxis= new NumberAxis();
       xAxis.setAutoRanging(true);//("",0d,duration,0.05);
       xAxis.setForceZeroInRange(false);
        NumberAxis yAxis = new NumberAxis("", -1d, 1d, 1);
      
        lineChart1= new LineChart(xAxis, yAxis);
        java.nio.file.Path p=Paths.get(filename);
      
        String substring= p.getFileName().toString();
      // stage.setTitle(substring);
        tab1.setText(substring);  
     //  lineChart.setMaxHeight(170);
       
       
       
       if(check==0){
            lineChart= new LineChart(xAxis, yAxis);
        
        yAxis.tickMarkVisibleProperty();
       
        
        //XYChart.Series<Integer,Double> dataSeries1 = new XYChart.Series<>();
      //  XYChart.Data<Integer,Double> data = new XYChart.Data<>();
       
   final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
   
     XYChart.Series series = new XYChart.Series( );
       lineChart.setCreateSymbols(false);
     
     lineChart.getData().clear();
      
     lineChart.getData().add(series); 
      
       for(int i=0;i<numsamples;i++){
     
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         data.add(dd);
          
          
       }
      //data.remove(0,5000);
       series.setData(data);
       //series.getData().remove(0, 2000);
       
    
       //series.getData().setAll(dd);
       //series.setData(data);
              
       
       
     //dataSeries1.getData().add(data);
     
  
    // XYChart.Series=new XYChart.Series<>
     //xAxis.setUpperBound(2000);
     //xAxis.setLayoutX(factor);
     //series.setName("yyyyyy");
    // xAxis.autosize();
    xAxis.setSide(Side.TOP);
   
    
     //
     
     
     
     
     
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
//        tab1.setText(substring);
        TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
    
   //lineChart.setPrefHeight(402);
     lineChart.setPrefWidth(1180);
     lineChart.setMinWidth(1180);
     
    lineChart.setStyle(this.getClass().getResource("test.css").toExternalForm());
     wavepane.setContent(lineChart);
     
       }
     
     else if(check==1){
         
       
     lineChart.setMaxHeight(170);
     //lineChart.setPrefWidth(50);
     lineChart.setMinHeight(170);////////key step of resampling
     
       xAxis= new NumberAxis("",0d,duration,0.05);
       NumberAxis yaxis = new NumberAxis("", -1d, 1d, 1);
            
       java.nio.file.Path p1=Paths.get(filename);
       String substring1= p1.getFileName().toString();
     //  stage.setTitle(substring);
       tab1.setText(substring);  
       yAxis.tickMarkVisibleProperty();
       
       final   ObservableList<XYChart.Data<Double,Double>> data1 = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();
    
       for(int i=0;i<numsamples;i++){
       
         dd = new XYChart.Data<>(i/sam_freq,samples[i]);
         
         data1.add(dd);
        
       }
   
     XYChart.Series series1 = new XYChart.Series(data1);
  
    
    xAxis.setSide(Side.BOTTOM);
   
     lineChart1.setCreateSymbols(false);
     
     lineChart1.getData().clear();
      
     lineChart1.getData().add(series1); 
  ///////////////////////////////////////////////////    
   ///////////////////////////////////////////////////   
     
     
     
        System.out.println("upper   "+yAxis.getUpperBound()+"lower     "+yAxis.getLowerBound());
    
     lineChart1.setLegendVisible(false);
       boolean verticalGridLinesVisible1 = lineChart1.getVerticalGridLinesVisible();
      verticalGridLinesVisible1=true;
      boolean horizontalGridLinesVisible1 = lineChart.isHorizontalGridLinesVisible();
      horizontalGridLinesVisible1=true;
        
      lineChart1.setCreateSymbols(false);
      lineChart1.setAnimated(false);
      lineChart1.setStyle(this.getClass().getResource("test.css").toExternalForm());
     wavepane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
     wavepane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
     wavepane.setFitToWidth(true);
     wavepane.setFitToHeight(true);
     Platform.setImplicitExit(false);
     VBox root = new VBox();
     root.getChildren().addAll(lineChart1,lineChart);
        
 //       lineChart1.setMaxHeight(170);
      //  lineChart.setMaxHeight(170);
    //    root.getChildren().add(lineChart);
    //    root.setMinHeight(340);
   //     root.setPrefHeight(340);
   //     root.setMaxHeight(340);
       // root.fillWidthProperty().set(false);
        
        wavepane.setContent(root);
        
      // wavepane.setPannable(true);
     }
  
       
       final double SCALE_DELTA = 1.1;
lineChart.setOnScroll(new EventHandler<ScrollEvent>() {
    public void handle(ScrollEvent event) {
        event.consume();

        if (event.getDeltaY() == 0) {
            return;
        }

        double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : 1 / SCALE_DELTA;

        lineChart.setScaleX(lineChart.getScaleX() * scaleFactor);
        //lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
    }
});

lineChart.setOnMousePressed(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            lineChart.setScaleX(1.0);
            lineChart.setScaleY(1.0);
        }
    }
});

final double SCALE_DELTA1 = 1.1;
lineChart1.setOnScroll(new EventHandler<ScrollEvent>() {
    public void handle(ScrollEvent event) {
        event.consume();

        if (event.getDeltaY() == 0) {
            return;
        }

        double scaleFactor1 = (event.getDeltaY() > 0) ? SCALE_DELTA1 : 1 / SCALE_DELTA1;

        lineChart1.setScaleX(lineChart1.getScaleX() * scaleFactor1);
        //lineChart.setScaleY(lineChart.getScaleY() * scaleFactor);
    }
});

lineChart1.setOnMousePressed(new EventHandler<MouseEvent>() {
    public void handle(MouseEvent event) {
        if (event.getClickCount() == 2) {
            lineChart1.setScaleX(1.0);
            lineChart1.setScaleY(1.0);
        }
    }
});


//////check////





   /*  final NumberAxis axis = (NumberAxis) lineChart.getXAxis();
        final double lowerX = axis.getLowerBound();
        final double upperX = axis.getUpperBound();
     
     
      lineChart.setOnScroll(new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                final double minX = axis.getLowerBound();
                final double maxX = axis.getUpperBound();
                double threshold = minX + (maxX - minX) / 2d;
                double x = event.getX();
                double value = axis.getValueForDisplay(x).doubleValue();
                double direction = event.getDeltaY();
                if (direction > 0) {
                    if (maxX - minX <= 1) {
                        return;
                    }
                    if (value < threshold) {
                        axis.setLowerBound(minX + 1);
                    } else {
                        axis.setUpperBound(maxX - 1);
                    }
                } else {
                    if (value < threshold) {
                        double nextBound = Math.max(lowerX, minX - 1);
                        axis.setLowerBound(nextBound);
                    } else {
                        double nextBound = Math.min(upperX, maxX + 1);
                        axis.setUpperBound(nextBound);
                    }
                }

            }
        });

      */
    
      
 // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
 //    wavepane.getStylesheets().add(this.getClass().getResource("test.css").toExternalForm());

   /* lineChart.setOnDragDetected(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) 
            {  mousePosX1=event.getX();
               mousePosX2=0;           
               setStartSample(mousePosX1);
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
    } 
    );
        
   */ 
   
//pane.setHgap(10);
//pane.setVgap(10);
//VBox root = new VBox();
//root.getChildren().add(pane);
//scene = new Scene(root);
//lineChart.setOnMouseDragged(mouseHandler);
//lineChart.setOnMousePressed(mouseHandler);
//lineChart.setOnMouseReleased(mouseHandler);
//rect = getNewRectangle();
//rect.widthProperty().bind(rectX.subtract(rectinitX));
//rect.heightProperty().bind(rectY.subtract(rectinitY));
//lineChart.getData().add(rect);
//wavepane.setContent(rect);///////add the rect 

/*Rectangle dragBox = new Rectangle(0, 0, 0, 0);
dragBox.setVisible(false);
lineChart.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
    @Override
    public void handle(MouseEvent mouseEvent) {
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED){
            dragBox.setVisible(true);
            dragBox.setTranslateX(mouseEvent.getX());
            dragBox.setTranslateY(mouseEvent.getY());
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_DRAGGED && dragBox.isVisible()){
            dragBox.setWidth(mouseEvent.getX() - dragBox.getTranslateX());
            dragBox.setHeight(mouseEvent.getY() - dragBox.getTranslateY());
        }
        if(mouseEvent.getEventType() == MouseEvent.MOUSE_RELEASED){
            dragBox.setVisible(false);
        System.out.println("Rectangle is created.......");
        XYChart.Data data = new XYChart.Data();
        data.setNode(dragBox);
        
        
        XYChart.Series series1 = new XYChart.Series();
            
            series1.getData().add(data);
            lineChart.getData().add(series1);
        }
    }
});

 
*/


/*
lineChart.addEventHandler(MouseEvent.DRAG_DETECTED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            mousePosX1=e.getX();
            mousePosX2=0;
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX1);
            double doubleValue = valueForDisplay.doubleValue();
            plot.setMouseStartpos(doubleValue);
            double d=doubleValue*sam_freq;
            plot.setStartSample(d);
            System.out.println("startposition"+xAxis.getValueForDisplay(mousePosX1)+"startsample"+d);
           // Rectangle r=new Rectangle(0,0,1180,350);
           // wavepane.setContent(r);
           //stage.setScene(scene);
           //stage.show();

        }
});
   
   lineChart.addEventHandler(MouseEvent.MOUSE_RELEASED, 
    new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) 
        {
            mousePosX2=e.getX();
            mousePosX1=0;    
            Number valueForDisplay = xAxis.getValueForDisplay(mousePosX2);
            double doubleValue = valueForDisplay.doubleValue();
            double d=doubleValue*sam_freq;
            plot.setEndSample(d);
            plot.setMouseEndpos(doubleValue);
           // System.out.println("\nendposition"+xAxis.getValueForDisplay(mousePosX2)+"endsample"+d);
           // System.out.println("total sample\t"+plot.getSamplingPositions());
           System.out.println("startsample after dragover\t"+plot.getStartSample()+"endsample after dragover\t"+plot.getEndSample());
           String input="\nMouseStartPosition\t"+plot.getMouseStartpos()+"\nMouseEndPosition\t"+plot.getMouseEndpos()+"\nStartSample-->\t"+plot.getStartSample()+"\nendSample-->\n"+plot.getEndSample();
           generate_text_file(input);      
           
        }

           
       });
     
        }
    
  */} 
    
    
  public void SeT_No_Of_Samples_Copied(int setSample) {
            Copied_Samples=setSample;
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }  
    
    
    public int Get_No_Of_Samples_Copied(){
    return Copied_Samples;    
    }
    
    
    
    
    
    
    
    
    
  private void generate_text_file(String input) {
         
//////////////write to the file for articulation

 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
  String cexedir = currentDir + "//cexe//";
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
              
  /////////////end of the file writer////////////////            
           
        



    
    
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
       /*
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
         */
         
         
         
     
                               
        

        
        
        
        
        
        
        
        
        
    
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
        
       public void paste(AudioInputStream audioInputStream,Tab tab1 ,TabPane TP,ScrollPane wavepane) throws Exception
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
                          int size = (int) newInputStream.getFrameLength()*2;
                          byte audioData [] = new byte[size];
                          System.out.println("ffffff = "+  newInputStream.getFrameLength() + "  " + newInputStream.getFormat());
 /////ask about this read function                         int bytesRead = newInputStream.read(audioData);
            //               System.out.println("bytes read = "+ bytesRead);
                           format = audioInputStream.getFormat();
                           double sampling_freq=format.getSampleRate();
                         numSamples = (int) newInputStream.getFrameLength();
                           System.out.println("audio data length"+audioData.length);
   // frames_per_pixel=audioData.length/1292;
     //   System.out.println("frames_per_pixel"+frames_per_pixel);
                          double samples[] = readAudioData(audioData);
                           long audioFileLength = audioData.length+44;//new File( filename).length();/////////////////////////adding 44 to match new File() length
                         frameSize = format.getFrameSize();
                          frameRate = format.getFrameRate();
                       frames_per_pixel = (audioFileLength / (frameSize * frameRate));///////in fact this is duration
                      actual_frames_per_pixel=audioData.length/1180/2;
                       System.out.println("actual_frames_per_pixel\t"+actual_frames_per_pixel);
                       String filename="copy&Paste";
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                         pastenfx.startforplotwave(classStage,samples,numSamples,filename,tab1,TP,wavepane,sampling_freq,frames_per_pixel,newInputStream,frameSize,actual_frames_per_pixel);
                            CutAudioInputStream.setCutWave(null);
                        }
                    }
               
            
     }  
    
        
 double[] readAudioData(byte audioBytes[]){
     
     
      int audioData[]  = {};
      if (format.getSampleSizeInBits() == 16) {
                    int nlengthInSamples = audioBytes.length / 2;
                    audioData = new int[nlengthInSamples];
                    if (format.isBigEndian()) {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i];
                            /* Second byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                        }
                    } else {
                        for (int i = 0; i < nlengthInSamples; i++) {
                            /* First byte is LSB (low order) */
                            int LSB = (int) audioBytes[2 * i];
                            /* Second byte is MSB (high order) */
                            int MSB = (int) audioBytes[2 * i + 1];
                            audioData[i] = MSB << 8 | (255 & LSB);
                            
                            
                           /* File fi=new File("C:\\TATA-V-42\\src\\Speech\\WavePanel\\Au.txt");
	                    FileWriter fw=new FileWriter(fi,true);
                            BufferedWriter tout = new BufferedWriter(fw);
	                    tout.write(String.valueOf(audioData[i]));
                            tout.newLine();
                            tout.flush();
                            tout.close();  */
                            //System.out.println(audioData[i]);
                        }
                        
//calculating the maximum and minimum amplitude 
                           int maximum = audioData[0];   // start with the first value
                           for (int m=1; m<audioData.length; m++) {
                           if (audioData[m] > maximum) {
                           maximum = audioData[m];   // new maximum
                           
                            }
                        }
                           
                         
                           int minimum = audioData[0];   // start with the first value
                           for (int k=1; k<audioData.length; k++) {
                           if (audioData[k] < minimum) {
                           minimum = audioData[k];   // new maximum
                           
                            }
                        }
                           
                            }
                        }
                                
                                     
                                
                            
                           
                 else if (format.getSampleSizeInBits() == 8) {
                    int nlengthInSamples = audioBytes.length;
                    audioData = new int[nlengthInSamples];
                    if (format.getEncoding().toString().startsWith("PCM_SIGN")) {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i];

                        }
                    } else {
                        for (int i = 0; i < audioBytes.length; i++) {
                            audioData[i] = audioBytes[i] - 128;

                        }
                    }
                }
      
                    sigProc = new SignalProc();
                    double[] audioDataNormalize1 = sigProc.normalize(sigProc.intToDouble(audioData), 1400);
      
      return audioDataNormalize1;
 }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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
       private double CheckHypernasality(String filelocName,AnchorPane resultpane,Stage stage) 
       {
             
               try {        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
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
                                     PlotProbability plot1=new  PlotProbability(this);
                                       plot1.plotfunction(resultpane,stage);
                                       plot1.setstageStage(stage);
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
 public double Hypernasality(String filename,AnchorPane resultpane,Stage stage){
 
 
    
                        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
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
                                     PlotProbability plot1=new  PlotProbability(this);
                                       plot1.plotfunction(resultpane,stage);
                                       plot1.setstageStage(stage);
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
    public static double getvaluefromc()
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

    void getrecord(int Is) {
        System.out.println("value in getrecord"+Is);
        getrecordvalue=Is;
        System.out.println("value in getrecord"+getrecordvalue);
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   int getrecordvalue() {
      System.out.println("value from  getrecordvalue"+ getrecordvalue); 
      return  getrecordvalue;//=Is_record_done;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public int x=getrecordvalue();

               
 /////////////////select and check hypernasality///////////// 
       
 public String Select_Check_hypernalasityclick(String getfilename) throws UnsupportedAudioFileException, IOException{
      
      int Samples_Copied = this.Get_No_Of_Samples_Copied();     
      AudioInputStream inputStream = null;
      AudioInputStream shortenedStream = null;
      File file = new File(getfilename);
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      AudioFormat format = fileFormat.getFormat();
      inputStream = AudioSystem.getAudioInputStream(file);
      int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
        //    inputStream.skip((long) (plot.getMouseStartpos()*bytesPerSecond));
        // inputStream.skip(Samples_Copied);
        double startSecond = plot.getStartSecond();
        double LastSecond=plot.getLastSecond();
        long StartByte=(long) startSecond*bytesPerSecond ;
       inputStream.skip(StartByte);
      
      ////////////////////////////////////////////
      /* inputStream.skip(startSecond * bytesPerSecond);///startduration in seconds
      long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();///secondstocopy i.e.ending seconds
      shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
     */
      ///////////////////////////////////////////////
     long framesOfAudioToCopy;
     framesOfAudioToCopy = (long) LastSecond * (long)format.getFrameRate();
  //  long framesOfAudioToCopy=2;
     // long framesOfAudioToCopy =endSample;
    // System.out.println("startsecond\t"+plot.getStartSecond()+"LastSecond\t"+plot.getLastSecond()); 
     
     shortenedStream = new AudioInputStream(inputStream, format,framesOfAudioToCopy);
      String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                     //   String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                        String cexedi=currentDir+"\\cexe\\";
                   
     
     String HypernasalityFile =cexedi+"Hypernasal.wav";//rightClick.saveLocation();                           later add record here
       File destinationFile = new File(HypernasalityFile);
      AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
    
     // Plotwave plot=new Plotwave();
      
     double[] samples = plot.readWaveData(HypernasalityFile);
     return HypernasalityFile;
     //RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);
     //duration=plot.getduration();
   //     factor=plot.getsampfrq();
        //numSamples=plot.getnumsamples();
    //                 double[] fpoints;
                    // fpoints= rdpf.filter(samples);
  //                   int numsamples=samples.length;
   //     double duration=numsamples/ factor;
        
  //     System.out.println(" file is created-->"+factor+numsamples+duration);       
        
        
 
  
  
  }     
    
    
//////////////////////////////////////////////////////    
    
    
      
   
      
   
   
   
   
   
}
class HoveredThresholdNode extends StackPane {
    HoveredThresholdNode(double value) {
      setPrefSize(15, 15);

      final Label label = createDataThresholdLabel(value);

      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().setAll(label);
          setCursor(Cursor.NONE);
          toFront();
        }
      });
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getChildren().clear();
          setCursor(Cursor.CROSSHAIR);
        }
      });
      
      
  }

    HoveredThresholdNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     private Label createDataThresholdLabel(double value) {
      final Label label = new Label(value + "");
      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
      label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

     /*if (priorValue == 0) {
       label.setTextFill(Color.DARKGRAY);
      } else if (value > priorValue) {
        label.setTextFill(Color.FORESTGREEN);
      } else {
        label.setTextFill(Color.FIREBRICK);
      }

      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
      
    }*/
    
    return label;
    
    
    
}

      
      
}    
      
      
    