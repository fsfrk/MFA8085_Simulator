package main.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;

import main.Languages;

public class C_MenuBar extends MenuBar {//To-Do Anzeige Texte in sprache Datei aulagern
    public final static int MenuList[][] = { { 5, 0 }, { 6, 13, 14 }, { 7, 11 }, { 8 } };

    public C_MenuBar(ActionListener listener) {
	for (int[] menu_text : MenuList) {
	    Menu menu = new Menu(Languages.getText(menu_text[0]));
	    for (int i = 1; i < menu_text.length; i++) {
		if (menu_text.length > 1) {
		    MenuItem item = new MenuItem(Languages.getText(menu_text[i]));
		    menu.add(item);
		    item.addActionListener(listener);
		    item.setActionCommand("" + menu_text[i]);
		} else {
		    menu.addActionListener(listener);
		}
	    }
	    add(menu);
	}
    }
}
