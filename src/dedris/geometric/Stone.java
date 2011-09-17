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

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import dedris.main.Data;

/**
 * Erzeugt einen Stein
 * 
 * @author DSIW
 * 
 */
public class Stone extends GeometricObject
{

  /**
   * Stein-Breite = Stein-Höhe
   */
  public final static int WIDTH      = Data.STONE_WIDTH;
  int                     stoneIndex = 0;


  /**
   * Erzeugt einen quadratischen Stein mit verschiedenen Parametern
   * 
   * @param x
   *          Start-Punkt (oben-links) X-Wert
   * @param y
   *          Start-Punkt (oben-links) Y-Wert
   * @param isFilled
   *          true, Steine sind mit Farbe gefüllt
   * @param c
   *          Füll-Farbe
   * @param stoneIndex
   *          Index des Steins
   */
  public Stone(int x, int y, boolean isFilled, Color c, int stoneIndex)
  {
    super(x, y, WIDTH, WIDTH, 0, WIDTH, isFilled, c);
    this.stoneIndex = stoneIndex;
  }


  /**
   * Zeichnen des Steins
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void paintMeTo (Graphics g)
  {
    ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(
        stoneIndex + ".png"));
    g.drawImage(icon.getImage(), this.getV().getX(), this.getV().getY(),
        icon.getImageObserver());
  }

  // public boolean imageUpdate(Image img, int infoflags, int x, int y, int
  // width, int height) { return false; }
}