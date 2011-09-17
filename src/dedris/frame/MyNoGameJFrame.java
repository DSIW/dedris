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
