/*
 * dedris - game written in Java
 * Copyright (C) 2011  DSIW <dsiw@privatdemail.net>
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package dedris.frame;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Informations-Dialog mit Informationen zum Spiel und Autor
 * @author DSIW
 *
 */
@SuppressWarnings("serial")
public class About extends MyJFrame {

	/**
	 * Erzeugt den Dialog.
	 * @param gw Game für den Titel des Spiels
	 */
	public About(Game gw) {
		Icon icon = new ImageIcon(getClass().getClassLoader().getResource("icon.png"));
		
		String message = gw.getTitle()+"\n" +
				"Version 1.0\n" +
				"\n" +
				"Autor: DSIW\n" +
				"E-Mail: dsiw@privatdemail.net\n" +
				"\n" +
				"Projekt „Spielprogrammierung“\n" +
				"im 1. Semester"+
				"\n" +
				"Lizenz:\n" +
				"GPLv3" +
				"(siehe http://www.gnu.de/documents/gpl.de.html)";
		
		JOptionPane.showMessageDialog(gw, message, "Über "+gw.getTitle(), 1, icon);
	}
	
}
