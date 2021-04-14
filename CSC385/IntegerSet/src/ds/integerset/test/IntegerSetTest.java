package ds.integerset.test;

import ds.integerset.IntegerSet;

public class IntegerSetTest {

	public static void main(String[] args) {

		// TEST 1 (Add and Contains)
		System.out.println("Testing Add and Contains Methods");
		System.out.println();

		IntegerSet is = new IntegerSet();
		is.add(5);
		is.add(4);
		is.add(6);
		is.add(7);
		System.out.println("Added {5, 4, 6, 7} to the IntegerSet");
		System.out.println("The resulting tree: " + is);
		System.out.println();

		is.add(8);
		System.out.println("Added 8 to the IntegerSet");
		System.out.println("The updated IntegerSet: " + is);
		System.out.println();

		System.out.println("Checking if the IntegerSet contains 23: " + is.contains(23));
		System.out.println("-----------");

		// TEST 2 (Array Constructor)
		System.out.println("Testing IntegerSet constructor with an array {40, 23, 80, 23, 0, 100}");
		System.out.println();

		int[] array = { 40, 23, 80, 23, 0, 100 };
		IntegerSet is2 = new IntegerSet(array);
		System.out.println("IntegerSet: " + is2);
		System.out.println("-----------");

		// TEST 3 (Intersection)
		System.out.println("Testing IntegerSet " + is + " intersection with another IntegerSet { 6 3 8 10 }");
		System.out.println();

		int[] array2 = { 3, 6, 8, 10 };
		IntegerSet is3 = new IntegerSet(array2);
		System.out.println("Their intersection: " + is.intersection(is3));
		System.out.println("-----------");

		// TEST 4 (Union)
		System.out.println("Testing IntegerSet " + is + " union with with another IntegerSet { 6 3 8 10 }");
		System.out.println();

		System.out.println("Their union: " + is.union(is3));
		System.out.println("-----------");

		// TEST 5 (Provided tests)
		System.out.println("Provided tests");
		System.out.println();

		int[] array3 = { 2, 3, 4, 1 };
		IntegerSet iS1 = new IntegerSet(array3);
		System.out.println("IntegerSet " + iS1);
		System.out.println("Contains 3? " + iS1.contains(3)); // returns true
		System.out.println("Contains 6? " + iS1.contains(6)); // returns false
		System.out.println();

		int[] array4 = { 5, 7, 6, 3, 4 };
		IntegerSet iS2 = new IntegerSet(array4);
		System.out.println("IntegerSet " + iS1 + " intersects with " + iS2);
		System.out.println("Intersection: " + iS1.intersection(iS2));
		System.out.println();

		int[] array5 = { 2, 1, 3 };
		int[] array6 = { 3, 6 };
		IntegerSet iS3 = new IntegerSet(array5);
		IntegerSet iS4 = new IntegerSet(array6);
		System.out.println("IntegerSet " + iS3 + " unions with " + iS4);
		System.out.println("Union: " + iS3.union(iS4));

	}

}
