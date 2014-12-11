package main;

import main.gui.GUI;

public final class Befehlsdatenbank {
    Befehlssatz befehle[] = new Befehlssatz[0];

    protected final String assembler(byte reg[], String name) {
	return null;
    }// Gib den Maschinen Code in Hex zu�ck

    protected final String assembler() {
	return null;
    }

    public void addEntry(byte flag, byte rows, String command, byte takt[], byte value[], String function[]) {
	Befehlssatz sp[] = this.befehle;
	this.befehle = new Befehlssatz[befehle.length + 1];

	for (byte i = 0; i < sp.length; i++) {
	    this.befehle[i] = sp[i];
	}
	this.befehle[sp.length] = new Befehlssatz(flag, rows, command, takt, value, function);
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////get
    // Methoden/////////////////////////////////////////////////////
    public final byte getflag(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.flagset;
	    }
	}
	GUI.createErrorMessage("Command" + name + " doesn't exist", false);
	return -1;
    }

    public final byte getrow(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.rows;
	    }
	}
	GUI.createErrorMessage("Command" + name + " doesn't exist", false);
	return -1;
    }

    public final byte gettakt1(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.taktzyklen1;
	    }

	}
	GUI.createErrorMessage("Command" + name + " doesn't exist", false);
	return 0;
    }

    public final byte gettakt2(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.taktzyklen2;
	    }

	}
	GUI.createErrorMessage("Command" + name + " doesn't exist", false);
	return 0;
    }

    public final byte[] getvalue(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.assemblerwert;
	    }
	}
	GUI.createErrorMessage("Command" + name + " doesn't exist", false);
	return null;
    }

    public final String getcommands() {
	String text = "";
	for (Befehlssatz sp : this.befehle) {
	    text = text + "#" + sp.befehl;
	}
	return text;
    }

    public final String[] getfunction(String name) {
	for (Befehlssatz sp : this.befehle) {
	    if (sp.befehl.equals(name)) {
		return sp.function;
	    }
	}
	GUI.createErrorMessage("Command doesn't found: " + name, false);
	return null;
    }

    private final class Befehlssatz {
	protected byte   flagset;
	protected byte   rows;	   // in Assembler ben�tigte Hex Zeilen
	protected String befehl;	 // Assembler Befehl
	protected byte   taktzyklen1;    // Anzahl der Takte zum Ausf�hren[0]
	protected byte   taktzyklen2;
	protected byte   assemblerwert[]; // Beinhaltet die Werte zum umrechnen in
					  // Maschinencode
	protected String function[];     // Function des Befehls

	public Befehlssatz(byte flag, byte rows, String command, byte takt[], byte value[], String function[]) {
	    this.flagset = flag;
	    this.rows = rows;
	    this.befehl = command;
	    this.taktzyklen1 = takt[0];
	    this.taktzyklen2 = takt[1];
	    this.assemblerwert = value;
	    this.function = function;
	}
    }
}
