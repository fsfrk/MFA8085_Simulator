package main;

import java.util.HashMap;
import java.util.Map;

import main.gui.GUI;
import main.listener.guiListener;

public class CPU {
    public static final String      REGISTER[]	 = new String[] { "A", "B", "C", "D", "E", "H", "L", "SP" };
    public static final String      FLAG[]	     = new String[] { " ", " ", "C", "A", "Z", "S", "P", "O" };

    public static int	       Hz		 = 5;						       // Die Herz anzahl vom MFA
    static Befehlsdatenbank	 befehl_DB	  = new Befehlsdatenbank();				  // Alle Allgemeine
    // angaben zu den
    // m�glichen Befehlen
    private static Interpreter      interpreter	= null;
    private Map<String, Byte>       baugruppenspeicher = new HashMap<>();
    public static Map<String, Byte> reg		= new HashMap<>();					 // A,B,C,D,E,H,L,SP,FLAG

    @SuppressWarnings("static-access")
    public CPU() {
	for (String sp : this.REGISTER) {
	    this.reg.put(sp, (byte) 0);
	}
	this.baugruppenspeicher.put("in", (byte) 0); // Input Nr. ...
	new IOPut(this.befehl_DB);
    }

    public static Befehlsdatenbank getCommandDB() {
	return befehl_DB;
    }

    public static void main(String[] args) {
	new GUI(new guiListener());
    }

    public static void toogleInterpreter() {
	if ((interpreter == null) || !interpreter.isAlive()) {
	    interpreter = new Interpreter(befehl_DB, (String) (GUI.getModul(0).getValue()));
	    interpreter.start();
	} else {
	    interpreter.interrupt();
	    interpreter = null;
	}
    }

    public static void isActiv() {
	interpreter.isAlive();
    }
}
