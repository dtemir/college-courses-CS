import java.util.Arrays;
import java.util.Random;

public class SortedListTest {

	public static void main(String args[]) {

		System.out.println("*****TESTING TOSTRING WITH INTEGERS*********");
		Random rand = new Random();
		SortedList<Integer> s1 = new SortedList<Integer>();
		int numbers[] = new int[10];

		for (int i = 0; i < numbers.length; i++) {
			int number = rand.nextInt(41) - 20;
			numbers[i] = number;
			s1.add(number); // random values between -20 and 20
		}
		System.out.println("\nAdding " + Arrays.toString(numbers));
		System.out.println("List: " + s1);

		System.out.println("\nAdding more numbers to same list\n");
		numbers = new int[rand.nextInt(5) + 1]; // Random length between 1 and 5
		for (int i = 0; i < numbers.length; i++) {
			int number = rand.nextInt(41) - 20;
			numbers[i] = number;
			s1.add(number);
		}

		System.out.println("Added " + Arrays.toString(numbers));
		System.out.println("List: " + s1);
		System.out.println("\nThe above list prints should show a sorted list.\n\n");

		System.out.println("\n******TESTING TOSTRING WITH CHARACTERS*******");
		char alpha[] = "abcdefghijklmnopqrstuvwxyz".toCharArray();

		SortedList<Character> s2 = new SortedList<Character>();

		char characters[] = new char[10];
		for (int i = 0; i < characters.length; i++) {
			char c = alpha[rand.nextInt(alpha.length)];
			characters[i] = c;
			s2.add(c);
		}

		System.out.println("\nAdded " + Arrays.toString(characters));
		System.out.println("List: " + s2);

		System.out.println("\nThe above list should be a sorted list of characters\n");

		System.out.println("\n******TESTING CONTAINS*******");

		s1 = new SortedList<Integer>();

		for (int i = 1; i <= 10; i++) {
			s1.add(i * 2);
		}

		System.out.println("List: " + s1 + "\n");
		System.out.println("Does the list have 6?\t" + s1.contains(6));
		System.out.println("Does the list have 20?\t" + s1.contains(20));
		System.out.println("Does the list have -10?\t" + s1.contains(-10));
		System.out.println("Does the list have 3?\t" + s1.contains(3));

		System.out.println("\nShould be \ntrue\ntrue\nfalse\nfalse");

		System.out.println("\n*******TESTING GET METHOD********");
		s1 = new SortedList<Integer>();

		for (int i = 1; i <= 10; i++) {
			s1.add(rand.nextInt(20));
		}

		System.out.println("\nUsing list: " + s1);
		System.out.println("Getting element at position 7: " + s1.get(7));
		try {
			System.out.println("\nTesting out of bounds index positive value.");
			s1.get(100);
			System.out.println("Did not throw an IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw an IndexOutOfBoundsException.  Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		try {
			System.out.println("\nTesting out of bounds index negative value.");
			s1.get(-100);
			System.out.println("Did not throw an IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw an IndexOutOfBoundsException.  Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		try {
			System.out.println("\nTesting EmptyCollectionException.");
			s1.clear();
			s1.get(0);
			System.out.println("Did not throw EmptyCollectionException.");
		} catch (EmptyCollectionException e) {
			System.out.println("Threw EmptyCollectionException. Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		System.out.println("\n*******TESTING REMOVEAT********");
		s1 = new SortedList<Integer>();

		for (int i = 1; i <= 10; i++) {
			s1.add(rand.nextInt(20));
		}

		System.out.println("\nUsing list: " + s1);
		s1.removeAt(3);
		System.out.println("\nRemove element at position 3: " + s1);
		s1.removeAt(0);
		System.out.println("\nRemove element at position 0: " + s1);
		s1.removeAt(s1.getSize() - 1);
		System.out.println("\nRemove element at position size - 1: " + s1);

		try {
			System.out.println("\nTesting out of bounds index positive value.");
			s1.removeAt(100);
			System.out.println("Did not throw an IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw an IndexOutOfBoundsException.  Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		try {
			System.out.println("\nTesting out of bounds index negative value.");
			s1.removeAt(-100);
			System.out.println("Did not throw an IndexOutOfBoundsException.");
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Threw an IndexOutOfBoundsException.  Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		try {
			System.out.println("\nTesting EmptyCollectionException.");
			s1.clear();
			s1.removeAt(0);
			System.out.println("Did not throw EmptyCollectionException.");
		} catch (EmptyCollectionException e) {
			System.out.println("Threw EmptyCollectionException. Pass.");
		} catch (Exception e) {
			System.out
					.println("Did not throw the correct exception.  Exception " + e.getClass().getName() + " thrown.");
		}

		System.out.println("\n******TESTING REMOVEALL (INTEGERS)*******");

		SortedList<Integer> s3 = new SortedList<Integer>();
		SortedList<Integer> s4 = new SortedList<Integer>();

		for (int i = 1; i <= 10; i++) {
			s3.add(rand.nextInt(10));
		}

		for (int i = 1; i <= rand.nextInt(15) + 1; i++) {
			s4.add(rand.nextInt(10));
		}

		System.out.println("Using lists \n\tlist1=" + s3 + "\n\tlist2=" + s4);
		s3.removeAll(s4);
		System.out.println("\nlist1.removeAll(list2)\n\tlist1=" + s3 + "\n\tlist2=" + s4);
		
		SortedList<Character> s5 = new SortedList<Character>();
		SortedList<Character> s6 = new SortedList<Character>();

		System.out.println("\n******TESTING REMOVEALL (CHARS)*******");		

		for (int i = 1; i <= 10; i++) {
			s5.add(alpha[rand.nextInt(alpha.length)]);
		}

		for (int i = 1; i <= rand.nextInt(15) + 1; i++) {
			s6.add(alpha[rand.nextInt(alpha.length)]);
		}

		System.out.println("Using lists \n\tlist1=" + s5 + "\n\tlist2=" + s6);
		s5.removeAll(s6);
		System.out.println("\nlist1.removeAll(list2)\n\tlist1=" + s5 + "\n\tlist2=" + s6);

		
		SortedList<Integer> s7 = new SortedList<Integer>();
		s7.add(2);
		s7.add(2);
		s7.add(2);
		s7.add(9);
		s7.add(9);
		SortedList<Integer> s8 = new SortedList<Integer>();
		s8.add(2);
		s8.add(9);
		System.out.println("Using lists \n\tlist1=" + s7 + "\n\tlist2=" + s8);
		s7.removeAll(s8);
		System.out.println("\nlist1.removeAll(list2)\n\tlist1=" + s7 + "\n\tlist2=" + s8);
 	}
}
