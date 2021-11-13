public class Gleitpunktzahl {

    /**
     * Update by
     *
     * @author Juergen Braeckle (braeckle@in.tum.de)
     * @author Sebastian Rettenberger (rettenbs@in.tum.de)
     * @since Oktober 22, 2014
     * @version 1.2
     *
     *          Diese Klasse beschreibt eine Form von Gleitpunktarithmetik
     */

    /********************/
    /* Membervariablen: */
    /********************/

    /* Vorzeichen, Mantisse und Exponent der Gleitpunktzahl */
    public boolean vorzeichen; /* true = "-1" */
    public int exponent;
    public int mantisse;

    /*
     * Anzahl der Bits fuer die Mantisse: einmal gesetzt, soll sie nicht mehr
     * veraendert werden koennen
     */
    private static int sizeMantisse = 32;
    private static boolean sizeMantisseFixed = false;

    /*
     * Anzahl der Bits fuer dem Exponent: einmal gesetzt, soll sie nicht mehr
     * veraendert werden koennen. Maximale Groesse: 32
     */
    private static int sizeExponent = 8;
    private static boolean sizeExponentFixed = false;

    /*
     * Aus der Anzahl an Bits fuer den Exponenten laesst sich der maximale
     * Exponent und der Offset berechnen
     */
    private static int maxExponent = (int) Math.pow(2, sizeExponent) - 1;
    private static int expOffset = (int) Math.pow(2, sizeExponent - 1) - 1;

    /**
     * Falls die Anzahl der Bits der Mantisse noch nicht gesperrt ist, so wird
     * sie auf abm gesetzt und gesperrt
     */
    public static void setSizeMantisse(int abm) {
        /*
         * Falls sizeMantisse noch nicht gesetzt und abm > 0 dann setze auf
         * abm und sperre den Zugriff
         */
        if (!sizeMantisseFixed & (abm > 0)) {
            sizeMantisse = abm;
            sizeMantisseFixed = true;
        }
    }

    /**
     * Falls die Anzahl der Bits des Exponenten noch nicht gesperrt ist, so wird
     * sie auf abe gesetzt und gesperrt. maxExponent und expOffset werden
     * festgelegt
     */
    public static void setSizeExponent(int abe) {
        if (!sizeExponentFixed & (abe > 0)) {
            sizeExponent = abe;
            sizeExponentFixed = true;
            maxExponent = (int) Math.pow(2, abe) - 1;
            expOffset = (int) Math.pow(2, abe - 1) - 1;
        }
    }

    /**
     * Liefert die Anzahl der Bits der Mantisse
     */
    public static int getSizeMantisse() {
        return sizeMantisse;
    }

    /**
     * Liefert die Anzahl der Bits des Exponenten
     */
    public static int getSizeExponent() {
        return sizeExponent;
    }

    /**
     * erzeugt eine Gleitpunktzahl ohne Anfangswert. Die Bitfelder fuer Mantisse
     * und Exponent werden angelegt. Ist die Anzahl der Bits noch nicht gesetzt,
     * wird der Standardwert gesperrt
     */
    Gleitpunktzahl() {
        sizeMantisseFixed = true;
        sizeExponentFixed = true;
    }

    /**
     * erzeugt eine Kopie der reellen Zahl r
     */
    Gleitpunktzahl(Gleitpunktzahl r) {

        /* Vorzeichen kopieren */
        this.vorzeichen = r.vorzeichen;
        /*
         * Kopiert den Inhalt der jeweiligen Felder aus r
         */
        this.exponent = r.exponent;
        this.mantisse = r.mantisse;
    }

    /**
     * erzeugt eine reelle Zahl mit der Repraesentation des Double-Wertes d. Ist
     * die Anzahl der Bits fuer Mantisse und Exponent noch nicht gesetzt, wird
     * der Standardwert gesperrt
     */
    Gleitpunktzahl(double d) {

        this();
        this.setDouble(d);
    }

    /**
     * setzt dieses Objekt mit der Repraesentation des Double-Wertes d.
     */
    public void setDouble(double d) {

        /* Abfangen der Sonderfaelle */
        if (d == 0) {
            this.setNull();
            return;
        }
        if (Double.isInfinite(d)) {
            this.setInfinite(d < 0);
            return;
        }
        if (Double.isNaN(d)) {
            this.setNaN();
            return;
        }

        /* Falls d<0 -> Vorzeichen setzten, Vorzeichen von d wechseln */
        if (d < 0) {
            this.vorzeichen = true;
            d = -d;
        } else
            this.vorzeichen = false;

        /*
         * Exponent exp von d zur Basis 2 finden d ist danach im Intervall [1,2)
         */
        int exp = 0;
        while (d >= 2) {
            d = d / 2;
            exp++;
        }
        while (d < 1) {
            d = 2 * d;
            exp--;
        } /* d in [1,2) */

        this.exponent = exp + expOffset;

        /*
         * Mantisse finden; fuer Runden eine Stelle mehr als noetig berechnen
         */
        double rest = d;
        this.mantisse = 0;
        for (int i = 0; i <= sizeMantisse; i++) {
            this.mantisse <<= 1;
            if (rest >= 1) {
                rest = rest - 1;
                this.mantisse |= 1;
            }
            rest = 2 * rest;
        }
        this.exponent -= 1; /* Mantisse ist um eine Stelle groesser! */

        /*
         * normalisiere uebernimmt die Aufgaben des Rundens
         */
        this.normalisiere();
    }

    /**
     * liefert eine String-Repraesentation des Objekts
     */
    public String toString() {
        if (this.isNaN())
            return "NaN";
        if (this.isNull())
            return "0";

        StringBuffer s = new StringBuffer();
        if (this.vorzeichen)
            s.append('-');
        if (this.isInfinite())
            s.append("Inf");
        else {
            for (int i = 32 - Integer.numberOfLeadingZeros(this.mantisse) - 1;
                 i >= 0; i--) {
                if (i == sizeMantisse - 2)
                    s.append(',');
                if (((this.mantisse >> i) & 1) == 1)
                    s.append('1');
                else
                    s.append('0');
            }
            s.append(" * 2^(");
            s.append(this.exponent);
            s.append("-");
            s.append(expOffset);
            s.append(")");
        }
        return s.toString();
    }

    /**
     * berechnet den Double-Wert des Objekts
     */
    public double toDouble() {
        /*
         * Wenn der Exponent maximal ist, nimmt die Gleitpunktzahl einen der
         * speziellen Werte an
         */
        if (this.exponent == maxExponent) {
            /*
             * Wenn die Mantisse Null ist, hat die Zahl den Wert Unendlich oder
             * -Unendlich
             */
            if (this.mantisse == 0) {
                if (this.vorzeichen)
                    return -1.0 / 0.0;
                else
                    return 1.0 / 0.0;
            }
            /* Ansonsten ist der Wert NaN */
            else
                return 0.0 / 0.0;
        }
        double m = this.mantisse;
        if (this.vorzeichen)
            m *= (-1);
        return m
                * Math.pow(2, (this.exponent - expOffset)
                - (sizeMantisse - 1));
    }

    /**
     * Sonderfaelle abfragen
     */
    /**
     * Liefert true, wenn die Gleitpunktzahl die Null repraesentiert
     */
    public boolean isNull() {
        return (!this.vorzeichen && this.mantisse == 0 && this.exponent == 0);
    }

    /**
     * Liefert true, wenn die Gleitpunktzahl der NotaNumber Darstellung
     * entspricht
     */
    public boolean isNaN() {
        return (this.mantisse != 0 && this.exponent == maxExponent);
    }

    /**
     * Liefert true, wenn die Gleitpunktzahl betragsmaessig unendlich gross ist
     */
    public boolean isInfinite() {
        return (this.mantisse == 0 && this.exponent == maxExponent);
    }

    /**
     * vergleicht betragsmaessig den Wert des aktuellen Objekts mit der reellen
     * Zahl r
     */
    public int compareAbsTo(Gleitpunktzahl r) {
        /*
         * liefert groesser gleich 1, falls |this| > |r|
         * 0, falls |this| = |r|
         * kleiner gleich -1, falls |this| < |r|
         */

        /* Exponenten vergleichen */
        int expVergleich = this.exponent - r.exponent;

        if (expVergleich != 0)
            return expVergleich;

        /* Bei gleichen Exponenten: Bitweisses Vergleichen der Mantissen */
        return this.mantisse - r.mantisse;
    }

    /**
     * gibt die Anzahl an bits zurueck, die fuer die Darstellung von num benoetigt werden
     * wird in der Funktion normalisiere() aufgerufen
     */
    private int sizeOf(int num) {
        int counter = 0;
        while (num != 0) {
            num /= 2; //recht shiften, aber >> würde hier nicht funktionieren, da >> ist ein logical shift, und mit negativen zahlen wird das sign bit immer an der höchstwertigsten bit bleiben, was zu einer unendlichen Schleife führt
            counter++;
        }
        return counter;
    }

    /**
     * Die Funktion prüft, ob es sich um die Mantisse oder um den Exponenten handelt,
     * und verkleinert die Größe entsprechend der benötigten Mantisse/Exponent Größe
     * Am Ende wird den Exponent ebenfalls entsprechend angepasst
     * */
    private void sizeDown(boolean mantisse, int current) {
        /*Hier wird gespeichert, ob aufrunden benötigt wird, oder nicht.*/
        boolean roundUp;
        if (mantisse) {
            int dif = current - getSizeMantisse() - 1;
            /*passt die Groesse der Mantisse, sodass sie 1 bit mehr hat als die benoetigte Groesse*/
            this.mantisse >>= (dif);
            /*prueft ob das letzte bit in der Mantisse gleich 1 ist*/
            roundUp = (this.mantisse % 2 != 0);
            this.mantisse >>= 1; //jetzt hat die Mantisse die richtige Groesse
            if (roundUp) this.mantisse += 1; //aufrunden wenn nötig
            this.exponent += dif; //exponent anpassen
        }
        else {
            /*Dasselbe für den Exponenten*/
            this.exponent >>= (current - getSizeExponent() - 1);
            roundUp = (this.exponent % 2 != 0);
            this.exponent >>= 1;
            if (roundUp) this.exponent += 1;
        }
    }

    /**
     * Die Funktion prüft, ob es sich um die Mantisse oder um den Exponenten handelt,
     * und vergrößert die Größe entsprechend der benötigten Mantisse/Exponent Größe
     * */
    private void sizeUp(boolean mantisse, int current) {
        if (mantisse) {
            int dif = getSizeMantisse() - current;
            this.mantisse <<= (dif); //jetzt hat die Mantisse die richtige Groesse
            this.exponent -= dif;
        }
            /*Dasselbe für den Exponenten*/
        else this.exponent >>= (getSizeExponent() - current);
    }

    /**
     * normalisiert und rundet das aktuelle Objekt auf die Darstellung r =
     * (-1)^vorzeichen * 1,r_t-1 r_t-2 ... r_1 r_0 * 2^exponent. Die 0 wird zu
     * (-1)^0 * 0,00...00 * 2^0 normalisiert WICHTIG: Es kann sein, dass die
     * Anzahl der Bits nicht mit sizeMantisse uebereinstimmt. Das Ergebnis
     * soll aber eine Mantisse mit sizeMantisse Bits haben. Deshalb muss
     * evtl. mit Bits aufgefuellt oder Bits abgeschnitten werden. Dabei muss das
     * Ergebnis nach Definition gerundet werden.
     * <p>
     * Beispiel: Bei 3 Mantissenbits wird die Zahl 10.11 * 2^-1 zu 1.10 * 2^0
     */
    public void normalisiere() {
        /*
         * TODO: hier ist die Operation normalisiere zu implementieren.
         * Beachten Sie, dass die Groesse (Anzahl der Bits) des Exponenten
         * und der Mantisse durch sizeExponent bzw. sizeMantisse festgelegt
         * ist.
         * Achten Sie auf Sonderfaelle!
         */
        int curManSize = sizeOf(this.mantisse);
        if (curManSize > getSizeMantisse()) sizeDown(true, curManSize);
        else if (curManSize < getSizeMantisse()) sizeUp(true, curManSize);

        int curExpSize = sizeOf(this.exponent);
        if (curExpSize > getSizeExponent()) sizeDown(false, curExpSize);
        else if (curExpSize < getSizeExponent()) sizeUp(false, curExpSize);
    }

    /**
     * denormalisiert die betragsmaessig goessere Zahl, so dass die Exponenten
     * von a und b gleich sind. Die Mantissen beider Zahlen werden entsprechend
     * erweitert. Denormalisieren wird fuer add und sub benoetigt.
     */
    public static void denormalisiere(Gleitpunktzahl a, Gleitpunktzahl b) {
        /*
         * TODO: hier ist die Operation denormalisiere zu implementieren.
         */
        if (a.exponent != b.exponent) {
            Gleitpunktzahl greater;
            Gleitpunktzahl smaller;
            if (a.exponent > b.exponent) {
                greater = a;
                smaller = b;
            }
            else {
                greater = b;
                smaller = a;
            }
            int dif = greater.exponent - smaller.exponent;
            //greater.exponent = smaller.exponent;
            greater.mantisse <<= dif;
        }

    }

    /**
     * addiert das aktuelle Objekt und die Gleitpunktzahl r. Dabei wird zuerst
     * die betragsmaessig groessere Zahl denormalisiert und die Mantissen beider
     * zahlen entsprechend vergroessert. Das Ergebnis wird in einem neuen Objekt
     * gespeichert, normiert, und dieses wird zurueckgegeben.
     */
    public Gleitpunktzahl add(Gleitpunktzahl r) {
        /*
         * TODO: hier ist die Operation add zu implementieren. Verwenden Sie die
         * Funktionen normalisiere und denormalisiere.
         * Achten Sie auf Sonderfaelle!
         */
        Gleitpunktzahl ret = new Gleitpunktzahl(this);
        denormalisiere(ret, r);
        ret.mantisse += r.mantisse;
        if (ret.exponent < r.exponent) ret.exponent = r.exponent;
        ret.normalisiere();
        r.normalisiere();
        return ret;
    }

    /**
     * subtrahiert vom aktuellen Objekt die Gleitpunktzahl r. Dabei wird zuerst
     * die betragsmaessig groessere Zahl denormalisiert und die Mantissen beider
     * zahlen entsprechend vergroessert. Das Ergebnis wird in einem neuen Objekt
     * gespeichert, normiert, und dieses wird zurueckgegeben.
     */
    public Gleitpunktzahl sub(Gleitpunktzahl r) {
        /*
         * TODO: hier ist die Operation sub zu implementieren. Verwenden Sie die
         * Funktionen normalisiere und denormalisiere.
         * Achten Sie auf Sonderfaelle!
         */

        return new Gleitpunktzahl();
    }

    /**
     * Setzt die Zahl auf den Sonderfall 0
     */
    public void setNull() {
        this.vorzeichen = false;
        this.exponent = 0;
        this.mantisse = 0;
    }

    /**
     * Setzt die Zahl auf den Sonderfall +/- unendlich
     */
    public void setInfinite(boolean vorzeichen) {
        this.vorzeichen = vorzeichen;
        this.exponent = maxExponent;
        this.mantisse = 0;
    }

    /**
     * Setzt die Zahl auf den Sonderfall NaN
     */
    public void setNaN() {
        this.vorzeichen = false;
        this.exponent = maxExponent;
        this.mantisse = 1;
    }
}
