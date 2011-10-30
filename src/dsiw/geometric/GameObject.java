package dsiw.geometric;

/**
 * Verwaltung der Spielobjekte
 * 
 * @author DSIW
 *
 */
public interface GameObject {

	/**
	 * Prüft, ob ein Spielobjekt ein anderes Spielobjekt berührt
	 * @param that andere Spielobjekt
	 * @return true, wenn eine Berührung stattfindet
	 */
	public boolean touches(GameObject that);

	/**
	 * Bekomme Position der Spielfigur
	 * @return Position
	 */
	public Vertex getPosition();

	/**
	 * Gekomme Breite
	 * @return Breite
	 */
	public int getWidth();

	/**
	 * Bekomme Höhe
	 * @return Höhe
	 */
	public int getHeight();

	/**
	 * Setze die Figur um einen Abstand nach rechts.
	 */
	public void moveHorizontal();
	
	/**
	 * Setze die Figur um einen Abstand nach unten.
	 */
	public void moveDown();
	
}