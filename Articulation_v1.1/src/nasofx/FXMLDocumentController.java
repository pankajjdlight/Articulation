/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

//import java.awt.event.MouseEvent;
import java.awt.Desktop;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
//import javazoom.jl.decoder.JavaLayerException;
/**
 *
 * @author IITG
 */
public class FXMLDocumentController extends Application {
      @FXML
    private LineChart<?, ?> lineChartForRecord;
    
    @FXML
    private Label recordlabel;

    @FXML
    private MenuItem copy;
    @FXML
    private MenuItem pasteitem;
       @FXML
    private Slider slider;
        @FXML
    private Tab tab1;
    @FXML
    private Tab tabForPaste; 
    @FXML
    private Tab tabForPaste1; 
           @FXML
    private TabPane TP;
         @FXML
    private ScrollPane wavepane;
    @FXML
    private ScrollPane wavepaneForPaste;  
 @FXML
    private ScrollPane wavepaneForPaste1;     
 
    @FXML
    private AnchorPane tab1ap;
    
    @FXML
    private MenuItem hypernasality;
    @FXML
    private MenuItem scorecard;
    
        @FXML
    private MenuItem saveas;
     @FXML
    public ImageView marker;
     
       @FXML
    private AnchorPane counterap;
        @FXML
    private Label hour;

    @FXML
    private Label min;

    @FXML
    private Label sec;

    @FXML
    private Label milisec;
    @FXML
    private MenuItem closebtn;   
      @FXML
    private Button stopicon;
      private AudioInputStream audioInputStream;
      
      @FXML
    private AnchorPane resultpane;
       @FXML
    private Button recordbtn1;
      
       @FXML
    private CheckMenuItem mi8k;

    @FXML
    private CheckMenuItem mi11k;

    @FXML
    private CheckMenuItem mi16k;

    @FXML
    private CheckMenuItem mi22k;

    @FXML
    private CheckMenuItem mi32k;

    @FXML
    private CheckMenuItem mi44k;

    @FXML
    private CheckMenuItem mi48k;

    @FXML
    private CheckMenuItem mi96k;


      
      public  Media pick;  
       public MediaPlayer player ;
      public  TranslateTransition trans,transtab;
       
       @FXML
    private MenuItem open_menu_item;
         @FXML
    private Button fwdbtn;
         
    @FXML
    private Button rewindbtn;
    @FXML
    private Button stopbtn;
    @FXML
    private Button playbtn;
    
    
    @FXML
    private Button recordbtn;
     @FXML
    private MenuItem selectall;

  
    final Tooltip playtip = new Tooltip();
    final Tooltip pausetip = new Tooltip();
    final Tooltip forwardtip = new Tooltip();
    final Tooltip rewindtip = new Tooltip();
    final Tooltip stoptip = new Tooltip();
    final Tooltip recordtip = new Tooltip();
    
    
    
     
     double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
     @FXML
     final int bufSize = 16384;
    public  double frames_per_pixel;
    
    static int count=0;
    
    String errStr;
    double duration, seconds;
    File file;
    public String fileName = "untitled";
    public String abbfilePath = null;
    Vector lines = new Vector();
    private Toolkit tk;
    public double mousePosX1, mousePosX2, mouseMoveX1, mousePosY1;
    public JPopupMenu menu;
    public StreamBytes streamBytes;
  //  public MainFrame mainFrame;
   // public Mainpopup mainpopup;
//    private SamplingGraph sg;
    private int graphFromScreen = 5, graphVerticalSize = 240, normalPixcel = 60;
    public boolean selectedPlay = false;
  //  public StreamVariables streamVariable;
 //   public RightClickEvent rightClick;
    public String[][] annotationPos;
    public ByteArrayOutputStream capOut;
    private int samplingpanelSize;
    List<List<Integer>> dummyList;
  //  public ServerFinder serverStatus;
    public int xSize;
    public String fileHashValue;
    private double record_duration;
    private boolean buffStatus = true;
    private boolean lineStatus = false;
    public static String filenamefortab;
    public static Stage classStage = new Stage();
    public static Stage classStage1 = new Stage();
    
    private Thread thread;
    private AudioFormat format,format1;  
    private int[] audioDataNormalize;
    private int normalizedValue = 3000;
    public SignalProc sigProc;
    public static String dummy;
    Capture cap = new Capture();
     TranslateTransition trans1= new TranslateTransition();
     TranslateTransition trans2= new TranslateTransition();
 // public  LineChart lineChart ;
      boolean variable=false;
      XYChart.Data<String,Double> dd;
      double[] samples;
      boolean recordstop=false;
    @FXML
    private LineChart wave;
    
    @FXML
    private NumberAxis recordxaxis;
    @FXML
    private LineChart<? ,? > recordinglinechart;
    Thread recordplot;
    NasoFX nfx=new NasoFX();
    double factor;
    int numSamples;
    float frameRate;
    int frameSize;
    int actual_frames_per_pixel;
    Object source ;
    ByteArrayOutputStream capOut1;
      @FXML
    private ImageView marker1;
    Stage getstage(){
      return classStage;
      }
    ///////////////////////////
    Button getrecordbtn(){
    return recordbtn;
    }  
    
    
    //////////////////////////////////
      @FXML
      void shutdown(){
          System.out.println("hjkgvkhgvkhgkhlhl");
      }
      
   @FXML
    void Zoomfunction(MouseEvent event) {
        
        float value = (float) slider.getValue();
        nfx.dozoom(value,wavepane,duration);
        
        
        

    }

    
    @FXML
    void scorecardclick(ActionEvent event) {
        Tab tab1= new Tab();
            tab1.setText("Scorecard  ");
            TP.getTabs().add(tab1);
            TP.getSelectionModel().select(tab1);

    }
    
    
    @FXML
    void markerpress(MouseEvent event) {
        orgSceneX = event.getSceneX();//event.getX();
            //orgSceneY = event.getY();
            orgTranslateX = ((ImageView)(event.getSource())).getTranslateX();
          //  orgTranslateY = ((ImageView)(event.getSource())).getTranslateY();
    }
    
    
    @FXML
    void markerdrag(MouseEvent event) {
        double offsetX = event.getSceneX()- orgSceneX;
        
           // double offsetY = event.getY() - orgSceneY;
            double newTranslateX = orgTranslateX + offsetX;
           // double newTranslateY = orgTranslateY + offsetY;
          // System.out.println("\nThe offset is :"+ newTranslateX);
           
           if(newTranslateX>0.0 && newTranslateX<1140.11){
             
            ((ImageView)(event.getSource())).setTranslateX(newTranslateX);
            //((ImageView)(event.getSource())).setTranslateY(newTranslateY);
           }
           
          /* double intial_second = ((newTranslateX * (this.frames_per_pixel*2)));// / this.audioInputStream.getFormat().getFrameRate()) ;
          //  System.out.println("initial seconds"+intial_second+"\tmarkerx1\t"+orgTranslateX+"\tframespersecond\t"+frames_per_pixel);
                            double x=intial_second*1000; 
                          //  System.out.println("time type cast\t"+x);
           String timeConversion2 = timeConversion2(x);
           //System.out.println("totaltime:\t"+timeConversion2);
           //System.out.println("hour"+timeConversion2.substring(0, 2));
          // System.out.println("minute"+timeConversion2.substring(2, 5));
          // System.out.println("second"+timeConversion2.substring(5, 8));
          // System.out.println("millisecond"+timeConversion2.substring(8, 12));
           hour.setText(timeConversion2.substring(0, 2));
           min.setText(timeConversion2.substring(2, 5));
           sec.setText(timeConversion2.substring(5, 8));
           milisec.setText(timeConversion2.substring(8, 12));
           */
          
          hour.setText("00");
          min.setText("00");
          double width=1135;
          double factor=(frames_per_pixel*1000)/width;
          double movefactor=newTranslateX*factor;
          int milli=(int)movefactor%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)movefactor/1000;
          String ss=String.valueOf(second);
          
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
          
          if(milli>=0 && movefactor < (frames_per_pixel*1000))
          {
          milisec.setText(mm);
          sec.setText(ss);
          
          }
           
           
           
           
           
           
           
           
           
    }
    
   
    @FXML
    void closesystem(ActionEvent event) {
        System.exit(0);

    }
    
    
     
 


    

    
  //  @Override
    public void initialize() 
    {
        // TODO
     /* 
    slider.setMin(0);
    slider.setMax(100);
    slider.setValue(40);
    //slider.setShowTickLabels(true);
    slider.setShowTickMarks(true);
    slider.setMajorTickUnit(50);
    slider.setMinorTickCount(5);
    slider.setBlockIncrement(10);
    
    
   TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
   double a = 0.1;
   int i=0;
  
   
for (i = 3,a=0; i <2100; i+=50,a+=1)
{
    //double a = 0.1;
   String number = Double.toString(a);
    Text t = new Text(i, 23,number);
     double strokeWidth = t.getStrokeWidth();
      System.out.println("the stroke width is  :  "+strokeWidth);
    t.setStrokeWidth(0.1);
    t.setStyle("-fx-text-fill:white; -fx-font-size:10;");
       
      
        t.setStrokeWidth(0.5);
        
       t.setStrokeWidth(0.1);
     t.setStroke(Color.rgb(204, 204, 204));
  
        System.out.println("the new  stroke width is  :  "+strokeWidth);
   
    
    
    
    Line line1 = new Line(i, 20, i, 322);
     line1.setStrokeWidth(0.1);
     
    line1.setStroke(Color.rgb(204, 204, 204));
    
   
    Line line2 = new Line(0, i, 600, i);
    line2.setStroke(Color.LIGHTGRAY); 

    tab1ap.getChildren().addAll(line1,t);
    
}

        Line redLine = new Line(0, 5, 2100, 5);

    redLine.setStroke(Color.rgb(221, 221, 221));
    redLine.setStrokeWidth(10);
    
    tab1ap.getChildren().addAll(redLine);
    
  // marker.toFront();

//counterap.setStyle("-fx-border-color: black");
       
 //nfx.setTab(tab1);
 
  TP.getTabs().addAll(tab1);  
        */
        resultpane.setTranslateX(351);
  
    
 
    }    

    /**
     *
     */
    
    
    
    
    
    
    
    
   
    
    
   
    
    
    
    
    
    @FXML
    
    public void open_button_Event(ActionEvent event) throws Exception
    { 
        //Tab tab1=new Tab();
        //TP.getTabs().add(tab1);
        Plotwave plot=new Plotwave();
        String filename;
        filename=plot.fileopenmethod();//fileopenmethod();
        System.out.println("filename_in 1st time load-------->>>>>>\t"+filename);
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        trans= new TranslateTransition();
        transtab=new TranslateTransition();
        double[] samples = plot.readWaveData(filename);
        sendfilename(filename);
  //  RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.1);
  // Function to auto-check the sample rate and tick the menuitem of samplerate
           this.audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
           AudioFormat audioFormat = audioInputStream.getFormat();
          float sampleRate = audioFormat.getSampleRate();
           System.out.println("The sample rate is : "+sampleRate);
           clearcheckmenuitem();
           tickmenuitem(sampleRate);
           
   //end of tickmenu function
        
        duration=plot.getduration();
        factor=plot.getsampfrq();
        frames_per_pixel=plot.getduration();
        //numSamples=plot.getnumsamples();
                   //  double[] fpoints;
                   // fpoints= rdpf.filter(samples);
                   //  int numsamples=fpoints.length;
                   int numsamples=samples.length;
        
        //double duration=numsamples/ factor;
      
        nfx.tempplot(classStage,filename,samples, numsamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
  
       
    }
 /////////////////////conversion/////////////
  @FXML
    public String SampleRateConversion(File SourceFile, float dSampleRate) throws UnsupportedAudioFileException, IOException
            
    { 
        float fTargetSampleRate = dSampleRate;
        AudioFileFormat SourceFileFormat = AudioSystem.getAudioFileFormat(SourceFile);
        AudioFileFormat.Type TargetFileType = SourceFileFormat.getType();
        AudioInputStream SourceStream = null;
        SourceStream = AudioSystem.getAudioInputStream(SourceFile);
        AudioFormat SourceFormat = SourceStream.getFormat();
        float fTargetFrameRate = fTargetSampleRate;
        
        AudioFormat TargetFormat = new AudioFormat(
                                        SourceFormat.getEncoding(),
                                        fTargetSampleRate,
                                        SourceFormat.getSampleSizeInBits(),
                                        SourceFormat.getChannels(),
                                        SourceFormat.getFrameSize(),
                                        fTargetFrameRate,
                                        SourceFormat.isBigEndian()
        
                                    );
                
        
        AudioInputStream TargetStream = AudioSystem.getAudioInputStream(TargetFormat, SourceStream);
        //OutputStream targetFile = null;
        File targetFile = new File("converted.wav");
        int nWrittenBytes = AudioSystem.write(TargetStream, TargetFileType, targetFile);
        return targetFile.getAbsolutePath();
    }
   

   
    
  
    
    @FXML
    void SR8(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
    mi8k.setSelected(true);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
    String  filename = getfilename();
    File sendfile = new File(filename);
    String newFilename = null;
      newFilename = SampleRateConversion(sendfile, (float) 8000.0);
      System.out.println("The converted file is "+ newFilename);
      Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        trans= new TranslateTransition();
        transtab=new TranslateTransition();
        //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);
      
       // nfx.startforplotwave(classStage,samples,numSamples,newFilename,tab1,TP,wavepane,sampling_freq,frames_per_pixel,this.audioInputStream,frameSize,actual_frames_per_pixel);

    }
    @FXML
    void SR11(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        
        mi8k.setSelected(false);
    mi11k.setSelected(true);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 11025.0);
        System.out.println("The converted file is "+ newFilename);
        
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        
        trans= new TranslateTransition();
        transtab= new TranslateTransition();
        //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);
        

    }
    @FXML
    void SR16(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(true);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 16000.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
       trans= new TranslateTransition();
        transtab= new TranslateTransition();
         //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }
    @FXML
    void SR22(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(true);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 22050.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
       trans= new TranslateTransition();
        transtab= new TranslateTransition();
         //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }
    @FXML
    void SR32(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(true);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 32000.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
       trans= new TranslateTransition();
        transtab= new TranslateTransition();
         //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }
    @FXML
    void SR44(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(true);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 44100.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
       trans= new TranslateTransition();
        transtab= new TranslateTransition();
         //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }
    @FXML
    void SR48(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
        mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(true);
    mi96k.setSelected(false);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 48000.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
      trans= new TranslateTransition();
        transtab= new TranslateTransition();
          //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }
    
    @FXML
    void SR96(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception {
    mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(true);
        String  filename = getfilename();
      File sendfile = new File(filename);
     
      String newFilename = null;
      
      newFilename = SampleRateConversion(sendfile, (float) 96000.0);
        System.out.println("The converted file is "+ newFilename);
        
        Plotwave plot=new Plotwave();
        //Thread.sleep(2000);
       
       // String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(newFilename);
        pick = new Media(new File(newFilename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
       trans= new TranslateTransition();
        transtab= new TranslateTransition();
         //sendfilename(filename);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        nfx.tempplot1(classStage,newFilename, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration,1);

    }

    @FXML
    public void clearcheckmenuitem(){
        
    mi8k.setSelected(false);
    mi11k.setSelected(false);
    mi16k.setSelected(false);
    mi22k.setSelected(false);
    mi32k.setSelected(false);
    mi44k.setSelected(false);
    mi48k.setSelected(false);
    mi96k.setSelected(false);
     
    }

   
    @FXML
    public void tickmenuitem(float sr){
        
    if(sr==8000.0){
        mi8k.setSelected(true);
    }
    else if (sr==11025.0){
        mi11k.setSelected(true);
    }
    else if (sr==16000.0){
        mi16k.setSelected(true);
    }
    else if (sr==22050.0){
        mi22k.setSelected(true);
    }
    else if (sr==32000.0){
        mi32k.setSelected(true);
    }
    else if (sr==44100.0){
        mi44k.setSelected(true);
    }
    else if (sr==48000.0){
        mi48k.setSelected(true);
    }
    else if (sr==96000.0){
        mi96k.setSelected(true);
    }
    
    
    }
    
    
    
    
                  ///////////////////////////////////////////    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        
       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void sendfilename(String filename) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        this.dummy=filename;
        }
    String getfilename(){
        return dummy;
    
    }

    private String timeConversion2(double time1) {
        
        String hD = "", mD = "", sD = "", msd="", totalTD = "";
        try {
            int time=(int)time1;
            int hour = time / (1000*3600);
            int hour_balance = time % (1000*3600);
            int min = hour_balance / (60*1000);
            int min_balance = hour_balance % (60*1000);
            int sec=min_balance/(1000);
            int sec_balance=min_balance% (1000);
           // System.out.println("time\t"+time+"sec\t"+sec+"hour"+hour_balance+"minute"+min);
            if (hour < 10*1000) {
                hD = "0" + hour;
            } else {
                hD = hD + hour;
            }
            if (min < 10*1000) {
                mD = "0" + min;
            } else {
                mD = mD + min;
            }
            if (sec < 60*1000) {
                sD = "0" + sec;
            } else {
                sD = sD + sec;
            }
            if(sec_balance<10){
               msd= "00"+sec_balance;          
             }else{
            msd=msd+sec_balance;
            }

            totalTD = hD + ":" + mD + ":" + sD+":"+msd;
           // System.out.println("timeformat"+totalTD);
        } catch (Exception er) {
          //  Logger.getLogger(PlotWave.class.getName()).log(Level.SEVERE, null, er);
        }
        return totalTD;
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 @FXML
    void hideresult(ActionEvent event) {
          TranslateTransition openNav=new TranslateTransition(new Duration(350), resultpane);
           openNav.setToX(351);
           openNav.play();
        
    }
  ///////////////////////////////select and check hypernasality///////
    
    
   @FXML
    void Select_Check_hypernalasityclick(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        
        if(nfx.Get_No_Of_Samples_Copied()>1){
        
        
        
        String Select_Check_hypernalasityclick = nfx.Select_Check_hypernalasityclick(this.getfilename());
       
        //this.sendfilename(Select_Check_hypernalasityclick);
         
        Tab tab = new Tab();
           tab.setText("Hypernasality  ");
         //  String filename = this.getfilename(); //fileName);
           
            TP.getTabs().add(tab);
            TP.getSelectionModel().select(tab);
            TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            double probability = nfx.Hypernasality(Select_Check_hypernalasityclick,resultpane,classStage);
          
     System.out.println("The probability value is:"+probability);
     
     
    Rectangle rect = new Rectangle(40,100,100 ,40);
    rect.setX(50);
    rect.setY(50);
    
    rect.setFill(Color.rgb(0, 156, 73));

    rect.setStroke(Color.BLACK);
    
    Text t = new Text();
    String value = Double.toString(probability);
    t.setText("The Hypernasality Score is :"+value);
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.RED);
   
    tab.setContent(t);
     File file,file1;
     String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                        System.out.println("correct format"+cexedir);
             String filename1=cexedir+"Hypernasal.wav"; 
             String filename2=cexedir+"mfcc_output_13dim.txt";
    file=new File(filename1);
    file1=new File(filename2);
    if(file.delete()){
    
        System.out.println("file is deleted");}
        else{
        System.out.println("file cannt delete");       
                        }
  /* if(file1.delete()){
    
        System.out.println("file1 is deleted");}
        else{
        System.out.println("file1 cannt delete");       
                        }
   */
    
        }
        else
        {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("");
alert.setHeaderText(null);
alert.setContentText("Please select some samples to analyse hypernasality");

alert.showAndWait();
        
        
        
        
        }
    
    
    
   
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    ////////////////////////end of select check/////////////
   
 @FXML
    void hypernalasityclick(ActionEvent event) {
          final SwingNode swingNode = new SwingNode();
          createAndSetSwingContent(swingNode);
        //  resultpane.getChildren().add((swingNode));
      //    TranslateTransition openNav=new TranslateTransition(new Duration(350), resultpane);
      //     openNav.setToX(0);
      //     openNav.play();
         // Tab tab = new Tab();
      //    tab.setText("Hypernasality  ");
           String filename = this.getfilename(); //fileName);
           
      //      TP.getTabs().add(tab);
       //     TP.getSelectionModel().select(tab);
      //      TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            double probability = nfx.Hypernasality(filename,resultpane,classStage);
          
     System.out.println("The probability value is:"+probability);
     
   /*  
    Rectangle rect = new Rectangle(40,100,100 ,40);
    rect.setX(50);
    rect.setY(50);
    
    rect.setFill(Color.rgb(0, 156, 73));

    rect.setStroke(Color.BLACK);
    
    Text t = new Text();
    String value = Double.toString(probability);
    t.setText("The Hypernasality Score is :"+value);
    t.setFont(Font.font ("Verdana", 20));
    t.setFill(Color.RED);
   
    tab.setContent(t);
   */ 
    
  //  classStage.setScene(new Scene(resultpane));
    //classStage.show();
    }
  private void createAndSetSwingContent(final SwingNode swingNode) {
          JFrame window = new JFrame(); 
           
        //  window.add(DC);
      SwingUtilities.invokeLater(new Runnable() {
                  @Override
                 public void run() {
                   //  swingNode.setContent((JComponent) window.getContentPane());
                  // drawingComponent DC = new drawingComponent();
                  // swingNode.setContent(DC);
                 
                 }
             });
         }
    
    
    
    
    
    
    
 
    @FXML
    void playsound(ActionEvent event) throws FileNotFoundException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        String filename = this.getfilename();
        System.out.println("filename in playsound"+filename);
        String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\Icons\\";
                        System.out.println("correct format"+cexedir);
     String image1="file:\\"+cexedir+"pausebtn.png";
     String image2="file:\\"+cexedir+"play-arrow-(1).png";
  //  Image pause=new Image(image1);
      Image pause = new Image(image1,27,27,false,false);
      //  Image pause = new Image("C:\\Users\\Naso\\Documents\\NetBeansProjects\\NasoDemoWindows\\Icons\\pausebtn.png",27,27,false,false);
      
        // Image pause=new Image("image1",27,27,false,false);
        Image playimage = new Image(image2,15,17,false,false);
     //  Image playimage=new Image(image2);
        MediaPlayer.Status status = player.getStatus();
        
           double d = pick.getDuration().toMillis();
          // System.out.println("The duration of the file is:"+d);
          // double aaa =Double.parseDouble(d);
          // double translate = 1147.77/d;
            //System.out.println("The translate of the file is:"+translate);   
           // TranslateTransition trans= new TranslateTransition();
           
          
            trans.setDuration(Duration.millis(d));
            trans.setToX(1140);
            trans.setNode(marker);
             transtab.setDuration(Duration.millis(d));
            transtab.setToX(1140);
            transtab.setNode(marker1);
            
          // Animation.Status  tttt= trans.getStatus();
           //System.out.println("The animation status is :"+tttt);
           if(!(status == MediaPlayer.Status.PLAYING) && !(status == MediaPlayer.Status.PAUSED)) {
                player.play();
               
                player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = trans.getCurrentTime().toMillis();
      int aa = (int)a;
      
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
          
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                trans.play();
                transtab.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
                player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
        
         }
            
         else if (status == MediaPlayer.Status.PLAYING )
         {
         player.pause();
         trans.pause();
         transtab.pause();
         playtip.setText("play");
         playbtn.setTooltip(playtip);
         playbtn.setGraphic(new ImageView(pause));
         playbtn.setGraphic(new ImageView(playimage));
         
         }
         else if(status == MediaPlayer.Status.PAUSED){
          player.play();
          player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
   @Override
   //is usually updated every 100 ms
   public void changed(ObservableValue<? extends Duration> observable,
     Duration oldValue, Duration newValue) {
       
      double a = player.getCurrentTime().toMillis();
      int aa = (int)a;
       
       int milli=(int)aa%1000;
          String mm=String.valueOf(milli);
          
          int second=(int)aa/1000;
          String ss=String.valueOf(second);
          if(ss.length()<2){
          ss= "0"+ss;
          }
          if(mm.length()==1){
          mm= "00"+mm;
          }
          if (mm.length()==2){
          mm= "0"+mm;
          }
              
          milisec.setText(mm);
          sec.setText(ss);
          
           
   }
        });
                trans.play();
                transtab.play();
                playtip.setText("pause");
                playbtn.setTooltip(playtip);
                playbtn.setGraphic(new ImageView(pause));
               player.setOnEndOfMedia(new Runnable() {
            @Override public void run() {
                try {
                       resetmedia();
                       System.out.println("M ON");
                       playtip.setText("play");
                       playbtn.setTooltip(playtip);
                       playbtn.setGraphic(new ImageView(playimage));
                       
                } catch (MalformedURLException ex) {
                       Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                 });
                 }
         
     }
    
  @FXML
    void stopsound(ActionEvent event) throws MalformedURLException {
         
          String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                        System.out.println("correct format"+cexedir);
     String image1=cexedir+"record_1.png";
     String image2=cexedir+"play-arrow-(1).png";
       // Image record = new Image(image1,27,27,false,false);
          Image record = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\record_1.png",27,27,false,false);
       
         
          
         recordbtn1.setGraphic(new ImageView(record));
         count++;
         
         MediaPlayer.Status status = player.getStatus();
                 
         if (status == MediaPlayer.Status.PLAYING )
         { Image playimage = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\play-arrow-(1).png",15,17,false,false);
              player.stop();
              trans.stop();
              transtab.stop();
              resetmedia();
              playtip.setText("play");
              playbtn.setTooltip(playtip);
              playbtn.setGraphic(new ImageView(playimage));
              
         }

    }
    
     @FXML
    void forwardseek(ActionEvent event) {
        
        player.seek(player.getCurrentTime().multiply(1.5));
        trans.playFrom(trans.getCurrentTime().multiply(1.5));
        transtab.playFrom(trans.getCurrentTime().multiply(1.5));
        
    }
    
    
    @FXML
    void rewindseek(ActionEvent event) {
        player.seek(player.getCurrentTime().divide(1.5));
        trans.playFrom(trans.getCurrentTime().divide(1.5));
         transtab.playFrom(trans.getCurrentTime().divide(1.5));
    }
    
    
       @FXML
    void rewindenter(MouseEvent event) {
        rewindtip.setText("rewind");
        rewindbtn.setTooltip(rewindtip);

    }
      @FXML
    void fwdenter(MouseEvent event) {
        forwardtip.setText("forward");
        fwdbtn.setTooltip(forwardtip);
    }
      @FXML
    void playenter(MouseEvent event) {
        playtip.setText("play");
        playbtn.setTooltip(playtip);
    }
      @FXML
    void stopenter(MouseEvent event) {
        stoptip.setText("stop");
        stopbtn.setTooltip(stoptip);
    }
     @FXML                                                          
    void recordenter(MouseEvent event) {
        recordtip.setText("record");
        recordbtn.setTooltip(recordtip);
    }
    
    void resetmedia() throws MalformedURLException{
      String  filename = getfilename();
      File file = new File(filename);
          pick = new Media(file.toURI().toURL().toExternalForm());
          player = new MediaPlayer(pick);
         // trans= new TranslateTransition();
          trans.stop();
          transtab.stop();
      
           marker.setTranslateX(0);
           marker1.setTranslateX(0);
           milisec.setText("000");
           sec.setText("00");
         
         
         // System.out.println("The marker translate is :"+marker.getTranslateX());
          //System.out.println("The getX of the marker is :"+marker.getLayoutX());
    }
    
    
      @FXML
    void selectallfunction(ActionEvent event) 
    {
           //Graphics g;
        
       // this.paint(g);
       
        nfx.selectAll(numSamples, frames_per_pixel);
        
        
        
        
        
        

    }
    //@FXML
   

                        //End Selection
    @FXML
    void openwebpage(ActionEvent event) {
try {
    Desktop.getDesktop().browse(new URL("http://www.iitg.ac.in/shri/nS/").toURI());
} 
catch (Exception e)
{}
   
    }
   
    
    
    
    
    
    
    
        
        /////////////saveas function/////////
        
        @FXML
     void saveas(ActionEvent event) throws UnsupportedAudioFileException, IOException, LineUnavailableException, Exception
             
        { 
          Plotwave plot=new Plotwave();
            //System.out.println("sampling positions"+plot.getSamplingPositions());
    
          // byte[] current = streamBytes.getCurrent();
         //   System.out.println("current"+Arrays.toString(current));
//do        nfx.saveas(this.audioInputStream,frameSize, actual_frames_per_pixel);
        
        if(nfx.Is_record==1)
        {
            String filename = nfx.AfterRecordSave();
            Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText("File is saved successfully!");

alert.showAndWait();
            
  /*         
            pick = new Media(new File(filename).toURI().toURL().toExternalForm());
            player = new MediaPlayer(pick);
            trans= new TranslateTransition();
        
             double[] samples = plot.readWaveData(filename);
             sendfilename(filename);
             this.audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
             AudioFormat audioFormat = audioInputStream.getFormat();
             float sampleRate = audioFormat.getSampleRate();
             System.out.println("The sample rate is : "+sampleRate);
             clearcheckmenuitem();
             tickmenuitem(sampleRate);
           
   //end of tickmenu function
        
        duration=plot.getduration();
        factor=plot.getsampfrq();
        frames_per_pixel=plot.getduration();
        //numSamples=plot.getnumsamples();
                   //  double[] fpoints;
                   // fpoints= rdpf.filter(samples);
                   //  int numsamples=fpoints.length;
                   int numsamples=samples.length;
        
        //double duration=numsamples/ factor;
        
        
  nfx.tempplot(classStage,filename,samples, numsamples, tabForPaste, TP, wavepane, factor, duration);
  
     */       
            
            
            
        
        }
        
        }
        
     
        @FXML
     void copy(ActionEvent event)
        {   
            Plotwave plot=new Plotwave();
          // byte[] current = streamBytes.getCurrent();
          //   System.out.println("current"+Arrays.toString(current));
          // nfx.saveas(this.audioInputStream,numSamples,frames_per_pixel);
///do  nfx.copy(this.audioInputStream, frameSize , actual_frames_per_pixel, frameRate);
          
            
            System.out.println("NoOfSampledCopied-->"+nfx.Get_No_Of_Samples_Copied() );
        }
        @FXML
        void paste(ActionEvent event) throws Exception
        {   int Samples_Copied = nfx.Get_No_Of_Samples_Copied();
           // Tab tab1 = new Tab();
           // tab1.setText("untitled ");
           //String filename = this.getfilename(); //fileName);
           // ScrollPane wavepane1=new ScrollPane();
           // tab1.setContent(wavepane1);
          //  TP.getTabs().add(tab1);
          //  TP.getSelectionModel().select(tab1);
           // TP.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            AudioInputStream inputStream = null;
           AudioInputStream shortenedStream = null;
    
      File file = new File(getfilename());
      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
      AudioFormat format = fileFormat.getFormat();
      inputStream = AudioSystem.getAudioInputStream(file);
    //  int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
     // inputStream.skip(startSecond * bytesPerSecond);
      //long framesOfAudioToCopy = secondsToCopy * (int)format.getFrameRate();
      shortenedStream = new AudioInputStream(inputStream, format, Samples_Copied);
      String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                        String cexedir = currentDir + "\\cexe\\";
                        System.out.println("correct format"+cexedir);
                       
      String pasteFile=cexedir+"paste.wav";
      
      //String pasteFile ="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\paste.wav";//rightClick.saveLocation();                           later add record here
       File destinationFile = new File(pasteFile);
       AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
       Plotwave plot=new Plotwave();
      
       double[] samples = plot.readWaveData(pasteFile);
       sendfilename(pasteFile);
     //RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);
     //duration=plot.getduration();
        factor=plot.getsampfrq();
        //numSamples=plot.getnumsamples();
                     double[] fpoints;
                    // fpoints= rdpf.filter(samples);
                     int numsamples=samples.length;
        double duration=numsamples/ factor;
        nfx.tempplot(classStage,pasteFile,samples, numsamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
  
      
      
      
      
      
      
      
      
    //  nfx.tempplot(classStage, pasteFile, samples, numSamples, tabForPaste, TP, wavepaneForPaste, factor, duration);
            
            
            
            
            
            
            
            
            
////do            nfx.paste(this.audioInputStream,tab1,TP,wavepane1);
            
        
        }
        
      private IntegerProperty timeSeconds = new SimpleIntegerProperty();
      private IntegerProperty timeSeconds1 = new SimpleIntegerProperty();
      private IntegerProperty timeSeconds2 = new SimpleIntegerProperty();      
      private Timeline timeline; 
      private Duration time = Duration.ZERO;
         
        
        
        
        
        
        
     
            @FXML
             void record(ActionEvent event) throws UnsupportedAudioFileException, IOException, Exception
             {  
                 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                    //   String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                       // String cexedir = currentDir + "\\Icons\\";
                        //System.out.println("correct format"+cexedir);
   //  String image1="file:\\"+cexedir+"pausebtn.png";
    // String image2="file:\\"+cexedir+"record_1.png";
  
                       
                       String cexedir = currentDir +"\\cexe\\";
                        String cexedi=currentDir+"\\Icons\\";
                      //  System.out.println("correct format"+cexedir);
     String image1="file:\\"+cexedi+"pausebtn.png";
     String image2="file:\\"+cexedi+"record_1.png";
                TP.getSelectionModel().select(tab1);
       Image pause = new Image(image1,27,27,false,false);
         Image record = new Image(image2,27,27,false,false);
            
         if(count%2==0){
         
         recordbtn1.setGraphic(new ImageView(pause));
         count++;
        
         }
         else
         {
         /////record stop////////////////    
                   timeline.stop();
                   recordstop=true;
                   nfx.Is_record=1;
                   recordplot.stop();
                   recordplot.suspend();
                 
                   cap.stop();
                   variable=false;
                   trans1.stop();
                   marker.setTranslateX(0);
                   marker1.setTranslateX(0);
                   
                   String filename =cexedir+"record.wav";
                   pick = new Media(new File(filename).toURI().toURL().toExternalForm());
                   player = new MediaPlayer(pick);
                   trans= new TranslateTransition();
                   sendfilename(filename);   
          
                   //////////////end of record stop///////////////////////    
                    recordbtn1.setGraphic(new ImageView(record));
                      count++;
                    return;
         }
                // rewindbtn.setDisable(true);
               //  fwdbtn.setDisable(true);
               ///  stopbtn.setDisable(true);
               //  playbtn.setDisable(true);
      //    String filenameForRecord="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\record.wav";             
       //       byte[] audiobytes = StreamConverter.wavefileToBytes(new File(filenameForRecord));
          
                 

                 //////////////////timer///////////////    
                sec.textProperty().bind(timeSeconds.asString());
                milisec.textProperty().bind(timeSeconds1.asString());
                min.textProperty().bind(timeSeconds2.asString());
               
                 if (timeline != null) {
                  //  splitTime = Duration.ZERO;
                   // splitTimeSeconds.set(splitTime.toSeconds());
                } else {
                    timeline = new Timeline(
                        new KeyFrame(Duration.millis(100),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) 
                            {
                                Duration duration = ((KeyFrame)t.getSource()).getTime();
                                time = time.add(duration);
                           //     splitTime = splitTime.add(duration);
                                timeSeconds.set((int) time.toSeconds()%60);
                                timeSeconds1.set((int) time.toMillis()%1000);
                                timeSeconds2.set((int) time.toMinutes());
                             //   splitTimeSeconds.set(splitTime.toSeconds());
                            
                            }
                        }
                        )
                    );
                    timeline.setCycleCount(Timeline.INDEFINITE);
//Dont play it now                    timeline.play();
                }
                          
                 
              //////////end of the timer/////////////   
               String filename=cexedir+"sahana_s1.wav";
               AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
               format= audioInputStream.getFormat();
               float freq = format.getSampleRate();
                // variable=true;              
               cap.start();
                     
 /////////////////plot waveform while recording////////////////////////////////////////          
  
 //  RecordTask task=new RecordTask();
            //     recordlabel.textProperty().bind(task.messageProperty());
        //  final ObservableList<XYChart.Data<String,Double>> data = FXCollections.<XYChart.Data<String,Double>>observableArrayList();
       // for(int i=0;i<10;i++)
      //  {
     //   dd = new XYChart.Data<>(String.valueOf(i),i*1.5);
     //   data.add(dd);
      // } 
          XYChart.Series series = new XYChart.Series();
           //  wavepane.setStyle(this.getClass().getResource("test.css").toExternalForm());
           lineChartForRecord.getData().clear();
           lineChartForRecord.getData().add(series);
        //  series.dataProperty().bind(task.valueProperty());
        //  series.setData(task.getValue());
          Plotwave plot=new Plotwave();
// String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
          // samples = plot.readWaveData(filename);
          // factor=plot.getsampfrq();
         //  numSamples=plot.getnumsamples();
         //  duration=plot.getduration();
            
          // lineChartForRecord.dataProperty().b
          //double in=20,j=20*1.5;
          //series.getData().add(new XYChart.Data<>(in, j));
          Thread.sleep(1000);
                  
            recordplot=  new Thread (()-> {
                     try{
                    //  Thread.sleep(100);
                     ///open this thread /
                     int start=2;
                     int lastV=0;
                     int diff =0;
                     int Checknumsamples=0;
                     while(true/*nfx.Is_record!=1*/)
           {        // AudioInputStream audioInputStream1 = cap.getAudioInputStream();
           
              // String filenameForRecord="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\record.wav";             
            //   byte[] audiobytes = StreamConverter.wavefileToBytes(new File(filenameForRecord));
          //     double[] samples = plot.readAudioData(audiobytes, format);
                    capOut1 = cap.getcapout();
                    byte audioBytes[] = capOut1.toByteArray();
                    //capOut1.reset();
                    double[] samples = plot.readAudioData(audioBytes, format);
                    //int Checknumsamples=0;
                    Checknumsamples=samples.length; //+Checknumsamples;
                    System.out.println("Checknumsamples"+Checknumsamples);
                    double[] fpoints;
                    int numsamples=0;
                    
                    diff = Checknumsamples - lastV;
       
                    
                    
                    if( Checknumsamples<5000)
                {
                    RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.01);
                    fpoints= rdpf.filter(samples);
                    numsamples=fpoints.length;
                }
                  else
                    {  RamerDouglasPeuckerFilter rdpf1 = new RamerDouglasPeuckerFilter(0.3);
                     fpoints= rdpf1.filter(samples);
                     numsamples=fpoints.length;
                    }
                     System.out.println("in record numsamples-->"+numsamples);
                       //  String ff="";
                  //  double d=2;
                  //  dd=new XYChart.Data<>(null,null);
                      // numsamples=500;
                      //Thread.sleep(2000);
                    
                     for(int i=start;i<numsamples;i++)
                    { 
                        int finalI=i;  
                       
                        Platform.runLater(()->
                        
                        series.getData().add(new XYChart.Data<>(finalI/freq,fpoints[finalI])));
                    }
                    start= numsamples;
                    
                   
                    //lineChartForRecord.getData().clear();
                   //  System.out.println("start"+start);
                         
                   Thread.sleep(50); 
             /*     
                 for(int i=0;i<numsamples;i++){
                 int finalI=i;
                 Platform.runLater(()0->
                 series.getData().add(new XYChart.Data<>(String.valueOf(finalI/factor),samples[finalI]))
                 );
                 
                 Thread.sleep(1);
             
                }
*/
/////////////////////just/////////////////////////////
  /*               Platform.runLater(new Runnable() {
                     @Override
                     public void run() {
                         
                         try {
                             nfx.tempplot(classStage, fileName, samples, numSamples, tab1, TP, wavepane, factor, duration);
                             //     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                         } catch (Exception ex) {
                             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                         }
                     }
                 });
             Thread.sleep(1);
    */        
   //////////////////end of just////////////////////////////////          
       //   
       
          }
                   }catch(InterruptedException e){
                         System.out.println("e+"+e);}      
             
                  });
           recordplot.start();
                  
          
//          new Thread(task).start();
                 
 /////////////////////////////end of the plotwave/////////////////               
                 trans1.setDuration(Duration.millis(25000));
                 trans1.setToX(1140);
                 trans1.setNode(marker);
                 trans1.play();
 
    
                 
    
                 
                 
                 
  /*       
                 //  NumberAxis xAxis= new NumberAxis("",0d,100,0.05);
               //  NumberAxis yAxis = new NumberAxis("", 0, 1000, 1);
                // recordinglinechart=new LineChart(xAxis,yAxis);
                //Thread.sleep(1000);
                XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");
      

        dataSeries1.getData().add(new XYChart.Data( "1",40 ));
        dataSeries1.getData().add(new XYChart.Data( "5", 50));
        dataSeries1.getData().add(new XYChart.Data("10", 60));
        dataSeries1.getData().add(new XYChart.Data("20", 70));
        dataSeries1.getData().add(new XYChart.Data("40", 80));
        dataSeries1.getData().add(new XYChart.Data("80", 80));
        
 
        recordinglinechart.getData().add(dataSeries1);
       
        trans2.setNode(recordinglinechart);
        //trans2.setDuration(Duration.millis(10000));
        trans2.play();
      */ 
     /*
         Plotwave plot=new Plotwave();
        String filenamedis="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\recordfile.wav";
        double[] samples = plot.readWaveData(filenamedis);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        final   ObservableList<XYChart.Data<String,Double>> data = FXCollections.<XYChart.Data<String,Double>>observableArrayList();    
        for(int i=0;i<numSamples;i++)
        {
        dd = new XYChart.Data<>(Double.toString(i/factor),samples[i]);
        data.add(dd);
        }
        XYChart.Series series = new XYChart.Series(data);
        recordinglinechart.getData().add(series);
        trans2.setNode(recordinglinechart);
        trans2.play();
       */ 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
             
      
        /*         while(variable!=false)
                 {
        Thread.sleep(1000);
        String filenamedis="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\recordfile.wav";
        double[] samples = plot.readWaveData(filenamedis);
        duration=plot.getduration();
        factor=plot.getsampfrq();
        numSamples=plot.getnumsamples();
        final   ObservableList<XYChart.Data<Double,Double>> data = FXCollections.<XYChart.Data<Double,Double>>observableArrayList();    
        for(int i=0;i<numSamples;i++)
        {
        dd = new XYChart.Data<>(i/factor,samples[i]);
        data.add(dd);
        }
        XYChart.Series series = new XYChart.Series(data);
        recordinglinechart.getData().add(series);
        trans2.setNode(recordinglinechart);
        trans2.play();
                 }
          */ 
           }
             @FXML
            void marker_play_during_record(){
                
            trans1.setDuration(Duration.millis(1000));
            trans1.setToX(1140);
            trans1.setNode(marker);
            trans1.play();
           
             }
            @FXML
            void stopRecord(ActionEvent event) throws Exception
            {
                // nfx.Is_record_done=1;
                 String currentDir = System.getProperty("user.dir");
                       // System.out.println("cu");
                      //  String cexedir = currentDir + "\\src\\"+"\\Icons\\";
                 String cexedi=currentDir+"\\cexe\\";  
                 timeline.stop();
                 rewindbtn.setDisable(false);
                 fwdbtn.setDisable(false);
                 stopbtn.setDisable(false);
                 playbtn.setDisable(false);
                 
                 recordstop=true;
                 nfx.Is_record=1;
                 recordplot.stop();
                 recordplot.suspend();
                 
                 cap.stop();
                 variable=false;
                 trans1.stop();
                 marker.setTranslateX(0);
                 marker1.setTranslateX(0);
                 String filename =cexedi+"record.wav";
                   pick = new Media(new File(filename).toURI().toURL().toExternalForm());
                   player = new MediaPlayer(pick);
                   trans= new TranslateTransition();
                   sendfilename(filename);
               //  trans1.pause();
                 //playbtn.setDisable(false);
                // stopbtn.setDisable(true);
                // recordtip.setText("record");
                // recordbtn.setTooltip(recordtip);
           ///      double[] samplesfromrecord = cap.getsamplesfromrecord();
     //   int numsamples = cap.getnumsamples();
       // double duration1 = cap.getduration();
        //double factor=cap.getsampfrq();
        //double[] samplesfromrecord1 = cap.getsamplesfromrecord();
          //      System.out.println("numsample"+numsamples+"duration1"+duration1+"factor"+factor);
        //nfx.tempplot(classStage, samplesfromrecord, numsamples, tab1, TP, wavepane, factor, duration1);
      
       // Thread.sleep(1000);
       
       
   //////////////////// display the recorded file///////////    
   /*    Plotwave plot=new Plotwave();
        Thread.sleep(100);
       
        String filename="C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEndNew\\cexe\\untitled.wav";
        
        double[] samples = plot.readWaveData(filename);
        pick = new Media(new File(filename).toURI().toURL().toExternalForm());
        player = new MediaPlayer(pick);
        trans= new TranslateTransition();
        sendfilename(filename);
        RamerDouglasPeuckerFilter rdpf = new RamerDouglasPeuckerFilter(0.3);
                     double[] fpoints;
                     fpoints= rdpf.filter(samples);
                     int numsamples=fpoints.length;
        
      
        
        double duration=numsamples/ factor;
        
        
        nfx.tempplot(classStage,filename,fpoints, numsamples, tab1, TP, wavepane, factor, duration);
  
       // nfx.getrecord(1);//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // nfx.Is_record=1;
   */                   
      ///////////end of the displayed recored file/////////////  
        
    /*    
        String saveLocation = nfx.saveLocation();
          AudioInputStream audioInputStream = 
        AudioSystem.getAudioInputStream(new File(filename));
        //AudioSystem.write(audioInputStream, , saveLocation);
       int size = (int) audioInputStream.getFrameLength()*2;
        byte audioData [] = new byte[size];
                System.out.println("size of the arrary"+size);
       StreamConverter.streamTowavefile(saveLocation, audioInputStream);
       sendfilename(saveLocation);
       double[] samples1 = plot.readWaveData(saveLocation);
       duration=plot.getduration();
       factor=plot.getsampfrq();
       numSamples=plot.getnumsamples();
       System.out.println("duration"+duration+" factor"+factor+" numSamples"+numSamples);
       nfx.tempplot(classStage,saveLocation, samples1, numSamples, tab1, TP, wavepane, factor, duration);
       */ 
       
      
       
       
       
}
            
            
           public Button returnbtn(){
               
               return recordbtn;             
          
           } 
    @FXML
    void recordAction(ActionEvent event)
    {
         Image pause = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\pausebtn.png",27,27,false,false);
         Image record = new Image("file:\\C:\\Users\\Naso\\Documents\\NasoSpeech Team\\CurrentlyWorking\\NasoFXBackEnd\\src\\Icons\\record_1.png",27,27,false,false);
         
         if(count%2==0){
         
         recordbtn1.setGraphic(new ImageView(pause));
         count++;
         
         }
         else
         {
             
         recordbtn1.setGraphic(new ImageView(record));
         count++;
         
         }
         
         
         
         
         

   } 
    ////////////////////////////Articulate Error/////////////////////
    
    
    
    
    
}
 

 
 
 

 
     
    
    
    
    


