package assignment.utility;

import java.util.Arrays;

public class ArrayUtils {

	public static void printIntegerArray(int arr[]) {
		if (arr == null) {
			System.out.println("null");
			return;
		}
		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
	}

	public static void printObjectArray(Object arr[]) {
		if (arr == null) {
			System.out.println("null");
			return;
		}
		System.out.print("[ ");
		for (int i = 0; i < arr.length; i++) {
			Object obj = arr[i];
			System.out.print(obj.getClass().getName() + obj);
			if (i < arr.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println(" ]");
	}

	/**
	 * This method takes a T array and the number of elements to truncate up to.
	 * The result is a new T array with elements up to, but not including, count.
	 * An example:
	 * 
	 * <pre>
	 * 		arr[] = [1,2,3,4,5,6]
	 * 		truncateArray(arr, 3);
	 * 		result: [1,2,3]
	 * </pre>
	 * 
	 * @param arr   The array to truncate.
	 * @param count The number of elements to keep.
	 * @return A T array that is the truncated version of arr.
	 */
	public static <T> T[] truncateArray(T arr[], int count) {
		if (arr == null) {
			throw new IllegalArgumentException("Truncate Array: The integer array must not be null");
		}
		if (count > arr.length || count < 0) {
			throw new IllegalArgumentException("Count must be between 0 and the array length (inclusive).");
		}
		return Arrays.copyOf(arr, count);
	}
}
