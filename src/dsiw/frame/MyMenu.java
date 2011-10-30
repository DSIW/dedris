package dsiw.frame;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import dsiw.exception.NoHighscoreInFileException;
import dsiw.highscore.HighscoreTable;
import dsiw.main.Main;


/**
 * Erzeugt Menü-Leiste
 * @author DSIW
 *
 */
public class MyMenu extends JMenuBar {
	private static final long serialVersionUID = -2514195470467599933L;
	
	private Game g;

	/**
	 * Erzeugt eigenes Menü
	 * @param g Game für die Pause-Funktion
	 */
	public MyMenu(Game g) {
		this.g = g;
		JMenuBar menubar = new JMenuBar();
		menubar.add(createGameMenu());
		menubar.add(createOptionMenu());
		menubar.add(createAboutMenu());
		g.setJMenuBar(menubar);
	}
	
	private JMenuItem newGame, pauseGame, stopGame, quitGame;
	/**
	 * Highscore-Liste
	 */
	public JMenuItem highscore;

	private JMenu createGameMenu() {
		JMenu ret = new JMenu("Spiel");
		ret.setMnemonic('G');
		
		// Neues Spiel
		newGame = new JMenuItem("Neues Spiel", 'N');
		setCtrlAccelerator(newGame, 'N');
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!g.isGameOver()) {
					Main.stop();
				}
				Main.start();
				activateEntries();
			}
		});
		newGame.setToolTipText("Neues Spiel.");
		ret.add(newGame);
		
		// Pause
		pauseGame = new JMenuItem("Pause", 'P');
		setCtrlAccelerator(pauseGame, 'Z');
		pauseGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!g.isGameOver()) {
					togglePause();
				}
			}
		});
		pauseGame.setToolTipText("Spiel pausieren.");
		pauseGame.setEnabled(false);
		ret.add(pauseGame);
		
		// Stop Spiel
		stopGame = new JMenuItem("Abbruch", 'A');
		setCtrlAccelerator(stopGame, 'C');
		stopGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!g.isPause() && !g.isGameOver()) {
					deactivateEntries();
					Main.stop();
				}
			}
		});
		stopGame.setToolTipText("Spiel abbrechen.");
		stopGame.setEnabled(false);
		ret.add(stopGame);
		
		// Separator
		ret.addSeparator();
		
		// Highscore
		highscore = new JMenuItem("Highscores", 'H');
		setCtrlAccelerator(highscore, 'H');
		try {
			HighscoreTable ht = new HighscoreTable(g.getHighscore(), g, false, false);
			ht.dispose();
			highscore.setEnabled(true);
			highscore.setToolTipText("Zeige Highscore-Einträge.");
		} catch (NoHighscoreInFileException e2) {
			highscore.setEnabled(false);
			highscore.setToolTipText("Highscore-Datei enthält noch keine Einträge.");
		}
		highscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new HighscoreTable(g.getHighscore(), g, true, true);
				} catch (NoHighscoreInFileException e1) {
					highscore.setEnabled(false);
					highscore.setToolTipText("Highscore-Datei enthält noch keine Einträge.");
				}
			}
		});
		ret.add(highscore);
		
		// Separator
		ret.addSeparator();
		
		// Beenden
		quitGame = new JMenuItem("Beenden", 'Q');
		setCtrlAccelerator(quitGame, 'Q');
		quitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.exit();
			}
		});
		quitGame.setToolTipText("Beende Spiel.");
		ret.add(quitGame);
		return ret;
	}
	
	private JMenu createOptionMenu() {
		JMenu ret = new JMenu("Optionen");
		ret.setMnemonic('O');
		JMenuItem mi;
		
		// Neues Spiel
		mi = new JMenuItem("Optionen", 'O');
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Option(g, true);
			}
		});
		mi.setToolTipText("Öffne Options-Fenster.");
		ret.add(mi);
		return ret;
	}
	
	private JMenu createAboutMenu() {
		JMenu ret = new JMenu("?");
//		ret.setMnemonic('?');
		JMenuItem mi;
		
		// Neues Spiel
		mi = new JMenuItem("Über "+g.getTitle(), 'A');
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new About(g);
			}
		});
		mi.setToolTipText("Öffne Informations-Fenster.");
		ret.add(mi);
		return ret;
	}

	private void setCtrlAccelerator(JMenuItem mi, char acc) {
		KeyStroke ks = KeyStroke.getKeyStroke(acc, Event.CTRL_MASK);
		mi.setAccelerator(ks);
	}
	
	/**
	 * Pausiert Spiel
	 */
	public void toPause() {
		if(!g.isGameOver() && !g.isNewGame()) {
			g.stopTimer();
			g.setPause(true);
			g.repaint();
			pauseGame.setText("Fortsetzen");
			pauseGame.setToolTipText("Setze Spiel fort.");
			stopGame.setEnabled(false);
			stopGame.setToolTipText("Spiel kann erst wieder gestoppt werden, wenn die Pause deaktiviert ist.");
		}
	}
	
	private void toResume() {
		if(g.isPause()) {
			g.startTimer();
			g.setPause(false);
//			g.repaint();
			pauseGame.setText("Pause");
			pauseGame.setToolTipText("Spiel pausieren.");
			stopGame.setEnabled(true);
		}
	}
	
	/**
	 * Schaltet zwischen Pausieren und Fortsetzen
	 */
	public void togglePause() {
		if(!g.isPause()) {
			toPause();
		} else {
			toResume();
		}
	}
	
	private void activateEntries() {
		if(!g.isGameOver()) {
			stopGame.setEnabled(true);
			stopGame.setToolTipText("Spiel abbrechen.");
			pauseGame.setEnabled(true);
			pauseGame.setToolTipText("Spiel pausieren.");
		}
	}
	
	/**
	 * Deaktiviert Stop- und Pause-Menüeintrag
	 */
	public void deactivateEntries() {
		stopGame.setEnabled(false);
		stopGame.setToolTipText("Spiel kann nicht gestoppt werden.");
		pauseGame.setEnabled(false);
		pauseGame.setToolTipText("Spiel kann nicht pausiert werden.");
	}
}