import java.util.Arrays;

public class Gauss {

    /**
     * Diese Methode soll die Loesung x des LGS R*x=b durch
     * Rueckwaertssubstitution ermitteln.
     * PARAMETER:
     * R: Eine obere Dreiecksmatrix der Groesse n x n
     * b: Ein Vektor der Laenge n
     */
    public static double[] backSubst(double[][] R, double[] b) {
        int n = b.length - 1;
        double[] x = new double[n+1];

        while(n >= 0){

            double prev = 0;
            for(int i = n; i < b.length - 1; i++){
                prev+=R[n][i+1]*x[i+1];
            }

            x[n] = (b[n] - prev)/R[n][n];

            n--;
        }

        return x;
    }

    private static int findmax(double[][] a, int column){
        double max = 0;
        int max_row = column;

        for(int i = column; i < a[0].length; i++){
            if(Math.abs(a[i][column]) > max){
                max = Math.abs(a[i][column]);
                max_row = i;
            }
        }

        return max_row;
    }

    /**
     * Diese Methode soll die Loesung x des LGS A*x=b durch Gauss-Elimination mit
     * Spaltenpivotisierung ermitteln. A und b sollen dabei nicht veraendert werden.
     * PARAMETER: A:
     * Eine regulaere Matrix der Groesse n x n
     * b: Ein Vektor der Laenge n
     */

    public static double[] solve(double[][] A, double[] b) {
        //TODO: Diese Methode ist zu implementieren
        int n = b.length - 1;
        double[][] a = new double[n+1][n+1];
        double[] B = new double[b.length];

        System.arraycopy(b, 0, B, 0, b.length);

        for(int i = 0; i < A.length; i++){
            System.arraycopy(A[i], 0, a[i], 0, A[i].length);
        }

        for(int j = 0; j < n; j++){

            int max_row = findmax(a, j);
            if(j != max_row){
                double[] temp = new double[n+1];
                for(int i = 0; i < n + 1; i++){
                    temp[i] = a[j][i];
                    a[j][i] = a[max_row][i];
                    a[max_row][i] = temp[i];
                }

                double temp_b = B[j];
                B[j] = B[max_row];
                B[max_row] = temp_b;
            }

            double akk = a[j][j];

            double[] k = new double[n+1];
            System.arraycopy(a[j], 0, k, 0, n + 1);

            for(int i = j + 1; i < n + 1; i++){
                double aik = a[i][j];
                for(int l = j; l < n + 1; l++){
                    a[i][l] -= k[l]*aik/akk;
                }

                B[i] -= B[j]*aik/akk;
            }
        }

        return backSubst(a, B);
    }

    /**
     * Diese Methode soll eine Loesung p!=0 des LGS A*p=0 ermitteln. A ist dabei
     * eine nicht invertierbare Matrix. A soll dabei nicht veraendert werden.
     *
     * Gehen Sie dazu folgendermassen vor (vgl.Aufgabenblatt):
     * -Fuehren Sie zunaechst den Gauss-Algorithmus mit Spaltenpivotisierung
     *  solange durch, bis in einem Schritt alle moeglichen Pivotelemente
     *  numerisch gleich 0 sind (d.h. <1E-10)
     * -Betrachten Sie die bis jetzt entstandene obere Dreiecksmatrix T und
     *  loesen Sie Tx = -v durch Rueckwaertssubstitution
     * -Geben Sie den Vektor (x,1,0,...,0) zurueck
     *
     * Sollte A doch intvertierbar sein, kann immer ein Pivot-Element gefunden werden(>=1E-10).
     * In diesem Fall soll der 0-Vektor zurueckgegeben werden.
     * PARAMETER:
     * A: Eine singulaere Matrix der Groesse n x n
     */
    public static double[] solveSing(double[][] A) {
        int n = A.length - 1;
        double[][] a = new double[n+1][n+1];
        int stop = -1;

        for(int i = 0; i < A.length; i++){
            System.arraycopy(A[i], 0, a[i], 0, A[i].length);
        }

        for(int j = 0; j < n; j++){

            int max_row = findmax(a, j);
            if(j != max_row){
                double[] temp = new double[n+1];
                for(int i = 0; i < n + 1; i++){
                    temp[i] = a[j][i];
                    a[j][i] = a[max_row][i];
                    a[max_row][i] = temp[i];
                }
            }

            double akk = a[j][j];
            if(Math.abs(akk) < Math.pow(1, -10)){
                stop = j;
                break;
            }

            double[] k = new double[n+1];
            System.arraycopy(a[j], 0, k, 0, n + 1);

            for(int i = j + 1; i < n + 1; i++){
                double aik = a[i][j];
                for(int l = j; l < n + 1; l++){
                    a[i][l] -= k[l]*aik/akk;
                }
            }
        }

        if(stop == -1 && Math.abs(a[n][n]) < Math.pow(1, -10)){
            stop = n;
        }

        double[] v = new double[stop];
        for(int i = 0;  i < stop; i++){
            v[i] = -1 * a[i][stop];
        }

        double[] p = new double[n+1];
        p[0] = backSubst(a,v)[0];
        p[1] = 1;

        return p;
    }

    /**
     * Diese Methode berechnet das Matrix-Vektor-Produkt A*x mit A einer nxm
     * Matrix und x einem Vektor der Laenge m. Sie eignet sich zum Testen der
     * Gauss-Loesung
     */
    public static double[] matrixVectorMult(double[][] A, double[] x) {
        int n = A.length;
        int m = x.length;

        double[] y = new double[n];

        for (int i = 0; i < n; i++) {
            y[i] = 0;
            for (int j = 0; j < m; j++) {
                y[i] += A[i][j] * x[j];
            }
        }

        return y;
    }
}
