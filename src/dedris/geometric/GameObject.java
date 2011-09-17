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
package dedris.geometric;

/**
 * Verwaltung der Spielobjekte
 * 
 * @author DSIW
 * 
 */
public interface GameObject
{

  /**
   * Prüft, ob ein Spielobjekt ein anderes Spielobjekt berührt
   * 
   * @param that
   *          andere Spielobjekt
   * @return true, wenn eine Berührung stattfindet
   */
  public boolean touches (GameObject that);


  /**
   * Bekomme Position der Spielfigur
   * 
   * @return Position
   */
  public Vertex getPosition ();


  /**
   * Gekomme Breite
   * 
   * @return Breite
   */
  public int getWidth ();


  /**
   * Bekomme Höhe
   * 
   * @return Höhe
   */
  public int getHeight ();


  /**
   * Setze die Figur um einen Abstand nach rechts.
   */
  public void moveHorizontal ();


  /**
   * Setze die Figur um einen Abstand nach unten.
   */
  public void moveDown ();

}