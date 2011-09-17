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
