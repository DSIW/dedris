package dedris.geometric;

import java.awt.Color;

/**
 * Verwaltung Geometrischer Objekte (Rechtecke)
 * 
 * @author DSIW
 * 
 */
public class GeometricObject implements GameObject
{

  private int     width    = 0;
  private int     height   = 0;
  private Vertex  v        = new Vertex();
  private Vertex  d        = new Vertex();
  private Vertex  end      = new Vertex();
  private boolean isFilled = false;        ;
  private Color   color    = new Color(0);
  private Vertex  abstand  = new Vertex();


  /**
   * Setze Abstand
   * 
   * @param abstand
   *          Abstand
   */
  public void setAbstand (Vertex abstand)
  {
    this.abstand = abstand;
  }


  /**
   * Bekomme Abstand
   * 
   * @return Abstand
   */
  public Vertex getAbstand ()
  {
    return abstand;
  }


  /**
   * Bekomme Startpunkt
   * 
   * @return Startpunkt
   */
  public Vertex getV ()
  {
    return v;
  }


  /**
   * Setze Startpunkt
   * 
   * @param v
   *          Startpunkt
   */
  public void setV (Vertex v)
  {
    this.v = v;
  }


  /**
   * Bekomme Geschwindigkeit
   * 
   * @return Geschwindigkeit
   */
  public Vertex getD ()
  {
    return d;
  }


  /**
   * Setze Geschwindigkeit
   * 
   * @param d
   *          Geschwindigkeit
   */
  public void setD (Vertex d)
  {
    this.d = d;
  }


  /**
   * Bekomme Endpunkt
   * 
   * @return Endpunkt
   */
  public Vertex getEnd ()
  {
    return end;
  }


  private void setEnd (Vertex end)
  {
    this.end = end;
  }


  /**
   * Setze Breite
   * 
   * @param width
   *          Breite
   */
  public void setWidth (int width)
  {
    this.width = width;
  }


  /**
   * Setze Höhe
   * 
   * @param height
   *          Höhe
   */
  public void setHeight (int height)
  {
    this.height = height;
  }


  /**
   * Setze Wahrheitswert, ob es gefüllt sein soll.
   * 
   * @param isFilled
   *          Wahrheitswert
   */
  public void setFilled (boolean isFilled)
  {
    this.isFilled = isFilled;
  }


  /**
   * Bekomme Wahrheitswert, ob es gefüllt ist.
   * 
   * @return Wahrheitswert
   */
  public boolean isFilled ()
  {
    return isFilled;
  }


  /**
   * Bekomme Farbe
   * 
   * @return Farbe
   */
  public Color getColor ()
  {
    return color;
  }


  /**
   * Setze Farbe
   * 
   * @param color
   *          Farbe
   */
  public void setColor (Color color)
  {
    this.color = color;
  }


  /**
   * Erzeugt ein neues Rechteck mit verschiedenen Eigenschaften.
   * Standard-Eigenschaften sind, dass es keine Geschwindigkeit hat, sowohl
   * vertikal, als auch horizontal; keine Füllung; Farbe 'weiß'; Höhe und Breite
   * auf 1 Pixel; Startpunkt liegt bei (0,0).
   */
  public GeometricObject()
  {
    this(0, 0);
  }


  /**
   * Erzeugt ein neues Rechteck mit verschiedenen Eigenschaften.
   * Standard-Eigenschaften sind, dass es keine Geschwindigkeit hat, sowohl
   * vertikal, als auch horizontal; keine Füllung; Farbe 'weiß'; Höhe und Breite
   * auf 1 Pixel.
   * 
   * @param x
   *          Start-Punkt (horizontal)
   * @param y
   *          Start-Punkt (vertikal)
   */
  public GeometricObject(int x, int y)
  {
    this(x, y, 1, 1);
  }


  /**
   * Erzeugt ein neues Rechteck mit verschiedenen Eigenschaften.
   * Standard-Eigenschaften sind, dass es keine Geschwindigkeit hat, sowohl
   * vertikal, als auch horizontal; keine Füllung und die Farbe 'weiß'.
   * 
   * @param x
   *          Start-Punkt (horizontal)
   * @param y
   *          Start-Punkt (vertikal)
   * @param width
   *          Breite
   * @param height
   *          Höhe
   */
  public GeometricObject(int x, int y, int width, int height)
  {
    this(x, y, width, height, 0, 0, false, Color.WHITE);
  }


  /**
   * Erzeugt ein neues Rechteck mit verschiedenen Eigenschaften.
   * 
   * @param x
   *          Start-Punkt (horizontal)
   * @param y
   *          Start-Punkt (vertikal)
   * @param width
   *          Breite
   * @param height
   *          Höhe
   * @param dx
   *          Geschwindigkeit (horizontal)
   * @param dy
   *          (vertikal)
   * @param isFilled
   *          Füllen mit Farbe
   * @param c
   *          Farbe
   */
  public GeometricObject(int x, int y, int width, int height, int dx, int dy,
      boolean isFilled, Color c)
  {
    setV(new Vertex(x, y));
    setAbstand(new Vertex(x, y));

    setWidth(width);
    setHeight(height);

    setEnd(getV().add(new Vertex(getWidth(), getHeight())));

    setD(new Vertex(dx, dy));

    setFilled(isFilled);
    setColor(c);

    calcEndV();
  }


  protected void calcEndV ()
  {
    setEnd(getV().add(new Vertex(getWidth(), getHeight())));
  }


  @Override
  public String toString ()
  {
    return "Das Rechteck mit den Anfangspunkten [" + v.getX() + " | "
        + v.getY() + "] hat eine Breite von " + width + " und eine Hoehe von "
        + height + ".";
  }


  @Override
  public boolean equals (Object obj)
  {
    if (obj instanceof GeometricObject) {
      GeometricObject that = (GeometricObject) obj;
      return that.v.equals(this.v) && that.width == this.width
          && that.height == this.height;
    }
    return false;
  }


  void moveTo (Vertex v)
  {
    this.v = v;
  }


  void move (Vertex v)
  {
    this.v = this.v.add(v);
  }


  double area ()
  {
    return this.width * this.height;
  }


  double weight ()
  {
    return area();
  }


  boolean hasWithinX (Vertex p)
  {
    if (p.getX() >= v.getX() && p.getX() <= getEnd().getX()) {
      return true;
    } else {
      return false;
    }
  }


  boolean hasWithinY (Vertex p)
  {
    if (p.getY() >= v.getY() && p.getY() <= getEnd().getY()) {
      return true;
    } else {
      return false;
    }
  }


  boolean hasWithin (Vertex p)
  {
    if (hasWithinX(p) && hasWithinY(p)) {
      return true;
    } else {
      return false;
    }
  }

  boolean touchOhneEcke = false;


  boolean hasWithin (GeometricObject that)
  {
    final Vertex thatvlo = that.v;
    final Vertex thatvlu = that.v.add(new Vertex(0, that.height));
    final Vertex thatvro = that.v.add(new Vertex(that.width, 0));
    final Vertex thatvru = that.v.add(new Vertex(that.width, that.height));

    // Sonderfall "Kreuz"
    final Vertex thisvlo = this.v;
    final Vertex thisvro = this.v.add(new Vertex(width, 0));

    if (this.hasWithinY(thatvlo) && this.hasWithinY(thatvlu)
        && that.hasWithinX(thisvlo) && that.hasWithinX(thisvro)) {
      touchOhneEcke = true;
    } else {
      touchOhneEcke = false;
    }
    // //

    if (this.hasWithin(thatvlo) || this.hasWithin(thatvlu)
        || this.hasWithin(thatvro) || this.hasWithin(thatvru) || touchOhneEcke) {
      return true;
    } else {
      return false;
    }
  }


  public boolean touches (GameObject that)
  {
    if (that instanceof GeometricObject) {
      GeometricObject new_geoObj = (GeometricObject) that;
      // Prüfe, ob Obj1 in Obj2 ODER Obj2 in Obj1
      return this.hasWithin(new_geoObj) || new_geoObj.hasWithin(this);
    }
    return false;
  }


  public Vertex getPosition ()
  {
    return this.v;
  }


  public int getWidth ()
  {
    return this.width;
  }


  public int getHeight ()
  {
    return (int) this.height;
  }


  // public void paintMeTo(Graphics g) {
  // g.setColor(color);
  // g.drawRect(v.getX(), v.getY(), width, height);
  // if (isFilled()) {
  // g.fillRect(v.getX(), v.getY(), width, height);
  // }
  // g.setColor(new Color(0));
  // }

  public void moveHorizontal ()
  {
    v = v.add(new Vertex(getD().getX(), 0));
  }


  public void moveDown ()
  {
    v = v.add(new Vertex(0, getD().getY()));
  }


  // public void reverseX() {
  // getD().setX(getD().getX() * -1);
  // }
  //
  // public void reverseY() {
  // getD().setY(getD().getY() * -1);
  // }

  // public void reverse() {
  // reverseX();
  // reverseY();
  // }

  /**
   * Setze horizontale Geschwindigkeit auf 0
   */
  public void stopSpeedHorizontal ()
  {
    setD(new Vertex(0, getD().getY()));
  }


  /**
   * Setze vertikale Geschwindigkeit auf 0
   */
  public void stopSpeedVertical ()
  {
    setD(new Vertex(getD().getX(), 0));
  }

}
