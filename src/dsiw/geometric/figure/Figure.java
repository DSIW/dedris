package dsiw.geometric.figure;

import java.awt.Color;
import java.util.LinkedList;

import dsiw.exception.NoRelativeStoneException;
import dsiw.game.RandomInteger;
import dsiw.geometric.GeometricObject;
import dsiw.geometric.Stone;
import dsiw.geometric.Vertex;
import dsiw.main.Main;

/**
 * Eine Figur besteht aus vier Steinen. Die Figur hat einen Orientierungsstein,
 * an dem sich alle anderen drei Steine ausrichten. Die Figur enthält einen Drehstein,
 * der den Drehpunkt darstellt. Jeder Stein hat eine maximale Drehanzahl. Die aktuelle Drehposition wird gespeichert.
 * 
 * @author DSIW
 */
public abstract class Figure {
	
	protected int rotate = 0;
	final private int MAX_ROTATE = 3;
	protected Stone s0, s1, s2, s3;
    protected int stoneIndex = 0;
    protected int randomColor = 0;

    /**
     * Relative Positionen von s1 zum Orientierungsstein s0
     */
    protected Vertex v1;
    /**
     * Relative Positionen von s2 zum Orientierungsstein s0
     */
    protected Vertex v2;
    /**
     * Relative Positionen von s3 zum Orientierungsstein s0
     */
    protected Vertex v3;
    private int width = 0;
	private int height = 0;
    
    /**
     * Bekomme Breite
     * @return Breite der Figur
     */
    public int getWidth() {
		return width;
	}

	/**
	 * Setze neue Breite
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Bekomme Höhe
	 * @return Höhe der Figur
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Setze neue Höhe
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Bekomme Steinindex der Figure
	 * @return Steinindex
	 */
	public int getStoneIndex() {
		return stoneIndex;
	}
	
	/**
	 * Vertausche Masse (Hoehe und Breite)
	 */
	public void swapDimension() {
		int temp = getWidth();
		setWidth(getHeight());
		setHeight(temp);
	}

	void calcDimension() {
		setWidth((Math.abs(getMaxRelStonePos().getX()) + Math.abs(getMinRelStonePos().getX())+1)*Stone.WIDTH);
		setHeight((Math.abs(getMaxRelStonePos().getY()) + Math.abs(getMinRelStonePos().getY())+1)*Stone.WIDTH);
	}
	
	Stone getRelativeStone(final int x, final int y, Color c, int stoneIndex) {
		calcDimension();
		
		return new Stone(this.s0.getV().getX() + x * Stone.WIDTH, this.s0.getV().getY() + y * Stone.WIDTH, s0.isFilled(), c, stoneIndex);
	}

	/**
	 * Orientierungsstein laeuft nach links
	 * @param refresh Performante Option, damit bei mehrfacher Rotation die Steine nicht aktualisiert werden.
	 */
	public void goLeft(boolean refresh) {
		s0.getV().setX(s0.getV().getX()-(int)Stone.WIDTH);
		if(refresh) refreshStones();
	}

	/**
	 * Orientierungsstein lauft nach rechts
	 * @param refresh Performante Option, damit bei mehrfacher Rotation die Steine nicht aktualisiert werden.
	 */
	public void goRight(boolean refresh) {
		s0.getV().setX(s0.getV().getX()+(int)Stone.WIDTH);
		if(refresh) refreshStones();
	}

	/**
	 * Orientierungsstein laeuft nach oben
	 * @param refresh Performante Option, damit bei mehrfacher Rotation die Steine nicht aktualisiert werden.
	 */
	public void goUp(boolean refresh) {
		s0.getV().setY(s0.getV().getY()-(int)Stone.WIDTH);
		if(refresh) refreshStones();
	}
	
	/**
	 * Orientierungsstein laeuft nach unten
	 * @param refresh Performante Option, damit bei mehrfacher Rotation die Steine nicht aktualisiert werden.
	 */
	public void goDown(boolean refresh) {
		s0.getV().setY(s0.getV().getY()+(int)Stone.WIDTH);
		if(refresh) refreshStones();
	}
	
	/**
	 * Bekomme Stein mit Stein-Index
	 * @param s Index des Steins
	 * @return Stone
	 */
	public Stone getStone(int s) {
		if(s > 3) throw new NoRelativeStoneException();
		if(s == 0) return s0;
		if(s == 1) return s1;
		if(s == 2) return s2;
		if(s == 3) return s3;
		return new Stone(0, 0, false, Color.WHITE, 0);
	}
	
	/**
	 * Setze Stein mit Index
	 * @param i Index des Steins
	 * @param s Stein
	 */
	public void setStone(int i, Stone s) {
		if(i == 0) s0 = s;
		if(i == 1) s1 = s;
		if(i == 2) s2 = s;
		if(i == 3) s3 = s;
	}
	
	/**
	 * Bekomme 
	 * @param v Index des relativen Steins
	 * @return Position des Steins
	 */
	public Vertex getRelV(int v) {
		if(v == 1) return v1;
		if(v == 2) return v2;
		if(v == 3) return v3;
		return new Vertex();
	}

	/**
	 * Setze 
	 * @param i Index des relativen Steins
	 * @param v Position des Steins
	 */
	public void setRelV(int i, Vertex v) {
		if(i == 1) v1 = v;
		if(i == 2) v2 = v;
		if(i == 3) v3 = v;
	}
	
	/**
	 * Bekomme Figuren-Index
	 * @return Figuren-Index
	 */
	public int getFigureIndex() {
		return stoneIndex;
	}
	
	/**
	 * Bekomme max. X-Wert des entferntesten rel. Steins und max. Y-Wert des entferntesten rel. Steins als Vertex
	 * @return Max. X-Wert
	 */
	public Vertex getMaxRelStonePos() {
		int maxX = 0;
		// Suche MAX.getX() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			maxX = Math.max(getRelStonePos(i).getX(), maxX);
		}
		
		int maxY = 0;
		// Suche MAX.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			maxY = Math.max(getRelStonePos(i).getY(), maxY);
		}
		return new Vertex(maxX, maxY);
	}
	
	/**
	 * Bekomme Min. X-Wert des entferntesten rel. Steins und min. Y-Wert des entferntesten rel. Steins als Vertex
	 * @return Min. X-Wert
	 */
	public Vertex getMinRelStonePos() {
		int minX = 0;
		// Suche MIN.getX() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			minX = Math.min(getRelStonePos(i).getX(), minX);
		}
		
		int minY = 0;
		// Suche MIN.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			minY = Math.min(getRelStonePos(i).getY(), minY);
		}
		return new Vertex(minX, minY);
	}
	
	/**
	 * Bekomme max. Y-Wert des entferntesten rel. Steins in der Spalte
	 * @param column Spalte
	 * @return max. Y-Wert
	 */
	public int getMaxRelStonePosInColumn(int initMaxY, int column) {	
		int maxY = initMaxY;
		// Suche MAX.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			if(getRelStonePos(i).getX() == column) {
				maxY = Math.max(getRelStonePos(i).getY(), maxY);
			}
		}
		return maxY;
	}
	
	/**
	 * Bekomme min. Y-Wert des entferntesten rel. Steins in der Spalte
	 * @param column Spalte
	 * @return min. Y-Wert
	 */
	public int getMinRelStonePosInColumn(int initMinY, int column) {	
		int minY = initMinY;
		// Suche MIN.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			if(getRelStonePos(i).getX() == column) {
				minY = Math.min(getRelStonePos(i).getY(), minY);
			}
		}
		return minY;
	}
	
	/**
	 * Bekomme max. X-Wert des entferntesten rel. Steins in der Zeile
	 * @param relStonePosRow relative Stein-Position in Zeile
	 * @return max. X-Wert
	 */
	public int getMaxRelStonePosInRow(int initMaxX, int relStonePosRow) {	
		int maxX = initMaxX;
		// Suche MAX.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			if(getRelStonePos(i).getY() == relStonePosRow) {
				maxX = Math.max(getRelStonePos(i).getX(), maxX);
			}
		}
		return maxX;
	}
	
	/**
	 * Bekomme min. X-Wert des entferntesten rel. Steins in der Zeile 
	 * @param relStonePosRow relative Stein-Position in Zeile
	 * @return min. X-Wert
	 */
	public int getMinRelStonePosInRow(int initMinX, int relStonePosRow) {	
		int minX = initMinX;
		// Suche MIN.getY() der rel. Stein-Pos.
		for(int i = 1; i <= 3; i++) {
			if(getRelStonePos(i).getY() == relStonePosRow) {
				minX = Math.min(getRelStonePos(i).getX(), minX);
			}
		}
		return minX;
	}
	
	/**
	 * Prüft Objekt, ob es am unteren Rand ein anderes Objekt berührt.
	 * @return true, wenn Objekt unten angekommen ist. 
	 */
//	public boolean checkBottom(GeometricObject window, java.util.List<Figure> savedFigures) {
////		Main.debugPrintln("Prüf: bottom");
//		// Figur nicht im Spielfeld
//		if(isOutOfFieldVerticalBottom(window)) {
//			return true;
//		}
//		
//		// Vergleich mit allen Steinen der aktiven Figur mit allen Steinen der gespeicherten Figur(en).
//		// Alle Steine in dieser Figur
//		for(int i = 0; i <= 3; i++) {
//			// Alle gespeicherten Figuren
//			for(Figure f : savedFigures) {
//				// Alle Steine der gespeicherten Figuren
//				for(int j = 0; j <= 3; j++) {
//					if(this.getStone(i).touches(f.getStone(j))) {
//						Main.debugPrintln("++++++++++TOUCHES++++++++++");
//						return true;
//					}
//				}
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * Prüft, ob sich Figur links ausserhalb des Spielfeldes befindet 
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldHorizontalLeft(GeometricObject window) {
//		Main.debugPrintln("Prüf: HorizontalLeft");
		for(int i = 0; i <= 3; i++) {
//			Main.debugPrintln(this.getStone(i).getV().getX() + " <= " + window.getV().getX());
			if(this.getStone(i).getV().getX() <= window.getV().getX()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur links ausserhalb des Spielfeldes befinden würde, wenn sie sich einen Schritt weiterbewegt.
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldHorizontalLeftBefore(GeometricObject window) {
//		Main.debugPrintln("Prüf: HorizontalLeftBEFORE");
		for(int i = 0; i <= 3; i++) {
			if(this.getStone(i).getV().getX() - this.getStone(i).getWidth() <= window.getV().getX()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur rechts ausserhalb des Spielfeldes befindet
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldHorizontalRight(GeometricObject window) {
		for(int i = 0; i <= 3; i++) {
			if(this.getStone(i).getV().getX() + this.getStone(i).getWidth() >= window.getV().getX() + window.getWidth()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur rechts ausserhalb des Spielfeldes befinden würde, wenn sie sich einen Schritt weiterbewegt.
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldHorizontalRightBefore(GeometricObject window) {
		for(int i = 0; i <= 3; i++) {
			if(this.getStone(i).getV().getX() + this.getStone(i).getWidth() + this.getStone(i).getWidth() >= window.getV().getX() + window.getWidth()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur horizontal ausserhalb des Spielfeldes befindet
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldHorizontal(GeometricObject window) {
		if(isOutOfFieldHorizontalLeft(window) || isOutOfFieldHorizontalRight(window)) {
				return true;
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur vertikal ausserhalb des Spielfeldes befindet
	 * @param window Spieldfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldVerticalBottom(GeometricObject window) {
		for(int i = 0; i <= 3; i++) {
			if(this.getStone(i).getV().getY() + this.getStone(i).getHeight() >= window.getV().getY() + window.getHeight()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur oben ausserhalb des Spielfeldes befindet
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldVerticalTop(GeometricObject window) {
		for(int i = 0; i <= 3; i++) {
			if(this.getStone(i).getV().getY() < window.getV().getY()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Prüft, ob sich Figur vertikal ausserhalb des Spielfeldes befindet
	 * @param window Spielfeld
	 * @return true, wenn ausserhalb
	 */
	public boolean isOutOfFieldVertical(GeometricObject window) {
		if(isOutOfFieldVerticalBottom(window) || isOutOfFieldVerticalTop(window)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Objekte werden gegen den Uhrzeigersinn gedreht.
	 */
	public abstract void rotate();
	
	/**
	 * Alle anderen Steine orientieren sich an dem Orientierungsstein und richten sich neu aus.
	 */
	public void refreshStones() {		
		for(int i = 1; i <= 3; i++) {
			try {
				setStone(i, getRelativeStone(getRelStonePos(i)));
			} catch (NoRelativeStoneException e) {
				e.printStackTrace();
			}
		}
	}
    
    abstract Stone getRelativeStone(Vertex v);
    
    Stone getRelativeStone(final Vertex v, Color c, int stoneIndex) {
		return new Stone((int) this.s0.getV().getX() + (int)v.getX() * Stone.WIDTH,
						 (int) this.s0.getV().getY() + (int)v.getY() * Stone.WIDTH,
						 s0.isFilled(),
						 c,
						 stoneIndex
		);
	}
    
	/**
	 * Bekomme Position des relativen Steins
	 * @param stone Index des relativen Steins
	 * @param relVs Liste aller relativen Steine
	 * @return Vertex der Position
	 */
	public Vertex getRelativeStonePosition(int stone, LinkedList<Vertex> relVs) {	
		if(stone == 0) {
			try {
				throw new NoRelativeStoneException();
			} catch (NoRelativeStoneException e) {
				e.printStackTrace();
			}
		}
	
		for(int i = 1; i <= 3; i++) {
			setRelV(i, new Vertex(relVs.get(i-1).getX(), relVs.get(i-1).getY()));
		}
		
		for(int i = 1; i <= rotate; i++) {
			for(int j = 1; j <= 3; j++) {
				setRelV(j, new Vertex(getRelV(j).getY(), getRelV(j).getX()*-1));
			}
		}
		
		return getRelV(stone);
	}
    
    /**
     * Bekomme die Position des relativen Steins, in Abhängigkeit von der Rotation.
     * @param stone Index des relativen Steins
     * @return Vertex der Position
     */
    public abstract Vertex getRelStonePos(int stone) throws NoRelativeStoneException;
    
    protected void rotateFinish() {
		// Rotate-Index inkrementieren mit Überlauf
		if(rotate == MAX_ROTATE) {
			rotate = 0;
		} else {
			rotate++;
		}
		
		// Masse tauschen
		swapDimension();
		// Muss an der Stelle stehen, damit die Steine wieder neu ausgerichtet werden
		refreshStones();
	}
    
    //--------------------------------------------------------
    //--------------------------------------------------------
    
 // Zufallszahl
	static RandomInteger randomStone = new RandomInteger();
	static RandomInteger randomRotate = new RandomInteger();
    
    /**
	 * Bekomme Zufallsfigur
	 * @param isFilled Fülle Figur
	 * @return Zufallsfigur
	 */
	public static Figure getRandom(boolean isFilled) {
		// Zufallszahlen generieren
		int randomStoneInt = randomStone.getRandomInteger(0, 6);
		int randomRotateInt = randomStone.getRandomInteger(0, 3);
		int randomStoneColor = new RandomInteger().getRandomInteger(0, 6);
		
		int x = 0;
		int y = 0;

		Figure geo = null;
		switch (randomStoneInt) {
		case 0:
			geo = new I(x, y, isFilled, randomStoneColor);
			break;
		case 1:
			geo = new K(x, y, isFilled, randomStoneColor);
			break;
		case 2:
			geo = new LLeft(x, y, isFilled, randomStoneColor);
			break;
		case 3:
			geo = new LRight(x, y, isFilled, randomStoneColor);
			break;
		case 4:
			geo = new O(x, y, isFilled, randomStoneColor);
			break;
		case 5:
			geo = new ZLeft(x, y, isFilled, randomStoneColor);
			break;
		case 6:
			geo = new ZRight(x, y, isFilled, randomStoneColor);
			break;
		}
//		geo = new I(x, y, isFilled, randomStoneColor);
		geo.refreshStones();

		for (int i = 0; i <= randomRotateInt; i++) {
			geo.rotate();
		}
		
		return geo;
	}
	
	/**
	 * Füge eine Figur zum Spiel hinzu
	 * @param f Figur, die hinzugefügt werden soll.
	 */
	public static void add(Figure f) {
		f.calcDimension();
		Main.gw.addFigure(f);
	}
	
	/**
	 * Alle Steine laufen einen Schritt nach unten.
	 */
	public void moveDown() {
		for(int i = 0; i <= 3; i++) {
			getStone(i).moveDown();
		}
	}
	
}
