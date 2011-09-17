package dedris.geometric.figure;

import java.awt.Color;
import java.util.LinkedList;

import dedris.exception.NoRelativeStoneException;
import dedris.geometric.GeometricObject;
import dedris.geometric.Stone;
import dedris.geometric.Vertex;
import dedris.main.Data;

/**
 * Figur LLeft
 * 
 * @author DSIW
 * 
 */
public class LLeft extends Figure
{

  Color       c          = Color.YELLOW;
  private int stoneIndex = 2;
  Vertex      v1         = new Vertex(0, 1);
  Vertex      v2         = new Vertex(-1, 2);
  Vertex      v3         = new Vertex(0, 2);


  /**
   * Erzeugt den Orientierungsstein
   * 
   * @param x
   *          X-Koordinate
   * @param y
   *          Y-Koordinate
   * @param isFilled
   *          true, damit die Figur gefüllt gezeichnet wird.
   */
  public LLeft(int x, int y, boolean isFilled, int stoneIndex)
  {
    if (Data.randomColor) this.stoneIndex = stoneIndex;
    s0 = new Stone(x, y, isFilled, c, this.stoneIndex); // Orientierungspunkt f.
                                                        // Rotation
  }


  Stone getRelativeStone (Vertex v)
  {
    return super.getRelativeStone(v, c, stoneIndex);
  }


  @Override
  public Vertex getRelStonePos (int stone) throws NoRelativeStoneException
  {
    LinkedList<Vertex> l = new LinkedList<Vertex>();
    l.add(v1);
    l.add(v2);
    l.add(v3);
    return super.getRelativeStonePosition(stone, l);
  }


  @Override
  public void rotate ()
  {
    GeometricObject window = Data.FIELD;
    if (rotate == 0) {
      // Right
      if (isOutOfFieldHorizontalRight(window)) {
        goLeft(false);
      }

      goDown(false);
      goLeft(true);
    } else if (rotate == 1) {

      goDown(false);
      goRight(true);
    } else if (rotate == 2) {
      // Left
      if (isOutOfFieldHorizontalLeft(window)) {
        goRight(false);
      }

      goUp(false);
      goRight(true);
    } else if (rotate == 3) {
      goUp(false);
      goLeft(true);
    }

    rotateFinish();
  }
}
