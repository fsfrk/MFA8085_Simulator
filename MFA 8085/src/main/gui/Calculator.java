package main.gui;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import main.CPU;

class Calculator extends Frame implements ActionListener, WindowListener {
    Calculator(CPU listener) {
	setTitle("Rechner");
	TextField hex = new TextField(), dec = new TextField(), bin = new TextField();
	Label text[] = new Label[] { new Label("Dezimal: "), new Label("Hex: "), new Label("Biner: ") };
	Button key = new Button("Umrechnen");

	setLayout(new GridLayout(4, 2));
	add(text[0]);
	add(dec);
	add(text[1]);
	add(hex);
	add(text[2]);
	add(bin);
	add(key);

	this.addWindowListener(this);
	dec.addActionListener(this);
	hex.addActionListener(this);
	bin.addActionListener(this);
	key.addActionListener(this);
	setVisible(true);
	setSize(250, 150);
	setLocation(150, 150);
    }

    @Override
    public void windowActivated(WindowEvent arg0) {}

    @Override
    public void windowClosed(WindowEvent arg0) {}

    @Override
    public void windowClosing(WindowEvent arg0) {
	this.setVisible(false);
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {}

    @Override
    public void windowDeiconified(WindowEvent arg0) {}

    @Override
    public void windowIconified(WindowEvent arg0) {}

    @Override
    public void windowOpened(WindowEvent arg0) {}

    @Override
    public void actionPerformed(ActionEvent arg0) {}
}
