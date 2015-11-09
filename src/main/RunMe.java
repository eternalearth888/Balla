package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

public class RunMe {

	public RunMe() {
		fileLocation();
	}

	public String fileLocation() {
		JFileChooser fileChooser = new JFileChooser();
		File selectedFile = null;

		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fileChooser.getSelectedFile();
			//System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
		return selectedFile.getAbsolutePath();
	}

	public static void main(String[] args) throws FileNotFoundException,IOException {
		@SuppressWarnings("unused")
		SplashBalla start = new SplashBalla();

		BuildCombos calculate = new BuildCombos(new RunMe().fileLocation());
	}
}
