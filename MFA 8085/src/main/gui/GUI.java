package main.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import main.listener.guiListener;

public class GUI implements WindowListener {
    protected final String		     MFA_ver = "MFA 8085 Version Beta 1.1";		// Name of the
												      // project +
												      // version
												      // number
    private static HashMap<Integer, Modulable> modules = new HashMap<>();

    private static Frame		       window  = new Frame();
    private final Dimension		    screen  = Toolkit.getDefaultToolkit().getScreenSize();

    // //////////////////////////////////////////////////////////////////////////////////////////////////////
    // /////////////////////////////////////Methoden/////////////////////////////////////////////////////////

    public GUI(guiListener listener) {
	C_MenuBar menubar = new C_MenuBar(listener);

	Input8BitModul input;
	Output8BitModul output;
	InputAssemblerCode code_input;
	Debugger debugger;

	window.setTitle(MFA_ver);
	window.setBounds((int) (screen.getWidth() / 4), (int) (screen.getHeight() / 4), (int) (screen.getWidth() / 2), (int) (screen.getHeight() / 2));
	window.setMenuBar(menubar);

	code_input = new InputAssemblerCode();
	debugger = new Debugger();
	input = new Input8BitModul();
	output = new Output8BitModul();

	window.setLayout(new GridLayout(1, 8, 10, 0));
	window.add(code_input);
	window.add(debugger);
	window.add(output);
	window.add(input);
	window.addWindowListener(this);
	// window.add(.adconverter);

	window.setBackground(Color.LIGHT_GRAY);
	window.setVisible(true);

	modules.put(0, code_input);
	modules.put(40, input);
	modules.put(41, output);

    }

    public static Modulable getModul(Integer port) {
	return modules.get(port);
    }

    public static void createErrorMessage(String message, final boolean exit) {
	new ErrorMessage(message, window, exit);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowClosed(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowClosing(WindowEvent arg0) {
	System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowIconified(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void windowOpened(WindowEvent arg0) {
	// TODO Auto-generated method stub

    }
}
