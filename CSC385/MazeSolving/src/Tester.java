import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import maze.Maze;
import maze.MazeSolver;
import maze.Position;

/**
 * Driver class to test students solution. The printed result should be a path
 * from the start to the finish where the path is represented by asterisks.
 * 
 * The Solvable and Unsolvable mazes were generated using
 * https://www.dcode.fr/maze-generator
 * 
 * @author Brian Rogers
 *
 */
public class Tester {

	private static Maze loadMaze(String file) {
		char maze[][] = null;
		Position start = null, stop = null;
		try {
			Scanner filein = new Scanner(new File(file));
			// Load Header
			// First number is how many rows.
			// Second and third number are the row and column to start at.
			// Fourth and fifth number are the row and column to stop at.
			int row = filein.nextInt();
			start = new Position(filein.nextInt(), filein.nextInt());
			stop = new Position(filein.nextInt(), filein.nextInt());
			filein.nextLine();
			// End Header

			maze = new char[row][];

			int currRow = 0;
			while (filein.hasNextLine()) {
				maze[currRow++] = filein.nextLine().replace("\n", "").toCharArray();
			}

			filein.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (Exception e) {
			System.out.println("Something bad happened. See exception message");
			e.printStackTrace();
			System.exit(2);
		}

		return new Maze(maze, start, stop);
	}

	private static void walkPath(Maze maze, Position traversal[]) {
		Position previous = null;
		for (Position p : traversal) {
			if (maze.getAt(p) == ' ') {
				maze.setAt(p, '-');
			} else if (maze.getAt(p) == '-') {
				System.out.println("MISTAKE");
				System.out.println("There is a repeated position " + p);
				System.exit(3);
			} else {
				System.out.println("MISTAKE");
				System.out.println("There is an obstacle at position " + p);
				System.out.println(maze);
				System.exit(3);
			}

			// Check to make sure path is connected.
			if (previous != null) {
				int prevRow = previous.getRow();
				int prevCol = previous.getColumn();
				int curRow = p.getRow();
				int curCol = p.getColumn();

				boolean isPathConnected = false;
				if (curRow - 1 == prevRow || curRow + 1 == prevRow) {
					isPathConnected = curCol == prevCol;
				} else if (curCol - 1 == prevCol || curCol + 1 == prevCol) {
					isPathConnected = curRow == prevRow;
				}

				if (!isPathConnected) {
					System.out.println("Path not connected");
					System.out.println("From: " + previous);
					System.out.println("To: " + p);
					maze.setAt(previous, '?');
					maze.setAt(p, '?');
					System.out.println(maze);
					System.exit(3);
				}
			}
			previous = p;
		}
	}

	private static void printDialog() {
		System.out.println("Pick an option");
		System.out.println("1: Solvable Maze");
		System.out.println("2: Unsolvable Maze");
		System.out.println("3: Huge Unsolvable Maze");
		System.out.println("4: EXIT");
	}

	private static int getChoice(Scanner in) {
		boolean exit = false;
		int choice = 0;
		do {
			try {
				System.out.print(">> ");
				choice = Integer.parseInt(in.nextLine());
				if (choice > 0 && choice <= 4) {
					exit = true;
				} else {
					System.out.println("Choice must be either 1, 2, 3, or 4.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Choice must be either 1, 2, 3, or 4.");
			}
		} while (!exit);

		return choice;
	}

	public static void main(String[] args) {
		Maze clone;
		Position solution[];
		Scanner userIn = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			printDialog();
			int choice = getChoice(userIn);
			switch (choice) {
			case 1:
				/** Solvable Maze ***/
				Maze solvableMaze = loadMaze("SolvableMaze");
				clone = solvableMaze.clone();
				System.out.println("Testing Solvable Maze");
				System.out.println("Starts at " + clone.getStart());

				System.out.println("Ends at " + clone.getEnd());
				System.out.println(clone);

				solution = MazeSolver.solve(clone);
				if (solution.length != 0) {
					walkPath(solvableMaze, solution);
					System.out.println("\nSOLUTION");
					System.out.println(solvableMaze);
				} else {
					System.out.println("Null returned but maze is solvable.\n");
				}
				break;
			/*******************************************************************************/
			case 2:
				/** Unsolvable Maze **/
				Maze unsolvableMaze = loadMaze("UnsolvableMaze");
				clone = unsolvableMaze.clone();

				System.out.println("Testing Unsolvable Maze");
				System.out.println(clone);
				solution = MazeSolver.solve(clone);
				if (solution.length == 0) {
					System.out.println("CORRECT");
					System.out.println("This maze is unsolvable so an empty array should be returned.");
				} else {
					System.out.println("INCORRECT");
					System.out.println("This maze is unsolvable. An empty array should be returned.");
				}
				break;
			/********************************************************************************/

			/** Huge Solvable Maze **/
			case 3:
				Maze hugeSolvableMaze = loadMaze("HugeSolvableMaze");
				clone = hugeSolvableMaze.clone();
				System.out.println("Testing Huge Solvable Maze");
				System.out.println("Starts at " + clone.getStart());

				System.out.println("Ends at " + clone.getEnd());
				System.out.println(clone);

				solution = MazeSolver.solve(clone);
				if (solution.length != 0) {
					walkPath(hugeSolvableMaze, solution);
					System.out.println("\nSOLUTION");
					System.out.println(hugeSolvableMaze);
				} else {
					System.out.println("Null returned but maze is solvable.\n");
				}
				break;
			case 4:
				exit = true;
			}
		}
		System.out.println("Exitting!");
	}

}
