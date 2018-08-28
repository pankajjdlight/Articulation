/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Speech.WavePanel;

import javax.sound.sampled.AudioInputStream;

/**
 *
 *  @author Tatapower SED
 *  
 */
public class SelectedAudioWave {

    private AudioInputStream inputAudioStream;
    private AudioInputStream outputAudioStream;
    private byte[] nonSelectedByteArray;
    private byte[] selectedByteArray;

    public SelectedAudioWave() {
    }

    public void selectdPortion(byte[] audioBytes, double startSample, double endSample) {

        //  AudioInputStream shortenedStream = null;
        //  AudioInputStream outputStream = null;

        try {



            if (audioBytes == null) {
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

    private void getStreemPart(double startSample, double endSample, byte[] audioBytes) {

        byte[] newAudioBytes = null;
        byte[] cutnewAudioBytes = null;

        try {

            int startPos = (int) startSample;
            int endPos = (int) endSample;
            int newLength = (int) (audioBytes.length) - (endPos - startPos) + 10;
            int newCutln = (int) (endPos - startPos) + 10;
            newAudioBytes = new byte[newLength];
            cutnewAudioBytes = new byte[newCutln];
            int j = 0;
            int cutin = 0;
            for (int i = 0; i < audioBytes.length; i++) {

                if (i < startPos || i >= endPos) {
                    newAudioBytes[j++] = audioBytes[i];


                } else {

                    cutnewAudioBytes[cutin++] = audioBytes[i];
                }

            }
            setnonSelectedByteArray(newAudioBytes);
            setSelectedByteArray(cutnewAudioBytes);


        } catch (Exception e) {
            System.out.println(e);
        }

        //return newAudioBytes;
    }

    private void setnonSelectedByteArray(byte[] audioBytes) {
        this.nonSelectedByteArray = audioBytes;
    }

    public byte[] getnonSelectedByteArray() {
        return this.nonSelectedByteArray;
    }

    private void setSelectedByteArray(byte[] audioBytes) {

        this.selectedByteArray = audioBytes;
    }

    public byte[] getSelectedByteArray() {
        return this.selectedByteArray;
    }
}
