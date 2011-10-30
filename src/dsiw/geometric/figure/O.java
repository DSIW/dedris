package dsiw.geometric.figure;

import java.awt.Color;
import java.util.LinkedList;

import dsiw.exception.NoRelativeStoneException;
import dsiw.geometric.Stone;
import dsiw.geometric.Vertex;
import dsiw.main.Data;

/**
 * Figur O
 * @author DSIW
 *
 */
public class O extends Figure {

	final int MAX_ROTATE = 0;
	Color c = Color.ORANGE;
	private int stoneIndex = 4;
	Vertex v1 = new Vertex(1, 0);
	Vertex v2 = new Vertex(0, 1);
	Vertex v3 = new Vertex(1, 1);
	
	/**
	 * Erzeugt den Orientierungsstein
	 * @param x X-Koordinate
	 * @param y Y-Koordinate
	 * @param isFilled true, damit die Figur gefüllt gezeichnet wird.
	 */
	public O(int x, int y, boolean isFilled, int stoneIndex) {
		if(Data.randomColor) this.stoneIndex = stoneIndex;
		s0 = new Stone(x, y, isFilled, c, this.stoneIndex); 													// Orientierungspunkt f. Rotation
	}

	Stone getRelativeStone(Vertex v) {
		return super.getRelativeStone(v, c, stoneIndex);
	}

	@Override
	public Vertex getRelStonePos(int stone)
			throws NoRelativeStoneException {
		LinkedList<Vertex> l = new LinkedList<Vertex>();
		l.add(v1);
		l.add(v2);
		l.add(v3);
		return super.getRelativeStonePosition(stone, l);
	}

	@Override
	public void rotate() {;}
}
