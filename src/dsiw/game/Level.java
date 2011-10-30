package dsiw.game;

import dsiw.main.Data;
import dsiw.main.Main;

/**
 * Diese Klasse besitzt ein Time-Array, welches die Zeiten mit dem entsprechenden Index (Level) enthält.
 * 
 * @author DSIW
 *
 */
public class Level {
	
	private int level;
	private int[] timeArray;
	
	/**
	 * Bekomme die aktuelle Zeit
	 * @return Zeit in ms
	 */
	public int getAktTime() {
		return timeArray[level];
	}
	
	/**
	 * Setze Level. Überprüfe, ob Level im Bereich des Arrays ist.
	 * @param level Level
	 */
	public void setLevel(int level) {
		if(level >= 0 && level < timeArray.length) {
			this.level = level;
		}
	}

	/**
	 * Setze Level von der angegebenen Zeit
	 * @param time Zeit, von der das Level gesetzt wird.
	 */
	public void setLevelOfTime(int time) {
		for(int i = 0; i < timeArray.length; i++) {
			if(timeArray[i] == time) {
				level = i;
			}
		}
	}
	
	
	/**
	 * Bekomme Zeiten-Array
	 * @return timeArray Das Array, wo die Zeiten drin stehen.
	 */
	public int[] getTimeArray() {
		return timeArray;
	}
	
	/**
	 * Bekomme Level von der angegebenen Zeit
	 * @param time Zeit, von der das Level ausgesucht wird.
	 * @return Level als Integer
	 */
	public int getLevelOfTime(int time) {
		for(int i = 0; i < timeArray.length; i++) {
			if(timeArray[i] == time) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Bekomme aktuelle Level
	 * @return aktuelle Level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Initialisiert die Felder.
	 * Initialisiert das TimeArray, setzt Start-Level, setzt Intervall und  berechnet Zeiten.
	 */
	public Level() {
		timeArray = new int[Data.ANZAHL_LEVEL];
		setLevelOfTime(Data.START_GESCHWINDIGKEIT);
		// Init Level
		final int intervall = Data.START_GESCHWINDIGKEIT / Data.ANZAHL_LEVEL;
		for(int i = 0; i < timeArray.length; i++) {
			timeArray[i] = Data.START_GESCHWINDIGKEIT-i*intervall;
		}
	}
	
	/**
	 * Inkrementiere Level in Abhängigkeit der gelöschten Reihen. Der Trigger wird benötigt, weil sonst bei jedem Speichern der Figur inkrementiert wird.
	 * @param deletedRowsCounterTotal Gelöschte Reihen
	 * @param trigger Gerade Reihe gelöscht
	 */
	public void inkrementLevel(int deletedRowsCounterTotal, boolean trigger) {
		if(trigger) {
			if(Main.DEBUG) System.out.println("INKREMENTIEREN!");
			setLevel(deletedRowsCounterTotal / 10 + Data.startLevel);
		}
	}
	
	@Override
	public String toString() {
		return (getLevel()+1)+"";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Level) {
			Level that = (Level) obj;
			if(this.getLevel() == that.getLevel()) {
				return true;
			}
		}
		return false;
	}
}
