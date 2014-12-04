package main.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Output8BitModul extends Panel implements ModulableByte {
    final String NAME  = "";
    Label	num[] = new Label[8];

    public Output8BitModul() {
	this.setLayout(new GridLayout(8, 1, 0, 15));
	for (byte i = 0; i < 8; i++) {
	    num[i] = new Label("0", Label.CENTER);
	    num[i].setBackground(Color.RED);
	    add(num[i]);
	}
	setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void setValue(Byte value) {
	int var = value.intValue();
	for (int i = 0; i < num.length; i++) {
	    if ((var & (1 << i)) > 0) {
		num[i].setBackground(Color.GREEN);
		num[i].setText("1");
	    } else {
		num[i].setBackground(Color.RED);
		num[i].setText("0");
	    }
	}
    }

    @SuppressWarnings("unchecked")
    @Override
    public Byte getValue() {
	return (byte) (0);
    }

    @Override
    public String getName() {
	return NAME;
    }

}
