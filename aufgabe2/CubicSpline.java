import java.util.Arrays;

/**
 * Die Klasse CubicSpline bietet eine Implementierung der kubischen Splines. Sie
 * dient uns zur effizienten Interpolation von aequidistanten Stuetzpunkten.
 *
 * @author braeckle
 *
 */
public class CubicSpline implements InterpolationMethod {

    /** linke und rechte Intervallgrenze x[0] bzw. x[n] */
    double a, b;

    /** Anzahl an Intervallen */
    int n;

    /** Intervallbreite */
    double h;

    /** Stuetzwerte an den aequidistanten Stuetzstellen */
    double[] y;

    /** zu berechnende Ableitunge an den Stuetzstellen */
    double yprime[];

    /**
     * {@inheritDoc} Zusaetzlich werden die Ableitungen der stueckweisen
     * Polynome an den Stuetzstellen berechnet. Als Randbedingungen setzten wir
     * die Ableitungen an den Stellen x[0] und x[n] = 0.
     */
    @Override
    public void init(double a, double b, int n, double[] y) {
        this.a = a;
        this.b = b;
        this.n = n;
        h = ((double) b - a) / (n);

        this.y = Arrays.copyOf(y, n + 1);

        /* Randbedingungen setzten */
        yprime = new double[n + 1];
        yprime[0] = 0;
        yprime[n] = 0;

        /* Ableitungen berechnen. Nur noetig, wenn n > 1 */
        if (n > 1) {
            computeDerivatives();
        }
    }

    /**
     * getDerivatives gibt die Ableitungen yprime zurueck
     */
    public double[] getDerivatives() {
        return yprime;
    }

    /**
     * Setzt die Ableitungen an den Raendern x[0] und x[n] neu auf yprime0 bzw.
     * yprimen. Anschliessend werden alle Ableitungen aktualisiert.
     */
    public void setBoundaryConditions(double yprime0, double yprimen) {
        yprime[0] = yprime0;
        yprime[n] = yprimen;
        if (n > 1) {
            computeDerivatives();
        }
    }

    /**
     * Berechnet die Ableitungen der stueckweisen kubischen Polynome an den
     * einzelnen Stuetzstellen. Dazu wird ein lineares System Ax=c mit einer
     * Tridiagonalen Matrix A und der rechten Seite c aufgebaut und geloest.
     * Anschliessend sind die berechneten Ableitungen y1' bis yn-1' in der
     * Membervariable yprime gespeichert.
     *
     * Zum Zeitpunkt des Aufrufs stehen die Randbedingungen in yprime[0] und yprime[n].
     * Speziell bei den "kleinen" Faellen mit Intervallzahlen n = 2
     * oder 3 muss auf die Struktur des Gleichungssystems geachtet werden. Der
     * Fall n = 1 wird hier nicht beachtet, da dann keine weiteren Ableitungen
     * berechnet werden muessen.
     */
    public void computeDerivatives() {
        if(n == 2){
            yprime[1] = (3.0 / h) * (y[2] - y[0]) - yprime[0] - yprime[2];
            yprime[1] /= 4;
            return;
        }


        double[] one = new double[n-2];
        double[] four = new double[n-1];

        for(int i = 0; i < n - 2; i++){
            one[i] = 1;
            four[i] = 4;
        }
        four[n - 2] = 4;

        TridiagonalMatrix tm = new TridiagonalMatrix(one, four, one);

        double[] ys = new double[n-1];
        for(int i = 0; i < ys.length; i++){
            ys[i] = y[i+2] - y[i];
            if(i == 0){
                ys[i] -= (h/3) * yprime[0];
            }
            else if(i == ys.length - 1){
                ys[i] -= (h/3) * yprime[n];
            }

            ys[i] *= (3/h);
        }

        double[] result = tm.solveLinearSystem(ys);
        System.arraycopy(result, 0, yprime, 1, result.length);
    }

    /**
     * {@inheritDoc} Liegt z ausserhalb der Stuetzgrenzen, werden die
     * aeussersten Werte y[0] bzw. y[n] zurueckgegeben. Liegt z zwischen den
     * Stuetzstellen x_i und x_i+1, wird z in das Intervall [0,1] transformiert
     * und das entsprechende kubische Hermite-Polynom ausgewertet.
     */
    @Override
    public double evaluate(double z) {
        if(z < a){
            return y[0];
        }
        if(z > b){
            return y[n];
        }

        double temp = a;
        int counter = 0;
        while(z > temp + h){
            temp += h;
            counter++;
        }

        double t = (z - temp) / h;

        return y[counter] * (1 - 3*t*t + 2*t*t*t) + y[counter+1]*(3*t*t - 2*t*t*t) +
                h*yprime[counter]*(t - 2*t*t + t*t*t) + h*yprime[counter+1]*(-t*t + t*t*t);
    }
}
