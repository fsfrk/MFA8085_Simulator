package main;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    private static Map<Integer, String> terms;
    public final static int	     DE      = 0, EN = 1;
    public final static int	     DEFAULT = DE;

    /**
     * 
     * @param regioCode
     *            0=German, 1=English
     */
    public static void setLanguages(int regioCode) {
	init(regioCode);
    }

    public static String getText(int id) {
	if (terms == null) {
	    init(DEFAULT);
	}
	return terms.get(new Integer(id));
    }

    private static void initDE() {
	/**
	 * [00]-[020] => MenuBar [41]-[100] => Modul labels [101]-[130] =>
	 * Warnings [131]-[150] => Errors
	 */
	terms.put(0, "Beenden");
	terms.put(1, "Optionen");

	terms.put(5, "Datei");
	terms.put(6, "Extras");
	terms.put(7, "Kontrollzentrum");
	terms.put(8, "Hilfe");

	terms.put(11, "start Interpreter");
	terms.put(12, "stop Interpreter");

	terms.put(13, "Taschenrechner");
	terms.put(14, "Befehlssatz anzeigen");

	terms.put(41, "an");
	terms.put(42, "aus");

	terms.put(131, "Es ist ein Fehler Aufgetreten");
    }

    private static void init(int regioCode) {
	terms = new HashMap<>();
	switch (regioCode) {
	    case 0:
		initDE();
		break;
	    case 1:
		break;
	    default:
		init(DEFAULT);
	}
    }
}
