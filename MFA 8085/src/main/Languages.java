package main;

import java.util.HashMap;
import java.util.Map;

public class Languages {
    private static Map<Integer, String> terms;
    public final static int	     DE = 0, EN = 1;

    /**
     * 
     * @param regioCode
     *            0=German, 1=English
     */
    public Languages(int regioCode) {
	if (terms == null) {
	    init(regioCode);
	}
    }

    private static void init(int regioCode) {
	terms = new HashMap<>();
	switch (regioCode) {
	    case 0:
		initDE();
		break;
	    case 1:
		break;
	}
    }

    public static String getText(int id) {
	if (terms == null) {
	    init(EN);
	}
	return terms.get(new Integer(id));
    }

    private static void initDE() {
	terms.put(0, "Beenden");

	terms.put(10, "start Interpreter");

    }
}
