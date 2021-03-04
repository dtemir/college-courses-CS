# CSC 385

## Week 5 - Sorting Assignment

**Problem Definition:**

Problem: Given an array of integers, find all pairs of integers, a and b, where a – b is equal to a given number.
For example, consider the following array and suppose we want to find all pairs of integers a and b where a – b = 3
A = [10, 4, 6, 16, 1, 6, 12, 13]

Then your method should return the following pairs:

	* 4, 1
	* 16, 13
	* 13, 10 

**Problem Solution:**

The algorithm has an average efficiency of **O(nlogn)** as it uses *merge sort* to sort the array and *two pointers* to traverse the sorted array. The algorithm reaches that average efficiency by first sorting the array in ascending order and then traversing it while checking for the difference between **adjacent** elements.

The algorithm uses two pointers, **first** and **second**. If the difference between the elements to which the pointers point is **lower than the diff**, we can move the second pointer to compare its element against the first element. If the difference between the elements to which the pointers point is **higher than the diff**, we can move the first pointer to compare its element against the second element. Otherwise, we found a pair and we put it in the new array.

The average efficiency of finding all pairs from the sorted array is around **O(2n)** because we have **two pointers to traverse the array**, meaning that we will technically traverse it twice. 

Since the most complex part of the solution is sorting the array with merge sort, the **magnitude is O(nlogn)**, while a more precise average complexity would be O(nlogn + 2n), not including managing the Pair[] array.

