package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.awt.TextArea;

public class InputAssemblerCode extends Panel implements Modulable<String> {
    TextArea codefield = new TextArea("", 0, 0, TextArea.SCROLLBARS_BOTH);

    public InputAssemblerCode() {
	this.setLayout(new BorderLayout());
	this.add(this.codefield, BorderLayout.CENTER);
	this.setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void setValue(String value) {
	codefield.setText(value);
    }

    @Override
    public String getValue() {
	return codefield.getText();
    }
}
