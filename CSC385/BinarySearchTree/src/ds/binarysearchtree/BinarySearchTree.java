package ds.binarysearchtree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<? super T>> {

	private Node root;
	private int size;

	public BinarySearchTree() {
		clear();
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Adding a node to the Binary Search Tree
	 * @param newKey
	 * @return true if added, false if not
	 */
	public boolean add(T newKey) {
		if (newKey == null) {
			throw new IllegalArgumentException("Null not allowed!");
		}
		
		boolean wasAdded = add(null, root, newKey);
		
		if (wasAdded) {
			size += 1;
		}
		
		return wasAdded;
	}
	
	/*
	 * Recursive way of adding to the BST.
	 * If it's empty, set newKey as root.
	 * If it's smaller than the current, go left.
	 * If it's bigger than the current, go right.
	 * If it reached the bottom (current is null),
	 * use parent to set newKey as its child.
	 */
	private boolean add(Node parent, Node current, T newKey) {
		boolean wasAdded = true;
		
		if (isEmpty()) {
			root = new Node(newKey);
		} else if (current == null) { // end of the tree, add from parent
			int comparison = newKey.compareTo(parent.key);
			if (comparison < 0) {
				parent.left = new Node(newKey);
			} else {
				parent.right = new Node(newKey);
			}
		} else if (newKey.compareTo(current.key) < 0) { // key is smaller that the current
			wasAdded = add(current, current.left, newKey);
		} else if (newKey.compareTo(current.key) > 0) { // key is larger than the current
			wasAdded = add(current, current.right, newKey);
		} else { // key is equal to the current
			return false;
		}
		
		fixHeight(current);
		
		if (current != null && wasAdded) {
			rebalance(parent, current);
		}
		
		return wasAdded;
	}

	/**
	 * Removing a node from the Binary Search Tree
	 * @param keyToRemove
	 * @return true if removed, false if not
	 */
	public boolean remove(T keyToRemove) {
		if (keyToRemove == null) {
			throw new IllegalArgumentException("Null not allowed!");
		}
		if (isEmpty()) {
			throw new EmptyCollectionException("Can't remove from an empty tree");
		}
		
		boolean wasRemoved = remove(null, root, keyToRemove);
		
		if (wasRemoved) {
			size -= 1;
		}
		
		return wasRemoved;
	}
	
	/*
	 * Recursive way of removing from the BST.
	 * If the current is null, nothing to remove.
	 * If it's smaller than the current, go left.
	 * If it's bigger than the current, go right.
	 * If it reached the needed node, 
	 * remove it based on four cases.
	 */
	private boolean remove(Node parent, Node current, T toRemove) {
		boolean wasRemoved = true;
		
		if (current == null) {
			return false;
		} else if (toRemove.compareTo(current.key) < 0) {
			wasRemoved = remove(current, current.left, toRemove);
		} else if (toRemove.compareTo(current.key) > 0) {
			wasRemoved = remove(current, current.right, toRemove);
		} else {
			removeNode(parent, current);
		}
		
		fixHeight(current);
		
		if (wasRemoved) {
			rebalance(parent, current);
		}
		
		return wasRemoved;
	}
	
	/*
	 * Removing from the BST.
	 * Case 1: Leaf Node
	 * Case 2: No Right Child
	 * Case 3: No Left Child
	 * Case 4: Two Children
	 */
	private void removeNode(Node parent, Node nodeToRemove) {
		if (isLeafNode(nodeToRemove)) {
			removeCase1(parent, nodeToRemove);
		} else if (nodeToRemove.left != null && nodeToRemove.right == null) {
			removeCase2(parent, nodeToRemove);
		} else if (nodeToRemove.left == null && nodeToRemove.right != null) {
			removeCase3(parent, nodeToRemove);
		} else {
			removeCase4(nodeToRemove);
		}
		
		fixHeight(parent);
	}
	
	// Remove node when it's a leaf
	private void removeCase1(Node parent, Node nodeToRemove) {
		if (parent == null) {
			root = null;
		}
		if (parent.left == nodeToRemove) {
			parent.left = null;
		} else {
			parent.right = null;
		}
	}
	
	// Remove node when it has no right child
	private void removeCase2(Node parent, Node nodeToRemove) {
		if (parent == null) {
			root = nodeToRemove.left;
		} else if (parent.left == nodeToRemove) {
			parent.left = nodeToRemove.left;
		} else {
			parent.right = nodeToRemove.left;
		}
		
		nodeToRemove.left = null;
	}
	
	// Remove node when it has no left child
	private void removeCase3(Node parent, Node nodeToRemove) {
		if (parent == null) {
			root = nodeToRemove.right;
		} else if (parent.left == nodeToRemove) {
			parent.left = nodeToRemove.right;
		} else {
			parent.right = nodeToRemove.right;
		}
		
		nodeToRemove.right = null;
	}
	
	// Remove node when it has both children
	private void removeCase4(Node nodeToRemove) {
		Node minimumOfRight = nodeToRemove.right;
		Node parentOfMinimum = nodeToRemove;
		
		while (minimumOfRight.left != null) {
			parentOfMinimum = minimumOfRight;
			minimumOfRight = minimumOfRight.left;
		}
		
		nodeToRemove.key = minimumOfRight.key;
		
		removeNode(parentOfMinimum, minimumOfRight);
		
		// rebalance the AVL tree because the right child does not get its height updated
		rebalance(nodeToRemove, nodeToRemove.right); 
	}
	
	// Check if node is a leaf
	private boolean isLeafNode(Node node) {
		return (node.left == null && node.right == null);
	}
	
	public int getHeight() {
		return getHeight(root);
	}
	
	/**
	 * Gets the height of the node
	 * @param current
	 * @return height of the node in the tree
	 */
	private int getHeight(Node current) {
		if (current == null) {
			return -1;
		}
		
		return Math.max(current.leftHeight, current.rightHeight);
	}
	
	/**
	 * Fixing height of the given node
	 * based on the heights of its sub-trees.
	 * @param current
	 */
	private void fixHeight(Node current) {
		if (current != null) { // update the heights with the heights of the left sub-tree and the right sub-tree
			current.leftHeight = getHeight(current.left) + 1;
			current.rightHeight = getHeight(current.right) + 1;
		}
	}
	
	// returns the difference between the left sub-tree and the right sub-tree heights
	private int balance(Node node) {
		return node.leftHeight - node.rightHeight;
	}
	
	// rebalances the tree according to AVL trees
	private void rebalance(Node parent, Node node) {
		if (node == null) {
			return;
		}
		// imbalance in left sub-tree
		if (balance(node) > 1) { // node's left child 
			if (balance(node.left) < 0) { // the right sub-tree of node's left child (do left rotation and then right rotation)
				node.left = leftRotation(node.left);
			}
			
			if (parent == null) { // right rotation
				root = rightRotation(node);
			} else if (parent.left == node) { 
				parent.left = rightRotation(node);
			} else {
				parent.right = rightRotation(node);
			}
		} 
		// imbalance in right sub-tree
		else if (balance(node) < -1) {
			if (balance(node.right) > 0) { // the left sub-tree of node's right child (do right rotation and left rotation)
				node.right = rightRotation(node.right);
			}
			
			if (parent == null) { // left rotation
				root = leftRotation(node);
			} else if (parent.left == node) {
				parent.left = leftRotation(node);
			} else {
				parent.right = leftRotation(node);
			}
		}
	}
	
	// rightRotation for an AVL tree
	private Node rightRotation(Node n) {
		Node c = n.left; // take left child
		Node t2 = c.right; // store left child's right sub-tree as t2
		
		n.left = t2; // set node's left child to t2
		c.right = n; // update the root to be c
		
		// update the heights of the nodes
		fixHeight(n);
		fixHeight(c);
		
		return c;
	}
	
	private Node leftRotation(Node n) {
		Node c = n.right; // take right child
		Node t2 = c.left; // store right child's left sub-tree as t2
		
		n.right = t2; // set node's right child to t2
		c.left = n; // update the root to be c
		
		// update the heights of the nodes
		fixHeight(n);
		fixHeight(c);
		
		return c;
	}
	

	@Override
	public String toString() {
		return levelOrderString();
	}

	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();

		sb.append("{ ");

		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();

			Node temp;

			q.add(root);
			sb.append(root);
			sb.append(" ");
			while (!q.isEmpty()) {
				temp = q.remove();

				if (temp.left != null) {
					q.add(temp.left);
					sb.append(temp.left);
					sb.append(" ");
				}

				if (temp.right != null) {
					q.add(temp.right);
					sb.append(temp.right);
					sb.append(" ");
				}
			}
		}

		sb.append("}");

		return sb.toString();

	}

	private class Node {
		private Node left;
		private Node right;
		
		private int leftHeight;
		private int rightHeight;
		
		private T key;

		public Node(T key) {
			this.key = key;
		}
		
		@Override 
		public String toString() {
			String leftKey = left != null ? left.key.toString() : "";
			String rightKey = right != null ? right.key.toString() : "";
			return "(" + key + " | " + leftKey + " | " + rightKey + " ) "; 
		}
	}

}
