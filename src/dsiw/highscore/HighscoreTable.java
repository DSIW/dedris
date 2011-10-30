package dsiw.highscore;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import dsiw.exception.NoHighscoreInFileException;
import dsiw.frame.Game;
import dsiw.frame.MyNoGameJFrame;

/**
 * Die Highscore-Tabelle wird aus den Highscores erstellt. Spaltenüberschriften, -laengen werden hinzugefügt.
 * @author DSIW
 *
 */
public class HighscoreTable extends MyNoGameJFrame {

	private static final long serialVersionUID = 1L;
	private Highscore highscore;
	private final String[] columnsNames = {"Place", "Gamer", "Level", "Points"};
	private final int[] lengthColumns =   {50, 240, 50, 90};
	private final String[][] DATA;
	
//	private int getIndexOfGamerColumn() {
//		for(int i = 0; i < columnsNames.length; i++) {
//			if(columnsNames[i].equalsIgnoreCase("Gamer")) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	/**
	 * Highscore-Tabelle wird mit der ausgelesenen Highscore-Liste erzeugt.
	 */
	public HighscoreTable(Game g) {
		this(new Highscore(), g, true, true);
	}
	
	/**
	 * Highscore-Tabelle wird erstellt.
	 * @param hs Highscore-Liste wird in ein Array umgewandelt.
	 */
	@SuppressWarnings("unchecked")
	public HighscoreTable(Highscore hs, Game g, boolean setVisible, boolean setPos) {
		super("Highscores");
		highscore = hs;
//		Main.debugPrintln(hs.toString());
		DATA = highscore.listToArray();
		
		// Absteigend sortieren
		Arrays.sort(DATA, new ColumnComparator(3));
		
		// Platzierung eintragen
		for(int i = 0; i < DATA.length; i++) {
			DATA[i][0] = (i+1)+".";
		}
		
		// Setze max. Zeichenlänge der Gamer
//		lengthColumns[getIndexOfGamerColumn()] = highscore.getMaxNameLength();
		
		// Spaltenmodell erzeugen
		DefaultTableColumnModel cm = new DefaultTableColumnModel();
//		Main.debugPrint(this.getWidth()+" | ");
		for(int i = 0; i < columnsNames.length; i++) {
			TableColumn col = new TableColumn(i);
			col.setHeaderValue(columnsNames[i]);
//			Main.debugPrint("minW["+i+"]: "+lengthColumns[i]+" | ");
			col.setMinWidth(lengthColumns[i]);
//			lengthColumns[getIndexOfGamerColumn()] = 450-lengthColumns[0]-lengthColumns[2]-lengthColumns[3];
			col.setResizable(false);
			cm.addColumn(col);
		}
		
		// Tabellenmodell erzeugen
		TableModel tm = null;
		if(DATA.length == 0 || DATA[0].length == 0) {
			throw new NoHighscoreInFileException();
		} else {
			tm = new AbstractTableModel() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public Object getValueAt(int rowIndex, int columnIndex) {
					return DATA[rowIndex][columnIndex];
				}
				
				public int getRowCount() {
					return DATA.length;
				}
				
				public int getColumnCount() {
					return DATA[0].length;
				}
			};
		
			// Tabelle erzeugen
			JTable table = new JTable(tm, cm);
			table.setRowHeight(table.getRowHeight() + 10);
			table.setShowGrid(false);
			table.setShowHorizontalLines(true);
			table.setShowVerticalLines(false);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			table.setRowSelectionAllowed(true);
			
			Container cp = getContentPane();
			cp.add(new JScrollPane(table), BorderLayout.CENTER);
			
			setAlwaysOnTop(true);
			setResizable(false);
			
			pack();
//			setMaximumSize(new Dimension(Data.getMiddlePosition(this).x, 250));
			
			// Fokus zurückbeziehen, damit die Tasten funktionieren.
			setFocusable(true);
			requestFocus();
			
			addFocusListener(new FocusListener() {
				public void focusLost(FocusEvent arg0) {
					requestFocus();
				}
				
				public void focusGained(FocusEvent arg0) {
					requestFocus();			
				}
			});
			
			if(setPos) setLocation(getMiddlePosition(g));
			setVisible(setVisible);
		}
	}
}
