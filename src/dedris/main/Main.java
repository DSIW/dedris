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
package dedris.main;

import javax.swing.JOptionPane;

import dedris.frame.Game;
import dedris.geometric.figure.Figure;

/**
 * Hauptklasse, von der das Spiel gestartet, gestoppt und beendet werden kann.
 * 
 * @author DSIW
 * 
 */
public class Main
{

  /**
   * Wahrheitswert-Variable. True, wenn DEBUG-Modus aktiv.
   */
  public final static boolean DEBUG = false;

  /**
   * Game
   */
  public static Game          gw;


  /**
   * Initialisiere und starte Spiel
   */
  public static void start ()
  {
    gw.reset();
    // Initialisierung
    Figure.add(Figure.getRandom(true));
    Figure.add(Figure.getRandom(true));
  }


  /**
   * Stoppe Spiel
   */
  public static void stop ()
  {
    gw.setGameOver(true);
    gw.repaint();
  }


  /**
   * Schliesse Programm
   */
  public static void exit ()
  {
    int confirm = JOptionPane.showConfirmDialog(gw, "Wirklich beenden?",
        "Beenden", JOptionPane.YES_NO_OPTION);
    if (confirm == 0) {
      stop();
      gw.setVisible(false);
      gw.dispose();
      System.exit(0);
    } else {
      gw.requestFocus();
      gw.requestFocusInWindow();
    }
  }


  /**
   * Erzeugt ein neues Spiel.
   */
  public Main()
  {
    gw = new Game();
  }


  /**
   * Main-Methode
   * 
   * @param _
   *          ungenutzt
   */
  public static void main (String[] _)
  {
    new Main();
  }
}
