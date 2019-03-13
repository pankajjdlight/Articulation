/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package articulate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.animation.TranslateTransition;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
//import static nasofx.FXMLDocumentController.classStage;

/**
 *
 * @author Naso
 */
class Capture implements Runnable 
        {
      
        TargetDataLine line;
        Thread thread;
        public String errStr;
        public AudioInputStream audioInputStream;
        public ByteArrayOutputStream capOut;
        public int numSamples;
        public double samples[];
        public double duration ;
        public double sam_freq;
        byte[] data ;
        String currentDir = System.getProperty("user.dir");
        String cexedi=currentDir+"\\cexe\\";
                  
        String recordFile =cexedi+"record.wav";                         
                   
        public void start() 
        {   System.out.println("inside capture");
            errStr = null;
            thread = new Thread(this);
            thread.setName("Capture");
            thread.start();
        }

        public void stop() throws InterruptedException {
            thread = null;
            
        }

        private void shutDown(String message) {
            try {
                if ((errStr = message) != null && thread != null) {
                    thread = null;
 
                    System.err.println(errStr);
              
                }
            } catch (Exception er) {
            }
        }

        private AudioFormat getAudioFormat() {
            float sampleRate = 44100.0F;
            //8000,11025,16000,22050,44100
            int sampleSizeInBits = 16;
            //8,16
            int channels = 1;
            //1,2
            boolean signed = true;
            //true,false
            boolean bigEndian = false;
            //true,false

            return new AudioFormat(sampleRate,
                    sampleSizeInBits,
                    channels,
                    signed,
                    bigEndian);
        }//end getAudioFormat

        @Override
        public void run() {
            try {
                System.out.println("inside run");
                audioInputStream = null;
                try {
                    AudioFormat format = null;
                    DataLine.Info info = null;
                    try {

                        format = getAudioFormat();

                        info = new DataLine.Info(TargetDataLine.class,
                                format);
                        AudioSystem.getLine(info);

                        if (!AudioSystem.isLineSupported(info)) {
                            shutDown("Line matching " + info + " not supported.");
                            return;
                        }

                    } catch (Exception er) 
                    {
                        
                    }

                    // get and open the target data line for capture.

                    try {
                        line = (TargetDataLine) AudioSystem.getLine(info);
                        line.open(format, line.getBufferSize());
                    } catch (LineUnavailableException ex) {
                        shutDown("Unable to open the line: " + ex);
                        return;
                    } catch (SecurityException ex) {
                        shutDown(ex.toString());
                        
                        return;
                    } catch (Exception ex) {
                        shutDown(ex.toString());
                        return;
                    }

                    // play back the captured audio data
                    capOut = new ByteArrayOutputStream();
                    int frameSizeInBytes = format.getFrameSize();
                    int bufferLengthInFrames = line.getBufferSize() / 8;
                    int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                     data = new byte[bufferLengthInBytes];
                    int numBytesRead, noofwrites = 1;


                    line.start();

                    while (thread != null) {
                        if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                            break;
                        }


                        try {

                            FileOutputStream fos2 = new FileOutputStream(recordFile, true);
                            fos2.write(data);
                            fos2.close();
                        } catch (Exception e1) {//Catch exception if any
                            System.err.println("Error: " + e1.getMessage());
                        }
                        capOut.write(data, 0, numBytesRead);
                         byte audioBytes[] = capOut.toByteArray();
                     ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
                    audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
                     StreamConverter.streamTowavefile(recordFile, audioInputStream);
                        
                        
                    }

                    // we reached the end of the stream.  stop and close the line.
                    line.stop();
                    line.close();
                    line = null;

                    // stop and close the output stream
                    try {
                        capOut.flush();
                        capOut.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

 String currentDir = System.getProperty("user.dir");
                       String cexedi=currentDir+"\\cexe\\";
                  
                   String recordFile =cexedi+"untitled.wav";
                       if (recordFile == null) 
                       {
                        recordFile = "temp.wav";
                       }
                    StreamConverter.streamTowavefile(recordFile, audioInputStream);
                    audioInputStream = null;
                    File recordFileName = new File(recordFile);
                    if (recordFileName.exists()) {

                     AudioInputStream audiofile= AudioSystem.getAudioInputStream(recordFileName);
                     int size = (int) audiofile.getFrameLength()*2;
                     byte audioData [] = new byte[size];
       int bytesRead = audiofile.read(audioData);
      format = audiofile.getFormat();
      
     sam_freq=format.getSampleRate();
     numSamples = (int) audiofile.getFrameLength();
        
   /*
    Plotwave plot = new Plotwave();
    Articulate arti=new Articulate();
    plot.samples= plot.readAudioData(audioData,format);
    plot.duration=numSamples/sam_freq;
    plot.sampling_freq=sam_freq;
    plot.numSamples=numSamples;
*/
    } else {
             
           }
             
           } catch (Exception ex) 
    {
                   ex.printStackTrace();
              return;
    }
              
} catch (Exception er) {
            System.err.println(er);
    }
}
        
        
public ByteArrayOutputStream  getcapout(){
return capOut;
}        
  
public AudioInputStream getAudioInputStream(){

return  audioInputStream;
}


        
public double[] getsamplesfromrecord(){
return samples;
}
public double getduration(){
    return duration;
    }
    public double getsampfrq(){
    return sam_freq;
    }
     public int getnumsamples(){
    return numSamples;
    }

    private String savelocation() {
        Stage prime=FXMLDocumentController.classStage;
        String filename="";
   File fileDir = new File(System.getProperty("user.dir"));      
   FileChooser fc=new FileChooser();
   fc.setInitialDirectory(fileDir);
   fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
    File selectedfile=fc.showSaveDialog(prime);
       
   if(selectedfile!=null)
   {
   
   }
        System.out.println("record save filename"+selectedfile.getAbsolutePath());
   return selectedfile.getAbsolutePath();  
   
           
        
        
       
    }
      
} // End class Capture


