import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

public class AVLTree<T extends Comparable<? super T>> {

	private Node root;
	private int size;

	public AVLTree() {
		clear();
	}

	public boolean add(T item) {
		boolean added = false;
		if (isEmpty()) {
			root = new Node(item);
			size += 1;
			added = true;
		} else {
			added = add(null, root, item);
		}

		return added;
	}

	private boolean add(Node parent, Node current, T data) {
		boolean added = false;
		if (current == null) {
			int result = data.compareTo(parent.data);

			if (result < 0) {
				parent.left = new Node(data);
			} else {
				parent.right = new Node(data);
			}
			size += 1;
			return true;
		} else if (data.compareTo(current.data) < 0) {
			added = add(current, current.left, data);
			if (added) {
				current.leftHeight = 1 + getHeight(current.left);
			}
		} else if (data.compareTo(current.data) > 0) {
			added = add(current, current.right, data);
			if (added) {
				current.rightHeight = 1 + getHeight(current.right);
			}
		} else {
			return false;
		}

		if (added) {
			rebalance(parent, current);
		}

		return added;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getHeight() {
		return getHeight(root);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return -1;
		}

		return Math.max(node.leftHeight, node.rightHeight);
	}

	private void fixHeight(Node node) {
		if (node != null) {
			fixHeight(node.left);
			fixHeight(node.right);

			if (node.left == null) {
				node.leftHeight = 0;
			}
			if (node.right == null) {
				node.rightHeight = 0;
			}
			if (node.left != null && node.right != null) {
				node.leftHeight = 1 + getHeight(node.left);
				node.rightHeight = 1 + getHeight(node.right);
			}
		}
	}

	private void rebalance(Node parent, Node node) {
		// Left
		if (balance(node) > 1) {
			if (balance(node.left) < 0) {
			// left rotation
				node.left = leftRotation(node.left);
			}
			// right rotation
			if (parent == null) {
				root = rightRotation(node);
			} else if (parent.left == node) {
				parent.left = rightRotation(node);
			} else {
				parent.right = rightRotation(node);
			}
			// Right
		} else if (balance(node) < -1) {
			if (balance(node.right) > 0) {
				node.right = rightRotation(node.right);
			}

			// left rotation
			if (parent == null) {
				root = leftRotation(node);
			} else if (parent.left == node) {
				parent.left = leftRotation(node);
			} else {
				parent.right = leftRotation(node);
			}
		}
	}

	private int balance(Node node) {
		return node.leftHeight - node.rightHeight;
	}

	private Node rightRotation(Node n) {
		Node c = n.left;
		Node t2 = c.right;

		c.right = n;
		n.left = t2;

		fixHeight(c);

		return c;
	}

	private Node leftRotation(Node n) {
		Node c = n.right;
		Node t2 = c.left;

		c.left = n;
		n.right = t2;

		fixHeight(c);

		return c;
	}

	@Override
	public String toString() {
		return levelOrderString();
	}

	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{");

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();

			q.add(root);
			Node temp = null;

			while (!q.isEmpty()) {

				temp = q.remove();
				if (temp.left != null) {
					q.add(temp.left);
				}

				if (temp.right != null) {
					q.add(temp.right);
				}

				sb.append(temp);
				if (!q.isEmpty()) {
					sb.append(", ");
				}
			}

		}

		sb.append("}");

		return sb.toString();
	}

	/******************** STUDENT CODE ************************/

	public List<T> getReverseCrown(T data) {		
		Node topNode = searchForNode(root, data);		
		
		List<T> listToReturn = new LinkedList<>(); // initialize the list to pass by reference		
		getReverseCrown(topNode, listToReturn); // call the solution and pass the list
		
		return listToReturn;
	}

	/*
	 * Adds all the leaves of a given node to the passed by reference list 
	 */
	private void getReverseCrown (Node current, List<T> listToReturn) {
		if (current.left == null && current.right == null) { // leaf case: add it to the list
			listToReturn.add(current.data);
		}
		
		// ESSENTIAL: right must come first and left come second to get reverse list
		if (current.right != null) { // check right sub-tree
			getReverseCrown(current.right, listToReturn);
		}
		
		if (current.left != null) { // check left sub-tree
			getReverseCrown(current.left, listToReturn);
		}
	}
	
	/*
	 * Looks for the node that carries the given data in getReverseCrown
	 */
	private Node searchForNode(Node current, T data) {		
		if (current == null) { // couldn't find a node with the data
			throw new NoSuchElementException("No such node in the tree");
		}
		
		int res = current.data.compareTo(data);
		if (res == 0) { // found a node with the data
			return current;
		}
		if (res < 0) { // check left sub-tree
			return searchForNode(current.right, data);
		}
		
		return searchForNode(current.left, data); // check right sub-tree		
	}
	/***************** END STUDENT CODE ***********************/

	private class Node {
		private T data;
		private Node left;
		private Node right;

		private int leftHeight;
		private int rightHeight;

		public Node(T data) {
			this.data = data;
		}

		public String toString() {
			String repr = "(%s | %s | %s)";
			String leftString = left != null ? left.data.toString() : "";
			String rightString = right != null ? right.data.toString() : "";
			return data.toString(); // String.format(repr, data, leftString, rightString);
		}
	}

	public static void main(String[] args) {
		String test1 = "NMQGHADTSRPO";
		AVLTree<String> ft1 = new AVLTree<>();
		for (Character c : test1.toCharArray()) {
			ft1.add(c.toString());
		}

		System.out.println("----------------------------------------------");
		System.out.println("TREE 1: " + ft1);
		System.out.println();
		System.out.println("N Crown");
		System.out.println("Your output: " + ft1.getReverseCrown("N"));
		System.out.println("SHOULD BE: [T, R, O, M, G, A]");
		System.out.println();
		System.out.println("R Crown");
		System.out.println("Your output: " + ft1.getReverseCrown("R"));
		System.out.println("SHOULD BE: [R]");
		System.out.println();
		
		AVLTree<Integer> ft2 = new AVLTree<>();
		Integer arr[] = new Integer[] { 500, 250, 750, 125, 375, 625, 850, 62, 187, 312, 437, 550, 650, 775, 900, 58,
				77, 343, 575, 770, 975, 93, 765, 773 };
		for (Integer i : arr) {
			ft2.add(i);
		}
		
		System.out.println("----------------------------------------------");
		System.out.println("TREE 2: " + ft2);
		System.out.println();
		System.out.println("125 Crown");
		System.out.println("Your output: " + ft2.getReverseCrown(125));
		System.out.println("SHOULD BE: [187, 93]");
		System.out.println();
		System.out.println("750 Crown");
		System.out.println("Your output: " + ft2.getReverseCrown(750));
		System.out.println("SHOULD BE: [975, 773, 765, 650, 575]");
		System.out.println();

		AVLTree<String> ft3 = new AVLTree<>();
		ft3.add("ROOT");

		System.out.println("----------------------------------------------");
		System.out.println("TREE 3: " + ft3);
		System.out.println();
		System.out.println("ROOT Crown");
		System.out.println("Your output: " + ft3.getReverseCrown("ROOT"));
		System.out.println("SHOULD BE: [ROOT]");
		System.out.println();

		System.out.println("----------------------------------------------");
		AVLTree<String> ft4 = new AVLTree<>();
		System.out.println("TREE 4: " + ft4);
		System.out.println();
		try {
			ft4.getReverseCrown("Retrieving crown of element that does not exist.");
			System.out.println("You should raise an exception if the element is not found");
		} catch (NoSuchElementException e) {
			System.out.println("You raised a NoSuchElementException.");
		}
		System.out.println("----------------------------------------------");
	}
}
