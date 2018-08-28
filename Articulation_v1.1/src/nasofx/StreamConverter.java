/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 *
 * @author Tatapower SED
 *
 */
public class StreamConverter {

    public static AudioInputStream byteTostream(byte[] audioBytes, AudioInputStream inputStream) {
        AudioInputStream audioInputStream = null;

        if (audioBytes == null || inputStream == null) {
            return null;
        }

        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            audioInputStream = new AudioInputStream(bais, inputStream.getFormat(), audioBytes.length / inputStream.getFormat().getFrameSize());

        } catch (Exception ex) {
            Logger.getLogger(StreamConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return audioInputStream;
    }

    public static byte[] streamTobyte(AudioInputStream inputAudioStream) {
        byte[] audioBytes = null;

        if (inputAudioStream == null) {
            return null;
        }


//This is changed on 09-02-2013 from
            /* try {
         audioBytes = new byte[(int) (inputAudioStream.getFrameLength() * inputAudioStream.getFormat().getFrameSize())];
         inputAudioStream.read(audioBytes);
         } catch (Exception ex) {
         System.out.println(ex);
         }
         */
        //to

        try {
            //  if ((int) (inputAudioStream.getFrameLength() * inputAudioStream.getFormat().getFrameSize()) < 40000000) {
            audioBytes = new byte[(int) (inputAudioStream.getFrameLength() * inputAudioStream.getFormat().getFrameSize())];
            inputAudioStream.read(audioBytes);
            // } else {
            //javax.swing.JOptionPane.showMessageDialog(null, "Currently socket allows 40 minutes file");
            // }
        } catch (Exception ex) {
            System.out.println(ex);
        }


        return audioBytes;
    }

    public static void byteTowavefile(byte[] audioBytes, AudioInputStream inputStream, String fileName) {
        if (audioBytes == null || inputStream == null || fileName == null) {
            return;
        }

        try {
            System.out.println("try enter");
            ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
            AudioInputStream audioInputStream = new AudioInputStream(bais, inputStream.getFormat(), audioBytes.length / inputStream.getFormat().getFrameSize());
            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, new File(fileName));
        } catch (Exception ex) {
            Logger.getLogger(StreamConverter.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    public static void streamTowavefile(String fileName, AudioInputStream audioInputStream) {
        File fileDir = new File(fileName);
        try {
            if (AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, fileDir) == -1) {
                throw new IOException("Problems writing to file");
            }
        } catch (Exception ex)
        {
            System.err.println(ex);
        }
    }

    public static byte[] wavefileToBytes(File fileName) {
        byte[] audioBytes = null;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileName);
            audioBytes = streamTobyte(audioInputStream);
        } catch (Exception er) {
            System.err.println(er.getMessage());
        }
        return audioBytes;
    }

    
}
