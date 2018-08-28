/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;
import javax.sound.sampled.AudioInputStream;
//import Speech.common.CutAudioInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class CutAudioWave {

    private AudioInputStream inputAudioStream;
    private AudioInputStream outputAudioStream;
    private byte[] resultByteArray;
    private byte[] cutByteArray;

    public CutAudioWave() {
    }

    public void cutPortion(byte[] audioBytes, double startSample, double endSample) {
          System.out.println("in the cutprotion");
        //  AudioInputStream shortenedStream = null;
        //  AudioInputStream outputStream = null;

        try {



            if (audioBytes == null) {
                System.out.println("audiobyte null");
                return;
            }

            getStreemPart(startSample, endSample, audioBytes);
            /*
             if (getresultByteArray() == null) {
             return;
             }
             audioBytes = getresultByteArray();
             //outputStream = byteTostream(audioBytes);
             // setOutputAudioStream(outputStream);
             // AudioSystem.write(outputStream, AudioFileFormat.Type.WAVE, new File("t1.wav"));
             if (getcutByteArray() == null) {
             return;
             }
             audioBytes = getcutByteArray();
             //outputStream = byteTostream(audioBytes);
             // setCutAudioStream(outputStream);
             //  AudioSystem.write(outputStream, AudioFileFormat.Type.WAVE, new File("t2.wav"));*/

        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }

    private byte[] streamTobyte() {
        byte[] audioBytes = null;
        try {
            audioBytes = new byte[(int) (this.inputAudioStream.getFrameLength() * this.inputAudioStream.getFormat().getFrameSize())];
            this.inputAudioStream.read(audioBytes);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return audioBytes;
    }

    private AudioInputStream byteTostream(byte[] audioBytes) {
        AudioInputStream audioInputStream = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            audioInputStream = new AudioInputStream(bais, this.inputAudioStream.getFormat(), audioBytes.length / this.inputAudioStream.getFormat().getFrameSize());

        } catch (Exception ex) {
            Logger.getLogger(CutAudioWave.class.getName()).log(Level.SEVERE, null, ex);
        }
        return audioInputStream;
    }

    private void getStreemPart(double startSample, double endSample, byte[] audioBytes) {
        System.out.println("in streamPart");

        byte[] newAudioBytes = null;
        byte[] cutnewAudioBytes = null;

        try {

            int startPos = (int) startSample;
            int endPos = (int) endSample;
            System.out.println("startPos"+startPos+"endPos"+endPos);
            System.out.println("audiobyteslength\t"+(int) (audioBytes.length));
            int newLength = (int) (audioBytes.length) - (endPos - startPos) + 10;
            System.out.println("newLength\t"+newLength);
            int newCutln = (int) (endPos - startPos) + 10;
            System.out.println("newCutln\t"+newCutln);
            newAudioBytes = new byte[newLength];
            cutnewAudioBytes = new byte[newCutln];
            int j = 0;
            int cutin = 0;
            for (int i = 0; i < audioBytes.length; i++) {

                if (i < startPos || i >= endPos) {
                    newAudioBytes[j++] = audioBytes[i];
                    System.out.println("newauio");


                } else {
                    System.out.println("cutnew");
                    cutnewAudioBytes[cutin++] = audioBytes[i];
                }

            }
           // System.out.println("newAudioBytes"+newAudioBytes);
           // System.out.println("cutnewAudioBytes"+cutnewAudioBytes);
            setresultByteArray(newAudioBytes);
            setcutByteArray(cutnewAudioBytes);


        } catch (Exception e) {
            System.out.println(e);
        }

        //return newAudioBytes;
    }

    private void setresultByteArray(byte[] audioBytes) {
        this.resultByteArray = audioBytes;
    }

    public byte[] getresultByteArray() {
        return this.resultByteArray;
    }

    private void setcutByteArray(byte[] audioBytes) {

        CutAudioInputStream.setCutWave(audioBytes);
    }

    private void setCutAudioStream(AudioInputStream audioStreem) {
    }

    private void setInputAudioStream(AudioInputStream audioStreem) {
        this.inputAudioStream = audioStreem;
    }

    private void setOutputAudioStream(AudioInputStream audioStreem) {
        this.outputAudioStream = audioStreem;
    }

    private AudioInputStream getInputAudioStream() {
        return this.inputAudioStream;
    }

    private AudioInputStream getOutputAudioStream() {
        return this.outputAudioStream;
    }

    public static void main(String args[]) {
        try {
            File file = new File("Test1.wav");
            AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
            AudioFormat format = fileFormat.getFormat();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            // CutAudioWave cu = new CutAudioWave(inputStream);
            System.out.println("inputscreen framelength"+inputStream.getFrameLength());
            //cu.cutPortion(5000, 17250);

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(CutAudioWave.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CutAudioWave.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
