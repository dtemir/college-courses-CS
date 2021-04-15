package ds.integerset;

import java.util.LinkedList;
import java.util.Queue;

public class IntegerSet {

	private Node root;
	private int size;

	/*
	 * Empty set constructor
	 */
	public IntegerSet() {
		clear();
	}

	/*
	 * Initialized set constructor
	 */
	public IntegerSet(int array[]) {
		clear();
		for (int i = 0; i < array.length; i++) {
			add(array[i]);
		}
	}

	// clears the whole BST
	private void clear() {
		root = null;
		size = 0;
	}

	// checks if the BST is empty
	private boolean isEmpty() {
		return (size == 0);
	}

	/*
	 * Adds an item to the BST
	 */
	public boolean add(int newItem) {

		boolean wasAdded = add(null, root, newItem);

		if (wasAdded) {
			size++;
		}

		return wasAdded;
	}

	/*
	 * Recursive way of adding to the BST. If it's empty, set newKey as root. If
	 * it's smaller than the current, go left. If it's bigger than the current, go
	 * right. If it reached the bottom (current is null), use parent to set newKey
	 * as its child.
	 */
	private boolean add(Node parent, Node current, int newItem) {
		boolean wasAdded = true;

		if (isEmpty()) {
			root = new Node(newItem);
		} else if (current == null) {
			int comparison = newItem - parent.key;
			if (comparison < 0) {
				parent.left = new Node(newItem);
			} else if (comparison > 0) {
				parent.right = new Node(newItem);
			} else {
				return false;
			}
		} else if (newItem < current.key) {
			wasAdded = add(current, current.left, newItem);
		} else if (newItem > current.key) {
			wasAdded = add(current, current.right, newItem);
		} else {
			return false;
		}

		fixHeight(current);

		if (current != null && wasAdded) {
			rebalance(parent, current);
		}

		return wasAdded;
	}

	// Fixing height of the given node based on the heights of it's sub-trees
	private void fixHeight(Node current) {
		if (current != null) {
			current.leftHeight = getHeight(current.left) + 1;
			current.rightHeight = getHeight(current.right) + 1;
		}
	}

	// Gets the height of the node
	private int getHeight(Node current) {
		if (current == null) {
			return -1;
		}

		return Math.max(current.leftHeight, current.rightHeight);
	}

	// Re-balances the tree
	private void rebalance(Node parent, Node current) {
		if (current == null) {
			return;
		}

		if (balance(current) > 1) {
			if (balance(current.left) < 0) {
				current.left = leftRotation(current.left);
			}

			if (parent == null) {
				root = rightRotation(current);
			} else if (parent.left == current) {
				parent.left = rightRotation(current);
			} else {
				parent.right = rightRotation(current);
			}
		}

		else if (balance(current) < -1) {
			if (balance(current.right) > 0) {
				current.right = rightRotation(current.right);
			}

			if (parent == null) {
				root = leftRotation(current);
			} else if (parent.left == current) {
				parent.left = leftRotation(current);
			} else {
				parent.right = leftRotation(current);
			}
		}

	}

	private Node rightRotation(Node n) {
		Node c = n.left;
		Node t2 = c.right;

		n.left = t2;
		c.right = n;

		fixHeight(n);
		fixHeight(c);

		return c;
	}

	private Node leftRotation(Node n) {
		Node c = n.right;
		Node t2 = c.left;

		n.right = t2;
		c.left = n;

		fixHeight(n);
		fixHeight(c);

		return c;
	}

	// returns the difference between heights of sub-tree to find which is
	// unbalanced
	private int balance(Node current) {
		return current.leftHeight - current.rightHeight;
	}

	/*
	 * Returns true or false if a given value is in the set O(logN) because the tree
	 * is balanced
	 */
	public boolean contains(int itemToFind) {
		if (isEmpty()) {
			throw new EmptyCollectionException("Can't remove from an empty tree");
		}

		boolean wasFound = find(root, itemToFind);

		if (wasFound) {
			size--;
		}

		return wasFound;
	}

	// finds a node with the same value as toFind
	private boolean find(Node current, int toFind) {
		boolean wasFound = true;

		if (current == null) {
			return false;
		} else if (toFind < current.key) {
			wasFound = find(current.left, toFind);
		} else if (toFind > current.key) {
			wasFound = find(current.right, toFind);
		}

		return wasFound;
	}

	/*
	 * Takes another IntegerSet and returns a new InterSet containing the unique
	 * elements of both sets Returns a new object O(NlogN) because we add each node
	 * from two trees
	 */
	public IntegerSet union(IntegerSet other) {
		IntegerSet newSet = new IntegerSet();

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();
			Node temp;
			q.add(root);

			while (!q.isEmpty()) {
				temp = q.remove();
				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}
				newSet.add(temp.key);
			}

			Queue<Node> q2 = new LinkedList<>();
			Node temp2;
			q2.add(other.root);

			while (!q2.isEmpty()) {
				temp2 = q2.remove();
				if (temp2.left != null) {
					q2.add(temp2.left);
				}
				if (temp2.right != null) {
					q2.add(temp2.right);
				}
				newSet.add(temp2.key);
			}
		}

		return newSet;
	}

	/*
	 * Takes another IntegerSet and intersects it with the calling InterSet Returns
	 * a new object O(NlogN) because for each node we check if the other tree
	 * contains it and then add it
	 */
	public IntegerSet intersection(IntegerSet other) {

		IntegerSet newSet = new IntegerSet();

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();

			Node temp;
			q.add(root);

			while (!q.isEmpty()) {
				temp = q.remove();

				if (temp.left != null) {
					q.add(temp.left);
				}
				if (temp.right != null) {
					q.add(temp.right);
				}

				if (other.contains(temp.key)) {
					newSet.add(temp.key);
				}
			}
		}

		return newSet;
	}

	/*
	 * Returns size of the BST
	 */
	public int magnitude() {
		return size;
	}

	public String toString() {
		return inOrderString();
	}
	
	/*
	 * Prints the BST in in-order traversal
	 */
	public String inOrderString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		inOrderString(root, sb);
		sb.append("}");
		return sb.toString();
	}
	
	private void inOrderString(Node current, StringBuffer sb) {
		if (current != null) {
			inOrderString(current.left, sb);
			sb.append(current.key);
			sb.append(" ");
			inOrderString(current.right, sb);

		}
	}

	/*
	 * Prints the BST in level order
	 */
	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();

			Node temp;

			q.add(root);
			sb.append(root.key);
			sb.append(" ");
			while (!q.isEmpty()) {
				temp = q.remove();

				if (temp.left != null) {
					q.add(temp.left);
					sb.append(temp.left.key);
					sb.append(" ");
				}

				if (temp.right != null) {
					q.add(temp.right);
					sb.append(temp.right.key);
					sb.append(" ");
				}
			}
		}

		sb.append("}");

		return sb.toString();

	}
}