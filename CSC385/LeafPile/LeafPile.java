public class LeafPile {
	public static void main(String[] args) {
		Ground map[][] = new Ground[5][10];
		generateRandomGround(map);
		printMap(map);
		System.out.println(largestLeafPile(map));
	}

	/***************** STUDENT CODE HERE *******************/
	/*
	 * Finding the largest pile of leaves in the map matrix The method traverses
	 * through the matrix and calls the sumLeafPile method on the cell with a leaf
	 */
	private static int largestLeafPile(Ground map[][]) {
		int sum = 0;

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == Ground.LEAF) { // Detects a leaf
					// Saystem.out.println("Checking leaves starting from " + i + ", " + j);
					int pile_sum = sumLeafPile(map, i, j); // Calls the recursive function
					sum = (pile_sum > sum) ? pile_sum : sum; // Updates max
				}
			}
		}

		return sum;
	}

	/*
	 * Finding the number of adjacent leaves Calls itself with a reference to the
	 * map matrix, and the coordinates
	 */
	private static int sumLeafPile(Ground map[][], int i, int j) {
		// Base Case #1: Checks for ArrayIndexOutOfBoundsException
		if (i < 0 || i > map.length - 1 || j < 0 || j > map[i].length - 1) {
			return 0;
		}

		// Base Case #2: Checks if the given coordinate leads to a leaf
		if (map[i][j] == Ground.GRASS) {
			return 0;
		}

		int sum;
		map[i][j] = Ground.GRASS; // Updates the current cell to grass (picked up the leaf)

		// Makes a recursive call to the adjacent cells
		// Adds one to the sum, as it picked up the leaf
		// System.out.println("Currently checking leaf at " + i + ", " + j);
		sum = 1 + sumLeafPile(map, i, j - 1) + sumLeafPile(map, i, j + 1) + sumLeafPile(map, i - 1, j)
				+ sumLeafPile(map, i + 1, j);
		// System.out.println("We have just checked adjacent leaf at " + i + ", " +j +
		// ": " + sum);

		return sum;
	}

	/**************** END STUDENT CODE **********************/

	/************ Utility Methods *************/

	private static void printMap(Ground map[][]) {
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if (map[r][c] == Ground.GRASS) {
					System.out.print(". ");
				}
				if (map[r][c] == Ground.LEAF) {
					System.out.print("~ ");
				}
			}
			System.out.println();
		}
	}

	private static void generateRandomGround(Ground map[][]) {
		int randGround;
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				randGround = (int) (Math.random() * 2);
				map[r][c] = randGround == 0 ? Ground.GRASS : Ground.LEAF;
			}
		}
	}

	/****************************************/
}
