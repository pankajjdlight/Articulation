/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import javax.sound.sampled.AudioInputStream;

/**
 *
 * @author Tatapower SED
 *
 */
public class CopyAudioInputStream {

    public static AudioInputStream cutWave;

    public static void setCutWave(AudioInputStream cutStream) {
        CopyAudioInputStream.cutWave = cutStream;
    }

    public static AudioInputStream getCutWave() {
        return CopyAudioInputStream.cutWave;
    }
}
