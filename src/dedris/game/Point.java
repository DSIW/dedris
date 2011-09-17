package dedris.game;

import dedris.main.Data;

/**
 * Berechnet Punkte aus verschiedenen Parametern.
 * 
 * @author DSIW
 * 
 */
public class Point
{

  int           aktPoints = 0;
  final int     POINTS_PER_LEVEL;
  final int     POINTS_PER_FIGURE;
  final int     MIN_POINTS;
  final boolean staendigerFarbwechsel;


  /**
   * Bekomme die aktuelle Punktzahl
   * 
   * @return Punkte
   */
  public int getPoints ()
  {
    return aktPoints;
  }


  /**
   * Setze aktuellen Punkte aus dem Parameter.
   * 
   * @param aktPoints
   *          Punktzahl
   */
  public void setAktPoints (int aktPoints)
  {
    this.aktPoints = aktPoints;
  }


  /**
   * Bekomme Wahrheitswert von staendigerFarbwechsel
   * 
   * @return staendigerFarbwechsel
   */
  public boolean isStaendigerFarbwechsel ()
  {
    return staendigerFarbwechsel;
  }


  /**
   * Initialisiert die Felder.
   */
  public Point()
  {
    POINTS_PER_LEVEL = Data.POINTS_PER_LEVEL;
    POINTS_PER_FIGURE = Data.POINTS_PER_FIGURE;
    MIN_POINTS = Data.MIN_POINTS;
    aktPoints = MIN_POINTS;
    this.staendigerFarbwechsel = Data.randomColor;
  }


  /**
   * Berechnet die Punkte aus verschiedenen Parametern.
   * 
   * @param fullRows
   *          Volle Reihen
   * @param level
   *          Leel
   * @param figuresInGame
   *          Anzahl der Figuren, die dem Spiel hinzugefügt worden sind.
   */
  public void calc (int fullRows, int level, int figuresInGame)
  {
    aktPoints = (MIN_POINTS + fullRows * POINTS_PER_LEVEL + figuresInGame
        * POINTS_PER_FIGURE);
  }


  @Override
  public String toString ()
  {
    return getPoints() + "";
  }


  @Override
  public boolean equals (Object obj)
  {
    if (obj instanceof Point) {
      Point that = (Point) obj;
      if (this.getPoints() == that.getPoints()) {
        return true;
      }
    }
    return false;
  }
}
