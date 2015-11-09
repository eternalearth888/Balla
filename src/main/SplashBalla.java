package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class SplashBalla extends JFrame {
	public ImageIcon splash = createImageIcon("/images/ball.png");
	private JLabel image = new JLabel(splash);
	
	
	public SplashBalla() {
		getContentPane().add(image);
		setSize(500, 313);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setVisible(true);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setVisible(false);
		dispose();

	}

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SplashBalla.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

}
