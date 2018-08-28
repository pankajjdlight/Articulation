/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Naso
 */
public class Plotwave 
{   public AudioInputStream audioInputStream;
    public AudioFormat format;
    public SignalProc sigProc;
    public   int numSamples;
    public double sampling_freq;
    public  float frameRate;
    public int frameSize;
    public double samples[];
    String filename="";
    public double duration;
    public int startSample,endSample,totalSample;
    public double posStart,posEnd,startSecond,endSecond;
   public String fileopenmethod(){        
        
        String filename="";
        File fileDir = new File(System.getProperty("user.dir"));      
        FileChooser fc=new FileChooser();
        fc.setInitialDirectory(fileDir);
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("wave files","*.wav","*.wma","*.mp3"));
    
        File selectedfile=fc.showOpenDialog(null);
        return selectedfile.getAbsolutePath();  
   } //// fileopen method ends here
    
   
   
   
   
   
   
   
   
   
    
    
    public double[] readWaveData(String filename) throws UnsupportedAudioFileException, IOException{  
         
        audioInputStream = AudioSystem.getAudioInputStream(new File(filename));
        int size = (int) this.audioInputStream.getFrameLength()*2;
        System.out.println("size"+size);
        byte audioData [] = new byte[size];
        System.out.println("ffffff = "+  this.audioInputStream.getFrameLength() + "  " + this.audioInputStream.getFormat());
        int bytesRead = this.audioInputStream.read(audioData);
        System.out.println("bytes read = "+ bytesRead);
        format = audioInputStream.getFormat();
        sampling_freq=format.getSampleRate();
        numSamples = (int) this.audioInputStream.getFrameLength();
        System.out.println("numSamples"+numSamples);
        duration=numSamples/sampling_freq;
        samples = readAudioData(audioData,format);
        return samples;
        
    } 
///////  readwave data method ends here
    
    
    double[] readAudioData(byte audioBytes[],AudioFormat format){
     
     
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
 } /// readaudiodata method ends here
 
    
    public double getduration(){
    return duration;
    }
    public double getsampfrq(){
    return sampling_freq;
    }
     public int getnumsamples(){
    return numSamples;
    }
     
     
     
    public void setStartSample(double d){
    //double sampfrq = getsampfrq();
      //  System.out.println("mouseposn"+d+"getsampfrq()"+sampfrq);
        startSample=(int)d;//(int)(d*this.sampling_freq);
    }      
  //  
    
    
    public int getStartSample(){
    return startSample;
    }
    
    public void setEndSample(Double d){
         endSample=d.intValue();
   
    
    }
    public int getEndSample(){
    return endSample;
    }
    public int getSamplingPositions(){
        totalSample=endSample-startSample;
    
        
        return totalSample;
    }    

    void setMouseEndpos(double valueForDisplay) {
        posEnd=valueForDisplay;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    double getMouseEndpos() {
        return posEnd;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    void setMouseStartpos(double valueForDisplay) {
        posStart=valueForDisplay;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    double getMouseStartpos() {
        return posStart;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    void setLastSecond(double LastSecond) {
       endSecond=LastSecond; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void setStartSecond(double StartSecond) {
       startSecond=StartSecond;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     double getLastSecond() {
       return endSecond; //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    double getStartSecond() {
       return startSecond;// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
    
    
    
    
}//// Plotwave class ends here  
