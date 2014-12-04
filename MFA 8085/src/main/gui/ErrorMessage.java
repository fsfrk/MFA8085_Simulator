package main.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ErrorMessage {
    ErrorMessage(String message, Frame window, final boolean exit) {
	final Dialog error = new Dialog(window, "Error", true);
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
}
