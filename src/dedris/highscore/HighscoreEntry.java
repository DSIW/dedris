package dedris.highscore;

import dedris.game.Gamer;

/**
 * Bildet einen Eintrag in der Highscore-Liste ab. Dieser besteht aus dem
 * Spieler, dem erreichten Level und den Punkten.
 * 
 * @author DSIW
 * 
 */
public class HighscoreEntry
{

  private Gamer gamer;
  private int   points;
  private int   level;


  /**
   * Bekomme Spieler
   * 
   * @return Spieler
   */
  public Gamer getGamer ()
  {
    return gamer;
  }


  /**
   * Setze Spieler
   * 
   * @param gamer
   *          Spieler
   */
  public void setGamer (Gamer gamer)
  {
    this.gamer = gamer;
  }


  /**
   * Bekomme Punkte
   * 
   * @return Punkte
   */
  public int getPoints ()
  {
    return points;
  }


  /**
   * Setze Punkte
   * 
   * @param points
   *          Punkte
   */
  public void setPoints (int points)
  {
    this.points = points;
  }


  /**
   * Bekomme Level
   * 
   * @return Level
   */
  public int getLevel ()
  {
    return level;
  }


  /**
   * Setze Level
   * 
   * @param level
   *          Level
   */
  public void setLevel (int level)
  {
    this.level = level;
  }


  /**
   * Eintrag wird erzeugt. Spieler-Name wird gefragt. Spieler wird hinzugefügt
   * mit 0 Punkten und 0 Level
   */
  public HighscoreEntry()
  {
    this(new Gamer(), 0, 0);
  }


  /**
   * Eintrag wird erzeugt. Spieler wird hinzugefügt.
   * 
   * @param gamer
   *          Spieler
   * @param level
   *          Level
   * @param points
   *          Punkte
   */
  public HighscoreEntry(Gamer gamer, int level, int points)
  {
    this.gamer = gamer;
    this.points = points;
    this.level = level;
  }


  @Override
  public String toString ()
  {
    return "Gamer " + gamer + " (" + level + ".l, " + points + "pt)";
  }


  @Override
  public boolean equals (Object obj)
  {
    if (obj instanceof HighscoreEntry) {
      HighscoreEntry that = (HighscoreEntry) obj;
      if (this.getGamer().equals(that.getGamer())
          && this.getLevel() == that.getLevel()
          && this.getPoints() == that.getPoints()) {
        return true;
      }
    }
    return false;
  }

}
