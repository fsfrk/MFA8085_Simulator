package main.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input8BitModul extends Panel implements ModulableByte, ActionListener {
    private final String NAME	 = "";
    private Button       key[]	= new Button[8];
    private String       ButtonText[] = { "on", "off" };

    public Input8BitModul() {
	Panel panel[] = new Panel[] { new Panel(new GridLayout(8, 1)), new Panel(new GridLayout(8, 1)) };
	Label num[] = new Label[8];
	this.setLayout(new BorderLayout());
	for (byte i = 0; i < 8; i++) {
	    num[i] = new Label("" + i, Label.CENTER);
	    num[i].setBackground(Color.gray);
	    panel[0].add(num[i]);
	    key[i] = new Button(ButtonText[1]);
	    key[i].setActionCommand("" + i);
	    panel[1].add(key[i]);
	    key[i].addActionListener(this);
	    key[i].setBackground(Color.RED);
	}
	add(panel[0], BorderLayout.WEST);
	add(panel[1], BorderLayout.CENTER);
	setBackground(Color.LIGHT_GRAY);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Byte getValue() {
	byte value = 0;
	for (int i = 0; i < key.length; i++) {
	    value |= key[i].getBackground() == Color.GREEN ? 1 << i : 0;
	}
	return Byte.valueOf(value);
    }

    @Override
    public String getName() {
	return NAME;
    }

    /**
     * Modul ist nicht zur Ausgabe gedacht Methode Leer
     */
    @Override
    public void setValue(Byte value) {}

    @Override
    public void actionPerformed(ActionEvent arg0) {
	Button key = (Button) (arg0.getSource());
	if (key.getBackground() == Color.RED) {
	    key.setBackground(Color.GREEN);
	    key.setLabel(ButtonText[0]);
	} else {
	    key.setBackground(Color.RED);
	    key.setLabel(ButtonText[1]);
	}
    }
}
