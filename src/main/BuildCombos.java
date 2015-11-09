package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuildCombos {
	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		String filePath = "/home/earth/Desktop/DKSalaries.csv";
		File file = new File(filePath);

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

	private static class Player {
		private Positions position;
		private double average;
		private int salary;
		private String name;

		public Player(String line) {
			String[] info = line.split(",");
			String positionString = info[0];

			switch (positionString) {
			case "PG":
				position = Positions.PG;
				break;
			case "SG":
				position = Positions.SG;
				break;
			case "PF":
				position = Positions.PF;
				break;
			case "SF":
				position = Positions.SF;
				break;
			default:
				break;
			}

			average = Double.valueOf(info[1]);
			salary = Integer.valueOf(info[2]);
			name = info[3];
		}

		@Override
		public String toString() {
			return "Balla [position=" + position + ", average=" + average
					+ ", salary=" + salary + ", name=" + name + "]";
		}
	}

	public static class Team {
		// 5 people, one of each
		Player pointGuard;
		Player shootingGuard;
		Player powerForward;
		Player smallForward;
		Player center;

		public Team() {
			// TODO Auto-generated constructor stub
		}

		public boolean CanAddPlayer(Player player) {
			// check salary
			switch (player.position) {
			case PG:
				if (pointGuard == null)
					return true;
				break;
			case SG:
				if (shootingGuard == null)
					return true;
				break;
			case SF:
				if (smallForward == null)
					return true;
				break;
			case PF:
				if (powerForward == null)
					return true;
				break;
			case C:
				if (center == null)
					return true;
				break;
			default:
				return false;
			}

			return false;
		}

		public void AddPlayer(Player player) {
			switch (player.position) {
			case PG:
				pointGuard = player;
				break;
			case SG:
				shootingGuard = player;
				break;
			case SF:
				smallForward = player;
				break;
			case PF:
				powerForward = player;
				break;
			case C:
				center = player;
				break;
			default:
				break;
			}
		}

		public Team Copy() {
			Team backup = new Team();
			backup.pointGuard = pointGuard;
			backup.shootingGuard = shootingGuard;
			backup.smallForward = smallForward;
			backup.powerForward = powerForward;
			backup.center = center;

			return backup;
		}

		public void RemovePlayer(Player player) {
			switch (player.position) {
			case PG:
				pointGuard = null;
				break;
			case SG:
				shootingGuard = null;
				break;
			case SF:
				smallForward = null;
				break;
			case PF:
				powerForward = null;
				break;
			case C:
				center = null;
				break;
			default:
				break;
			}
		}

		public List<Team> CreateTeam(List<Player> playerList) {
			List<Team> teamList = new ArrayList<>();
			Team team = new Team();
			BuildTeam(teamList, team, playerList);

			return teamList;
		}

		public void BuildTeam(List<Team> tList, Team team, List<Player> pList) {
			if (tList.size() == 5) {
				tList.add(team.Copy());
			} else {
				for (Player player : pList) {
					if (team.CanAddPlayer(player)) {
						team.AddPlayer(player);
						BuildTeam(tList, team, pList);
						tList.add(team.Copy());
						team.RemovePlayer(player);
					}
				}
			}
		}
	}

	enum Positions {
		PG, SG, PF, SF, C
	}
}
