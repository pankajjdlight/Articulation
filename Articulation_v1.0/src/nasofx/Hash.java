/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class Hash {

    public static String getHashValue(AudioInputStream audioInputStream) {
        String hash = null;
        try {

            if (audioInputStream == null) {
                return null;
            }

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }


            byte[] dataBytes = new byte[1024];

            int nread = 0;
            try {
                while ((nread = audioInputStream.read(dataBytes)) != -1) {
                    md.update(dataBytes, 0, nread);
                }
            } catch (IOException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }

            byte[] mdbytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (Exception er) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, er);
        }
        return hash;

    }

    public static String getHashValue(String inputfile) {

        AudioInputStream audioInputStream = null;
        try {
            File file = new File(inputfile);

            try {
                audioInputStream = AudioSystem.getAudioInputStream(file);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException er) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, er);
        } catch (Exception er) {
            Logger.getLogger(Hash.class.getName()).log(Level.SEVERE, null, er);
        }

        return getHashValue(audioInputStream);
    }
}
