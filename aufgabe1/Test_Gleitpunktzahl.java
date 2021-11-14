import java.util.Arrays;

public class Test_Gleitpunktzahl {
/**
 * Testklasse, ob alles funktioniert!
 */
	
	
	public static void main(String[] argv) {
		test_Gleitpunktzahl();
	}

	public static void test_Gleitpunktzahl() {

		/**********************************/
		/* Test der Klasse Gleitpunktzahl */
		/**********************************/

		System.out.println("-----------------------------------------");
		System.out.println("Test der Klasse Gleitpunktzahl");

		/*
		 * Verglichen werden die BitFelder fuer Mantisse und Exponent und das
		 * Vorzeichen
		 */
		Gleitpunktzahl.setSizeMantisse(4);
		Gleitpunktzahl.setSizeExponent(4);

		Gleitpunktzahl x;
		Gleitpunktzahl y;
		Gleitpunktzahl gleitref = new Gleitpunktzahl();
		Gleitpunktzahl gleiterg;

		/* Test von setDouble */
		System.out.println("Test von setDouble");
		try {
			// Test: setDouble
			x = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(0.5);

			// Test, ob Ergebnis korrekt
			if (x.compareAbsTo(gleitref) != 0
					|| x.vorzeichen != gleitref.vorzeichen) {
				printErg("" + x.toDouble(), "" + gleitref.toDouble());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			/*************
			 * Eigene Tests einfuegen
			 */

			System.out.println("\n\nEIGENE TESTS EINFÜGEN!!!!!!!\n\n");

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Addition */
		System.out.println("Test der Addition mit Gleitpunktzahl");
		try {
			// Test: Addition
			System.out.println("Test: Addition  x + y");
			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(0.5);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(3.25 + 0.5);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			// Test: 0.25 + 1.75
			double a = 0.25, b = 1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//
			a = 0.25; b = -1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//
			a = 2.25; b = -1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//
			a = -0.25; b = 1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//
			a = -0.25; b = -1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//
			a = -3.25; b = -1.75;
			System.out.println("Test: Addition  "+ a + " + "+ b);
			x = new Gleitpunktzahl(a);
			y = new Gleitpunktzahl(b);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(a + b);

			// Berechnung
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Subtraktion */
		try {
			System.out.println("Test der Subtraktion mit Gleitpunktzahl");

			// Test: Subtraktion
			System.out.println("Test: Subtraktion  x - y");
			x = new Gleitpunktzahl(3.25);
			y = new Gleitpunktzahl(2.75);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((3.25 - 2.75));

			// Berechnung
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			/*************
			 * Eigene Tests einfuegen
			 */

			//2.25 - 1.0
			System.out.println("Test Sub: 2.25 - 1.0");
			x = new Gleitpunktzahl(2.25);
			y = new Gleitpunktzahl(1);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((2.25-1.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//0.5 - 0.25
			System.out.println("Test Sub: 0.5 - 0.25");
			x = new Gleitpunktzahl(0.5);
			y = new Gleitpunktzahl(0.25);
			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.5-0.25));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//1.0 - 0.125
			System.out.println("Test Sub: 1.0 - 0.125");
			x = new Gleitpunktzahl(1.0);
			y = new Gleitpunktzahl(0.125);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((1.0-0.125));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//0.25 - 1.75
			System.out.println("Test Sub: 0.25 - 1.75");
			x = new Gleitpunktzahl(0.25);
			y = new Gleitpunktzahl(1.75);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.25-1.75));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//1.25 - 2.25
			System.out.println("Test Sub: 1.25 - 2.25");
			x = new Gleitpunktzahl(1.25);
			y = new Gleitpunktzahl(2.25);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((1.25-2.25));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//0.25 - 3.0
			System.out.println("Test Sub: 0.25 - 3.0");
			x = new Gleitpunktzahl(0.25);
			y = new Gleitpunktzahl(3.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.25-3.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//0.25 - -3.0
			System.out.println("Test Sub: 0.25 - -3.0");
			x = new Gleitpunktzahl(0.25);
			y = new Gleitpunktzahl(-3.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.25-(-3.0)));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//-0.25 - -3.0
			System.out.println("Test Sub: -0.25 - -3.0");
			x = new Gleitpunktzahl(-0.25);
			y = new Gleitpunktzahl(-3.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((-0.25-(-3.0)));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//-3.25 - 3.0
			System.out.println("Test Sub: -3.25 - 3.0");
			x = new Gleitpunktzahl(-3.25);
			y = new Gleitpunktzahl(3.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((-3.25-3.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//-3.25 - 4.0
			System.out.println("Test Sub: -3.25 - 4.0");
			x = new Gleitpunktzahl(-3.25);
			y = new Gleitpunktzahl(4.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((-3.25-4.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

		} catch (Exception e) {
			System.out.print("Exception bei der Auswertung des Ergebnis!!\n");
		}

		/* Sonderfaelle */
		System.out.println("Test der Sonderfaelle mit Gleitpunktzahl");

		try {
			// Test: Sonderfaelle

			//1.Fall
			// 0 - inf
			System.out.println("Test: Sonderfaelle\n");
			System.out.println("Test: 0-inf");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(1.0 / 0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(true);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//2.Fall
			//inf - 0
			System.out.println("Test: inf-0");
			x = new Gleitpunktzahl(1.0/0.0);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(false);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//3.Fall
			//inf - inf
			System.out.println("Test: inf-inf");
			x = new Gleitpunktzahl(1.0/0.0);
			y = new Gleitpunktzahl(1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((1.0/0.0 - 1.0/0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//4.Fall
			//0 - 0
			System.out.println("Test: 0-0");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref.setNull();

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//5.Fall
			//0 - 5.25
			System.out.println("Test: 0 - 5.25");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(5.25);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.0-5.25));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//6.Fall
			//5.25 - 0
			System.out.println("Test: 5.25 - 0");
			x = new Gleitpunktzahl(5.25);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((5.25 - 0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//-inf - inf
			System.out.println("Test: -inf - inf");
			x = new Gleitpunktzahl(-1.0/0.0);
			y = new Gleitpunktzahl(1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(((-1.0/0.0) - 1.0/0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//-inf - -inf
			System.out.println("Test: -inf - -inf");
			x = new Gleitpunktzahl(-1.0/0.0);
			y = new Gleitpunktzahl(-1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(((-1.0/0.0)-(-1.0/0.0)));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.sub(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}



			//1.Fall
			// 0 - inf
			System.out.println("Test: Sonderfaelle\n");
			System.out.println("Test: 0+inf");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(1.0 / 0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(false);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//2.Fall
			//inf - 0
			System.out.println("Test: inf+0");
			x = new Gleitpunktzahl(1.0/0.0);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref.setInfinite(false);

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printAdd(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//3.Fall
			//inf - inf
			System.out.println("Test: inf+inf");
			x = new Gleitpunktzahl(1.0/0.0);
			y = new Gleitpunktzahl(1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((1.0/0.0 + 1.0/0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//4.Fall
			//0 - 0
			System.out.println("Test: 0+0");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref.setNull();

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}


			//5.Fall
			//0 - 5.25
			System.out.println("Test: 0 + 5.25");
			x = new Gleitpunktzahl(0.0);
			y = new Gleitpunktzahl(5.25);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((0.0+5.25));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//6.Fall
			//5.25 + 0
			System.out.println("Test: 5.25 + 0");
			x = new Gleitpunktzahl(5.25);
			y = new Gleitpunktzahl(0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl((5.25 + 0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//-inf - inf
			System.out.println("Test: -inf + inf");
			x = new Gleitpunktzahl(-1.0/0.0);
			y = new Gleitpunktzahl(1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(((-1.0/0.0) + 1.0/0.0));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}

			//-inf - -inf
			System.out.println("Test: -inf + -inf");
			x = new Gleitpunktzahl(-1.0/0.0);
			y = new Gleitpunktzahl(-1.0/0.0);

			// Referenzwerte setzen
			gleitref = new Gleitpunktzahl(((-1.0/0.0)+(-1.0/0.0)));

			// Berechnung mit der Methode des Studenten durchfuehren
			gleiterg = x.add(y);

			// Test, ob Ergebnis korrekt
			if (gleiterg.compareAbsTo(gleitref) != 0
					|| gleiterg.vorzeichen != gleitref.vorzeichen) {
				printSub(x.toString(), y.toString());
				printErg(gleiterg.toString(), gleitref.toString());
			} else {
				System.out.println("    Richtiges Ergebnis\n");
			}
		} catch (Exception e) {
			System.out
					.print("Exception bei der Auswertung des Ergebnis in der Klasse Gleitpunktzahl!!\n");
		}

	}


	private static void printAdd(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " + " + y);
	}

	private static void printSub(String x, String y) {
		System.out.println("    Fehler!\n      Es wurde gerechnet:            "
				+ x + " - " + y + " = \n");
	}

	private static void printErg(String erg, String checkref) {
		System.out.println("      Ihr Ergebnis lautet:           " + erg
				+ "\n      Das Korrekte Ergebnis lautet:  " + checkref + "\n");
	}
}