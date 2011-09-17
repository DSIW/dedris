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
