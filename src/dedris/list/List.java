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
package dedris.list;

/**
 * Listen werden verwaltet. Die Liste kann einen beliebigen Datentypen
 * enthalten. Der allerdings bei jedem Eintrag gleich sein muss.
 * 
 * @author DSIW
 * 
 * @param <A>
 *          Datentyp
 */
public interface List<A>
{

  /**
   * Bekomme das letzte Element der Liste.
   * 
   * @return Element
   */
  A getElement ();


  /**
   * Bekomme restliche Liste.
   * 
   * @return Liste
   */
  List<A> getNext ();


  /**
   * Wahrheitswert, ob die Liste leer ist. D.h. keinen Eintrag enthält.
   * 
   * @return true, wenn Liste leer ist.
   */
  boolean isEmpty ();


  /**
   * Füge einen Eintrag, am Anfang der Liste, hinzu.
   * 
   * @param a
   */
  void add (A a);


  /**
   * Liefert die Anzahl der enthaltenen Elemente.
   * 
   * @return Anzahl.
   */
  int size ();


  /**
   * Prüft, ob die Liste den Eintrag enthält.
   * 
   * @param a
   *          Eintrag
   * @return true, wenn Eintrag vorhanden ist.
   */
  boolean contains (A a);


  /**
   * Bekomme das Element mit einem bestimmten Index.
   * 
   * @param i
   *          Index
   * @return Element
   */
  A get (int i);


  /**
   * Bekomme das letzte Element der Liste.
   * 
   * @return Element
   */
  A last ();


  /**
   * Bekomme neue Liste, die umgedreht ist.
   * 
   * @return umgedrehte Liste
   */
  List<A> reverse ();

}
