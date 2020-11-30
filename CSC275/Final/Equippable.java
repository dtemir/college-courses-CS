package Assignments;

public class Equippable extends Item{
	private Boolean used;
	private int defense;
	private int fashion;
	
	public Equippable(String name, Double weight, int value, int iD, int durability, Boolean used, int defense, int fashion) {
		super(name, weight, value, iD, durability);
		this.defense = defense;
		this.fashion = fashion;
		this.used = used;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getFashion() {
		return fashion;
	}

	public void setFashion(int fashion) {
		this.fashion = fashion;
	}
	public String toString() {
		return String.format("%-15s%-20s%-20s%-20s%-10s%-20s%-15s%-15s%-15s", "Equipable - ", "Name: " + getName(),
				"Weight: " + getWeight(), "Value: " + getValue(), "ID: " + getID(), "Durability: " + getDurability(),
				"Used: " + getUsed(), "Fashion: " + getFashion(), "Defense: " + getDefense());
		
				 
				 //return "Equippable - \tName:" + getName() + "\tWeight:" + getWeight() + "\tValue:" + getValue() + "\tID:"
				// + getID() + "\tDurability:" + getDurability() + "\tUsed:" + getUsed() + "\tDefense:" + getDefense() + "\tFashion:" + getFashion();
	}
}

