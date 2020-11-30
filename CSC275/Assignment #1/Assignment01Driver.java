package Assignments;

import java.util.Scanner;

public class Assignment01Driver {
	Scanner input = new Scanner(System.in);

	int cargotrack = 0; // Used to store how many places are currently used in cargohold

	public static void main(String[] args) {
		new Assignment01Driver();
	}

	// This will act as our program switchboard
	public Assignment01Driver() {
		String[] cargohold = new String[10]; // Changed from 25 to 10

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
			System.out.println("0: Exit the BlackStar Cargo Hold interface.");

			// Get the user input
			int userChoice = input.nextInt();
			input.nextLine();

			switch (userChoice) {
			case 1:
				addItem(cargohold);
				break;
			case 2:
				removeItem(cargohold);
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
			case 0:
				System.out.println("Thank you for using the BlackStar Cargo Hold interface. See you again soon!");
				System.exit(0);
			}
		}

	}

	private void addItem(String cargohold[]) {

		if (cargotrack < cargohold.length) {
			System.out.println("Enter an item to be stored: ");

			cargohold[cargotrack] = input.nextLine();

			cargotrack++;
			System.out.println("The item has been added, spaces left: " + (cargohold.length - cargotrack));

		} else {
			System.out.println("The cargo hold is full");
		}

	}

	private void removeItem(String cargohold[]) {

		System.out.println("Please enter the item to be removed: ");

		String itemToRemove = input.nextLine();
		boolean found = false;
		for (int i = 0; i < cargohold.length; i++) {
			if (cargohold[i] != null && itemToRemove.equalsIgnoreCase(cargohold[i])) {
				System.out.println(itemToRemove + " has been removed");
				cargohold[i] = null;
				for (int j = i; j < cargohold.length; j++) {
					if (j + 1 == cargohold.length) {
						cargohold[j] = null;
					} else {
						cargohold[j] = cargohold[j + 1];
					}

				}
				cargotrack--;
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println(itemToRemove + "was not found");
		}
	}

	private void sortItems(String cargohold[]) {

		int size = cargohold.length;

		for (int i = 0; i < size - 1; i++) {

			if (cargohold[i] != null) {

				int min = i;

				for (int j = i + 1; j < size; j++) {

					if (cargohold[j] != null && cargohold[min].compareToIgnoreCase(cargohold[j]) > 0) {
						min = j;

					}
				}

				String temp = cargohold[min];
				cargohold[min] = cargohold[i];
				cargohold[i] = temp;
			}
		}
	}

	private void searchItems(String cargohold[]) {

		System.out.println("Please enter the item to be found: ");

		String itemToSearch = input.nextLine();
		boolean found = false;
		for (int i = 0; i < cargohold.length; i++) {
			if (cargohold[i] != null && itemToSearch.equalsIgnoreCase(cargohold[i])) {
				System.out.println(itemToSearch + " was found at slot " + (i + 1));
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println(itemToSearch + " was not found");
		}

		// TODO: Search for a user specified item

	}

	private void displayItems(String cargohold[]) {
		int occurance = 1;
		sortItems(cargohold);
		for (int i = 0; i < cargohold.length; i++) {
			if (occurance > 1) { // I was trying to make it skip displaying the
									// same item several times
				occurance--;
			} else {
				if (cargohold[i] != null) {
					for (int j = i + 1; j < cargohold.length; j++) {
						if (cargohold[i].equalsIgnoreCase(cargohold[j])) {
							occurance++;
						}

					}
					System.out.println(cargohold[i] + " - " + occurance);

				}
			}

		}
		System.out.println();
		for (int i = 0; i < cargohold.length; i++) {
			if (cargohold[i] != null)
				System.out.println(cargohold[i]);
		}
	}
}
