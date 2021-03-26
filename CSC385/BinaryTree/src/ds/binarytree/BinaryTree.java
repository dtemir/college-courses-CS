package ds.binarytree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinaryTree<T> {
	
	private Node root;
	private int size;
	
	public BinaryTree() {
		clear();
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
	
	/**
	 * Adds data to the Binary Tree
	 * 
	 * @param newData
	 */
	public void add(T newData) {
		if (isEmpty()) {
			root = new Node(newData);
		} else {
			Queue<Node> q = new LinkedList<>();
			
			Node temp = root;
						
			while(true) {
				if (temp.left == null || temp.right == null) {
					break;
				}
				
				q.add(temp.left);
				q.add(temp.right);
				
				temp = q.remove();
			}
			
			if (temp.left == null) {
				temp.left = new Node(newData);
			} else {
				temp.right = new Node(newData);
			}
			
		}
		
		size += 1;
	}
	
	/**
	 * Removes data from the Binary Tree
	 * 
	 * @param toRemove
	 */
	public void remove(T toRemove) {
		if (isEmpty()) {
			throw new EmptyCollectionException("Can't remove from an empty tree");
		}
		
		Node toGet = getNode(toRemove);
		
		if (toGet == null) {
			throw new NoSuchElementException("Element " + toRemove + " does not exist in the tree");
		}
		
		Node last = getLast();
		Node parentOfLast = getParent(last);
		
		T temp = toGet.data;
		toGet.data = last.data;
		last.data = temp;
		
		// One item in the tree
		if (parentOfLast == null) {
			clear();
		} else if (parentOfLast.left == last) {
			parentOfLast.left = null;
			size -= 1;
		} else {
			parentOfLast.right = null;
			size -= 1;
		}
	}
	
	private Node getParent(Node child) {
		Node temp = null;
		Queue<Node> q = new LinkedList<>();
		
		q.add(root);
		
		while(!q.isEmpty()) {
			temp = q.remove();
			
			if (temp.left == child || temp.right == child) {
				return temp;
			}
			
			if (temp.left != null) {
				q.add(temp.left);
			}
			
			if (temp.right != null) {
				q.add(temp.right);
			}
		}
		
		return temp;
	}
	
	private Node getLast() {
		Node temp = null;
		Queue<Node> q = new LinkedList<>();
		
		q.add(root);
		
		while(!q.isEmpty()) {
			temp = q.remove();
			
			if (temp.left != null) {
				q.add(temp.left);
			}
			
			if (temp.right != null) {
				q.add(temp.right);
			}
		}
		
		return temp;
		
	}
	
	private Node getNode(T toGet) {
		Node temp;
		Queue<Node> q = new LinkedList<>();
		
		q.add(root);
		while(!q.isEmpty()) {
			temp = q.remove();
			
			if (temp.data.equals(toGet)) {
				return temp;
			}
			
			if (temp.left != null) {
				q.add(temp.left);
			}
			
			if (temp.right != null) {
				q.add(temp.right);
			}
		}
		
		return null;
	}
	
	public String levelOrderString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("{ ");
		
		if (!isEmpty()) {
			Queue<Node> q = new LinkedList<>();
			
			Node temp;
			
			q.add(root);
			sb.append(root.data);
			sb.append(" ");
			while (!q.isEmpty()) {
				temp = q.remove();
				
				if (temp.left != null) {
					q.add(temp.left);
					sb.append(temp.left.data);
					sb.append(" ");
				}
				
				if (temp.right != null) {
					q.add(temp.right);
					sb.append(temp.right.data);
					sb.append(" ");
				}
			}
		}
		
		sb.append("}");
		
		return sb.toString();
		
	}
	
	public String postOrderString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		postOrderString(root, sb);
		sb.append("}");
		return sb.toString();
	}
	
	private void postOrderString(Node current, StringBuffer sb) {
		if (current != null) {
			postOrderString(current.left, sb);
			postOrderString(current.right, sb);
			
			sb.append(current.data);
			sb.append(" ");
		}
	}
	
	public String toString() {
		return levelOrderString();
	}
	
	private class Node {
		private T data;
		private Node left;
		private Node right;
		
		public Node(T data) {
			this.data = data;
		}
	}
}
