import java.util.Arrays;
import java.util.Comparator;

public class PageRank {

    /**
     * Diese Methode erstellt die Matrix A~ fuer das PageRank-Verfahren
     * PARAMETER:
     * L: die Linkmatrix (s. Aufgabenblatt)
     * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
     * zufaellig irgendeine Seite zu besuchen
     */
    public static double[][] buildProbabilityMatrix(int[][] L, double rho) {
        //TODO: Diese Methode ist zu implementieren
        int size = L.length;
        // eine zweidimensionale Matrix mit der passenden Größe erstellen
        double probMatrix[][] = new double[size][size];
        // durch die Einträge iterieren und die Wahrscheinlichkeit mithilfe der Spaltensummen berechnen
        for (int j = 0; j < size; j++) {
            // Spalten iterieren
            int sum = sumSpalte(L, j);
            for (int i = 0; i < size; i++) {
                // Zeilen iterieren
                if (L[i][j] == 1) {
                    probMatrix[i][j] = (1.0 / sum) * (1-rho) + rho / size;
                } else probMatrix[i][j] = rho / size;
            }
        }
        return probMatrix;
    }

    /**
     * Diese Methode berechnet die PageRanks der einzelnen Seiten,
     * also das Gleichgewicht der Aufenthaltswahrscheinlichkeiten.
     * (Entspricht dem p-Strich aus der Angabe)
     * Die Ausgabe muss dazu noch normiert sein.
     * PARAMETER:
     * L: die Linkmatrix (s. Aufgabenblatt)
     * rho: Wahrscheinlichkeit, zufaellig irgendeine Seite zu besuchen
     * ,anstatt einem Link zu folgen.
     */
    public static double[] rank(int[][] L, double rho) {
        //TODO: Diese Methode ist zu implementieren
        double probMatrix[][] = buildProbabilityMatrix(L, rho);
        double eigenVec[] = Gauss.solveSing(probMatrix);
        double sum = Arrays.stream(eigenVec).sum();
        return Arrays.stream(eigenVec).map(x -> x/sum).toArray();
    }

    /**
     * Diese Methode erstellt eine Rangliste der uebergebenen URLs nach
     * absteigendem PageRank.
     * PARAMETER:
     * urls: Die URLs der betrachteten Seiten
     * L: die Linkmatrix (s. Aufgabenblatt)
     * rho: Wahrscheinlichkeit, anstatt einem Link zu folgen,
     * zufaellig irgendeine Seite zu besuchen
     */
    public static String[] getSortedURLs(String[] urls, int[][] L, double rho) {
        int n = L.length;

        double[] p = rank(L, rho);

        RankPair[] sortedPairs = new RankPair[n];
        for (int i = 0; i < n; i++) {
            sortedPairs[i] = new RankPair(urls[i], p[i]);
        }

        Arrays.sort(sortedPairs, new Comparator<RankPair>() {

            @Override
            public int compare(RankPair o1, RankPair o2) {
                return -Double.compare(o1.pr, o2.pr);
            }
        });

        String[] sortedUrls = new String[n];
        for (int i = 0; i < n; i++) {
            sortedUrls[i] = sortedPairs[i].url;
        }

        return sortedUrls;
    }

    /**
     * Ein RankPair besteht aus einer URL und dem zugehoerigen Rang, und dient
     * als Hilfsklasse zum Sortieren der Urls
     */
    private static class RankPair {
        public String url;
        public double pr;

        public RankPair(String u, double p) {
            url = u;
            pr = p;
        }
    }

    /**
     * HILFSFUNKTION
     * Berechnet die Summe der j-ten Spalte in einem zweidimensionalen Array
     */

    private static int sumSpalte(int L[][], int j) {
        int sum = 0;
        for (int i = 0; i < L.length; i++) {
            sum += L[i][j];
        }
        return sum;
    }
}
