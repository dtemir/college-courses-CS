package Assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assignment4 {
	Scanner input = new Scanner(System.in);
	Double totalWeight = 0.0;

	public static void main(String[] args) {
		new Assignment4();
	}

	// This will act as our program switchboard
	public Assignment4() {
		ArrayList<Item> cargohold = new ArrayList<Item>();
		ArrayList<Integer> arrayID = new ArrayList<Integer>();
		ArrayList<Double> totalWeight = new ArrayList<Double>();

		System.out.println("Welcome to the BlackStar Cargo Hold interface.");
		System.out.println("Please select a number from the options below");
		System.out.println("");

		while (true) {
			// Give the user a list of their options
			System.out.println("1: Add an item to the cargo hold.");
			System.out.println("2: Remove an item from the cargo hold.");
			System.out.println("3: Sort the contents of the cargo hold.");
			System.out.println("4: Search for an item.");
			System.out.println("5: Display the items in the cargo hold.");
			System.out.println("6: Perform a partical search for an item");
			System.out.println("0: Exit the BlackStar Cargo Hold interface.");

			// Get the user input
			int userChoice = input.nextInt();
			input.nextLine();

			switch (userChoice) {
			case 1:
				addItem(cargohold, arrayID, totalWeight);
				break;
			case 2:
				removeItem(cargohold, arrayID, totalWeight);
				break;
			case 3:
				sortItems(cargohold);
				break;
			case 4:
				searchItems(cargohold);
				break;
			case 5:
				displayItems(cargohold);
				break;
			case 6:
				decodeMessage();
				break;
			case 0:
				System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
				System.exit(0);
			}
		}

	}

	private void addItem(ArrayList<Item> cargohold, ArrayList<Integer> arrayID, ArrayList<Double> totalWeight) {
		String name = "";
		Double weight;
		int value, id, durability, choice1;

		int used = 0, fashion = 0, defense = 0; // variables for equipment
		Boolean used1;

		int healthy = 0, quality = 0; // variables for consumables
		String brand = "";
		Boolean healthy1;

		int power = 0, magazine = 0; // variables for weapons
		String format = "";

		System.out.println("Would you like to load a previous file? (Y/N)");
		String choice = input.nextLine().trim().toUpperCase();

		if (choice.charAt(0) == 'Y') {

			System.out.println("Enter the name of the file you want to load:");
			String fileName = input.nextLine().trim();
			try {
				Scanner fileInput = new Scanner(new File(fileName));
				while (fileInput.hasNext()) {
					name = fileInput.nextLine();
					weight = Double.parseDouble(fileInput.nextLine().trim());

					double sum = 0; // check if there is enough space
					for (int i = 0; i < totalWeight.size(); i++) {
						sum = sum + totalWeight.get(i);
					}

					if ((sum + weight) <= 25000) {
						totalWeight.add(weight);
					} else {
						System.out.println("The cargohold cannot exceed 25 tons");
						System.out.println("The current load is " + sum);
						System.out.println("Please remove an item to free some space \n");
						return;
					}

					value = Integer.parseInt(fileInput.nextLine().trim());
					id = Integer.parseInt(fileInput.nextLine().trim());

					boolean foundMatch = false; // check if id is being used
					for (int i = 0; i < arrayID.size(); i++) {
						if (id == arrayID.get(i)) {
							foundMatch = true;
						}
					}
					if (foundMatch) {
						System.out.println("The ID is being used");
						System.out.println("Please remove the item occupying the ID to use it \n");
						return;
					} else {
						arrayID.add(id);
					}

					durability = Integer.parseInt(fileInput.nextLine().trim());

					if (durability <= 0 || durability > 10) { // check durability is within range
						System.out.println("Please enter an appropriate number");
						totalWeight.remove(weight);
						arrayID.remove(id);
						return;
					}

					choice1 = Integer.parseInt(fileInput.nextLine().trim());

					if (choice1 <= 0 || choice1 > 3) {
						System.out.println("Please enter an appropriate number");
						totalWeight.remove(weight);
						arrayID.remove(id);
						return;
					}

					switch (choice1) {
					case 1:
						used = Integer.parseInt(fileInput.nextLine().trim());
						fashion = Integer.parseInt(fileInput.nextLine().trim());
						defense = Integer.parseInt(fileInput.nextLine().trim());
						if (used == 1)
							used1 = true;
						else
							used1 = false;
						Equippable equip = new Equippable(name, weight, value, id, durability, used1, defense, fashion);
						cargohold.add(equip);
						break;
					case 2:
						healthy = Integer.parseInt(fileInput.nextLine().trim());
						quality = Integer.parseInt(fileInput.nextLine().trim());
						brand = fileInput.nextLine().trim();
						if (healthy == 1)
							healthy1 = true;
						else
							healthy1 = false;
						Consumable food = new Consumable(name, weight, value, id, durability, quality, healthy1, brand);
						cargohold.add(food);
						break;
					case 3:
						power = Integer.parseInt(fileInput.nextLine().trim());
						magazine = Integer.parseInt(fileInput.nextLine().trim());
						format = fileInput.nextLine().trim();
						Weapon weapon = new Weapon(name, weight, value, id, durability, power, magazine, format);
						cargohold.add(weapon);
						break;
					}

				}
				System.out.println();
				fileInput.close();
			} catch (FileNotFoundException e) {
				System.out.println("Could not find the file. \n");
				return;
			}

		}

		else {

			System.out.println("Enter name");
			name = input.nextLine();

			double sum = 0; // check if there is enough space
			for (int i = 0; i < totalWeight.size(); i++) {
				sum = sum + totalWeight.get(i);
			}

			System.out.println("Enter weight in killograms (max 25 tons, current load is " + sum + ").");
			weight = input.nextDouble();
			input.nextLine();

			if ((sum + weight) <= 25000) {
				totalWeight.add(weight);
			} else {
				System.out.println("The cargohold cannot exceed 25 tons");
				System.out.println("The current load is " + sum);
				System.out.println("Please remove an item to free some space \n");
				return;
			}

			System.out.println("Enter value");
			value = input.nextInt();
			input.nextLine();

			System.out.println("Enter ID. These IDs are being used: " + arrayID + ".");
			id = input.nextInt();
			input.nextLine();

			boolean foundMatch = false; // check if id is being used
			for (int i = 0; i < arrayID.size(); i++) {
				if (id == arrayID.get(i)) {
					foundMatch = true;
				}
			}
			if (foundMatch) {
				System.out.println("The ID is being used");
				System.out.println("Please remove the item occupying the ID to use it \n");
				return;
			} else {
				arrayID.add(id);
			}

			System.out.println("Enter durability (1 - bad, 10 - good)");

			durability = input.nextInt();
			input.nextLine();
			if (durability <= 0 || durability > 10) {
				System.out.println("Please enter an appropriate number");
				totalWeight.remove(weight);
				arrayID.remove(id);
				return;
			}

			System.out.println("1: Equippable");
			System.out.println("2: Consumable");
			System.out.println("3: Weapon");

			choice1 = input.nextInt();
			input.nextLine();
			if (choice1 <= 0 || choice1 > 3) {
				System.out.println("Please enter an appropriate number");
				totalWeight.remove(weight);
				arrayID.remove(id);
				return;
			}

			switch (choice1) {
			case 1:
				System.out.println("Please input 0: NOT USED or 1: USED");
				used = input.nextInt();
				input.nextLine();
				if (used == 1)
					used1 = true;
				else
					used1 = false;
				System.out.println("Please input how fashionable is the item (1 - not cool, 10 - cool)");
				fashion = input.nextInt();
				input.nextLine();
				System.out.println("Please input how defensive is the item (1 - useless, 10 - supernice)");
				defense = input.nextInt();
				input.nextLine();

				Equippable equip = new Equippable(name, weight, value, id, durability, used1, defense, fashion);
				cargohold.add(equip);
				break;
			case 2:
				System.out.println("Please input 0: NOT HEALTHY or 1: HEALTHY");
				healthy = input.nextInt();
				input.nextLine();
				if (healthy == 1)
					healthy1 = true;
				else
					healthy1 = false;
				System.out.println("Please input the quality of the food (1- bad, 10 - good)");
				quality = input.nextInt();
				input.nextLine();
				System.out.println("Please input the manufacturer of the food");
				brand = input.nextLine();

				Consumable food = new Consumable(name, weight, value, id, durability, quality, healthy1, brand);
				cargohold.add(food);
				break;
			case 3:
				System.out.println("Please input how powerful is the weapon (1 - useless, 10 - supernice)");
				power = input.nextInt();
				input.nextLine();
				System.out.println("Please input the capacity of the weapon (i.e. 30)");
				magazine = input.nextInt();
				input.nextLine();
				System.out.println("Please input the format of the weapon (i.e. Pistol)");
				format = input.nextLine();

				Weapon weapon = new Weapon(name, weight, value, id, durability, power, magazine, format);
				cargohold.add(weapon);
				break;
			}
			System.out.println("Your item has been successfully added \n");

			PrintStream pStream = null;
			File outFile = new File("assignment4.txt");
			try {
				FileOutputStream fos = new FileOutputStream(outFile, true);
				pStream = new PrintStream(fos);
				pStream.println(name);
				pStream.println(weight);
				pStream.println(value);
				pStream.println(id);
				pStream.println(durability);
				pStream.println(choice1);

				switch (choice1) {
				case 1:
					pStream.println(used);
					pStream.println(fashion);
					pStream.println(defense);
					break;
				case 2:
					pStream.println(healthy);
					pStream.println(quality);
					pStream.println(brand);
					break;
				case 3:
					pStream.println(power);
					pStream.println(magazine);
					pStream.println(format);
					break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				pStream.close();
			}
			return;
		}
	}

	private void removeItem(ArrayList<Item> cargohold, ArrayList<Integer> arrayID, ArrayList<Double> totalWeight) { 
// NOTE: This method does not delete the item from the input file. Only from the current buffer. 
		System.out.println("Enter the name of the item to be removed");
		String nameToRemove = input.nextLine();
		System.out.println("Enter the ID of the item to be removed");
		int idToRemove = input.nextInt();
		boolean found = false;
		for (Item s : cargohold) {
			if (s.getName().equalsIgnoreCase(nameToRemove) && s.getID() == idToRemove) {
				for (int i = 0; i < arrayID.size(); i++) {
					if (arrayID.get(i) == idToRemove)
						arrayID.remove(i); // used to remove the id of the item so that we could use this id further
				}
				for (int i = 0; i < totalWeight.size(); i++) {
					if (s.getWeight() == totalWeight.get(i)) {
						totalWeight.remove(i); // used to remove the weight of the item so that the hold had more space
					}
				}

				cargohold.remove(s);
				System.out.println("The item has been removed");
				found = true;
				break;

			}
		}

		if (!found) {
			System.out.println(nameToRemove + " was not found");
		}

	}

	private void sortItems(ArrayList<Item> cargohold) { // works just fine, but it is hard to see because the display
														// method only outputs one subclass at a time
// NOTE: It does not sort the input file.
		for (int i = 0; i < cargohold.size() - 1; i++) {
			int min = i;
			for (int j = i + 1; j < cargohold.size(); j++) {
				if (cargohold.get(min).getName().compareToIgnoreCase(cargohold.get(j).getName()) > 0) {
					min = j;
				}
			}
			Collections.swap(cargohold, min, i); // derived from stackoverflow
		}
	}

	private void searchItems(ArrayList<Item> cargohold) {

		System.out.println("Please enter the name of the item to be found: ");
		String nameToSearch = input.nextLine();
		System.out.println("Please enter the ID of the item to be found");
		int idToSearch = input.nextInt();

		boolean found = false;

		for (Item s : cargohold) {
			if (s.getName().equals(nameToSearch) && s.getID() == idToSearch) {
				System.out.println("The item was found at slot #" + (cargohold.indexOf(s) + 1));
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("The item could not be found");
		}

	}

	private void displayItems(ArrayList<Item> cargohold) { // NOTE: index at the beginning of each line might change
															// depending if sort has been applied. Please do not confuse
															// with the ID which remains the same.
		for (int j = 0; j < cargohold.size(); j++) {
			if (cargohold.get(j) instanceof Equippable)
				System.out.println((cargohold.indexOf(cargohold.get(j)) + 1) + " " + cargohold.get(j));
		}
		for (int j = 0; j < cargohold.size(); j++) {
			if (cargohold.get(j) instanceof Consumable)
				System.out.println((cargohold.indexOf(cargohold.get(j)) + 1) + " " + cargohold.get(j));
		}
		for (int j = 0; j < cargohold.size(); j++) {
			if (cargohold.get(j) instanceof Weapon)
				System.out.println((cargohold.indexOf(cargohold.get(j)) + 1) + " " + cargohold.get(j));
		}
		System.out.println();
	}

	private void decodeMessage() {

		System.out.println("1: To use the Advanced Decryption");
		System.out.println("2: To use the Basic Decryption");
		int choice = input.nextInt();
		input.nextLine();

		if (choice == 1) {
			System.out.println("Enter a phrase:");
			String phrase = input.nextLine().trim();
			String option = "";
			for (int number = 0; number < 26; number++) {
				for (int number1 = 0; number1 < 26; number1++) {
					option = "";
					for (int i = 0; i < phrase.length(); i++) {
						char c = phrase.charAt(i);

						if (i % 2 == 0) {
							if (Character.isAlphabetic(c)) {
								boolean isUpper = false;
								int valueOfC = (int) c;
								if (valueOfC < 97) {
									valueOfC -= 65;
									isUpper = true;
								} else {
									valueOfC -= 97;
								}
								valueOfC += number1;
								valueOfC %= 26;
								if (isUpper) {
									valueOfC += 65;
								} else {
									valueOfC += 97;
								}
								option = option + (char) valueOfC;
							} else {
								option = option + c;
							}
						} else {
							if (Character.isAlphabetic(c)) {
								boolean isUpper = false;
								int valueOfC = (int) c;
								if (valueOfC < 97) {
									valueOfC -= 65;
									isUpper = true;
								} else {
									valueOfC -= 97;
								}
								valueOfC += number;
								valueOfC %= 26;
								if (isUpper) {
									valueOfC += 65;
								} else {
									valueOfC += 97;
								}
								option = option + (char) valueOfC;
							} else {
								option = option + c;
							}

						}
					}
					if (option.contains("Greetings")) {
						System.out.println(option);
					}

				}

			}

		} else if (choice == 2) {
			System.out.println("Enter a phrase:");
			String phrase = input.nextLine().trim();
			for (int number = 0; number < 26; number++) {

				for (int i = 0; i < phrase.length(); i++) {
					char c = phrase.charAt(i);
					if (Character.isAlphabetic(c)) {
						boolean isUpper = false;
						int valueOfC = (int) c;
						if (valueOfC < 97) {
							valueOfC -= 65;
							isUpper = true;
						} else {
							valueOfC -= 97;
						}
						valueOfC += number;
						valueOfC %= 26;
						if (isUpper) {
							valueOfC += 65;
						} else {
							valueOfC += 97;
						}
						System.out.print((char) valueOfC);
					} else {
						System.out.print(c);
					}
				}
				System.out.println();

			}
		}
	}
}
