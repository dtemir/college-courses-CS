package Assignments;

public class Weapon extends Item {
	private int power;
	private int magazine;
	private String format;

	public Weapon(String name, Double weight, int value, int iD, int durability, int power, int magazine,
			String format) {
		super(name, weight, value, iD, durability);
		this.format = format;
		this.magazine = magazine;
		this.power = power;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMagazine() {
		return magazine;
	}

	public void setMagazine(int magazine) {
		this.magazine = magazine;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;

	}

	public String toString() {
		return String.format("%-15s%-20s%-20s%-20s%-10s%-20s%-15s%-15s%-15s", "Weapon - ", "Name: " + getName(),
				"Weight: " + getWeight(), "Value: " + getValue(), "ID: " + getID(), "Durability: " + getDurability(),
				"Power: " + getPower(), "Magazine: " + getMagazine(), "Format: " + getFormat());

		// return "Weapon - \tName:" + getName() + "\tWeight:" + getWeight() +
		// "\tValue:" + getValue() + "\tID:"
		// + getID() + "\tDurability:" + getDurability() + "\tPower:" + getPower() +
		// "\tMagazine:" + getMagazine() + "\tFormat:" + getFormat();
	}
}
