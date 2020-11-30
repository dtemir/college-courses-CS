package Assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assignment03Driver {
	Scanner input = new Scanner(System.in);
	Double totalWeight = 0.0;

	public static void main(String[] args) {
		new Assignment03Driver();
	}

	// This will act as our program switchboard
	public Assignment03Driver() {
		ArrayList<Item> cargohold = new ArrayList<Item>();
		ArrayList<Integer> arrayID = new ArrayList<Integer>();

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
				addItem(cargohold, arrayID);
				System.out.println();
				break;
			case 2:
				removeItem(cargohold, arrayID);
				System.out.println();
				break;
			case 3:
				sortItems(cargohold);
				System.out.println();
				break;
			case 4:
				searchItems(cargohold);
				System.out.println();
				break;
			case 5:
				displayItems(cargohold);
				System.out.println();
				break;
			case 6:
				decodeMessage();
				System.out.println();
				break;
			case 0:
				System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
				System.exit(0);
			}
		}

	}

	private void addItem(ArrayList<Item> cargohold, ArrayList<Integer> arrayID) {

		System.out.println("Enter name");
		String name = input.nextLine();

		System.out.println("Enter weight in kilograms (the cargo bay cannot exceed 25 tons)");
		Double weight = input.nextDouble();
		input.nextLine();
		totalWeight = totalWeight + weight;
		if (totalWeight <= 25000 && weight > 0) {
			System.out.println("Total weight of the cargo bay is: " + totalWeight);
			System.out.println("Enter value");
			int value = input.nextInt();
			input.nextLine();

			System.out.println("Enter ID");
			int id = input.nextInt();
			input.nextLine();

			boolean foundMatch = false;

			for (int s = 0; s < arrayID.size(); s++) {
				if (arrayID.get(s) == id) {
					System.out.println("The ID is already being used, try again");
					foundMatch = true;
					break;
				}
			}
			if (!foundMatch) {
				arrayID.add(id);
				System.out.println("Enter durability from 1 (bad) to 10 (perfect)");
				boolean appropriate = true;
				while (appropriate) {
					int durability = input.nextInt();
					input.nextLine();
					if (durability >= 1 && durability <= 10) {
						Item newItem = new Item(name, weight, value, id, durability);
						cargohold.add(newItem);
						System.out.println("Your item has been successfully added");
						appropriate = false;
					} else {
						System.out.println("Please enter an appropriate number");
					}
				}
				return;
			}

		} else {
			System.out.println("The cargo bay cannot exceed 25 tons");
			totalWeight = totalWeight - weight;
			System.out.println("The capacity left is: " + (25000 - totalWeight));
			System.out.println("Please try another item or remove an item from the cargo bay");
			return;
		}
	}

	private void removeItem(ArrayList<Item> cargohold, ArrayList<Integer> arrayID) {

		System.out.println("Enter the name of the item to be removed");
		String nameToRemove = input.nextLine();
		System.out.println("Enter the ID of the item to be removed");
		int idToRemove = input.nextInt();
		boolean found = false;
		for (Item s : cargohold) {
			if (s.getName().equalsIgnoreCase(nameToRemove) && s.getID() == idToRemove) {
				for (int i = 0; i < arrayID.size(); i++) {
					if (arrayID.get(i) == idToRemove)
						arrayID.remove(i); // used to remove an id of the item so that we could use this id further
				}
				totalWeight = totalWeight - s.getWeight(); // used to lower weight in the cargo bay since the item is
															// being removed
				cargohold.remove(s);
				System.out.println("The item has been removed");
				found = true;
				break;

			}
		}

		if (!found) {
			System.out.println(nameToRemove + "was not found");
			;
		}

	}

	private void sortItems(ArrayList<Item> cargohold) {

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

	private void displayItems(ArrayList<Item> cargohold) {
		int occurance = 1;
		sortItems(cargohold);
		for (int i = 0; i < cargohold.size(); i++) {
			if (occurance > 1) {
				System.out.println(
						"  ID: " + cargohold.get(i).getID() + " Weight: " + cargohold.get(i).getWeight() + " Value: "
								+ cargohold.get(i).getValue() + " Durability: " + cargohold.get(i).getDurability());
				occurance--;
			} else {

				for (int j = i + 1; j < cargohold.size(); j++) {
					if (cargohold.get(i).getName().equalsIgnoreCase(cargohold.get(j).getName())) {
						occurance++;
					}

				}
				System.out.println(cargohold.get(i).getName() + " - " + occurance);
				System.out.println(
						"  ID: " + cargohold.get(i).getID() + " Weight: " + cargohold.get(i).getWeight() + " Value: "
								+ cargohold.get(i).getValue() + " Durability: " + cargohold.get(i).getDurability());
			}

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
		} else {
			decodeMessage();
		}
	}
}
