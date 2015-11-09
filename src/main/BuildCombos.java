package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildCombos {
	
	public BuildCombos(String fileLocation) throws FileNotFoundException, IOException{
		File file = new File(fileLocation);
		
		List<Player> playerList = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Player player = new Player(line);
				playerList.add(player);
			}
		}

		Team teamCombos = new Team();
		teamCombos.CreateTeam(playerList);

		System.out.print(teamCombos);
	}
}
