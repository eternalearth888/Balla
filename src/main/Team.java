package main;

import java.util.ArrayList;
import java.util.List;

class Team {
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

	public void AddPlayer(Player player) {
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

	public List<Team> CreateTeam(List<Player> playerList)
	{
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
