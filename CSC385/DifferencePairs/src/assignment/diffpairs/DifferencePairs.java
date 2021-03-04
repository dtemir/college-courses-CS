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
		 * Finding pairs of elements in the array whose subtraction is equal to diff.
		 * The method first sorts the array.
		 * The method then uses two pointers to traverse the array based on their difference.
		 * If difference is lower than the required value, second pointer progresses.
		 * If difference is higher than the required value, first pointer progresses.
		 * If difference is the one required, both pointers progress. 
		 */
		array = sort(array); // sort the array
		ArrayUtils.printIntegerArray(array); // check if it's sorted
		
		if (array.length <= 1) { // corner case
			return null;
		}
		
		Pair[] temp_pairs = new Pair[array.length]; // temporary array for storing all pairs
		
		int first = 0; // first pointer
		int second = 1; // second pointer
		int num_pairs = 0; // number of pairs
		
		while (first < array.length ) {
			int difference = array[second] - array[first];
			// if needed diff is found, create a pair and increment both pointers
			if (difference == diff) {
				Pair pair = new Pair(array[first++], array[second++]);
				temp_pairs[num_pairs++] = pair;
			}
			// if needed diff is bigger, progress second pointer
			if (difference < diff) {
				second++;
			// if needed diff is smaller, progress first pointer
			} else if (difference > diff) {
				first++;
			}
						
			// check for ArrayOutOfBounds 
			if ((second >= array.length) || (first >= array.length)) {
				break;
			}
		}
		
		// Initialize and declare a new array of the required length
		Pair[] pairs = new Pair[num_pairs];
		for (int i = 0; i < num_pairs; i++) {
			pairs[i] = temp_pairs[i];
		}
		
		return pairs;
	}

}
