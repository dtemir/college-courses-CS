
public class SortedList<T extends Comparable<? super T>> {
	
	// Fields
	private Node head;
	private Node tail;

	private int size;
	
	/**
	 * Node class
	 */
	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
		}
	}

	// Constructor
	public SortedList() {
		clear();
	}

	/**
	 * Returns true if list is empty, false if not
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Clears the list
	 */
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Gets the size of the list
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Adds a new object to the list in its sorted position
	 * 
	 * @param data
	 */
	public void add(T data) {
		Node newNode = new Node(data);
		if (isEmpty()) { // Adding to an empty list
			head = newNode;
			tail = head;
		} else { // Adding to a non-empty list
			Node temp = head;
			// Traverse until finding the place or getting to the end
			while (temp.next != null) {
				// if temp's data is lower than data's, keep moving
				if (temp.data.compareTo(data) <= 0) {
					temp = temp.next;
				// if temp's data is higher than data, stop
				} else {
					break;
				}
			}

			// Case 0: There is ONE node in the list
			if (temp.prev == null && temp.next == null) {
				// Current node is less than the new node
				if (temp.data.compareTo(data) <= 0) {
					temp.next = newNode;
					newNode.prev = temp;
					tail = newNode;
				// Current node is greater than the new node
				} else {
					temp.prev = newNode;
					newNode.next = temp;
					head = newNode;
				}
			// Case 1: The current node is at the end
			} else if (temp.next == null) {
				// Current node is less than the new node
				if (temp.data.compareTo(data) <= 0) {
					newNode.prev = temp;
					temp.next = newNode;
					tail = newNode;
				// Current node is greater than the new node
				} else {
					newNode.prev = temp.prev;
					newNode.next = temp;
					temp.prev.next = newNode;
					temp.prev = newNode;
				}
			// Case 2: The current node is at the start
			} else if (temp.prev == null) {
				newNode.next = temp;
				temp.prev = newNode;
				head = newNode;
			// Case 3: The current node is somewhere in the middle
			} else {
				newNode.next = temp;
				newNode.prev = temp.prev;
				temp.prev.next = newNode;
				temp.prev = newNode;
			}

		}
		size++;
	}
	
	/**
	 * Removes the item from a given index
	 * 
	 * @param index
	 * @return Node.data
	 */
	public T removeAt(int index) {
		if (isEmpty()) {
			throw new EmptyCollectionException("List is empty");
		}

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size);
		}

		Node toReturn;

		if (index == 0 && size == 1) { // Removing at the beginning of 1-node list
			toReturn = head;
			clear();
		} else if (index == 0) { // Removing at the beginning
			toReturn = head;
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			size--;
		} else if (index == size - 1) { // Removing at the end
			toReturn = tail;
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			size--;
		} else { // Removing at the middle
			Node temp = getNode(index);
			toReturn = temp;

			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			temp.next = null;
			temp.prev = null;
			size--;
		}

		return toReturn.data;
	}

	/**
	 * Returns the item data from a given position
	 * 
	 * @param index
	 * @return Node.data
	 **/
	public T get(int index) {
		return getNode(index).data;
	}

	/**
	 * Returns the node item from a given position
	 * 
	 * @param index
	 * @return Node
	 */
	private Node getNode(int index) {
		if (isEmpty()) {
			throw new EmptyCollectionException("List is empty");
		}

		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size);
		}

		Node temp;
		// Decide to traverse from head or tail (doubly linked list)
		if (index < size / 2) {
			temp = head;
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		} else {
			temp = tail;
			for (int i = size - index - 1; i > 0; i--) {
				temp = temp.prev;
			}
		}

		return temp;
	}

	/**
	 * Checks if the list contains an item
	 * 
	 * @param data
	 * @return found
	 */
	public boolean contains(T data) {
		boolean found = false;
		Node temp = head;
		
		// if data is at the head or tail, it is found
		if (head.data.equals(data) || tail.data.equals(data)) {
			return true;
		// if data is below the head's data or above the tail's data, no such item
		} else if (head.data.compareTo(data) > 0 || tail.data.compareTo(data) < 0) {
			return false;
		}
		
		// traverse the array until the item is found
		while (temp != null) {
			if (temp.data.equals(data)) {
				found = true;
				break;
			// if temp's data is bigger than data, it is not in the list
			} else if (temp.data.compareTo(data) > 0) {
				return false;
			}
			temp = temp.next;
		}

		return found;
	}

	/**
	 * Removes elements of the passed SortedList from the current list
	 * 
	 * @param otherList
	 */
	public void removeAll(SortedList<T> otherList) {
		
		Node temp1 = head;
		Node temp2 = otherList.head;
		
		// Iterate until one of the nodes next is null		
		while (temp1 != null && temp2 != null) {
			// CASE 0: The current list item is equal to the otherList item
			if (temp1.data.compareTo(temp2.data) == 0) {
				// Remove all nodes from the current list that are equal to the otherList item
				while (temp1 != null && temp2 != null && temp1.data.compareTo(temp2.data) == 0) {
					removeNode(temp1); // Remove node from the list
					if (temp1 == head) { // Change head if the current node is the head
						head = temp1.next;
					}
					if (temp1 == tail) { // Change tail if the current node is the tail
						tail = temp1.prev;
					}
					temp1 = temp1.next;
					if (temp1 != null) {
						removeRef(temp1.prev); // Remove node from the memory (change to null)
					}
				}
				temp2 = temp2.next;
			// CASE 1: The current list item is less than the otherList item
			} else if (temp1.data.compareTo(temp2.data) < 0){
				// Shift the current node until it is bigger or equal to the otherList item
				while (temp1 != null && temp2!= null && temp1.data.compareTo(temp2.data) < 0) {
					temp1 = temp1.next;
				}
			// CASE 2: The current list item is bigger than the otherList item
			} else {
				// Shift the otherList item until it is bigger or equal to the current list item
				while (temp1 != null && temp2 != null && temp1.data.compareTo(temp2.data) > 0) {
					temp2 = temp2.next;
				}
			}
		}
	}
	
	/**
	 * Removes given node from the chain
	 * (It does not remove the node, it just modifies the chain)
	 * 
	 * @param node
	 */
	private void removeNode(Node node) {
		// CASE 0: Node is in the middle of the list
		if (node.prev != null && node.next != null) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		// CASE 1: Node is at the start of the list
		} else if (node.prev == null && node.next != null) {
			node.next.prev = null;
		// CASE 2: Node is at the end of the list
		} else if (node.next == null && node.prev != null) {
			node.prev.next = null;
		}
	}
	
	/**
	 * Removes given node from the memory
	 * as it still points to its next and prev node
	 * 
	 * @param node
	 */
	private void removeRef(Node node){
		node = null;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		if (!isEmpty()) {
			Node temp = head;

			while (temp != null) {
				sb.append(temp.data);
				if (temp.next != null) {
					sb.append(", ");
				}
				temp = temp.next;
			}
		}

		sb.append("]");

		return sb.toString();
	}
}
