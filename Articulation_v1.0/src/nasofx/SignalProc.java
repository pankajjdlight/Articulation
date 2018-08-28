/* SignalProc.java */
package nasofx;

/**
 *
 * @author Tatapower SED
 *
 */
public class SignalProc {
    /* Implements an all pole filter command
     * 
     */

    private static int DEFAULT_QUANTIZATION = 16;

    public double[] filter(double a[], double x[]) {


        double[] y = new double[x.length];
        int n, k, order;

        for (n = 0; n < x.length; n++) {
            y[n] = x[n];
            order = Math.min(a.length, n + 1);
            for (k = 1; k < order; k++) {
                y[n] += -a[k] * y[n - k];

            }
        }
        return y;

    }

    public double[] convLinear(double x[], double y[]) {
        /*
         * Implements linear convolution
         */
        double[] c = new double[Math.max(x.length, y.length)];
        int n, j, k;


        // start convolution from c[y.length-1] to c[x.length-1] (last)
        for (n = y.length - 1; n < x.length; ++n) {
            for (j = n, k = 0; k < y.length; --j, ++k) {
                c[n] += x[j] * y[k];
            }
        }

        // convolution from c[0] to c[y.length-2]
        for (n = 0; n < y.length - 1; ++n) {
            for (j = n, k = 0; j >= 0; --j, ++k) {
                c[n] += x[j] * y[k];
            }
        }

        return c;
    }

    public double[] normalize(double x[], double peakValue) {
        double maxValue = 0, scaleFactor;

        for (int i = 0; i < x.length; i++) {
            maxValue = Math.max(Math.abs(x[i]), maxValue);
        }

        scaleFactor = peakValue / maxValue;
        for (int i = 0; i < x.length; i++) {
            //x[i] = x[i] * scaleFactor;
            x[i] = x[i]/maxValue;
        }
        return x;
    }

    public byte[] toByte(int[] x) {
        byte[] leByte = new byte[2 * x.length];

        for (int i = 0; i < x.length; i++) {
            leByte[2 * i] = (byte) (x[i] & 0xFF);
            leByte[2 * i + 1] = (byte) ((x[i] >>> 8) & 0xFF);
        }


        return leByte;
    }

    public int[] toInt(byte[] byteData, int bytesPerInt) {
        System.out.println("In here");
        if (bytesPerInt == 1) {
            int[] intData = new int[byteData.length];
            for (int i = 0; i < byteData.length; i++) {
                intData[i] = (int) byteData[i];
            }
            System.out.println("In here");
            return intData;
        } else {
            int[] intData = new int[byteData.length / bytesPerInt];
            for (int i = 0; i < byteData.length; i += bytesPerInt) {
                int sample = 0;
                for (int j = 0; j < bytesPerInt - 1; j++) {
                    sample = sample + ((byteData[i + j] & 0xFF) << (8 * (int) Math.pow(2, j)));
                }

                intData[i / bytesPerInt] = sample + (byteData[i + bytesPerInt - 1] << (8 * (int) Math.pow(2, bytesPerInt - 1)));
            }
            System.out.println("Also In here");
            return intData;

        }
    }

    public double[] intToDouble(int[] intArray) {
        double[] doubleData = new double[intArray.length];
        for (int i = 0; i < intArray.length; i++) {
            doubleData[i] = ((double) intArray[i]) / 32768;
        }
        return doubleData;
    }

    public double[] toDouble(byte[] byteData) {
        int[] intData = toInt(byteData, 2);
        double[] doubleData = new double[intData.length];
        int maxVal = 0;

        for (int i = 0; i < intData.length; i++) {
            doubleData[i] = ((double) intData[i]) / 32768;
            if (doubleData[i] > doubleData[maxVal]) {
                maxVal = i;
            }
        }
        System.out.println(intData[maxVal]);
        return doubleData;
    }

    public int[] toInt(byte[] byteData) {
        int[] intData = new int[byteData.length / 2];
        short sample = 0;
        for (int i = 0; i < intData.length; i++) {
            intData[i] = ((byteData[2 * i + 1]) << 8) & 0xFFFFFF00 | (byteData[2 * i] & 0xFF);
        }
        return intData;
    }

    public int[] doubleToInt(double[] doubleArray) {
        int[] intArray = new int[doubleArray.length];
        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = (int) doubleArray[i];
        }
        return intArray;
    }

    public int[] quantize(int[] x, int fromBits, int toBits) {

        int[] xQuantized = new int[x.length];
        double sample;

        for (int i = 0; i < x.length; i++) {
            sample = ((double) (x[i])) * Math.pow(2.0, toBits) / Math.pow(2, fromBits);
            xQuantized[i] = (int) Math.round(sample) * (int) (Math.pow(2, fromBits) / Math.pow(2.0, toBits));
        }

        return xQuantized;

    }

    public int[] quantize(int[] x, int reduceByBits) {

        return quantize(x, DEFAULT_QUANTIZATION, DEFAULT_QUANTIZATION + reduceByBits);

    }

    public int[] resample(int[] x, double factor) {
        // TODO: Not implemented. Please avoid calling resample(double)
        int[] xResampled = new int[(int) Math.round(((double) x.length) / factor)];

        return xResampled;

    }

    public int[] downsample(int[] x, float fromFs, float toFs, boolean doNotAlias) {
        int factor = (int) (fromFs / toFs);
        if (factor != 0) {
            return downsample(x, factor, doNotAlias);
        } else {
            return x;
        }
    }

    public int[] downsample(int[] x, int factor, boolean doNotAlias) {
        int[] xResampled = new int[x.length / factor];
        float avgSamples = 0.0F;
        if (doNotAlias) {
            for (int i = 0; i < xResampled.length; i++) {
                avgSamples = 0.0F;
                for (int j = 0; j < factor; j++) {
                    avgSamples = ((float) x[i * factor + j]) / factor;
                }
                xResampled[i] = Math.round(avgSamples);
            }
        } else {
            for (int i = 0; i < xResampled.length; i++) {
                xResampled[i] = x[i * factor];
            }
        }
        return xResampled;

    }

    public double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    public byte[] intToByteArray(int value) {
        return new byte[]{
            (byte) (value & 0xff),
            (byte) (value >> 8 & 0xff),
            (byte) (value >> 16 & 0xff),
            (byte) (value >>> 24)
        };
    }

    public byte[] ShortintToByteArray(short value) {
        return new byte[]{
            (byte) (value & 0xff),
            (byte) ((value >>> 8) & 0xff)
        };
    }
}
