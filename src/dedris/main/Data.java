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

import java.io.File;

import dedris.geometric.GeometricObject;

/**
 * Die wichtigsten Parameter des Spiels werden in dieser Klasse gespeichert.
 * 
 * @author DSIW
 * 
 */
public class Data
{

  // DEFAULT SETTINGS
  /**
   * Standard-Einstellung: Spielername
   */
  public static String                NAME                  = Data
                                                                .getUserName();
  /**
   * Standard-Einstellung: Farbveränderung
   */
  public static boolean               RANDOM_COLOR          = false;
  /**
   * Standard-Einstellung: Startlevel
   */
  public static int                   START_LEVEL           = 0;
  /**
   * Standard-Einstellung: Pfad zur Highscore-Datei
   */
  public static String                HIGHSCORE_FILE        = Data
                                                                .getUserHomeDirectory()
                                                                + File.separator
                                                                + "games"
                                                                + File.separator
                                                                + "dedris.scores";

  /**
   * Breite der Steine
   */
  public static final int             STONE_WIDTH           = 30;                         // in
                                                                                           // px
  /**
   * Wahrheitswert, ob die gleiche Figur unterschiedliche Farben haben darf.
   */
  public static boolean               randomColor           = RANDOM_COLOR;
  /**
   * Anzahl der Spalten auf dem Spielfeld
   */
  public static final int             COLUMNS               = 14;
  /**
   * Anzahl der Zeilen auf dem Spielfeld
   */
  public static final int             ROWS                  = 21;
  /**
   * Breite der Anzeige der Statistik
   */
  @SuppressWarnings("unused")
  public static int                   WINDOW_WIDTH_BORDER   = (STONE_WIDTH * 5) < 200 ? 200
                                                                : STONE_WIDTH * 5;
  /**
   * Breite des Fensters
   */
  public static final int             WINDOW_WIDTH          = COLUMNS
                                                                * STONE_WIDTH
                                                                + WINDOW_WIDTH_BORDER;
  /**
   * Breite des Spielfelds
   */
  public static final int             WINDOW_WIDTH_FIELD    = WINDOW_WIDTH
                                                                - WINDOW_WIDTH_BORDER;

  /**
   * Breite des Pause-Rahmens
   */
  public static final int             WINDOW_WIDTH_PAUSE    = (int) (WINDOW_WIDTH * 0.9);

  /**
   * Höhe des unteren Rands
   */
  public static final int             WINDOW_HEIGHT_BORDER  = 0;
  /**
   * Höhe des Fensters
   */
  public static final int             WINDOW_HEIGHT         = ROWS
                                                                * (STONE_WIDTH + 0)
                                                                + WINDOW_HEIGHT_BORDER;
  /**
   * Höhe des Spielfelds
   */
  public static final int             WINDOW_HEIGHT_FIELD   = WINDOW_HEIGHT
                                                                - WINDOW_HEIGHT_BORDER;

  /**
   * Höhe des Pause-Rahmens
   */
  public static final int             WINDOW_HEIGHT_PAUSE   = (int) (WINDOW_HEIGHT * 0.5);

  /**
   * Erzeugt ein Rechteck, welches die Masse des Spielfeldes hat.
   */
  public static final GeometricObject FIELD                 = new GeometricObject(
                                                                0,
                                                                0,
                                                                WINDOW_WIDTH_FIELD,
                                                                WINDOW_HEIGHT_FIELD);

  /**
   * Geschwindigkeit, mit der die Figuren nach unten fallen
   */
  public static final int             FALL_GESCHWINDIGKEIT  = 20;                         // in
                                                                                           // ms

  /**
   * Start Geschwindigkeit in Millisekunden, die angibt, in welchem Intervall
   * die Figur nach unten schreitet im Level 0/1
   */
  public static final int             START_GESCHWINDIGKEIT = 500;                        // in
                                                                                           // ms
  /**
   * Anzahl der Level
   */
  public static final int             ANZAHL_LEVEL          = 15;
  /**
   * Level, bei dem das Spiel beginnen soll.
   */
  public static int                   startLevel            = START_LEVEL;

  /**
   * TODO ?? Punkte pro Level
   */
  public static final int             POINTS_PER_LEVEL      = 40;
  /**
   * Punkte pro Figur, die gespeichert wird
   */
  public static final int             POINTS_PER_FIGURE     = 2;
  /**
   * Anfangspunkte
   */
  public static final int             MIN_POINTS            = 0;

  /**
   * Trennzeichen, zur Abtrennung der Daten in der Highscore-Datei
   */
  public static final String          SEPARATOR             = ";;";

  /**
   * Pfad und Dateiname der Highscore-Datei
   */
  public static String                highscoreFile         = HIGHSCORE_FILE;

  /**
   * Maximale Anzahl der Spieler in einer Highscore-Datei
   */
  public static int                   HIGHSCORE_MAX_GAMER   = 15;


  /**
   * Bekomme Benutzernamen des angemeldeten Benutzers
   * 
   * @return Benutzername
   */
  public static String getUserName ()
  {
    final String PATTERN = "user.name";
    // System.getProperties().list(System.out);
    return System.getProperties().getProperty(PATTERN);
  }


  /**
   * Bekomme Benutzerheimverzeichnis des angemeldeten Benutzers
   * 
   * @return Benutzerheimverzeichnis
   */
  public static String getUserHomeDirectory ()
  {
    final String PATTERN = "user.home";
    return System.getProperties().getProperty(PATTERN);
  }

}
