package main.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import main.CPU;

class Debugger extends Panel {
    Panel panel[] = new Panel[] { new Panel(new GridLayout(2, 8)),
	    new Panel(new GridLayout(2, 8)),
	    new Panel(new GridLayout(1, 2, 10, 10)),
	    new Panel(new GridLayout(1, 2, 10, 10)) };
    Label reg[] = new Label[8];
    Label flag[] = new Label[8];
    Label text[] = new Label[] { new Label("Schritt Modus:"),
	    new Label("Brk Modus:") };
    Button key[] = new Button[] { new Button("Aus"), new Button("Aus") };

    public Debugger() {
	for (byte i = 0; i < 8; i++) {
	    this.panel[0].add(new Label(CPU.REGISTER[i]));
	    this.panel[1].add(new Label(CPU.FLAG[i]));
	}
	for (byte i = 0; i < 8; i++) {
	    this.reg[i] = new Label("0");
	    this.reg[i].setName(CPU.REGISTER[i]);
	    this.flag[i] = new Label("0");
	    if (CPU.FLAG[i].equals("")) {
		this.flag[i].setText("");
	    }
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
