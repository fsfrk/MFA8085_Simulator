package main.gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionListener;

public class C_MenuBar extends MenuBar {//To-Do Anzeige Texte in sprache Datei aulagern
    public final static String MenuList[][] = { { "Datei", "Beenden" }, { "Extras", "Rechner", "Befehlssatz" }, { "Control", "Interpreter" },
	    { "Hilfe" }		    };

    public C_MenuBar(ActionListener listener) {
	for (String[] menu_text : MenuList) {
	    Menu menu = new Menu(menu_text[0]);
	    for (int i = 1; i < menu_text.length; i++) {
		if (menu_text.length > 1) {
		    MenuItem item = new MenuItem(menu_text[i]);
		    menu.add(item);
		    item.addActionListener(listener);
		} else {
		    menu.addActionListener(listener);
		}
	    }
	    add(menu);
	}
    }
}
