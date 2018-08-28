/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nasofx;

import java.math.BigInteger;

/**
 *
 * @author Tatapower SED
 *
 */
public class PixcelConversion {

    public PixcelConversion() {
    }

    public int milliSecondToPixcel(String ms, int frame_per_pixel, int formate) {

        int samples = 0;
        try {

            BigInteger microSecond = new BigInteger(ms.trim());
            BigInteger sampleRate = new BigInteger(Integer.toString(formate));
            BigInteger devided = new BigInteger("1000");
            samples = Integer.parseInt(((sampleRate.multiply(microSecond)).divide((devided)).toString()));
            samples = (int) (samples / frame_per_pixel);

        } catch (Exception er) {
            System.err.println(er);
        }
        return samples;
    }

    public int pixcelToMillisecond(int pixcel, int frame_per_pixel, int formate) {

        int milliSecond = 0;
        try {
            BigInteger pix = new BigInteger(Integer.toString(pixcel));
            BigInteger frame_pix = new BigInteger(Integer.toString(frame_per_pixel));
            BigInteger formate_pix = new BigInteger(Integer.toString(formate));
            BigInteger samples;
            samples = pix.multiply(frame_pix);
            // milliSecond = (int) ((samples * 1000) / formate);

            milliSecond = Integer.parseInt(((samples.multiply(new BigInteger("1000"))).divide(formate_pix)).toString());
        } catch (Exception er) {
            System.err.println(er);
        }
        return milliSecond;
    }
}
