import java.util.Arrays;
import dft.DFT;
import dft.IFFT;
import dft.Complex;

public class Test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        testNewton();
        //testSplines();
        //testFFT();
    }

    private static void testNewton() {

        double[] x = {-2.0, -1.0, 0.0, 1.0, 2.0};
        double[] y = {1.0, 0.0, 3.0, -2.0, 0.0};
        NewtonPolynom p = new NewtonPolynom(x, y);
        //System.out.println("x[] is " + Arrays.toString(p.x) + ", sollte sein: [1, 3]");
        //System.out.println("a[] is " + Arrays.toString(p.getCoefficients()) + ", sollte sein: [2, -3.5]");
        double sol[] = {1.0, -1.0, 0.0, 2.0, 3.0, 3.0, -2.0, -4.0, -5.0, -2.0, 1.125, 2.5, 3.5, 2.0, 0.0};
        System.out.println("f[] is " + Arrays.toString(p.f) + ", sollte sein: \n\t   " +
                Arrays.toString(sol) + " [" + (Arrays.equals(p.f, sol)) + "]");
//        System.out.println(p.evaluate(0) + " sollte sein: 0.0");
        System.out.println("-------------------------------");
    }

    public static void testSplines() {
        CubicSpline spl = new CubicSpline();
        double[] y = { 2, 0, 2, 3 };
        spl.init(-1, 2, 3, y);
        spl.setBoundaryConditions(9, 0);
        System.out.println(Arrays.toString(spl.getDerivatives())
                + " sollte sein: [9.0, -3.0, 3.0, 0.0].");
    }

    public static void testFFT() {
        System.out.println("Teste Fast Fourier Transformation");

        double[] v = new double[4];
        for (int i = 0; i < 4; i++)
            v[i] = i + 1;
        Complex[] c = dft.DFT.dft(v);
        Complex[] v2 = dft.IFFT.ifft(c);

        for (int i = 0; i < 4; i++) {
            System.out.println(c[i]);
        }
        for (int i = 0; i < 4; i++) {
            System.out.println(v2[i]);
        }
        System.out
                .println("Richtig waeren gerundet: Eigene Beispiele ueberlegen");

        System.out.println("*************************************\n");
    }
}
