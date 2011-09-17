package dedris.geometric.figure;

import java.awt.Color;
import java.util.LinkedList;

import dedris.exception.NoRelativeStoneException;
import dedris.geometric.GeometricObject;
import dedris.geometric.Stone;
import dedris.geometric.Vertex;
import dedris.main.Data;

/**
 * Figur ZRight
 * 
 * @author DSIW
 * 
 */
public class ZRight extends Figure
{

  Color       c          = Color.PINK;
  private int stoneIndex = 6;
  Vertex      v1         = new Vertex(-1, 0);
  Vertex      v2         = new Vertex(-1, 1);
  Vertex      v3         = new Vertex(-2, 1);


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
  public ZRight(int x, int y, boolean isFilled, int stoneIndex)
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
      goUp(false);
      goLeft(true);
    } else if (rotate == 1) {
      // Left
      if (isOutOfFieldHorizontalLeft(window)) {
        goRight(false);
      }

      goDown(false);
      goLeft(true);
    } else if (rotate == 2) {

      goDown(false);
      goRight(true);
    } else if (rotate == 3) {
      // Right
      if (isOutOfFieldHorizontalRight(window)) {
        goLeft(false);
      }

      goUp(false);
      goRight(true);
    }

    rotateFinish();
  }
}
