package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class RunMe {

	public RunMe() {

	}

	public String fileLocation() {
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + File.separator + "Desktop");
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = null;
			selectedFile = fileChooser.getSelectedFile();
			System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			return selectedFile.getAbsolutePath();
		}
		throw new IllegalStateException("What is the point if you don't pick a file");
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SplashBalla start = new SplashBalla();

		BuildCombos calculate = new BuildCombos(new RunMe().fileLocation());
	}
}
