package main.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.CPU;
import main.gui.Calculator;

public class guiListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent arg0) {
	try {
	    switch (Integer.parseInt(arg0.getActionCommand())) {
		case 0:
		    System.exit(0);
		case 11:
		    CPU.toogleInterpreter();
		    break;
		case 13:
		    new Calculator();
		    break;

	    }
	}
	catch (Exception e) {
	    System.out.println(e.getMessage());
	}
    }
    /*
     * if(e.getActionCommand().equals("Rechner")) { GUI.createCalculator(this);
     * }
     * 
     * if (e.getActionCommand().equals("umrechnen")) { TextField sp[] = new
     * TextField[3]; sp[0] = field.get("hex"); sp[1] = field.get("dec"); sp[2] =
     * field.get("bin"); if ((sp[0] == null) || (sp[1] == null) || (sp[2] ==
     * null)) { GUI.createErrorMessage(
     * "ERROR, the Label hex,dec,bin doesn't exist", false); } if
     * (((sp[0].getText().length() > 0) && (sp[1].getText().length() > 0)) ||
     * ((sp[1].getText().length() > 0) && (sp[2].getText() .length() > 0)) ||
     * ((sp[2].getText().length() > 0) && (sp[0].getText() .length() > 0))) {
     * GUI.createErrorMessage("Es mussen 2 Felder auf 0 sein", false); } else {
     * try { if (sp[2].getText().length() > 0) {
     * sp[0].setText(Integer.toHexString(Integer.parseInt( sp[2].getText(),
     * 2))); sp[1].setText("" + Integer.parseInt(sp[2].getText(), 2)); } if
     * (sp[0].getText().length() > 0) { sp[1].setText("" +
     * Integer.parseInt(sp[0].getText(), 16));
     * sp[2].setText(Integer.toBinaryString(Integer.parseInt( sp[0].getText(),
     * 16))); } if (sp[1].getText().length() > 0) {
     * sp[0].setText(Integer.toHexString(Integer .parseInt(sp[1].getText())));
     * sp[2].setText(Integer.toBinaryString(Integer
     * .parseInt(sp[1].getText()))); } } catch (NumberFormatException n) {
     * GUI.createErrorMessage("Wert zu gross: " + n.getMessage(), false); } } }
     */

}
