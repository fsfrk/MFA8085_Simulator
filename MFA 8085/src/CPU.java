import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;
import java.util.Map;

public class CPU implements ActionListener, WindowListener {
    public static final String REGISTER[] = new String[] { "A", "B", "C", "D",
	    "E", "H", "L", "SP" };
    public static final String FLAG[] = new String[] { " ", " ", "C", "A", "Z",
	    "S", "P", "O" };

    public static int Hz = 5; // Die Herz anzahl vom MFA
    Befehlsdatenbank befehl_DB = new Befehlsdatenbank(); // Alle Allgemeine
							 // angaben zu den
							 // m�glichen Befehlen
    private Map<String, Byte> baugruppenspeicher = new HashMap<String, Byte>();
    public Map<String, Byte> reg = new HashMap<String, Byte>(); // A,B,C,D,E,H,L,SP,FLAG
								// =
								// Flagregister:
								// C=Carry;A=Auxiliary;Z=Zero;S=Sign-Flag;P=Parit�t;O=Overflow
    private Interpreter interpreter;

    @SuppressWarnings("static-access")
    public CPU() {
	for (String sp : REGISTER) {
	    reg.put(sp, (byte) 0);
	}
	baugruppenspeicher.put("in", (byte) 0); // Input Nr. ...
	new IOPut(befehl_DB);
	new GUI(this);
    }

    public static void main(String[] args) {
	new CPU();
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////add
    // Abschnitt//////////////////////////////////////////////////////////////
    private Map<String, Button> key = new HashMap<String, Button>();
    private Map<String, MenuItem> m_item = new HashMap<String, MenuItem>();
    private Map<String, Frame> frame = new HashMap<String, Frame>();
    private Map<String, Label> label = new HashMap<String, Label>();
    private Map<String, TextArea> area = new HashMap<String, TextArea>();
    private Map<String, TextField> field = new HashMap<String, TextField>();
    private Frame cframe[] = new Frame[0];

    public void addMenuitem(MenuItem m_item, String name) {
	m_item.addActionListener(this);
	this.m_item.put(name, m_item);
    }

    public void addButton(Button key, String name) {
	key.addActionListener(this);
	this.key.put(name, key);
    }

    public void addFrame(Frame frame, String name) {
	frame.addWindowListener(this);
	this.frame.put(name, frame);
    }

    public void addCFrame(Frame cframe) {
	Frame sp[] = new Frame[this.cframe.length + 1];
	for (byte i = 0; i < this.cframe.length; i++) {
	    sp[i] = this.cframe[i];
	}
	cframe.addWindowListener(this);
	sp[this.cframe.length] = cframe;
	this.cframe = sp;
    }

    public void addLabel(Label label, String name) {
	this.label.put(name, label);
    }

    public void addTextArea(TextArea area, String name) {
	this.area.put(name, area);
    }

    public void addTextField(TextField field, String name) {
	this.field.put(name, field);
    }

    /*
     * public void setLabels(byte value) {
     * 
     * 
     * for(byte i=0 ; i<8 ; i++) { if((1<<i&value)>0)
     * speicher[i].setBackground(Color.green); else
     * speicher[i].setBackground(Color.red); } }
     */
    public void out(byte regA, byte port) {
	for (byte i = 0; i < 8; i++) {
	    if (((1 << i) & regA) > 0) {
		label.get("OUT: 41 " + i).setBackground(Color.green);
	    } else {
		label.get("OUT: 41 " + i).setBackground(Color.red);
	    }
	}
    }

    public byte in(byte port) {

	short wert = 0;
	for (byte i = 7; i >= 0; i--) {
	    wert = (short) (key.get("IN: 40 " + i).getBackground()
		    .equals(Color.green) ? (wert << 1) | 1 : wert << 1);
	}
	return (byte) wert;
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////Listener
    // Abschnitt/////////////////////////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getActionCommand().equals("Beenden")) {
	    System.exit(0);
	}
	if (e.getActionCommand().equals("Rechner")) {
	    GUI.rechner(this);
	}
	if (e.getActionCommand().equals("umrechnen")) {
	    TextField sp[] = new TextField[3];
	    sp[0] = field.get("hex");
	    sp[1] = field.get("dec");
	    sp[2] = field.get("bin");
	    if ((sp[0] == null) || (sp[1] == null) || (sp[2] == null)) {
		GUI.errormessage("ERROR, the Label hex,dec,bin doesn't exist",
			false);
	    }
	    if (((sp[0].getText().length() > 0) && (sp[1].getText().length() > 0))
		    || ((sp[1].getText().length() > 0) && (sp[2].getText()
			    .length() > 0))
		    || ((sp[2].getText().length() > 0) && (sp[0].getText()
			    .length() > 0))) {
		GUI.errormessage("Es m�ssen 2 Felder auf 0 sein", false);
	    } else {
		try {
		    if (sp[2].getText().length() > 0) {
			sp[0].setText(Integer.toHexString(Integer.parseInt(
				sp[2].getText(), 2)));
			sp[1].setText("" + Integer.parseInt(sp[2].getText(), 2));
		    }
		    if (sp[0].getText().length() > 0) {
			sp[1].setText(""
				+ Integer.parseInt(sp[0].getText(), 16));
			sp[2].setText(Integer.toBinaryString(Integer.parseInt(
				sp[0].getText(), 16)));
		    }
		    if (sp[1].getText().length() > 0) {
			sp[0].setText(Integer.toHexString(Integer
				.parseInt(sp[1].getText())));
			sp[2].setText(Integer.toBinaryString(Integer
				.parseInt(sp[1].getText())));
		    }
		} catch (NumberFormatException n) {
		    GUI.errormessage("Wert zu gro�: " + n.getMessage(), false);
		}
	    }
	}
	if (e.getActionCommand().equals("IN: 40")) {
	    for (byte i = 0; i < 8; i++) {
		if (key.get("IN: 40 " + i).equals(e.getSource())) {
		    if (key.get("IN: 40 " + i).getBackground()
			    .equals(Color.red)) {
			key.get("IN: 40 " + i).setBackground(Color.green);
			key.get("IN: 40 " + i).setLabel("An");
		    } else {
			key.get("IN: 40 " + i).setBackground(Color.red);
			key.get("IN: 40 " + i).setLabel("Aus");
		    }
		    baugruppenspeicher.put("in",
			    (byte) (baugruppenspeicher.get("in") ^ (1 << i)));
		}

	    }
	}
	if (e.getSource().equals(m_item.get("interpreter"))) {
	    if ((interpreter != null) && interpreter.isAlive()) {
		GUI.errormessage("Interpreter will be stop", false);
		interpreter.stop();
		m_item.get("interpreter").setLabel("Interpreter OFF");
	    } else {
		GUI.errormessage("Interpreter will be start", false);
		interpreter = new Interpreter(befehl_DB, area
			.get("interpreter").getText(), this,
			m_item.get("interpreter"));
		interpreter.start();
		m_item.get("interpreter").setLabel("Interpreter ON");
	    }
	}
    }

    @Override
    public void windowClosing(WindowEvent e) {
	if (e.getSource().equals(frame.get("rechner"))) {
	    frame.get("rechner").setVisible(false);
	}
	for (Frame sp : cframe) {
	    if (e.getSource() == sp) {
		System.exit(0);
	    }
	}
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
