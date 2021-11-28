import java.util.Arrays;

/**
 * Die Klasse Newton-Polynom beschreibt die Newton-Interpolation. Die Klasse
 * bietet Methoden zur Erstellung und Auswertung eines Newton-Polynoms, welches
 * uebergebene Stuetzpunkte interpoliert.
 *
 * @author braeckle
 */
public class NewtonPolynom implements InterpolationMethod {

    /**
     * Stuetzstellen xi
     */
    double[] x;

    /**
     * Koeffizienten/Gewichte des Newton Polynoms p(x) = a0 + a1*(x-x0) +
     * a2*(x-x0)*(x-x1)+...
     */
    double[] a;

    /**
     * die Diagonalen des Dreiecksschemas. Diese dividierten Differenzen werden
     * fuer die Erweiterung der Stuetzstellen benoetigt.
     */
    double[] f;

    /**
     * leerer Konstruktore
     */
    public NewtonPolynom() {
    }

    ;

    /**
     * Konstruktor
     *
     * @param x Stuetzstellen
     * @param y Stuetzwerte
     */
    public NewtonPolynom(double[] x, double[] y) {
        this.init(x, y);
    }

    /**
     * {@inheritDoc} Zusaetzlich werden die Koeffizienten fuer das
     * Newton-Polynom berechnet.
     */
    @Override
    public void init(double a, double b, int n, double[] y) {
        x = new double[n + 1];
        double h = (b - a) / n;

        for (int i = 0; i < n + 1; i++) {
            x[i] = a + i * h;
        }
        computeCoefficients(y);
    }

    /**
     * Initialisierung der Newtoninterpolation mit beliebigen Stuetzstellen. Die
     * Faelle "x und y sind unterschiedlich lang" oder "eines der beiden Arrays
     * ist leer" werden nicht beachtet.
     *
     * @param x Stuetzstellen
     * @param y Stuetzwerte
     */
    public void init(double[] x, double[] y) {
        this.x = Arrays.copyOf(x, x.length);
        computeCoefficients(y);
    }

    /**
     * computeCoefficients belegt die Membervariablen a und f. Sie berechnet zu
     * uebergebenen Stuetzwerten y, mit Hilfe des Dreiecksschemas der
     * Newtoninterpolation, die Koeffizienten a_i des Newton-Polynoms. Die
     * Berechnung des Dreiecksschemas soll dabei lokal in nur einem Array der
     * Laenge n erfolgen (z.B. spaltenweise Berechnung). Am Ende steht die
     * Diagonale des Dreiecksschemas in der Membervariable f, also f[0],f[1],
     * ...,f[n] = [x0...x_n]f,[x1...x_n]f,...,[x_n]f. Diese koennen spaeter bei
     * der Erweiterung der Stuetzstellen verwendet werden.
     * <p>
     * Es gilt immer: x und y sind gleich lang.
     */
    private void computeCoefficients(double[] y) {
        int length = y.length;
        /* initialisieren */
        this.f = new double[sum(length)];
        this.a = new double[length];
        /* inizializiert die Werte der ersten Spalte von dem Dreiecksschema*/
        for (int i = 0; i < length; i++) {
            this.f[indexInF(i, 0)] = y[i];
        }
        this.a[0] = f[0]; //erste Koeffizient

        /* wir fangen an Membervariablen a zu belegen ab dem index 1
        *  Gleichzeitig werden die Werte des Dreiecksschemas generiert und im Array f[] gespeichert */
        for (int i = 1; i < length; i++) {
            /* f[i, j] = (f[i, j-1] - f [i+1, j-1]) / (x[i] - x[i+j]), i = zeile, j = spalte */
            this.a[i] = (f[indexInF(0, i - 1)] - compF(1, i - 1)) / (x[0] - x[i]);
            f[indexInF(0, i)] = a[i];
        }
    }

    /**
     * Gibt die Koeffizienten des Newton-Polynoms a zurueck
     */
    public double[] getCoefficients() {
        return a;
    }

    /**
     * Gibt die Dividierten Differenzen der Diagonalen des Dreiecksschemas f
     * zurueck
     */
    public double[] getDividedDifferences() {
        return f;
    }

    /**
     * addSamplintPoint fuegt einen weiteren Stuetzpunkt (x_new, y_new) zu x
     * hinzu. Daher werden die Membervariablen x, a und f vergoessert und
     * aktualisiert . Das gesamte Dreiecksschema muss dazu nicht neu aufgebaut
     * werden, da man den neuen Punkt unten anhaengen und das alte
     * Dreiecksschema erweitern kann. Fuer diese Erweiterungen ist nur die
     * Kenntnis der Stuetzstellen und der Diagonalen des Schemas, bzw. der
     * Koeffizienten noetig. Ist x_new schon als Stuetzstelle vorhanden, werden
     * die Stuetzstellen nicht erweitert.
     *
     * @param x_new neue Stuetzstelle
     * @param y_new neuer Stuetzwert
     */
    public void addSamplingPoint(double x_new, double y_new) {
        // wenn der x Wert schon existiert
        if (Arrays.stream(x).anyMatch(n -> (n == x_new))) return;
        else {
            int xLen = x.length;
            x = expand(x, 1); // Array um 1 erweitern
            a = expand(a, 1);
            f = expand(f, xLen + 1); // f[] muss um die neue Länge der Stützstellen erweitert

            x[xLen] = x_new;
            f[indexInF(xLen, 0)] = y_new;

            a[xLen] = (f[indexInF(0, xLen - 1)] - compF(1, xLen - 1)) / (x[0] - x[xLen]);
            f[indexInF(0, xLen)] = a[xLen];
        }
    }

    /**
     * {@inheritDoc} Das Newton-Polynom soll effizient mit einer Vorgehensweise
     * aehnlich dem Horner-Schema ausgewertet werden. Es wird davon ausgegangen,
     * dass die Stuetzstellen nicht leer sind.
     */
    @Override
    public double evaluate(double z) {
        double p = a[x.length - 1];
        for (int i = x.length - 2; i >= 0; i--) {
            p *= (z - x[i]);
            p += a[i];
        }
        return p;
    }


    /**
     * HILFSFUNKTION!
     * Diese Funktion gibt den absoluten Offset in dem Array f[], von dem Wert F[Zeile, Spalte]
     * in dem Dreiecksschema, zurück.
     * Da die die Werte angefangen von c_i in f[] gespeichert werden, und danach folgen die
     * Werte der Diagonale, aus denen c_i berechnet werden kann, ist der gesuchte Offset
     * gleich den Offset von c_(spalte+zeile) + Anzahl an Zeilen
     */
    private int indexInF(int zeile, int spalte) {
        return sum(zeile + spalte) + zeile;
    }

    /**
     * HILFSFUNKTION
     * Gibt die Summe von i=0 bis i=n nach der Formel: n*(n+1)/2
     * diese Funktion wird verwendet in der Berechnung des Offsets von einem Koeffizient c_n
     */
    private int sum(int n) {
        return ((n * (n + 1)) / 2);
    }

    /**
     * HILFSFUNKTION
     * berechnet die Werte des Dreiecksschemas am Index (zeile, spalte), und aktualisiert f[]
     * Es wird angenommen, dass die Werte der ersten Spalte (spalte = 0) schon im Array gespeichert sind
     */
    private double compF(int zeile, int spalte) {
        int offset = indexInF(zeile, spalte);
        if (spalte == 0) return f[offset]; //base of recursion

        /* f[i, j] = (f[i, j-1] - f [i+1, j-1]) / (x[i] - x[i+j])
         *  i = zeile, j = spalte
         */
        f[offset] = (f[indexInF(zeile, spalte - 1)] - compF(zeile + 1, spalte - 1)) /
                (x[zeile] - x[spalte + zeile]);
        return f[offset];
    }


    /**
     * HILFSFUNKTION
     * erzeugt eine Kopie und gibt einen Array der Größe (arr.length + add) zurück
     */
    private double[] expand(double arr[], int add) {
        double tmp[] = new double[arr.length + add];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return tmp;
    }
}

