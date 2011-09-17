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
package dedris.game;

import javax.swing.JOptionPane;

import dedris.main.Data;

/**
 * Erzeugt einen Spieler.
 * 
 * @author DSIW
 * 
 */
public class Gamer
{

  private String name;


  // private String firstname;

  /**
   * Bekomme Spielernamen
   * 
   * @return Spielername
   */
  public String getName ()
  {
    return name;
  }


  /**
   * Setze Spielername
   * 
   * @param name
   *          Spielername
   */
  public void setName (String name)
  {
    this.name = name;
  }


  // public String getFirstname() {
  // return firstname;
  // }
  // public void setFirstname(String firstname) {
  // this.firstname = firstname;
  // }

  /**
   * Frage nach Namen des Spieler. Wenn die Länge der Antwort kleiner als 2,
   * dann wird der Spielernamen auf "anonymous" gesetzt.
   */
  public Gamer()
  {
    try {
      String tempName = askName();
      setName(tempName.length() >= 2 ? tempName : "anonymous");
    }
    catch (NullPointerException e) {
      setName("anonymous");
    }
  }


  /**
   * Konstruktor, mit dem ein Spielernamen übergeben wird.
   * 
   * @param name
   *          Spielername
   */
  public Gamer(String name)
  {
    this.name = name;
    // this.firstname = firstname;
  }


  /**
   * Frage nach dem Namen des Spielers mit Hilfe eines Dialog-Fensters.
   * 
   * @return Name
   */
  public String askName ()
  {
    String userName = Data.getUserName();

    name = JOptionPane.showInputDialog(
        "Bitte geben Sie Ihren Namen ein!\n(minimum: 2 Zeichen)", userName);

    while (name.indexOf(Data.SEPARATOR) >= 0) {
      name = JOptionPane
          .showInputDialog(
              "Bitte geben Sie Ihren Namen ein!\n(minimum: 2 Zeichen, kein \";;\")",
              userName);
    }

    return name;
  }


  @Override
  public String toString ()
  {
    return getName();
  }


  @Override
  public boolean equals (Object obj)
  {
    if (obj instanceof Gamer) {
      Gamer that = (Gamer) obj;
      if (this.name.equalsIgnoreCase(that.name)) {
        return true;
      }
    }
    return false;
  }
}
