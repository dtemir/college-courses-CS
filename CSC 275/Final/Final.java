package Assignments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Final {
	Scanner input = new Scanner(System.in);
	Double totalWeight = 0.0;
	private NodeF start, tail;
	private NodeR startR, tailR;

	public static void main(String[] args) {
		new Final();
	}

	// This will act as our program switchboard
	public Final() {

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
			System.out.println("7: Perform a ransack.");
			System.out.println("0: Exit the BlackStar Cargo Hold interface.");

			// Get the user input
			int userChoice = input.nextInt();
			input.nextLine();

			switch (userChoice) {
			case 1:
				addItem();
				break;
			case 2:
				removeItem();
				System.out.println();
				break;
			case 3:
				sortItems();
				System.out.println("Done");
				System.out.println();
				break;
			case 4:
				searchItems();
				System.out.println();
				break;
			case 5:
				displayItems();
				System.out.println();
				break;
			case 6:
				decodeMessage();
				System.out.println();
				break;
			case 7:
				startR = null;
				double ran_sack = totalWeight;
				int size = 1;
				System.out.println("Enter the name of the file you want to process:");
				String fileName = input.nextLine().trim();
				ransack(fileName, ran_sack, size);
				System.out.println("That is what we can have based on our current weight of " + totalWeight);
				System.out.println();
				break;
			case 0:
				System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
				System.exit(0);
			}
		}

	}

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

	double sum = 0;

	private void addItem() {

		System.out.println("Would you like to load a previous file? (Y/N)");
		String choice = input.nextLine().trim().toUpperCase();

		if (choice.charAt(0) == 'Y') {

			double weightToDelete = 0;
			int deleteNodes = 0;
			System.out.println("Enter the name of the file you want to load:");
			String fileName = input.nextLine().trim();
			try {
				Scanner fileInput = new Scanner(new File(fileName));
				while (fileInput.hasNext()) {
					name = fileInput.nextLine();
					weight = Double.parseDouble(fileInput.nextLine().trim());

					if ((totalWeight + weight) <= 25000) {
						totalWeight += weight;
						weightToDelete += weight;
						deleteNodes++;
					} else {
						System.out.println("The cargohold cannot exceed 25 tons");
						totalWeight = totalWeight - weightToDelete;
						System.out.println("The current load is " + totalWeight);
						System.out.println("Please remove an item to free some space \n");

						for (int i = 0; i < deleteNodes; i++) { // this is used to delete the nodes that have been added
																// before; in case all items of the file won't fit in
																// totalWeight
							if (start == null) {
								break;
							}
							if (start.next == null) {
								break;
							}

							NodeF second_last = start;
							while (second_last.next.next != null) {
								second_last = second_last.next;
							}
							second_last.next = null;
						}
						return;
					}

					value = Integer.parseInt(fileInput.nextLine().trim());
					id = Integer.parseInt(fileInput.nextLine().trim());

					durability = Integer.parseInt(fileInput.nextLine().trim());

					if (durability <= 0 || durability > 10) { // check durability is within range
						System.out.println("Please enter an appropriate number");
						totalWeight -= weight;
						return;
					}

					choice1 = Integer.parseInt(fileInput.nextLine().trim());

					if (choice1 <= 0 || choice1 > 3) {
						System.out.println("Please enter an appropriate number");
						totalWeight -= weight;
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
						if (start == null) {
							start = new NodeF(equip, null);
							tail = start;
						} else {
							tail.setNext(new NodeF(equip, null));
							tail = tail.getNext();
						}
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
						if (start == null) {
							start = new NodeF(food, null);
							tail = start;
						} else {
							tail.setNext(new NodeF(food, null));
							tail = tail.getNext();
						}
						break;
					case 3:
						power = Integer.parseInt(fileInput.nextLine().trim());
						magazine = Integer.parseInt(fileInput.nextLine().trim());
						format = fileInput.nextLine().trim();
						Weapon weapon = new Weapon(name, weight, value, id, durability, power, magazine, format);
						if (start == null) {
							start = new NodeF(weapon, null);
							tail = start;
						} else {
							tail.setNext(new NodeF(weapon, null));
							tail = tail.getNext();
						}
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

			System.out.println("Enter weight in killograms (max 25 tons, current load is " + totalWeight + ")");
			weight = input.nextDouble();
			input.nextLine();

			if ((totalWeight + weight) <= 25000) {
				totalWeight += weight;
			} else {
				System.out.println("The cargohold cannot exceed 25 tons");
				System.out.println("The current load is " + totalWeight);
				System.out.println("Please remove an item to free some space \n");
				return;
			}

			System.out.println("Enter value");
			value = input.nextInt();
			input.nextLine();

			System.out.println("Enter ID");
			id = input.nextInt();
			input.nextLine();

			System.out.println("Enter durability (1 - bad, 10 - good)");

			durability = input.nextInt();
			input.nextLine();
			if (durability <= 0 || durability > 10) {
				System.out.println("Please enter an appropriate number");
				totalWeight -= weight;
				return;
			}

			System.out.println("1: Equippable");
			System.out.println("2: Consumable");
			System.out.println("3: Weapon");

			choice1 = input.nextInt();
			input.nextLine();
			if (choice1 <= 0 || choice1 > 3) {
				System.out.println("Please enter an appropriate number");
				totalWeight -= weight;
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

				if (start == null) {
					start = new NodeF(equip, null);
					tail = start;
				} else {
					tail.setNext(new NodeF(equip, null));
					tail = tail.getNext();
				}
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

				if (start == null) {
					start = new NodeF(food, null);
					tail = start;
				} else {
					tail.setNext(new NodeF(food, null));
					tail = tail.getNext();
				}
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

				if (start == null) {
					start = new NodeF(weapon, null);
					tail = start;
				} else {
					tail.setNext(new NodeF(weapon, null));
					tail = tail.getNext();
				}
				break;
			}
			System.out.println("Your item has been successfully added \n");

			PrintStream pStream = null;
			File outFile = new File("initial.txt");
			try {
				FileOutputStream fos = new FileOutputStream(outFile, true); // the true statement means that it will add
																			// to the existing file and not create a
																			// new, empty file
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

	private void removeItem() {
		System.out.println("Enter the name of the item to be removed");
		String nameToRemove = input.nextLine();
		System.out.println("Enter the ID of the item to be removed");
		int idToRemove = input.nextInt();

		NodeF del = start, prev = null;
		while (del != null) {
			if (nameToRemove.equals(del.getItem().getName()) && idToRemove == del.getItem().getID()) {
				totalWeight -= del.getItem().getWeight();
				break;
			}
			prev = del;
			del = del.getNext();
		}

		if (del == null) {
			System.out.println("Item was not found, and could not be removed");
		} else {
			if (del == start) {
				start = start.getNext();
			} else {
				prev.setNext(del.getNext());
			}
			System.out.println("Item was removed");
		}

	}

	private void sortItems() {

		NodeF.selectionSort(start);

	}

	private void searchItems() {

		System.out.println("Please enter the name of the item to be found: ");
		String nameToSearch = input.nextLine();
		System.out.println("Please enter the ID of the item to be found");
		int idToSearch = input.nextInt();

		NodeF p = start;
		while (p != null) {
			if (nameToSearch.equals(p.getItem().getName()) && idToSearch == p.getItem().getID()) {
				break;
			}
			p = p.getNext();
		}

		if (p != null) {

			System.out.println(p.getItem());

		} else {
			System.out.println("Item was not found");
		}

	}

	private void displayItems() {

		NodeF p = start;
		while (p != null) {

			System.out.println(p.getItem());

			p = p.getNext();
		}

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

	public void ransack(String fileName, Double ran_sack, int size) { // NOTE: NodeR class Linked List is used to keep
																		// track of
																		// items that have already been included in the
																		// list (Since in the previous assignment I used
																		// ArrayList, I had to find a way out).
																		// Please know that I wrote the contains and
																		// size methods by myself - no Java-embedded
																		// Linked List function is used!
		String nameRS = null;
		int valueRS = 0, durabilityRS = 0;
		Boolean choiceRS = null;
		int choiceRS1 = 0, choiceRS2 = 0, choice0 = 0;
		String choiceRS4 = null;
		Scanner fileInput;
		try {
			fileInput = new Scanner(new File(fileName));
			double min = Integer.MAX_VALUE;
			int counter = 0;
			int temp = 0;
			if (ran_sack >= 25000 || NodeR.size(startR) == size) {
				return;
			} else {
				while (fileInput.hasNext()) {

					name = fileInput.nextLine();
					weight = Double.parseDouble(fileInput.nextLine().trim());
					counter++;
					// System.out.println(counter);
					value = Integer.parseInt(fileInput.nextLine().trim());
					id = Integer.parseInt(fileInput.nextLine().trim());

					durability = Integer.parseInt(fileInput.nextLine().trim());

					choice1 = Integer.parseInt(fileInput.nextLine().trim());
					switch (choice1) {
					case 1:
						used = Integer.parseInt(fileInput.nextLine().trim());
						fashion = Integer.parseInt(fileInput.nextLine().trim());
						defense = Integer.parseInt(fileInput.nextLine().trim());
						if (used == 1)
							used1 = true;
						else
							used1 = false;
						break;
					case 2:
						healthy = Integer.parseInt(fileInput.nextLine().trim());
						quality = Integer.parseInt(fileInput.nextLine().trim());
						brand = fileInput.nextLine().trim();
						if (healthy == 1)
							healthy1 = true;
						else
							healthy1 = false;
						break;
					case 3:
						power = Integer.parseInt(fileInput.nextLine().trim());
						magazine = Integer.parseInt(fileInput.nextLine().trim());
						format = fileInput.nextLine().trim();
						break;
					}
					if (weight < min && !NodeR.contains(counter, startR)) {
						min = weight;
						choice0 = choice1;
						temp = counter;
						nameRS = name;
						valueRS = value;
						durabilityRS = durability;
						switch (choice1) {
						case 1:
							choiceRS = used1;
							choiceRS1 = fashion;
							choiceRS2 = defense;
							break;
						case 2:
							choiceRS = healthy1;
							choiceRS1 = quality;
							choiceRS4 = brand;
							break;
						case 3:
							choiceRS1 = power;
							choiceRS2 = magazine;
							choiceRS4 = format;
						}

					}
				}
				if ((ran_sack + min <= 25000)) {
					size = counter;

					if (startR == null) {
						startR = new NodeR(temp, null);
						tailR = startR;
					} else {
						tailR.setNext(new NodeR(temp, null));
						tailR = tailR.getNext();
					}
					// System.out.println(size);
					ran_sack += min;
					switch (choice0) {
					case 1:
						System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-15s%-15s\n", "Equipable - ",
								"Name: " + nameRS, "Weight: " + min, "Value: " + valueRS, "Durability: " + durabilityRS,
								"Used: " + choiceRS, "Fashion: " + choiceRS1, "Defense: " + choiceRS2);
						break;
					case 2:
						System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-15s%-15s\n", "Consumable - ",
								"Name: " + nameRS, "Weight: " + min, "Value: " + valueRS, "Durability: " + durabilityRS,
								"Healthy: " + choiceRS, "Quality: " + choiceRS1, "Brand: " + choiceRS4);
						break;
					case 3:
						System.out.printf("%-15s%-20s%-20s%-20s%-20s%-15s%-15s%-15s\n", "Weapon - ", "Name: " + nameRS,
								"Weight: " + min, "Value: " + valueRS, "Durability: " + durabilityRS,
								"Power: " + choiceRS1, "Magazine: " + choiceRS2, "Format: " + choiceRS4);
						break;
					}

					ransack(fileName, ran_sack, size); // NOTE: Recursion

				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the file. \n");
			return;
		}

	}

}
