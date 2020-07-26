package Assignments;

public class NodeF {
	private Item item;
	NodeF next;
	
	public NodeF() {
		
	}
	
	public NodeF(Item item, NodeF next) {
		this.item = item;
		this.next = next;
	}
	

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public NodeF getNext() {
		return next;
	}

	public void setNext(NodeF next) {
		this.next = next;
	}
	
	public static NodeF selectionSort(NodeF start) { //The algorithm was derived from GeeksforGeeks
		
		NodeF a, b, c, d, r;

		a = b = start;

		// While b is not the last node
		while (b.next != null) {

			c = d = b.next;

			// While d is pointing to a valid node
			while (d != null) {
				int compare = b.getItem().getName().compareToIgnoreCase(d.getItem().getName());
				if (compare > 0) {

					// If d appears immediately after b
					if (b.next == d) {

						// Case 1: b is the head of the linked list
						if (b == start) {

							// Move d before b
							b.next = d.next;
							d.next = b;

							// Swap b and d pointers
							r = b;
							b = d;
							d = r;

							c = d;

							// Update the head
							start = b;

							// Skip to the next element
							// as it is already in order
							d = d.next;
						}

						// Case 2: b is not the head of the linked list
						else {

							// Move d before b
							b.next = d.next;
							d.next = b;
							a.next = d;

							// Swap b and d pointers
							r = b;
							b = d;
							d = r;

							c = d;

							// Skip to the next element
							// as it is already in order
							d = d.next;
						}
					}

					// If b and d have some non-zero
					// number of nodes in between them
					else {

						// Case 3: b is the head of the linked list
						if (b == start) {

							// Swap b.next and d.next
							r = b.next;
							b.next = d.next;
							d.next = r;
							c.next = b;

							// Swap b and d pointers
							r = b;
							b = d;
							d = r;

							c = d;

							// Skip to the next element
							// as it is already in order
							d = d.next;

							// Update the head
							start = b;
						}

						// Case 4: b is not the head of the linked list
						else {

							// Swap b.next and d.next
							r = b.next;
							b.next = d.next;
							d.next = r;
							c.next = b;
							a.next = d;

							// Swap b and d pointers
							r = b;
							b = d;
							d = r;

							c = d;

							// Skip to the next element
							// as it is already in order
							d = d.next;
						}
					}
				} else if (compare < 0) {

					// Update c and skip to the next element
					// as it is already in order
					c = d;
					d = d.next;
				}
			}

			a = b;
			b = b.next;
		}
  
        return start; 
	}
	
	
}
