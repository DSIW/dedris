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

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Fenster, das kein Spielfenster ist. Die ESCAPE-Taste wird zum Schließen des
 * Fensters benutzt.
 * 
 * @author DSIW
 * 
 */
@SuppressWarnings("serial")
public class MyNoGameJFrame extends MyJFrame
{

  /**
   * Erzeugt ein neues Fenster ohne Titel.
   */
  public MyNoGameJFrame()
  {
    super();
  }


  /**
   * Erzeugt ein neues Frame.
   * 
   * @param s
   *          Titel des Frames
   */
  public MyNoGameJFrame(String s)
  {
    super(s);

    // KeyListener
    addKeyListener(new KeyAdapter()
    {
      @Override
      public void keyPressed (KeyEvent e)
      {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          dispose();
        }
      }
    });
  }
}
