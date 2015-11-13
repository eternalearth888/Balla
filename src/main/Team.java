package main;

import java.util.ArrayList;
import java.util.List;

class Team {
	private static final int MAX_SALARY = 50000;
	// 5 people, one of each
	Player pointGuard;
	Player shootingGuard;
	Player powerForward;
	Player smallForward;
	Player center;

	int totalSalary = 0;

	public Team() {
		// TODO Auto-generated constructor stub
	}

	public boolean CanAddPlayer(Player player) {
		if (totalSalary + player.getSalary() > MAX_SALARY) {
			return false;
		}
		switch (player.getPosition()) {
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

	public boolean isFull() {
		return center != null && pointGuard != null && powerForward != null && smallForward != null && shootingGuard != null;
	}

	public void AddPlayer(Player player) {
		totalSalary += player.getSalary();
		switch (player.getPosition()) {
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
		totalSalary -= player.getSalary();
		switch (player.getPosition()) {
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

	public List<Team> CreateListofTeams(List<Player> playerList) {
		List<Team> teamList = new ArrayList<>();
		Team team = new Team();
		BuildTeam(teamList, team, playerList);

		return teamList;
	}

	public void BuildTeam(List<Team> tList, Team team, List<Player> pList) {
		if (team.isFull()) {
			System.out.println(team.Copy());
			tList.add(team.Copy());
		} else {
			// XXX we could use the team to help us filter players
			for (Player player : pList) {
				if (team.CanAddPlayer(player)) {
					team.AddPlayer(player);
					List<Player> leftPositions = team.LeftPositions(pList);
					BuildTeam(tList, team, leftPositions);
					team.RemovePlayer(player);
				}
			}
		}
	}

	public List<Player> LeftPositions(List<Player> pList) {
		List<Player> playersP = new ArrayList<Player>();

		for (Player player : pList) {
			if (CanAddPlayer(player)) {
				playersP.add(player);
			}
		}

		return playersP;
	}

	@Override
	public String toString() {
		return "Team [pointGuard=" + pointGuard + ", shootingGuard=" + shootingGuard + ", powerForward=" + powerForward + ", smallForward=" + smallForward
				+ ", center=" + center + "]";
	}

}
