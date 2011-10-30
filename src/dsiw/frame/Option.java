package dsiw.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import dsiw.game.Gamer;
import dsiw.highscore.Highscore;
import dsiw.main.Data;

/**
 * Options-Fenster, in dem man verschiedene Einstellungen zum Spiel treffen kann.
 * @author DSIW
 *
 */
@SuppressWarnings("serial")
public class Option extends MyNoGameJFrame {
	
	Gamer gamer;
	
	JComponent selectLevel(final Game g, final JComboBox level) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JLabel name = new JLabel("Startlevel:");
		for(int i = 0; i < g.getLevel().getTimeArray().length; i++) {
			level.addItem(i+1);
		}
		level.setEditable(false);
		level.setSelectedItem(Data.startLevel+1);
		level.setEnabled(g.isGameOver());
		if (level.getSelectedItem() instanceof Integer) {
			Integer newLevel = (Integer) level.getSelectedItem();
			Data.startLevel = newLevel;
		}
		
		p.add(name, BorderLayout.WEST);
		p.add(level, BorderLayout.EAST);
		return p;
	}
	
	JComponent setName(JTextField eingabe, Game g) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JLabel name = new JLabel("Name:");
		eingabe.setText(g.getGamer().getName());
		
		p.add(name, BorderLayout.WEST);
		p.add(eingabe, BorderLayout.EAST);
		return p;
	}
	
	JComponent setPath(Game g, final JTextField path) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		final JFileChooser fc = new JFileChooser(path.getText());
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileFilter filter = new FileNameExtensionFilter("Highscore file", "scores", "score", "txt");
		fc.addChoosableFileFilter(filter);
		
		final File file = new File("");
		JLabel name = new JLabel("Pfad zur Highscore-Datei:");
		
		JButton chooser = new JButton("Auswählen");
		chooser.addActionListener(new ActionListener() {
			
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				int rVal = fc.showSaveDialog(Option.this);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					path.setText(fc.getCurrentDirectory().toString()+file.separator+
							fc.getSelectedFile().getName());
				}
//				if (rVal == JFileChooser.CANCEL_OPTION) {}
			}
		});
		
		p.add(name, BorderLayout.NORTH);
		p.add(path, BorderLayout.WEST);
		p.add(chooser, BorderLayout.EAST);
		return p;
	}
	
	JComponent setRandomColor(Game g, JCheckBox randomColor) {
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		randomColor.setSelected(Data.randomColor);
		p.add(randomColor, BorderLayout.WEST);
		return p;
	}
	
	JComponent frameOperation(final MyJFrame f, final Game g, final JTextField eingabe, final JComboBox level, final JCheckBox randomColor, final JTextField path) {
//		JPanel auswVerfButtons = new JPanel();
//		auswVerfButtons.setLayout(new FlowLayout());
		
		JButton save = new JButton("Übernehmen");
		ActionListener actionSave = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Data.randomColor = randomColor.isSelected();
				Data.highscoreFile = path.getText();
				Data.startLevel = (Integer) level.getSelectedItem()-1;
				g.getLevel().setLevel(((Integer) level.getSelectedItem())-1);
				g.getGamer().setName(eingabe.getText());
				System.out.println(g.getGamer().getName());
				g.repaint();
				
				File file = new File(path.getText());
				if(Highscore.testFile(file)) f.dispose();
			}
		};
		save.addActionListener(actionSave);
		
		JButton close = new JButton("Verwerfen");
		ActionListener actionClose = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				g.repaint();
				f.dispose();
			}
		};
		close.addActionListener(actionClose);
		
		// AuswVerfButton als BorderL
		JPanel auswVerfButtonsFlow = new JPanel();
		auswVerfButtonsFlow.setLayout(new BorderLayout());
		auswVerfButtonsFlow.add(save, BorderLayout.WEST);
		auswVerfButtonsFlow.add(new JLabel(" "), BorderLayout.CENTER);	// Abstand einfügen
		auswVerfButtonsFlow.add(close, BorderLayout.EAST);
		
		// Default Settings als BorderL
		JPanel defaultSettings = new JPanel();
		defaultSettings.setLayout(new BorderLayout());
		JButton defaultB = new JButton("Standardeinstellungen");
		ActionListener actionDefault = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(level.isEnabled()) level.setSelectedItem(Data.START_LEVEL+1);
				eingabe.setText(Data.NAME);
				path.setText(Data.HIGHSCORE_FILE.toString());
				randomColor.setSelected(Data.RANDOM_COLOR);
			}
		};
		defaultB.addActionListener(actionDefault);
		defaultSettings.add(defaultB);
		
		// Alle Buttons als BorderL
		JPanel allButtons = new JPanel();
		allButtons.setLayout(new BorderLayout());
		allButtons.add(new JLabel("\n"), BorderLayout.NORTH);	// Abstand einfügen
		allButtons.add(defaultSettings, BorderLayout.WEST);
		allButtons.add(auswVerfButtonsFlow, BorderLayout.EAST);
		
		return allButtons;
	}

	/**
	 * Erzeugt das Options-Fenster.
	 * @param g Grafik-Objekt
	 * @param setPos Wahrheitswert, der angibt, ob das Fenster am Vaterfenster ausgerichtet werden soll.
	 */
	public Option(Game g, boolean setPos) {
		super("Optionen");
		
//		setFont(myFont);
		
		// Durch das BorderLayout konnte ich nicht alle Komponenten in das Layout implementieren,
		// da man max. 3 horizontal implementieren kann. Es wurde aufgeteilt.
		
		// 1. Teil des Hinzufügens
		JPanel first = new JPanel();
		first.setLayout(new BorderLayout());
		JTextField eingabe = new JTextField(30);
		first.add(setName(eingabe, g), BorderLayout.NORTH);
		JComboBox level = new JComboBox();
		first.add(selectLevel(g, level), BorderLayout.CENTER);
		JCheckBox randomColor = new JCheckBox("Zufällige Steinfarben verwenden");
		first.add(setRandomColor(g, randomColor), BorderLayout.SOUTH);
		
		// 2. Teil des Hinzufügens
		JPanel last = new JPanel();
		last.setLayout(new BorderLayout());
		last.add(new JLabel("\n"), BorderLayout.NORTH);	// Abstand einfügen
		JTextField path = new JTextField(Data.highscoreFile.toString(), 30);
		last.add(setPath(g, path), BorderLayout.CENTER);
		last.add(frameOperation(this, g, eingabe, level, randomColor, path), BorderLayout.SOUTH);
		
		// Alle Komponenten (1. + 2. Teil) zusammenfassen
		JPanel all = new JPanel();
		all.setLayout(new BorderLayout());
		all.add(first, BorderLayout.NORTH);
		all.add(last, BorderLayout.CENTER);
		
		// Abstand zum Fensterrahmen einfügen
		JPanel allFlow = new JPanel();
		allFlow.setLayout(new FlowLayout());
		allFlow.add(all);
		
		// Hinzufügen zum JFrame
		add(allFlow);
		pack();
		
		setLocation(getMiddlePosition(g));
//		setAlwaysOnTop(true);
		setFocusable(true);
		setResizable(false);
		requestFocus();
		
		setVisible(true);
		
		// KeyListener
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					dispose();
				}
			}
		});
	}
	
	
}
