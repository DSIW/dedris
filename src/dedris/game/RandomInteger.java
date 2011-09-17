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

import java.util.Random;

/**
 * Generiert Zufallszahlen.
 * 
 * @author DSIW
 * 
 */
public class RandomInteger
{

  /**
   * Bekomme Zufallszahl, die aus den Parametern generiert ist.
   * 
   * @param min
   *          Minimal-Zufallszahl (Intervall-Grenze)
   * @param max
   *          Maximal-Zufallszahl (Intervall-Grenze)
   * @return Zufallszahl
   */
  public int getRandomInteger (int min, int max)
  {
    Random random = new Random();
    return random.nextInt(max - min + 1) + min;
  }

}
