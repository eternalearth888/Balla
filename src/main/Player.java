package main;

class Player {
	private Positions position;
	private double average;
	private int salary;
	private String name;

	public Player(String line) {
		String[] info = line.split(",");
		String positionString = info[0];

		switch (positionString) {
		case "PG":
			setPosition(Positions.PG);
			break;
		case "SG":
			setPosition(Positions.SG);
			break;
		case "PF":
			setPosition(Positions.PF);
			break;
		case "SF":
			setPosition(Positions.SF);
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
		return "Balla [position=" + getPosition() + ", average=" + average
				+ ", salary=" + salary + ", name=" + name + "]";
	}

	public Positions getPosition() {
		return position;
	}

	public void setPosition(Positions position) {
		this.position = position;
	}
}


