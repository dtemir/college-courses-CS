package assignment.diffpairs;

import assignment.utility.ArrayUtils;

public class DifferencePairs {
	
	// Implement your sorting algorithm here.  Must be either
	//  * merge sort
	//  * quick sort
	//  * radix sort
	private static int[] sort(int arr[]) {
		if (arr.length <= 1) { // base case for the recursive call
			return arr;
		}
		
		int leftLength = arr.length / 2; // left sub-array
		int rightLength = (arr.length % 2 == 0) ? leftLength : leftLength + 1; // right sub-array
		
		int left[] = new int[leftLength];
		int right[] = new int[rightLength];
		
		int j = 0;
		for (int i = 0; i < leftLength; i++) { // copy into left sub-array
			left[i] = arr[j++];
		}
		for (int i = 0; i < rightLength; i++) { // copy into right sub-array
			right[i] = arr[j++];
		}
		
		return merge(sort(left), sort(right));
	}
	
	private static int[] merge(int[] left, int[] right) {
		int mergeResult[] = new int[left.length + right.length];
		
		int lIdx = 0;
		int rIdx = 0;
		int mIdx = 0;
		
		while (lIdx < left.length && rIdx < right.length) {
			if (left[lIdx] <= right[rIdx]) {
				mergeResult[mIdx++] = left[lIdx++];
			} else {
				mergeResult[mIdx++] = right[rIdx++];
			}
		}
		
		while (lIdx < left.length) {
			mergeResult[mIdx++] = left[lIdx++];
		}
		
		while (rIdx < right.length) {
			mergeResult[mIdx++] = right[rIdx++];
		}
		
		return mergeResult;
	}

	public static Pair[] findPairs(int array[], int diff) {
		/*
		 * Finding pairs of elements in the array whose subtraction is equal to the diff
		 * The algorithm uses two pointers: first for keeping the starting element,
		 * second for keeping the current element
		 * The algorithm uses sorting to skip over elements that have 
		 * already been used as the starting element, skipping over the large portions 
		 */
		array = sort(array); // sort the array
		ArrayUtils.printIntegerArray(array); // check if it's sorted
		
		if (array.length <= 1) {
			return null;
		}
		
		int start = 0; // first pointer for the base element
		int current = 1; // second pointer for traversing the rest of the array
		int prev = array[start]; // storing the previous elements to not check the same element 
		
		while (start < array.length) { // iterate the whole array O(n)
			
			while (current < array.length) { // iterate the smaller part of the array O(logn)
				// System.out.println(start + " " + current);
				int difference = array[current] - array[start];
				if (difference == diff) {
					System.out.println("Pair found " + array[start] + " " + array[current]);
					break;
				}
				current++;
			}
			prev = array[start];
			start++;
			current = start + 1;
			
			while ((start < array.length) && (array[start] == prev)) {
				start++;
				current++;
			}
			
		}
		
		return null;
	}

}
