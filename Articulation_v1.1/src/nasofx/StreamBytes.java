/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.util.Arrays;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class StreamBytes {

    private byte[] original = null;
    private byte[] current = null;
    private byte[] playSelected = null;

    public StreamBytes() {
    }

    public void setOriginal(byte[] audioBytes) {
        this.original = audioBytes;
    }

    public byte[] getOriginal() {
        return this.original;
    }

    public void setCurrent(byte[] audioBytes) {
        System.out.println("setcurrent executed");
        this.current = audioBytes;
    }

    public byte[] getCurrent() {
       // System.out.println("getcurent value"+Arrays.toString(this.current));
        return this.current;
    }

    public void setSelectedPlay(byte[] audioBytes) {
        this.playSelected = audioBytes;
    }

    public byte[] getSelectedPlay() {
        return this.playSelected;
    }
}
