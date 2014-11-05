import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    protected final String MFA_ver = "MFA 8085 Version Beta 1.1"; // Name des
								  // Projekts
								  // und
								  // Versionsnummer

    private static Frame fenster = new Frame();
    private final Dimension screen = Toolkit.getDefaultToolkit()
	    .getScreenSize();

    private MenuBar menubar = new MenuBar();
    private Menu datei = new Menu("Datei");
    private Menu extras = new Menu("Extras");
    private Menu ausfuhren = new Menu("Ausführen");
    private MenuItem d_item[] = new MenuItem[] { new MenuItem("Beenden") };
    private MenuItem e_item[] = new MenuItem[] { new MenuItem("Rechner"),
	    new MenuItem("Befehlssatz"), new MenuItem("Hilfe") };
    private MenuItem i_item = new MenuItem("Interpreter: OFF");

    private CPU listener;

    private Eingabebaugruppe input;
    private EingabeCode code_input;
    private Ausgabebaugruppe output;
    // private AnalogDigitalKonverter adconverter =new AnalogDigitalKonverter();
    private Debugger debugger;

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////Methoden/////////////////////////////////////////////////////////
    public GUI(CPU listener) {
	this.listener = listener;
	fenster.setTitle(this.MFA_ver);
	fenster.setBounds((int) (this.screen.getWidth() / 4),
		(int) (this.screen.getHeight() / 4),
		(int) (this.screen.getWidth() / 2),
		(int) (this.screen.getHeight() / 2));
	fenster.setMenuBar(this.menubar);

	this.menubar.add(this.datei);
	for (MenuItem item : this.d_item) {
	    this.datei.add(item);
	    this.listener.addMenuitem(item, item.getLabel());
	}
	this.menubar.add(this.extras);
	for (MenuItem item : this.e_item) {
	    this.extras.add(item);
	    this.listener.addMenuitem(item, item.getLabel());
	}
	this.menubar.add(this.ausfuhren);
	this.ausfuhren.add(this.i_item);
	this.listener.addMenuitem(this.i_item, "interpreter");

	this.code_input = new EingabeCode(listener);
	this.debugger = new Debugger(listener);
	this.input = new Eingabebaugruppe(listener);
	this.output = new Ausgabebaugruppe(listener);

	fenster.setLayout(new GridLayout(1, 8, 10, 0));
	fenster.add(this.code_input);
	fenster.add(this.debugger);
	fenster.add(this.output);
	fenster.add(this.input);
	// fenster.add(this.adconverter);

	this.listener.addCFrame(fenster);
	fenster.setBackground(Color.LIGHT_GRAY);
	fenster.setVisible(true);
    }

    public static void errormessage(String message, final boolean exit) {
	final Dialog error = new Dialog(fenster, "Error", true);
	Button ok = new Button("Ok");

	error.setLayout(new BorderLayout());
	error.add(new Label(message), BorderLayout.CENTER);
	error.add(ok, BorderLayout.SOUTH);
	ok.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		if (exit) {
		    System.exit(1);
		}
		error.setVisible(false);
	    }
	});
	error.setBounds(250, 250, 300, 100);
	error.setVisible(true);
    }

    public static void rechner(CPU listener) {
	Frame rechner = new Frame("Rechner");
	TextField hex = new TextField(), dec = new TextField(), bin = new TextField();
	Label text[] = new Label[] { new Label("Decimal: "), new Label("Hex:"),
		new Label("Binär: ") };
	Button key = new Button("Umrechnen");

	key.setActionCommand("umrechnen");
	rechner.setLayout(new GridLayout(4, 2));
	rechner.add(text[0]);
	rechner.add(dec);
	rechner.add(text[1]);
	rechner.add(hex);
	rechner.add(text[2]);
	rechner.add(bin);
	rechner.add(key);

	listener.addFrame(rechner, "rechner");
	listener.addTextField(dec, "dec");
	listener.addTextField(hex, "hex");
	listener.addTextField(bin, "bin");
	listener.addButton(key, "key");
	rechner.setVisible(true);
	rechner.setSize(250, 150);
	rechner.setLocation(150, 150);
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////Baugruppen///////////////////////////////////////////////////////
    @SuppressWarnings("serial")
    private class Eingabebaugruppe extends Panel {
	Panel panel[] = null;
	Label num[] = new Label[8];
	Button key[] = new Button[8];

	public Eingabebaugruppe(CPU listener) {
	    this.setLayout(new BorderLayout());
	    this.panel = new Panel[] { new Panel(new GridLayout(8, 1)),
		    new Panel(new GridLayout(8, 1)) };
	    for (byte i = 0; i < 8; i++) {
		this.num[i] = new Label("" + i, Label.CENTER);
		this.num[i].setBackground(Color.gray);
		this.panel[0].add(this.num[i]);
		this.key[i] = new Button("Aus");
		this.key[i].setActionCommand("IN: 40");
		this.panel[1].add(this.key[i]);
		listener.addButton(key[i], "IN: 40 " + i);
		this.key[i].setBackground(Color.RED);
	    }
	    this.add(this.panel[0], BorderLayout.WEST);
	    this.add(this.panel[1], BorderLayout.CENTER);
	    this.setBackground(Color.LIGHT_GRAY);
	}
    }

    @SuppressWarnings("serial")
    private class Ausgabebaugruppe extends Panel {
	Label num[] = new Label[8];

	public Ausgabebaugruppe(CPU listener) {
	    this.setLayout(new GridLayout(8, 1, 0, 15));
	    for (byte i = 0; i < 8; i++) {
		this.num[i] = new Label("", Label.CENTER);
		this.num[i].setBackground(Color.RED);
		this.add(this.num[i]);
		listener.addLabel(num[i], "OUT: 41 " + i);
	    }
	    this.setBackground(Color.LIGHT_GRAY);
	}
    }

    @SuppressWarnings("serial")
    private class Debugger extends Panel {
	Panel panel[] = new Panel[] { new Panel(new GridLayout(2, 8)),
		new Panel(new GridLayout(2, 8)),
		new Panel(new GridLayout(1, 2, 10, 10)),
		new Panel(new GridLayout(1, 2, 10, 10)) };
	Label reg[] = new Label[8];
	Label flag[] = new Label[8];
	Label text[] = new Label[] { new Label("Schritt Modus:"),
		new Label("Brk Modus:") };
	Button key[] = new Button[] { new Button("Aus"), new Button("Aus") };

	public Debugger(CPU listener) {
	    for (byte i = 0; i < 8; i++) {
		this.panel[0].add(new Label(CPU.REGISTER[i]));
		this.panel[1].add(new Label(CPU.FLAG[i]));
	    }
	    for (byte i = 0; i < 8; i++) {
		this.reg[i] = new Label("0");
		listener.addLabel(this.reg[i], "");
		this.reg[i].setName(CPU.REGISTER[i]);
		this.flag[i] = new Label("0");
		if (CPU.FLAG[i].equals("")) {
		    this.flag[i].setText("");
		}
		listener.addLabel(this.flag[i], "");
		this.flag[i].setName(CPU.FLAG[i]);
		this.panel[0].add(this.reg[i]);
		this.panel[1].add(this.flag[i]);
	    }
	    this.panel[2].add(this.text[0]);
	    this.panel[2].add(this.key[0]);
	    this.panel[3].add(this.text[1]);
	    this.panel[3].add(this.key[1]);

	    this.setLayout(new GridLayout(4, 1, 10, 50));
	    this.setBackground(Color.LIGHT_GRAY);
	    for (Panel sp : this.panel) {
		this.add(sp);
	    }
	}
    }

    @SuppressWarnings("serial")
    private class EingabeCode extends Panel {
	TextArea codefield = new TextArea("", 0, 0, TextArea.SCROLLBARS_BOTH);

	public EingabeCode(CPU listener) {
	    this.setLayout(new BorderLayout());
	    this.add(this.codefield, BorderLayout.CENTER);
	    listener.addTextArea(this.codefield, "interpreter");
	    this.setBackground(Color.LIGHT_GRAY);
	}
    }
}
