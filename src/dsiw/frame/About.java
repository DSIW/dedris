package dsiw.frame;

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
        "E-Mail: dsiw@dsiw-it.de\n" +
        "\n" +
        "Projekt „Objektorientierte Softwareentwicklung“\n" +
        "im 1. Semester\n"+
        "\n" +
        "Download:\n" +
        "GitHub: https://github.com/DSIW/dedris\n" +
        "\n" +
        "Lizenz:\n" +
        "GPLv3 (siehe http://www.gnu.de/documents/gpl.de.html)";
		
		JOptionPane.showMessageDialog(gw, message, "Über "+gw.getTitle(), 1, icon);
	}
	
}
