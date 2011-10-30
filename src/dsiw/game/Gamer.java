package dsiw.game;

import javax.swing.JOptionPane;

import dsiw.main.Data;

/**
 * Erzeugt einen Spieler.
 * 
 * @author DSIW
 *
 */
public class Gamer {

	private String name;

//	private String firstname;
	
	/**
	 * Bekomme Spielernamen
	 * @return Spielername
	 */
	public String getName() {
		return name;
	}
	/**
	 * Setze Spielername
	 * @param name Spielername
	 */
	public void setName(String name) {
		this.name = name;
	}
//	public String getFirstname() {
//		return firstname;
//	}
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
	
	/**
	 * Frage nach Namen des Spieler. Wenn die Länge der Antwort kleiner als 2, dann wird der Spielernamen auf "anonymous" gesetzt.
	 */
	public Gamer() {
		try {
			String tempName = askName();
			setName(tempName.length() >= 2 ? tempName : "anonymous");
		} catch (NullPointerException e) {
			setName("anonymous");
		}
	}
	
	/**
	 * Konstruktor, mit dem ein Spielernamen übergeben wird.
	 * @param name Spielername
	 */
	public Gamer(String name) {
		this.name = name;
//		this.firstname = firstname;
	}
	
	/**
	 * Frage nach dem Namen des Spielers mit Hilfe eines Dialog-Fensters.
	 * @return Name
	 */
	public String askName() {
		String userName = Data.getUserName();
		
		name = JOptionPane.showInputDialog("Bitte geben Sie Ihren Namen ein!\n(minimum: 2 Zeichen)", userName);
		
		while(name.indexOf(Data.SEPARATOR) >= 0) {
			name = JOptionPane.showInputDialog("Bitte geben Sie Ihren Namen ein!\n(minimum: 2 Zeichen, kein \";;\")", userName);
		}
		
		return name;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Gamer) {
			Gamer that = (Gamer) obj;
			if(this.name.equalsIgnoreCase(that.name)) {
				return true;
			}
		}
		return false;
	}
}
