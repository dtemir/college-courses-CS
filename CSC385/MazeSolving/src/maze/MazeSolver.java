package maze;

import java.util.Stack;

/**
 * Notes:
 * 
 * The start and end position are contained within the maze object. Use the
 * getStart() and getEnd() methods.
 * 
 * @author Brian Rogers
 *
 */
public class MazeSolver {
	/**
	 * You need to implement this method
	 * 
	 * @param maze: The maze to be solved.
	 * @return An array of Position which stores a solution to the maze. If a
	 *         solution is not found a null value should be returned.
	 */
	public static Position[] solve(Maze maze) {

		// Initialize stack frontier with the first node
		Stack<Position> frontier = new Stack<Position>(); // Stack
		Position initial = maze.getStart(); // Initial Node
		frontier.add(initial); // Populate Stack
		Position current = initial; // Pointer to Current Node

		// Iterate unless goal is reached OR stack is empty
		while (!((current.equals(maze.getEnd()) || (frontier.isEmpty())))) {

			maze.setAt(current, 'V'); // Set current node as visited

			// Four directions to go
			Position up = new Position((current.getRow() + 1), current.getColumn());
			Position down = new Position((current.getRow() - 1), current.getColumn());
			Position left = new Position(current.getRow(), (current.getColumn() - 1));
			Position right = new Position(current.getRow(), (current.getColumn() + 1));

			// Check each direction (valid, not visited, not blocked)
			if (maze.validPosition(up) && maze.getAt(up) != 'V' && maze.getAt(up) != 'X') {
				if (!frontier.peek().equals(current)) { // ensure that node extracted from stack is back
					frontier.add(current);
				}
				frontier.add(up); // Add valid direction to the frontier
				current = up; // Update current
			} else if (maze.validPosition(down) && maze.getAt(down) != 'V' && maze.getAt(down) != 'X') {
				if (!frontier.peek().equals(current)) {
					frontier.add(current);
				}
				frontier.add(down);
				current = down;
			} else if (maze.validPosition(left) && maze.getAt(left) != 'V' && maze.getAt(left) != 'X') {
				if (!frontier.peek().equals(current)) {
					frontier.add(current);
				}
				frontier.add(left);
				current = left;
			} else if (maze.validPosition(right) && maze.getAt(right) != 'V' && maze.getAt(right) != 'X') {
				if (!frontier.peek().equals(current)) {
					frontier.add(current);
				}
				frontier.add(right);
				current = right;
			} else { // No valid moves, backtrack
				current = frontier.pop();
			}

		}

		if (frontier.size() != 0) {
			return convertStack(frontier);
		}

		return new Position[0];
	}

	private static Position[] convertStack(Stack<Position> frontier) {
		Position[] positions = new Position[frontier.size()];

		for (int i = frontier.size() - 1; i >= 0; i--) {
			Position temp = frontier.pop();
			positions[i] = temp;

		}

		return positions;
	}

}
