package ds.test;

import ds.list.DoublyLinkedList;

public class DSTest {

	public static void main(String[] args) {

		DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();

		for (int i = 0; i < 10; i++) {
			dll.insert(i, (i + 1) * 10);
		}

		dll.insert(0, 200);
		dll.insert(5, 500);
		dll.insert(2, 600);
		System.out.println(dll);

		dll.removeAt(0);
		System.out.println(dll);

		dll.removeAt(dll.getSize() - 1);
		System.out.println(dll);

		dll.removeAt(3);
		System.out.println(dll);

		dll.removeAt(7);

		System.out.println(dll);
	}

}
