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
package dedris.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import dedris.game.Gamer;
import dedris.game.Level;
import dedris.game.Paint;
import dedris.game.Point;
import dedris.geometric.Stone;
import dedris.geometric.Vertex;
import dedris.geometric.figure.Figure;
import dedris.geometric.figure.I;
import dedris.geometric.figure.SavedFigure;
import dedris.highscore.Highscore;
import dedris.main.Data;
import dedris.main.Main;

/**
 * Das Spielfenster wird erzeugt und ein Teil der Spiellogik ist enthalten.
 * 
 * @author DSIW
 * 
 */
public class Game extends MyJFrame
{

  private static final long serialVersionUID          = -4766624579219028228L;

  private boolean           isGameOver                = true;
  private boolean           isNewGame                 = true;
  private boolean           isTimerStopped            = false;
  private boolean           isActiveFigureBottom      = false;
  private boolean           isPause                   = false;                     // „static“
                                                                                    // für
                                                                                    // Menü-Pause-Funktion
  private int               newGameTimer;
  private int               figuresInGameCounterTotal = 0;
  private int               oldTime                   = -1;

  private Figure            activeFigure              = new I(0, 0, true, 0);      // Parameter
                                                                                    // werden
                                                                                    // überschrieben
  private Figure            nextFigure                = new I(0, 0, false, 0);     // Parameter
                                                                                    // werden
                                                                                    // überschrieben

  // Gespeicherte Figuren als Array
  SavedFigure               savedFigures              = new SavedFigure();

  private Gamer             gamer                     = new Gamer();
  private Level             level                     = new Level();
  private Point             points                    = new Point();
  private Highscore         highscore                 = new Highscore();

  MyMenu                    menu;

  // Paint von Level, Punkten, gespeicherten Figuren, Name und Vorname
  private Paint             paint                     = new Paint(
                                                          getGamer(),
                                                          getLevel(),
                                                          getPoints(),
                                                          savedFigures
                                                              .getFullRowsTotal());

  // Timer
  /**
   * Timer des Fallens
   */
  public static Timer       tDown;                                                 // Geschwindigkeit
                                                                                    // der
                                                                                    // Spielfigur


  /**
   * Starte Timer des Fallens.
   */
  public void startTimer ()
  {
    tDown.start();
  }


  /**
   * Stoppe Timer des Fallens.
   */
  public void stopTimer ()
  {
    tDown.stop();
  }


  /**
   * Bekomme Wahrheitswert, ob Spiel vorbei ist.
   * 
   * @return true, wenn Spiel vorbei.
   */
  public boolean isGameOver ()
  {
    return isGameOver;
  }


  /**
   * Setze das Spiel auf GameOver mit einer Variable. Außerdem wird ein Eintrag
   * in der Highscore-Liste hinzugefügt, der Menüeintrag 'Highscore' wird
   * aktiviert und der Timer wird gestoppt.
   * 
   * @param isGameOver
   *          Variable
   */
  public void setGameOver (boolean isGameOver)
  {
    // Timer STOPPEN, wenn GameOver
    if (!this.isGameOver() && isGameOver) {
      this.isTimerStopped = true;
      highscore.add(getGamer(), getLevel().getLevel() + 1, getPoints()
          .getPoints());
      menu.highscore.setEnabled(true);
      stopTimer();
    } else {
      this.isTimerStopped = false;
    }
    this.isGameOver = isGameOver;
  }


  /**
   * Bekomme Wahrheitswert, ob Spiel auf GameOver steht.
   * 
   * @return true, wenn Spiel auf GameOver
   */
  public boolean isNewGame ()
  {
    return isNewGame;
  }


  /**
   * Setze Wahrheitswert, wenn GameOver
   * 
   * @param isNewGame
   *          Wahrheitswert
   */
  public void setNewGame (boolean isNewGame)
  {
    this.isNewGame = isNewGame;
  }


  /**
   * Bekomme Wahrheitswert, ob die aktive (fallende) Figur am Boden oder über
   * einem anderen Stein angekommen ist.
   * 
   * @return true, wenn angekommen
   */
  public boolean isActiveFigureBottom ()
  {
    return isActiveFigureBottom;
  }


  /**
   * Bekomme Wahrheitswert, ob das Spiel pausiert ist.
   * 
   * @return true, wenn das Spiel pausiert ist.
   */
  public boolean isPause ()
  {
    return isPause;
  }


  /**
   * Setze Pause, mit Wahrheitswert
   * 
   * @param isPause
   *          Wahrheitswert
   */
  public void setPause (boolean isPause)
  {
    this.isPause = isPause;
  }


  /**
   * Setze Wahrheitswert, ob die Figur am Boden angekommen ist.
   * 
   * @param isActiveFigureBottom
   *          Wahrheitswert
   */
  public void setActiveFigureBottom (boolean isActiveFigureBottom)
  {
    this.isActiveFigureBottom = isActiveFigureBottom;
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
   * Bekomme Spieler
   * 
   * @return Spieler
   */
  public Gamer getGamer ()
  {
    return gamer;
  }


  /**
   * Setze Punkte
   * 
   * @param points
   *          Punkte
   */
  public void setPoints (Point points)
  {
    this.points = points;
  }


  /**
   * Bekomme Punkte
   * 
   * @return Punkte
   */
  public Point getPoints ()
  {
    return points;
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
   * Bekomme Level
   * 
   * @return Level
   */
  public Level getLevel ()
  {
    return level;
  }


  /**
   * Bekomme Menü vom Typ MyMenu
   * 
   * @return Menü
   */
  public MyMenu getMenu ()
  {
    return menu;
  }


  /**
   * Bekomme Highscores
   * 
   * @return Highscores
   */
  public Highscore getHighscore ()
  {
    return highscore;
  }

  JPanel         p      = new JPanel()
                        {
                          private static final long serialVersionUID = 1L;


                          @Override
                          public Dimension getPreferredSize ()
                          {
                            return new Dimension(Data.WINDOW_WIDTH,
                                Data.WINDOW_HEIGHT + 1);
                          };


                          @Override
                          protected void paintComponent (Graphics g)
                          {
                            // Init paint
                            if (figuresInGameCounterTotal == 0) {
                              paint.init(g);
                            }

                            // Zeichne Grid
                            // paint.grid(g, 1, true, false);

                            // Zeichne aktive (fallende) Figur
                            if (figuresInGameCounterTotal > 0 && !isGameOver()) {
                              for (int i = 0; i <= 3; i++) {
                                activeFigure.getStone(i).paintMeTo(g);
                              }
                            }
                            // Zeichne Start-Stein (nur im DEBUG-Modus)
                            // if(Main.DEBUG) paint.startStone(g, activeFigure);

                            // Zeichne Vorschau-Figur
                            if (!isGameOver()) {
                              for (int i = 0; i <= 3; i++) {
                                nextFigure.getStone(i).paintMeTo(g);
                              }
                            }
                            // Zeichne Start-Stein (nur im DEBUG-Modus)
                            // if(Main.DEBUG) paint.startStone(g, nextFigure);

                            // Zeichne gefallene Figur(en)
                            savedFigures.paintFigures(g);

                            // Zeichne Spielfeldrahmen
                            paint.gameField(g);

                            // Zeichne Statistik
                            paint.statistic(g);

                            // Zeichne leere Highscoreliste
                            // if(!isGameOver() && !isNewGame &&
                            // !(newGameCounter > 0)) {
                            // paint.noHighscoreEntry(g);
                            // }

                            // Zeichne GameOver
                            if (isGameOver() && !isNewGame) {
                              paint.gameOver(g);
                            }

                            // Zeichne NeuesSpiel
                            if (!isGameOver() && newGameTimer > 0) {
                              paint.newGame(g);
                            }

                            // Zeichne Pause
                            if (isPause()) {
                              paint.pause(g);
                            }
                          };
                        };

  ActionListener goDown = new ActionListener()
                        {
                          public void actionPerformed (ActionEvent e)
                          {
                            // Main.debugPrintln("Timer: "+tDown.getDelay());

                            setActiveFigureBottom(savedFigures
                                .checkBottom(activeFigure));

                            if (!isActiveFigureBottom && !isGameOver()) {
                              setGameOver(savedFigures.isGameOver());

                              activeFigure.moveDown();
                            } else {
                              savedFigures.save(activeFigure);
                              // int deleted = savedFigures.getFullRowsTotal();
                              for (int i = 0; i <= 2; i++) {
                                savedFigures.checkAllRows();
                              }

                              // if(deleted < savedFigures.getFullRowsTotal()) {
                              // Alle 10 gelöschte Reihen, Level inkrementieren
                              // }

                              if (Main.DEBUG)
                                System.out.println("INCR_LEVEL:" + level);
                              level.inkrementLevel(
                                  savedFigures.getFullRowsTotal(),
                                  savedFigures.isRowJustDeleted());

                              paint.setLevel(level);
                              paint
                                  .setFullRows(savedFigures.getFullRowsTotal());

                              savedFigures.setRowJustDeleted(false);

                              refreshTime(getLevel().getAktTime());
                              oldTime = getLevel().getAktTime();

                              if (!isGameOver())
                                Figure.add(Figure.getRandom(true));
                            }
                            // Main.debugPrintln("Volle Reihen: "+deletedRowsCounterTotal
                            // + " Level: " + level.getLevel() +
                            // " Figuren i.G.: " + figuresInGameCounterTotal);
                            newGameTimer--;
                            if (isGameOver) menu.deactivateEntries();
                            // if(Main.DEBUG)
                            // System.out.println("SL: "+Data.startLevel);
                            repaint();
                          }
                        };


  /**
   * Erzeugt ein neues Spiel. Setze verschiedene Frame-Parameter. Initalisiere
   * den Timer, füge einen Key- und WindowListener hinzu.
   */
  public Game()
  {
    super("Dedris");

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    setResizable(false);
    setBackground(Color.BLACK);

    menu = new MyMenu(this);

    add(p);
    pack();

    // Position festlegen
    setLocation(getMiddlePosition());

    setPreferredSize(new Dimension(Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT));
    setVisible(true);

    // Init Level & Timer
    tDown = new Timer(getLevel().getAktTime(), goDown);
    oldTime = getLevel().getAktTime();
    refreshTime(oldTime);
    stopTimer();

    setFocusable(true);
    requestFocus();

    // KeyListener
    addKeyListener(new Keys(menu));

    // WindowListener
    addWindowListener(new Window(menu));
  }


  /**
   * Aktualisiert Zeiten des Timers neu. Level wird neu gesetzt. Wenn Timer
   * nicht laeuft, wird dieser gestartet.
   * 
   * @param time
   *          Zeit
   */
  public void refreshTime (int time)
  {
    stopTimer();
    this.getLevel().setLevelOfTime(time);
    tDown = new Timer(time, goDown);
    if (!isTimerStopped) startTimer();
  }


  /**
   * Fügt eine neue Figur f hinzu. Aktualisiere Timer, setze activeFigureBottom
   * auf false und außerdem inkrementiere figuresInGameCounterTotal und
   * aktualisiere die aktive (gerade hinzugefügte) Figur.
   * 
   * @param f
   *          Figur, die hinzugefügt werden soll.
   */
  public void addFigure (Figure f)
  {
    // Main.debugPrintln("Figur hinzufügen");

    // Nach dem Fallen einer Figur
    refreshTime(oldTime);
    nextToActiveFigure();
    nextFigure = f;
    if (figuresInGameCounterTotal++ > 0) {
      refreshNextFigure();
      // Main.debugPrintln("Width: "+nextFigure.getWidth());
      // Main.debugPrintln("Height: "+nextFigure.getHeight());
    }
    // figuresInGameCounterTotal++; // (s.o.)
    setActiveFigureBottom(false);
    isNewGame = false;
    startTimer();
  }


  /**
   * Aktualisiert die Vorschau-Figur. Die Geschwindigkeit des Figur wird auf 0
   * gesetzt. Die Positionierung der Figur wird berechnet.
   */
  public void refreshNextFigure ()
  {
    // Setze Geschwindigkeit auf 0
    nextFigure.getStone(0).setD(new Vertex());
    int heightPreviewField = 125;

    // Setze Startpunkt
    Vertex abstand = new Vertex();
    abstand.setX(Data.WINDOW_WIDTH_FIELD // Startpunkt des Rands
        + (Data.WINDOW_WIDTH_BORDER - nextFigure.getWidth()) / 2 // gleicher
                                                                 // Abstand der
                                                                 // Figur zum
                                                                 // Rand =
                                                                 // mittiges
                                                                 // Ausrichten
                                                                 // der Figur
        + Math.abs(nextFigure.getMinRelStonePos().getX()) * Stone.WIDTH); // Versetzung
                                                                          // des
                                                                          // Orientierungspunktes,
                                                                          // weil
                                                                          // dieser
                                                                          // nicht
                                                                          // immer
                                                                          // gleich
                                                                          // ist.
    abstand.setY((heightPreviewField - nextFigure.getHeight()) / 2 // gleicher
                                                                   // Abstand
                                                                   // der Figur
                                                                   // zum Rand =
                                                                   // mittiges
                                                                   // Ausrichten
                                                                   // der Figur
        + Math.abs(nextFigure.getMinRelStonePos().getY()) * Stone.WIDTH); // Versetzung
                                                                          // des
                                                                          // Orientierungspunktes,
                                                                          // weil
                                                                          // dieser
                                                                          // nicht
                                                                          // immer
                                                                          // gleich
                                                                          // ist.

    nextFigure.getStone(0).setV(abstand);
    nextFigure.refreshStones();

    getPoints().calc(savedFigures.getFullRowsTotal(), getLevel().getLevel(),
        figuresInGameCounterTotal - 1);
  }


  /**
   * Setzt die Vorschau-Figur zu einer fallenden Figur. Setze Geschwindigkeit
   * und Position neu. Aktualisiere alle Steine.
   */
  public void nextToActiveFigure ()
  {
    activeFigure = nextFigure;
    if (figuresInGameCounterTotal > 0) {
      // Setze Geschwindigkeit
      activeFigure.getStone(0).setD(new Vertex(0, Stone.WIDTH));
      // Setze Startpunkt
      Vertex abstand = new Vertex();
      abstand.setX((int) ((Data.WINDOW_WIDTH_FIELD / Stone.WIDTH - activeFigure
          .getWidth() / Stone.WIDTH) / 2)
          * Stone.WIDTH // gleicher Abstand der Figur zum Spielfeldrand =
                        // mittiges Ausrichten der Figur
          + Math.abs(activeFigure.getMinRelStonePos().getX()) * Stone.WIDTH); // Versetzung
                                                                              // des
                                                                              // Orientierungspunktes,
                                                                              // weil
                                                                              // dieser
                                                                              // nicht
                                                                              // immer
                                                                              // gleich
                                                                              // ist.
      abstand.setY(Math.abs(activeFigure.getMinRelStonePos().getY())
          * Stone.WIDTH); // Versetzung des Orientierungspunktes, weil dieser
                          // nicht immer gleich ist.
      // abstand.getY() = 0-abstand.getY();

      activeFigure.getStone(0).setV(abstand);
      // activeFigure.getStone(0).setV(new
      // Vertex((int)(Data.WINDOW_WIDTH_FIELD/2), 0));
    }
    activeFigure.refreshStones();
  }


  /**
   * Zurücksetzen der Einstellungen des letzten Spiels. Zum Beispiel Level,
   * Timer, Punkte, etc.
   */
  public void reset ()
  {
    setGameOver(false);
    setPause(false);
    level = new Level();
    level.setLevel(Data.startLevel);
    oldTime = getLevel().getAktTime();
    refreshTime(oldTime);
    figuresInGameCounterTotal = 0;
    newGameTimer = 2;
    paint.setFullRows(0);
    paint.setLevel(level);
    savedFigures = new SavedFigure();
    highscore = new Highscore();
  }

  /**
   * Innere Klasse: Tastenabfrage von den Tasten: LEFT; RIGHT; UP; PAUSE; P;
   * DOWN; SPACE; ESCAPE
   * 
   * @author max
   * 
   */
  public final class Keys extends KeyAdapter
  {
    private MyMenu menu;


    private Keys(MyMenu menu)
    {
      this.menu = menu;
    }


    @Override
    public void keyPressed (KeyEvent e)
    {
      if (!isGameOver() && !isPause() && e.getKeyCode() == KeyEvent.VK_LEFT) {
        if (!savedFigures.checkLeft(activeFigure)) activeFigure.goLeft(true);
      } else if (!isGameOver() && !isPause()
          && e.getKeyCode() == KeyEvent.VK_RIGHT) {
        if (!savedFigures.checkRight(activeFigure)) activeFigure.goRight(true);
      } else if (!isGameOver() && !isPause()
          && e.getKeyCode() == KeyEvent.VK_UP) {
        // if(GameWindow.rowCounter == 1 || GameWindow.rowCounter == 0)
        // activeFigure.goDown(false);
        activeFigure.rotate();
      } else if (!isGameOver()
          && (e.getKeyCode() == KeyEvent.VK_PAUSE || e.getKeyCode() == KeyEvent.VK_P)) {
        menu.togglePause();
      } else if (!isGameOver()
          && !isPause()
          && (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_SPACE)) {
        oldTime = getLevel().getAktTime();
        refreshTime(Data.FALL_GESCHWINDIGKEIT);
      } else if (isPause() && !isGameOver()
          && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        menu.togglePause();
      } else if (!isPause() && !isGameOver()
          && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        Main.stop();
      } else if (isGameOver() && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        Main.exit();
      }
      repaint();
    }
  }

  /**
   * Innere Klasse: WindowListener
   * 
   * @author max
   * 
   */
  public final class Window extends WindowAdapter
  {
    private MyMenu menu;


    private Window(MyMenu menu)
    {
      this.menu = menu;
    }


    @Override
    public void windowClosing (WindowEvent e)
    {
      Main.exit();
    }


    @Override
    public void windowIconified (WindowEvent e)
    {
      menu.toPause();
    }


    @Override
    public void windowDeactivated (WindowEvent e)
    {
      menu.toPause();
    }
  }
}
