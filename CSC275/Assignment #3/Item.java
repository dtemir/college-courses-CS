package Assignments;

public class Item {

String Name;
Double Weight;
int Value, ID, Durability; 

	public Item(){
		
	}

	public Item(String name, Double weight, int value, int iD, int durability) {
		Name = name;
		Weight = weight;
		Value = value;
		ID = iD;
		Durability = durability;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Double getWeight() {
		return Weight;
	}

	public void setWeight(Double weight) {
		Weight = weight;
	}

	public int getValue() {
		return Value;
	}

	public void setValue(int value) {
		Value = value;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getDurability() {
		return Durability;
	}

	public void setDurability(int durability) {
		Durability = durability;
	}

}

