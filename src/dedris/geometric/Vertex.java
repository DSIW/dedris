package dedris.geometric;

/**
 * Verwaltung einer Koordinate
 * 
 * @author DSIW
 * 
 */
public class Vertex
{

  private int x;
  private int y;


  /**
   * Bekomme X-Wert
   * 
   * @return X-Wert
   */
  public int getX ()
  {
    return x;
  }


  /**
   * Setze X-Wert
   * 
   * @param x
   *          X-Wert
   */
  public void setX (int x)
  {
    this.x = x;
  }


  /**
   * Bekomme Y-Wert
   * 
   * @return Y-Wert
   */
  public int getY ()
  {
    return y;
  }


  /**
   * Setze Y-Wert
   * 
   * @param y
   *          Y-Wert
   */
  public void setY (int y)
  {
    this.y = y;
  }


  /**
   * Erzeugt einen Vertex mit 0,0.
   */
  public Vertex()
  {
    this(0, 0);
  }


  /**
   * Erzeugt einen Vertex mit den beiden Parametern.
   * 
   * @param x
   *          X-Wert
   * @param y
   *          Y-Wert
   */
  public Vertex(int x, int y)
  {
    this.x = x;
    this.y = y;
  }


  @Override
  public String toString ()
  {
    return String.format("[%02d | %02d]", this.x, this.y);
  }


  @Override
  public boolean equals (Object obj)
  {
    if (obj instanceof Vertex) {
      Vertex that = (Vertex) obj;
      return that.x == this.x && that.y == this.y;
    }
    return false;
  }


  /**
   * Addiere einen anderen Vertex
   * 
   * @param that
   *          anderer Vertex
   * @return berechneter Vertex
   */
  public Vertex add (Vertex that)
  {
    return (new Vertex(this.x + that.x, this.y + that.y));
  }


  /**
   * Subtrahiere einen anderen Vertex
   * 
   * @param that
   *          anderer Vertex
   * @return berechneter Vertex
   */
  public Vertex sub (Vertex that)
  {
    return (new Vertex((this.x * 100 - that.x * 100) / 100,
        (this.y * 100 - that.y * 100) / 100));
  }


  /**
   * Multipliziere einen Skalar
   * 
   * @param skalar
   *          Skalar
   * @return berechneter Vertex
   */
  public Vertex mult (int skalar)
  {
    return (new Vertex(this.x * skalar, this.y * skalar));
  }

}
