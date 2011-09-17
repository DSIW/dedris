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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import dedris.exception.NoTerminationException;
import dedris.geometric.Stone;
import dedris.geometric.Vertex;
import dedris.geometric.figure.Figure;
import dedris.main.Data;

/**
 * Klasse, die verschiedene Paint-Methoden enthält.
 * 
 * @author DSIW
 * 
 */
public class Paint
{

  private Gamer gamer;
  private Point points;
  private Level level;
  private int   fullRows;


  // private Font font = new Font(new MyFont().getFont().getFontName(),
  // Font.PLAIN, 14);
  // private Font font = new Font("Ubuntu", Font.PLAIN, 15);

  /**
   * Setze volle Reihen
   * 
   * @param fullRows
   *          volle Reihen
   */
  public void setFullRows (int fullRows)
  {
    this.fullRows = fullRows;
  }


  /**
   * Setze Level
   * 
   * @param level
   *          Level
   */
  public void setLevel (Level level)
  {
    this.level = level;
  }


  /**
   * Initialisierung der Felder.
   * 
   * @param g
   *          Gamer
   * @param l
   *          Level
   * @param p
   *          Points
   * @param fullRows
   *          volle Reihen
   */
  public Paint(Gamer g, Level l, Point p, int fullRows)
  {
    this.gamer = g;
    this.level = l;
    this.points = p;
    this.fullRows = fullRows;
  }


  /**
   * Zeichnet Pause
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void pause (Graphics g)
  {
    g.setColor(Color.RED);
    Vertex point = new Vertex(
        (Data.WINDOW_WIDTH - Data.WINDOW_WIDTH_PAUSE) / 2,
        (Data.WINDOW_HEIGHT - Data.WINDOW_HEIGHT_PAUSE) / 2);
    Rectangle rect = new Rectangle(point.getX(), point.getY(),
        Data.WINDOW_WIDTH_PAUSE, Data.WINDOW_HEIGHT_PAUSE);
    g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 10);

    // Fülle Rechteck mit der Farbe Rot und mit Transparenz
    g.setColor(new Color(255, 0, 0, 180));
    g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 10);

    // String PAUSE
    g.setColor(Color.WHITE);
    g.drawString("PAUSE", rect.width / 2, Data.WINDOW_HEIGHT / 2);
  }

  // Beschreibungen
  int        maxLength = 0;
  String[][] description;
  int        line_height;
  int        onehalfline_height;
  int        x;


  /**
   * Initialisierung der Statistik. Setze Farbe auf 'weiß'. Setze
   * X-Anfangs-Abstand, Linienhöhen. Initialisiert Statistik-Array und berechne
   * maximale Beschreibungslänge.
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void init (Graphics g)
  {
    g.setColor(Color.WHITE);
    x = Data.WINDOW_WIDTH_FIELD + 10;

    // Linienhöhen
    line_height = g.getFontMetrics().getHeight();
    onehalfline_height = (int) (line_height * 1.5);

    String[][] array = { { "Name: ", gamer.getName() },
        { "Punkte: ", points + "" }, { "Zeilen: ", fullRows + "" },
        { "Level: ", level + "" } };
    description = array;

    // Max. Länge aller Beschreibungen

    int temp = 0;
    for (int i = 0; i < description.length; i++) {
      temp = Math.max(getPixelWidthOfString(description[i][0], g), temp);
    }
    maxLength = temp;
  }


  private int getPixelWidthOfString (String s, Graphics g)
  {
    return g.getFontMetrics().stringWidth(s);
  }


  private int getPixelLineHeight (Graphics g)
  {
    return g.getFontMetrics().getHeight();
  }


  private int fillPixel (final int maxLength, final int i)
  {
    return maxLength - i;
  }


  /**
   * Zeichnet Statistik (Name, Punkte, gelöschte Zeilen, Level)
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void statistic (Graphics g)
  {
    // if(Game.DEBUG) System.out.println("maxL: "+maxLength);
    int yAbstand = 200;

    // Daten aktualisieren
    description[0][1] = gamer.getName();
    description[1][1] = points + "";
    description[2][1] = fullRows + "";
    description[3][1] = level + "";

    int fuellung;
    // Zeichne Statistik
    for (int r = 0; r < description.length; r++) {
      for (int c = 0; c < description[0].length; c++) {
        if (c != 0) {
          fuellung = fillPixel(maxLength, description[r][0].length()) + 10;
        } else {
          fuellung = 0;
        }
        g.drawString(description[r][c], x + fuellung, yAbstand);
      }
      yAbstand += line_height;
    }
  }


  /**
   * Zeichnet Raster mit allen vertikalen und horizontalen Linien.
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void grid (Graphics g)
  {
    this.grid(g, 1);
  }


  /**
   * Zeichnet Raster mit vertikalen und horizontalen Linien.
   * 
   * @param g
   *          Grafik-Objekt
   * @param intervall
   *          Intervall, das bestimmt, wie oft die Linien gezeichnet werden
   *          sollen.
   */
  public void grid (Graphics g, int intervall)
  {
    this.grid(g, intervall, true, true);
  }


  /**
   * Zeichnet Raster als Hintergrund.
   * 
   * @param g
   *          Grafik-Objekt
   * @param intervall
   *          Intervall, das bestimmt, wie oft die Linien gezeichnet werden
   *          sollen.
   * @param vertical
   *          Wenn true, dann werden die vertikalen Linien gezeichnet.
   * @param horizontal
   *          Wenn true, dann werden die horizontalen Linien gezeichnet.
   */
  public void grid (Graphics g, int intervall, boolean vertical,
      boolean horizontal)
  {
    if (intervall <= 0) throw new NoTerminationException();
    g.setColor(Color.DARK_GRAY);
    if (vertical) {
      for (int i = 1; i <= Data.ROWS; i += intervall) {
        g.drawLine((int) Data.FIELD.getV().getX(), (int) Data.FIELD.getV()
            .getY() + i * Stone.WIDTH, (int) Data.FIELD.getV().getX()
            + Data.FIELD.getWidth(), (int) Data.FIELD.getV().getY() + i
            * Stone.WIDTH);
      }
    }
    if (horizontal) {
      for (int i = 1; i <= Data.COLUMNS; i += intervall) {
        g.drawLine((int) Data.FIELD.getV().getX() + i * Stone.WIDTH,
            (int) Data.FIELD.getV().getY(), (int) Data.FIELD.getV().getX() + i
                * Stone.WIDTH,
            (int) Data.FIELD.getV().getY() + +Data.FIELD.getHeight());
      }
    }
  }


  /**
   * Zeichnet Spielfeld-Rahmen. Setze Rahmen um 1 Pixel nach links und oben,
   * damit man den linken und oberen Rahmen nicht sieht.
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void gameField (Graphics g)
  {
    g.setColor(Color.WHITE);
    g.drawRect(-1, -1, Data.WINDOW_WIDTH_FIELD + 2,
        Data.WINDOW_HEIGHT_FIELD + 2);
  }


  /**
   * Zeichnet GameOver-Benachrichtigung
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void gameOver (Graphics g)
  {
    g.setColor(Color.RED);
    String name = "Game Over!";
    g.drawString(name, Data.WINDOW_WIDTH - getPixelWidthOfString(name, g) - 5,
        Data.WINDOW_HEIGHT - getPixelLineHeight(g) + 5);
  }


  /**
   * Zeichnet Motivations-Benachrichtigung
   * 
   * @param g
   *          Grafik-Objekt
   */
  public void newGame (Graphics g)
  {
    g.setColor(Color.WHITE);
    String name = "Auf geht's!";
    g.drawString(name, Data.WINDOW_WIDTH - getPixelWidthOfString(name, g) - 5,
        Data.WINDOW_HEIGHT - getPixelLineHeight(g) + 5);
  }


  /**
   * Zeichnet Indikator des Startsteins. Zu DEBUG-Zwecken.
   * 
   * @param g
   *          Grafik-Objekt
   * @param figure
   *          Figur, bei der gezeichnet werden soll.
   */
  public void startStone (Graphics g, Figure figure)
  {
    Vertex s0 = figure.getStone(0).getV();
    Vertex end = s0.add(new Vertex(Stone.WIDTH, Stone.WIDTH));
    g.setColor(Color.BLACK);
    g.drawLine(s0.getX(), s0.getY(), end.getX(), end.getY());
    g.drawLine(s0.getX(), end.getY(), end.getX(), s0.getY());
  }

}
