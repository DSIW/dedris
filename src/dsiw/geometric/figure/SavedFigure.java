package dsiw.geometric.figure;

import java.awt.Graphics;
import dsiw.exception.NoRelativeStoneException;
import dsiw.geometric.GeometricObject;
import dsiw.geometric.Stone;
import dsiw.geometric.Vertex;
import dsiw.main.Data;
import dsiw.main.Main;

/**
 * Klasse, die die gefallenen Steine speichert und verändert. Die gefallenen Steine werden in einem Array gespeichert.
 * @author DSIW
 *
 */
public class SavedFigure {
	
	private final int COLUMNS = Data.COLUMNS;
	private final int ROWS = Data.ROWS;
	private Stone[][] bottomStones;
	private int fullRowsTotal = 0;
	private boolean rowJustDeleted = false;

	/**
	 * Bekomme Anzahl der gelöschten Reihen
	 * @return Anzahl
	 */
	public int getFullRowsTotal() {
		return fullRowsTotal;
	}
	
	/**
	 * Bekomme, wenn Reihe gerade geloescht wurde.
	 * @return true, wenn gerade geloescht wurde.
	 */
	public boolean isRowJustDeleted() {
		return rowJustDeleted;
	}
	
	/**
	 * Setze Wahrheitswert, ob eine Reihe gerade geloescht wurde.
	 * @param rowJustDeleted true, wenn gerade geloescht wurde.
	 */
	public void setRowJustDeleted(boolean rowJustDeleted) {
		this.rowJustDeleted = rowJustDeleted;
	}

	/**
	 * Initialisiere Array
	 */
	public SavedFigure() {
		bottomStones = new Stone[ROWS][COLUMNS];
	}
	
	/**
	 * Speichern der aktiven Figur
	 * @param activeFigure aktive Figur
	 * @throws NoRelativeStoneException Diese Exception wird geworfen, wenn kein relativer Stein (1-3) existiert.
	 */
	public void save(Figure activeFigure) throws NoRelativeStoneException {
		try {
			// Stein 0 speichern
			bottomStones[(int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH)]
			            [(int)(activeFigure.getStone(0).getV().getX()/Stone.WIDTH)] = activeFigure.getStone(0);
			// Stein 0 oben angekommen?
			if((int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH) <= 0) {
				Main.stop();
			}
			
			// Steine 1,2,3 speichern
			for(int i = 1; i <= 3; i++) {
				bottomStones[(int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH + activeFigure.getRelStonePos(i).getY())]
				            [(int)(activeFigure.getStone(0).getV().getX()/Stone.WIDTH + activeFigure.getRelStonePos(i).getX())] = activeFigure.getStone(i);
			// Steine 1,2,3 oben angekommen?
				if((int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH + activeFigure.getRelStonePos(i).getY()) <= 0) {
					Main.stop();
				}
				
//			refreshLi();
			}
		} catch (Exception e) {
			Main.stop();
		}
	}
	
	/**
	 * Prüft Feld, ob es einen Stein enthält.
	 * @param row Reihe des Feldes
	 * @param column Spalte des Feldes
	 * @return true, wenn Feld voll ist
	 */
	private boolean isFieldFull(int row, int column) {
		if(bottomStones[row][column] != null) {
			return true;
		}
		return false;
	}
	
	private boolean isStoneInRow(int row) {
		for(int c = 0; c < COLUMNS; c++) {
			if(isFieldFull(row, c)) return true;
		}
		return false;
	}
	
	private boolean isStoneInAllRows(int row) {
		if(!isStoneInRow(row)) return false;
		if(row <= 2) return true;
		return isStoneInAllRows(row-1);
	}
	
	/**
	 * Bekomme Wahrheitswert, ob Spiel vorbei ist.
	 * @return true, wenn Spiel vorbei.
	 */
	public boolean isGameOver() {
		return isStoneInAllRows(ROWS-1);
	}
	
	/**
	 * Prüft, ob die Reihe row voll ist. Also alle Positionen mit Steinen gefüllt sind.
	 * @param row Die Reihe, die überprüft werden soll.
	 * @return wahr, wenn alle Positionen mit Steinen gefüllt sind.
	 */
	private boolean isRowFull(int row) {
		boolean result = true;
		for(int column = 0; column < COLUMNS; column++) {
			if(!isFieldFull(row, column) && result) {
				result = false;
			}
		}
		return result;
	}
	
	private void moveRowDown(int row) {
//		Main.debugPrintln("MOVE ROW DOWN! | row: "+row);
//		if(row == ROWS-1) { // letzte Reihe
//			return;
//		}
//		Main.debugPrintln("----------------------------------------------------------------------------------");
		for(int r = row; r >= 0; r--) {
//			if(Main.DEBUG) System.out.print("Reihe: "+(r<10?"0"+r:r)+" | ");
			for(int c = 0; c < COLUMNS; c++) {
				if(r == 0) { // erste Reihe
//					if(Main.DEBUG && c == 0) System.out.print("1 | ");
					bottomStones[r][c] = null;
				} else {
//					if(Main.DEBUG && c == 0) System.out.print("R | ");
					if(bottomStones[r-1][c] == null) {
						bottomStones[r][c] = null;
					} else {
						bottomStones[r][c] = bottomStones[r-1][c];
						// Anfangspunkt des Steins um einen Schritt nach unten bewegen.
						bottomStones[r][c].setV(new Vertex(bottomStones[r][c].getV().getX(), bottomStones[r][c].getV().getY()+Stone.WIDTH));
					}
				}
//				if(Main.DEBUG && c == 0 && r > 0) System.out.print((r-1<10?"0"+(r-1):r-1)+" > "+(r<10?"0"+r:r)+" || ");
//				if(Main.DEBUG && c == 0 && r <= 0)                     System.out.print("nl > "+(r<10?"0"+r:r)+" || ");
//				if(Main.DEBUG) System.out.print(/*"NS["+(r<10?"0"+r:r)+"]["+(c<10?"0"+c:c)+"] = "+*/(bottomStones[r][c] != null?"S":" ")+" | ");
			}
//			Main.debugPrintln();
		}
//		Main.debugPrintln("----------------------------------------------------------------------------------");
		
//		refreshLi();
	}
	
	/**
	 * Prüft alle Reihen, ob Reihen voll sind. Lösche alle vollen Reihen.
	 */
	public boolean checkRows(int row) {
		// Reihen nach Anzahl der vollen Reihen löschen
//		for(int row = ROWS-1; row >= 0; row--) {
//			if(isRowFull(row)) {
//				fullRowsTotal++;
//				Main.debugPrintln("FULL ROW! | row: "+row);
//				moveRowDown(row);
//			}
//		}
		
		if(row < 0) return false;
		if(isRowFull(row)) {
			rowJustDeleted = true;
			fullRowsTotal++;
			moveRowDown(row);
		}
		return checkRows(row-1);
	}
	
	/**
	 * Prüft alle Reihen, ob diese voll sind. Wenn eine Reihe voll ist, wird diese gelöscht und
	 * alle Steine darüber werden um eine Steinbreite nach unten versetzt.
	 */
	public void checkAllRows() {
		checkRows(ROWS-1);
	}
	
	/**
	 * Prüft, ob die aktuelle Figur einen der gespeicherten Steine im nächsten Schritt berührt.
	 * @param activeFigure Aktive Figur
	 * @return true, wenn berührt wird.
	 */
	public boolean checkBottom(Figure activeFigure) {
		Vertex startV = new Vertex((int)(activeFigure.getStone(0).getV().getX()/Stone.WIDTH),
				   (int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH));
		
		// Check Orientierungsstein auf mögliche Berührung im nächsten Schritt (Fallen)
		if(startV.getY()+1 >= 0 && startV.getY()+1 < ROWS) {
			if(bottomStones[startV.getY()+1][startV.getX()] != null) {
				return true;
			}
		} else if(startV.getY()+1 >= ROWS-1) {
			return true;
		}
		
		// Berechne letzten relativen Stein in allen relevanten Spalten
		int maxX = activeFigure.getMaxRelStonePos().getX();
		int minX = activeFigure.getMinRelStonePos().getX();
		
//		Main.debugPrintln("Start:"+startV.getY()+"|"+startV.getX()+"\n  Column:"+minX+"|"+maxX);
		
		for(int c = minX; c <= maxX; c++) {
			// MIN der Spalte herausfinden
			int minY = activeFigure.getMinRelStonePosInColumn(0, c);
			
			// MAX der Spalte herausfinden
			int maxY = activeFigure.getMaxRelStonePosInColumn(minY, c);
			maxY += startV.getY() +1; // '+1' = nächster Schritt
			
//			Main.debugPrintln("  Col:"+c+" > maxY: "+maxY);
			
			// Check rel. Steine
			if(maxY >= 0 && maxY < ROWS) {
				if(bottomStones[maxY][startV.getX()+c] != null) {	// Prüfe auf mögliche Berührung
					return true;
				}
			} else if(maxY >= ROWS-1) {							// Prüfe, ob Stein unten angekommen ist
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Prüft, ob die aktuelle Figur einen der gespeicherten Steine im nächsten Schritt berührt.
	 * @param activeFigure Aktive Figur
	 * @return true, wenn berührt wird.
	 */
	public boolean checkLeft(Figure activeFigure) {
		Vertex startV = new Vertex((int)(activeFigure.getStone(0).getV().getX()/Stone.WIDTH),
				   (int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH));
		
		// Check Orientierungsstein auf mögliche Berührung im nächsten Schritt (Links)
		if(startV.getX() > 0 && startV.getX() < COLUMNS) {
//			Main.debugPrintln("Start:"+startV);	//OK
			try {
				if(bottomStones[startV.getY()][startV.getX()-1] != null) {
//				Main.debugPrintln("s0 hat links kein leeres feld");		//OK
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return true;
			}
		} else if(startV.getX() == 0) {
//			Main.debugPrintln("s0 am linken Rand.");	//OK mit O
			return true;
		}
		
		// Berechne letzten relativen Stein in allen relevanten Zeilen
		int maxY = activeFigure.getMaxRelStonePos().getY();
		int minY = activeFigure.getMinRelStonePos().getY();
		
//		Main.debugPrintln(" -Row:"+minY+"|"+maxY);	//OK
		
		for(int r = minY; r <= maxY; r++) {
			// MIN der Zeile herausfinden
			int maxX = activeFigure.getMaxRelStonePosInRow(0, r);	// OK
			
//			Main.debugPrintln("  Row:"+r+"("+(r+startV.getY())+")"+" > maxX:"+maxX);
			
			// MAX der Zeile herausfinden
			int minX = activeFigure.getMinRelStonePosInRow(maxX, r);	// OK
			
			int minXabs = startV.getX() +minX-1; // '-1' = nächster Schritt
			
//			Main.debugPrintln("  Row:"+r+"("+(r+startV.getY())+")"+" > minX: "+minX+"("+minXabs+")");
			
			// Check rel. Steine
			if(minXabs >= 0 && minXabs < COLUMNS) {
				try {
					if(bottomStones[startV.getY()+r][minXabs] != null) {	// Prüfe auf mögliche Berührung
//					Main.debugPrintln("reihe "+(startV.getY()+r)+" hat links kein leeres feld");
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return true;
				}
			} else if(minXabs < 0) {							// Prüfe, ob Stein links angekommen ist
//				Main.debugPrintln("reihe "+(startV.getY()+r)+" am linken Rand.");
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Prüft, ob die aktuelle Figur einen der gespeicherten Steine im nächsten Schritt berührt.
	 * @param activeFigure Aktive Figur
	 * @return true, wenn berührt wird.
	 */
	public boolean checkRight(Figure activeFigure) {
		Vertex startV = new Vertex((int)(activeFigure.getStone(0).getV().getX()/Stone.WIDTH),
				   (int)(activeFigure.getStone(0).getV().getY()/Stone.WIDTH));
		
		// Check Orientierungsstein auf mögliche Berührung im nächsten Schritt (Links)
		if(startV.getX() > 0 && startV.getX() < COLUMNS-1) {
//			Main.debugPrintln("Start:"+startV);	//OK
			try {
				if(bottomStones[startV.getY()][startV.getX()+1] != null) {
					return true;
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				return true;
			}
		} else if(startV.getX() == COLUMNS-1) {
//			Main.debugPrintln("s0 am rechten Rand.");	//OK mit O
			return true;
		}
		
		// Berechne letzten relativen Stein in allen relevanten Zeilen
		int maxY = activeFigure.getMaxRelStonePos().getY();
		int minY = activeFigure.getMinRelStonePos().getY();
		
//		Main.debugPrintln(" -Row:"+minY+"|"+maxY);	//OK
		
		for(int r = minY; r <= maxY; r++) {
			// MIN der Zeile herausfinden
			int minX = activeFigure.getMinRelStonePosInRow(0, r);	// OK
			
//			Main.debugPrintln("  Row:"+r+"("+(r+startV.getY())+")"+" > maxX:"+minX);
			
			// MAX der Zeile herausfinden
			int maxX = activeFigure.getMaxRelStonePosInRow(minX, r);	// OK
			
			int maxXabs = startV.getX() +maxX+1; // '-1' = nächster Schritt
			
//			Main.debugPrintln("  Row:"+r+"("+(r+startV.getY())+")"+" > minX: "+maxX+"("+maxXabs+")");
			
			// Check rel. Steine
			if(maxXabs >= 0 && maxXabs < COLUMNS-1) {
				try {
					if(bottomStones[startV.getY()+r][maxXabs] != null) {	// Prüfe auf mögliche Berührung
//					Main.debugPrintln("reihe "+(startV.getY()+r)+" hat rechts kein leeres feld");
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					return true;
				}
				
			} else if(maxXabs > COLUMNS-1) {					// Prüfe, ob Stein rechts angekommen ist
//				Main.debugPrintln("reihe "+(startV.getY()+r)+" am rechten Rand.");
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Zeichnen der gefallenen Figuren/Steine
	 * @param g Graphics-Objekt
	 */
	public void paintFigures(Graphics g) {
		for(int row = ROWS-1; row >= 0; row--) {
			for(int column = 0; column < COLUMNS; column++) {
				// Stone zu GeometricObject typecasten
				if (bottomStones[row][column] instanceof GeometricObject) {
					Stone bottomGeo = (Stone) bottomStones[row][column];
					bottomGeo.paintMeTo(g);
				}
			}
		}
	}
}
