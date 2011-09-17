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
