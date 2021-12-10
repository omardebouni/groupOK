package dft;

import java.util.Arrays;

/**
 * Schnelle inverse Fourier-Transformation
 *
 * @author Sebastian Rettenberger
 */
public class IFFT {
    /**
     * Schnelle inverse Fourier-Transformation (IFFT).
     *
     * Die Funktion nimmt an, dass die Laenge des Arrays c immer eine
     * Zweierpotenz ist. Es gilt also: c.length == 2^m fuer ein beliebiges m.
     */
    public static Complex[] ifft(Complex[] c) {
        if(c == null){
            System.out.println("c was null in ifft");
            return null;
        }
        for(int i = 0; i < c.length; i++){
            if(c[i] == null){
                System.out.println("c[" + i + "] was null in ifft");
                return null;
            }
        }

        int length = c.length;
        if (length == 1) return c;
        Complex v[] = new Complex[length];
        int m = length/2;
        Complex z1[] = new Complex[m];
        Complex z2[] = new Complex[m];
        for (int i = 0; i < m; i++) {
            z1[i] = c[i*2];
            z2[i] = c[i*2+1];
        }
        z1 = ifft(z1);
        z2 = ifft(z2);
        Complex omega = Complex.fromPolar(1, 2 * Math.PI / length);
        for (int i = 0; i < m; i++) {
            Complex w = omega.power(i);
            v[i] = z1[i].add(z2[i].mul(w));
            v[i+m] = z1[i].sub(z2[i].mul(w));
        }
        return v;
    }
}
