package dsiw.frame;

import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 * Erzeugt eigenes Fenster mit Icon. LookAndFeel wird gesetzt.
 * @author DSIW
 *
 */
@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	
	Font myFont;
	Font fontName;

	/**
	 * Erzeugt eigenes Frame ohne Titel
	 */
	public MyJFrame() {
		this("");
	}

	/**
	 * Erzeugt eigenes Frame
	 * @param s Fenstertitel
	 */
	public MyJFrame(String s) {
		super(s);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			Image img = ImageIO.read((getClass().getClassLoader().getResource("icon_transparent_little.png")));
			setIconImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			myFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Ubuntu-R.ttf") );
//		} catch (FontFormatException e) {
//			System.out.println("Die eingelesene Schriftartendatei \""+fontName+"\" kann nicht verarbeitet werden!");
//		} catch (IOException e) {
//			System.out.println("Konnte Schrift-Datei \""+fontName+"\" nicht lesen!");
//		}
//		
//		setFont(myFont);
	}
	
	public void dispose() {
		setVisible(false);
		super.dispose();
	}
	
	/**
	 * Richte das Fenster in der Mitte des Bildschirm aus.
	 * @return Startpunkt des Fensters
	 */
	// Position (Mitte des Bildschirms) festlegen
	public Point getMiddlePosition() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		DisplayMode dm = gd.getDisplayMode();
		int newX = (dm.getWidth() - this.getWidth())/2;
		int newY = (dm.getHeight() - this.getHeight())/2;
		return new Point(newX, newY);
	}
	
	/**
	 * Bekomme Startpunkt (oben-links), an dem das Fenster in der Mitte des Vaterfensters ist
	 * @param parentJFrame Vaterfenster
	 * @return Startpunkt
	 */
	public Point getMiddlePosition(JFrame parentJFrame) {
		// Position von parentJFrame
		Point parentLocation = parentJFrame.getLocationOnScreen();
		// Position von this
		int thisX = (parentJFrame.getWidth() - this.getWidth())/2 + (int) parentLocation.getX();
		int thisY = (parentJFrame.getHeight() - this.getHeight())/2 + (int) parentLocation.getY();
//		Vertex middleParentPos = new Vertex(parentJFrame.getWidth()/2, parentJFrame.getHeight());
		return new Point(thisX, thisY);
	}
}
