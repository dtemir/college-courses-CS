
public class SortedList<T extends Comparable<? super T>> {
	
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

	// Fields
	private Node head;
	private Node tail;

	private int size;

	// Constructor
	public SortedList() {
		clear();
	}

	/**
	 * Returns True if list is empty, False if not
	 * 
	 * @return
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
	 * Returns size
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
//			 System.out.println("	Empty, so we add " + newNode.data);
			head = newNode;
			tail = head;
		} else { // Adding to a non-empty list
			Node temp = head;

			// Traverse until finding the place or getting to the end
			while (temp.next != null) {
//				 System.out.println("	Data to be added: " + data);
				// if temp's data is lower than data's, keep moving
				if (temp.data.compareTo(data) <= 0) {
//					 System.out.println("	" + temp.data + " is less than " + data + " because " + temp.data.compareTo(data));
//					 System.out.println("	Proceeding forward");
					temp = temp.next;
				// if temp's data is higher than data's, stop
				} else {
//					System.out
//							.println("	" + temp.data + " is greater than " + data + " because " + temp.data.compareTo(data));
					break;
				}
			}

			// Case 0: There is ONE node in the list
			if (temp.prev == null && temp.next == null) {
//				System.out.println("Case 0");
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
//				System.out.println("Case 1");
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
//				System.out.println("Case 2");
				newNode.next = temp;
				temp.prev = newNode;
				head = newNode;
			// Case 3: The current node is somewhere in the middle
			} else {
//				System.out.println("Case 3");
				newNode.next = temp;
				newNode.prev = temp.prev;
				temp.prev.next = newNode;
				temp.prev = newNode;
			}

		}
		size++;
	}
	
	/**
	 * Removes an item from a given index
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
		// decide to traverse from head or tail (doubly linked list)
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

		while (temp != null) {
			if (temp.data.equals(data)) {
				found = true;
				break;
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
		
		// Iterate unless one of the current nodes' next is null		
		while (temp1.next != null && temp2.next != null) {
			// CASE 0: The current list item is equal to the otherList item
			if (temp1.data.compareTo(temp2.data) == 0) {
				// Remove all nodes from the current list that are equal to the otherList item
				while (temp1.data.compareTo(temp2.data) == 0 && temp1.next != null) {
					System.out.println("Comparing " + temp1.data + " against " + temp2.data);
					removeNode(temp1); // Remove node from the list
					if (temp1 == head) { // Change head if the current node is the head
						head = temp1.next;
					}
					if (temp1 == tail) {
						tail = temp1.prev;
					}
					temp1 = temp1.next;
					System.out.println("now " + temp1.data);
					removeRef(temp1.prev); // Remove node from the memory (change to null)
				}
				temp2 = temp2.next;
				System.out.println("now " + temp2.data);
			// CASE 1: The current list item is less than the otherList item
			} else if (temp1.data.compareTo(temp2.data) < 0){
				// Shift the current node until it is bigger or equal to the otherList item
				while (temp1.data.compareTo(temp2.data) < 0 && temp1.next != null) {
					temp1 = temp1.next;
				}
			// CASE 2: The current list item is bigger than the otherList item
			} else {
				// Shift the otherList item until it is bigger or equal to the current list item
				while (temp1.data.compareTo(temp2.data) > 0 && temp2.next != null) {
					temp2 = temp2.next;
				}
			}
		}
		
		while (temp1.data.compareTo(temp2.data) == 0 && temp1.next != null) {
			System.out.println("Comparing " + temp1.data + " against " + temp2.data);
			removeNode(temp1); // Remove node from the list
			if (temp1 == tail) {
				tail = temp1.prev;
			}
			temp1 = temp1.next;
			System.out.println("now " + temp1.data);
			removeRef(temp1.prev); // Remove node from the memory (change to null)
		}
		
	}
	
	/**
	 * Removes given node from the chain
	 * (It does not remove the node, it just modifies the chain)
	 * 
	 * @param node
	 */
	private void removeNode(Node node) {
		if (node.prev != null && node.next != null) {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		} else if (node.prev == null) {
			node.next.prev = null;
		} else if (node.next == null) {
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
