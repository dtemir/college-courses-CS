package ds.list;

public class DoublyLinkedList<T> {

	private Node head;
	private Node tail;

	private int size;

	public DoublyLinkedList() {
		clear();
	}

	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public Boolean isEmpty() {
		return (size == 0);
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
	
	private Node getNode(int idx) {
		Node temp;

		if (idx < size / 2) {
			temp = head;
			for (int i = 0; i < idx; i++) {
				temp = temp.next;
			}
		} else {
			temp = tail;
			for (int i = size - idx - 1; i > 0; i--) {
				temp = temp.prev;
			}
		}
		
		return temp;
	}

	public void insert(int idx, T newData) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size);
		}

		Node newNode = new Node(newData);
		if (isEmpty()) { // Adding to an empty list
			head = newNode;
			tail = head;
		} else if (idx == 0) { // Adding to the front
			newNode.next = head;
			head.prev = newNode;

			head = newNode;
		} else if (idx == size) { // Adding to the end
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		} else { // Adding to the middle 
			Node temp = getNode(idx);

			temp.prev.next = newNode;
			newNode.next = temp;
			newNode.prev = temp.prev;
			temp.prev = newNode;

		}

		size++;
	}
	
	public void removeAt(int idx) {
		if (idx < 0 || idx > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and " + size);
		}
		
		if (idx == 0 && size == 1) { // Removing at the beginning of 1-node list (WARNING: size gets set to 0)
			clear();
		} else if (idx == 0) { // Removing at the begging
			head = head.next;
			head.prev.next = null;
			head.prev = null;
			size--;
		} else if (idx == size - 1) { // Removing at the end
			tail = tail.prev;
			tail.next.prev = null;
			tail.next = null;
			size--;
		} else { // Removing at the middle
			Node temp = getNode(idx);
			
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
			temp.next = null;
			temp.prev = null;
			size--;
		}
		
	}

	private class Node {
		private T data;
		private Node next;
		private Node prev;

		public Node(T data) {
			this.data = data;
		}
	}

}
