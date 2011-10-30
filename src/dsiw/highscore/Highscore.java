package dsiw.highscore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import dsiw.game.Gamer;
import dsiw.main.Data;

/**
 * Highscore-Einträge werden als Liste zusammengefasst und in einer Datei gespeichert. Datei kann geschrieben, gelesen, geprüft werden.
 * @author DSIW
 *
 */
public class Highscore {

	private List<HighscoreEntry> list = new ArrayList<HighscoreEntry>();
	private File file = new File(Data.highscoreFile);
	
	/**
	 * Bekomme Liste
	 * @return Liste
	 */
	public List<HighscoreEntry> getList() {
		return list;
	}

	/**
	 * Setze Liste
	 * @param list Liste
	 */
	public void setList(List<HighscoreEntry> list) {
		this.list = list;
	}

	/**
	 * Die Highscore-Datei wird angelegt, wenn diese nicht vorhanden ist und diese wird gelesen.
	 */
	public Highscore() {
		initFile();
		read();
	}
	
	/**
	 * Die Highscore-Datei wird angelegt, wenn diese nicht vorhanden ist und ein Highscore-Eintrag wird hinzugefügt.
	 * @param gamer Spieler
	 * @param level Level
	 * @param points Punkte
	 */
	public Highscore(Gamer gamer, int level, int points) {
		initFile();
		add(gamer, level, points);
	}
	
	/**
	 * Spieler-Name wird abgefragt und Highscore-Eintrag wird hinzugefügt.
	 * @param level Level
	 * @param points Punkte
	 */
	public void add(int level, int points) {
		this.add(new Gamer(), level, points);
	}
	
	private void initFile() {
		// Datei exisitert, lesbar, beschreibbar?
		if(file.exists() && file.canRead() && file.canWrite()) {
		} else {
			// Ordner existiert nicht?
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdir();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Highscore-Eintrag wird hinzugefügt
	 * @param gamer Spieler
	 * @param level Level
	 * @param points Punkte
	 */
	public void add(Gamer gamer, int level, int points) {
		HighscoreEntry he = new HighscoreEntry(gamer, level, points);
		
		if(isNotEntryInList(he)) {
			if (list.size() == Data.HIGHSCORE_MAX_GAMER) {
//				Main.debugPrintln("MAX GAMER IN HIGHSCORELIST");
				int indexOfLeast = getListIndexOfHighscoreEntry(getHighscoreEntryWithMinPoints());
				// Teste, ob egi Punktzahl größer ist, als die geringste Pktzahl in Highscore
				if(he.getPoints() > getHighscoreEntryWithMinPoints().getPoints()) {
					list.remove(indexOfLeast);
					list.add(he);
				} else {
//					Main.debugPrintln("EINTRAG zu klein!");
				}
			} else {
				list.add(he);
			}
		} else {
//			Main.debugPrintln("Eintrag schon vorhanden!");
		}
		
		write();
	}
	
	/**
	 * Bekomme der Listen-Index des Highscore-Eintrags
	 * @param he Highscore-Eintrag
	 * @return Index
	 */
	public int getListIndexOfHighscoreEntry(HighscoreEntry he) {
		for(int i = 0; i < list.size(); i++) {
			if(he.equals(list.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Bekomme den Highscore-Eintrag mit der geringsten Punktezahl
	 * @return Highscore-Eintrag
	 */
	public HighscoreEntry getHighscoreEntryWithMinPoints() {
		HighscoreEntry tempH = new HighscoreEntry(new Gamer("temp"), Integer.MAX_VALUE, Integer.MAX_VALUE);
		for(HighscoreEntry h : list) {
			if(h.getPoints() < tempH.getPoints()) {
				tempH = h;
			}
		}
//		Main.debugPrintln("Min H.: "+tempH.toString());
		return tempH;
	}
	
	/**
	 * Wahrheitswert, ob Highscore-Eintrag in der Liste nicht vorhanden ist.
	 * @param he Highscore-Eintrag
	 * @return true, wenn Highscore-Eintrag nicht vorhanden ist.
	 */
	// Prüfe, ob Eintrag schon vorhanden
	public boolean isNotEntryInList(HighscoreEntry he) {
		for(HighscoreEntry h : list) {
			if(he.equals(h)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Konvertiere Liste für Highscore-Datei
	 * @return Inhalt für die Highscore-Datei
	 */
	public String listToString() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < list.size(); i++) {
			// Formatieren der Einträge
//			sb.append(String.format("%-20s", list.get(i).getGamer().getName()));
			sb.append(list.get(i).getGamer().getName());
			sb.append(Data.SEPARATOR);
//			sb.append(String.format("%010d", list.get(i).getLevel()));
			sb.append(list.get(i).getLevel());
			sb.append(Data.SEPARATOR);
//			sb.append(String.format("%010d", list.get(i).getPoints()));
			sb.append(list.get(i).getPoints());
			sb.append("\r\n");
		}
		return sb.toString();
	}

	/**
	 * Konvertiere Liste zu Array
	 * @return Array der Liste
	 */
	public String[][] listToArray() {
		String[][] scores = new String[list.size()][4];
		if (scores.length > 0) {
			for (int r = 0; r < scores.length; r++) {
				if(list.get(r) == null) {
					break;
				}
				scores[r][1] = list.get(r).getGamer().getName();
				scores[r][2] = list.get(r).getLevel()+"";
				scores[r][3] = list.get(r).getPoints()+"";
			}
		}
		return scores;
	}

	/**
	 * Schreibe Highscore-Datei und prüfe, ob diese schon vorhanden ist.
	 */
	public void write() {
		try {
			Writer out = new FileWriter(file);
			out.write(listToString());
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("Highscore-Datei nicht vorhanden.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Teste Datei, ob diese gelesen, geschrieben werden kann oder existiert.
	 * @param file Datei, die überprüft werden soll.
	 * @return true, wenn kein Fehler auftrat.
	 */
	public static boolean testFile(File file) {
		boolean fileIsOK = true;
		if(!file.isFile()) {
			fileIsOK = false;
			JOptionPane.showMessageDialog(null, "Die Pfadangabe ist keine Datei. Bitte wählen Sie eine Datei aus!", "Keine Datei!", JOptionPane.ERROR_MESSAGE);
		}
		if(file.exists()) {
			if(!file.canRead()) {
				fileIsOK = false;
				JOptionPane.showMessageDialog(null, "Die Datei konnte nicht gelesen werden. Bitte wählen Sie eine andere Datei aus!", "Keine Leseberechtigung!", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				file.createNewFile();
				if(!file.canRead()) {
					fileIsOK = false;
					JOptionPane.showMessageDialog(null, "Die Datei konnte nicht gelesen werden. Bitte wählen Sie eine andere Datei aus!", "Keine Leseberechtigung!", JOptionPane.ERROR_MESSAGE);
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Die Datei konnte nicht geschrieben werden. Bitte wählen Sie eine andere Datei aus!", "Keine Schreibberechtigung!", JOptionPane.ERROR_MESSAGE);
			} finally {
				fileIsOK = false;
			}
		}
		return fileIsOK;
	}

	/**
	 * Lese Highscore-Datei und füge den Inhalt der Liste hinzu
	 */
	public void read() {
		try {
			Scanner scanner = new Scanner(file);
			
			while(scanner.hasNextLine()) {
				String[] s = scanner.nextLine().split(Data.SEPARATOR);
				
				// Neuen Eintrag zur Liste hinzufügen
				list.add(new HighscoreEntry(new Gamer(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])));
			}
		} catch (IOException e) {
			boolean fileIsOK = true;
			if(!file.canRead()) {
				fileIsOK = false;
				JOptionPane.showMessageDialog(null, "Die Datei konnte nicht gelesen werden. Bitte wählen Sie einen anderen Pfad aus!\n" +
						"Einen neuen Pfad können Sie in den Optionen festlegen.", "Keine Leseberechtigung!", JOptionPane.ERROR_MESSAGE);
			}
			if(fileIsOK) write();
		}
	}

	@Override
	public String toString() {
		String s = "";
		for(HighscoreEntry l : list) {
			s += l.toString()+"\n";
		}
		return s;
	}
}
