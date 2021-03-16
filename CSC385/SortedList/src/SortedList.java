
public class SortedList<T extends Comparable<? super T>> {

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
		
		while (temp1 != null && temp2 != null) {
			System.out.println("Comparing list1 " + temp1.data + " with list2 " + temp2.data);
			if (temp1.data.compareTo(temp2.data) == 0) {
				System.out.println(" case 0");
				while (temp1.data.compareTo(temp2.data) == 0) {
					removeNode(temp1);
					temp1 = temp1.next;
				}
				temp2 = temp2.next;
			} else if (temp1.data.compareTo(temp2.data) < 0){
				System.out.println(" case 1");
				while (temp1.data.compareTo(temp2.data) < 0) {
					temp1 = temp1.next;
				}
			} else {
				System.out.println(" case 2");
				while (temp1.data.compareTo(temp2.data) > 0) {
					temp2 = temp2.next;
				}
			}
		}
		
	}

	private void removeNode(Node node) {
		if (node.prev != null && node.next != null) {
			System.out.println("Case 1 with letter " + node.data);
			node.prev.next = node.next;
			node.next.prev = node.prev;
			node.next = null;
			node.prev = null;
			System.out.println("Successfully removed, Case 1");
		} else if (node.prev == null) {
			System.out.println("Case 2 with letter " + node.data);
			node.next.prev = null;
			node.next = null;
			node.prev = null;
			System.out.println("Successfully removed, Case 2");
		} else if (node.next == null) {
			System.out.println("Case 3 with letter " + node.data);
			node.prev.next = null;
			node.next = null;
			node.prev = null;
			System.out.println("Successfully removed, Case 3");
		}
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
}
