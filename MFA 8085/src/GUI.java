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
	fenster.setTitle(MFA_ver);
	fenster.setBounds((int) (screen.getWidth() / 4),
		(int) (screen.getHeight() / 4), (int) (screen.getWidth() / 2),
		(int) (screen.getHeight() / 2));
	fenster.setMenuBar(menubar);

	menubar.add(datei);
	for (MenuItem item : d_item) {
	    datei.add(item);
	    this.listener.addMenuitem(item, item.getLabel());
	}
	menubar.add(extras);
	for (MenuItem item : e_item) {
	    extras.add(item);
	    this.listener.addMenuitem(item, item.getLabel());
	}
	menubar.add(ausfuhren);
	ausfuhren.add(i_item);
	this.listener.addMenuitem(i_item, "interpreter");

	code_input = new EingabeCode(listener);
	debugger = new Debugger(listener);
	input = new Eingabebaugruppe(listener);
	output = new Ausgabebaugruppe(listener);

	fenster.setLayout(new GridLayout(1, 8, 10, 0));
	fenster.add(code_input);
	fenster.add(debugger);
	fenster.add(output);
	fenster.add(input);
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
	/**
	 *
	 */
	private static final long serialVersionUID = 7564074877706462179L;
	Panel panel[] = null;
	Label num[] = new Label[8];
	Button key[] = new Button[8];

	public Eingabebaugruppe(CPU listener) {
	    setLayout(new BorderLayout());
	    panel = new Panel[] { new Panel(new GridLayout(8, 1)),
		    new Panel(new GridLayout(8, 1)) };
	    for (byte i = 0; i < 8; i++) {
		num[i] = new Label("" + i, Label.CENTER);
		num[i].setBackground(Color.gray);
		panel[0].add(num[i]);
		key[i] = new Button("Aus");
		key[i].setActionCommand("IN: 40");
		panel[1].add(key[i]);
		listener.addButton(key[i], "IN: 40 " + i);
		key[i].setBackground(Color.RED);
	    }
	    this.add(panel[0], BorderLayout.WEST);
	    this.add(panel[1], BorderLayout.CENTER);
	    setBackground(Color.LIGHT_GRAY);
	}
    }

    @SuppressWarnings("serial")
    private class Ausgabebaugruppe extends Panel {
	/**
	 *
	 */
	private static final long serialVersionUID = -6266519008047377791L;
	Label num[] = new Label[8];

	public Ausgabebaugruppe(CPU listener) {
	    setLayout(new GridLayout(8, 1, 0, 15));
	    for (byte i = 0; i < 8; i++) {
		num[i] = new Label("", Label.CENTER);
		num[i].setBackground(Color.RED);
		this.add(num[i]);
		listener.addLabel(num[i], "OUT: 41 " + i);
	    }
	    setBackground(Color.LIGHT_GRAY);
	}
    }

    @SuppressWarnings("serial")
    private class Debugger extends Panel {
	/**
	 *
	 */
	private static final long serialVersionUID = -7640849266832185398L;
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
		panel[0].add(new Label(CPU.REGISTER[i]));
		panel[1].add(new Label(CPU.FLAG[i]));
	    }
	    for (byte i = 0; i < 8; i++) {
		reg[i] = new Label("0");
		listener.addLabel(reg[i], "");
		reg[i].setName(CPU.REGISTER[i]);
		flag[i] = new Label("0");
		if (CPU.FLAG[i].equals("")) {
		    flag[i].setText("");
		}
		listener.addLabel(flag[i], "");
		flag[i].setName(CPU.FLAG[i]);
		panel[0].add(reg[i]);
		panel[1].add(flag[i]);
	    }
	    panel[2].add(text[0]);
	    panel[2].add(key[0]);
	    panel[3].add(text[1]);
	    panel[3].add(key[1]);

	    setLayout(new GridLayout(4, 1, 10, 50));
	    setBackground(Color.LIGHT_GRAY);
	    for (Panel sp : panel) {
		this.add(sp);
	    }
	}
    }

    @SuppressWarnings("serial")
    private class EingabeCode extends Panel {
	/**
	 *
	 */
	private static final long serialVersionUID = -6579533556301716935L;
	TextArea codefield = new TextArea("", 0, 0, TextArea.SCROLLBARS_BOTH);

	public EingabeCode(CPU listener) {
	    setLayout(new BorderLayout());
	    this.add(codefield, BorderLayout.CENTER);
	    listener.addTextArea(codefield, "interpreter");
	    setBackground(Color.LIGHT_GRAY);
	}
    }
}
