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
 * Verkettete Listen werden verwaltet. Die Liste kann einen beliebigen
 * Datentypen enthalten. Der allerdings bei jedem Eintrag gleich sein muss.
 * 
 * @author DSIW
 * 
 * @param <E>
 *          Datentyp
 */
public class LinkedList<E> implements List<E>
{

  // data != null
  E       data;
  // Wenn next == null, dann data == null (d.h. leeres Element)
  List<E> next;


  /**
   * Erzeugt eine Liste mit keinem Inhalt. (letzter Eintrag)
   */
  public LinkedList()
  {
    data = null;
    next = null;
  }


  /**
   * Erzeugt eine neue Liste aus dem Datum und der alten Liste.
   * 
   * @param data
   *          Datum
   * @param next
   *          alte Liste
   */
  public LinkedList(E data, List<E> next)
  {
    this.data = data;
    this.next = next;
  }


  public E getElement ()
  {
    return data;
  }


  public List<E> getNext ()
  {
    return next;
  }


  public boolean isEmpty ()
  {
    return data == null && next == null;
  }


  public void add (E a)
  {
    if (a == null) return;
    // Alte Liste kopieren mit Werten
    LinkedList<E> copy = new LinkedList<E>(getElement(), getNext());
    // Aktuelles Element einfügen
    data = a;
    // Alte Liste verweisen
    next = copy;
  }


  public int size ()
  {
    if (isEmpty()) {
      return 0;
    } else {
      return getNext().size() + 1;
    }
  }


  public boolean contains (E a)
  {
    if (isEmpty()) {
      return false;
    }
    if (getElement().equals(a)) {
      return true;
    } else {
      return getNext().contains(a);
    }
  }


  public E get (int i)
  {
    if (i < 0 || i > size()) {
      return null;
    }
    if (i == 0) {
      return getElement();
    } else {
      // List<E> newList = this;
      // // newList i-mal verkürzen, damit beim letzten newList-Element,
      // das
      // // Datum drin steht.
      // for (int j = 1; j <= i; j++) {
      // newList = newList.getNext();
      // }
      return getNext().get(i - 1);
    }
  }


  public E last ()
  {
    // List<E> temp = new LinkedList<E>();
    // for (List<E> newList = this; !newList.isEmpty(); newList = newList
    // .getNext()) {
    // temp = newList;
    // }
    // return temp.getElement();

    // return getNext().get(size()-1);

    if (getNext().isEmpty()) {
      return getElement();
    }
    return getNext().last();
  }


  public List<E> reverse ()
  {
    List<E> temp = new LinkedList<E>();
    for (List<E> newList = this; !newList.isEmpty(); newList = newList
        .getNext()) {
      temp.add(newList.getElement());
    }
    return temp;
  }

}
