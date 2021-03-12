# CSC 385

## Week 5 - Sorting Assignment - [Difference Pairs](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/DifferencePairs)

**Problem Definition:**

Problem: Given an array of integers, find all pairs of integers, a and b, where a – b is equal to a given number.
For example, consider the following array and suppose we want to find all pairs of integers a and b where a – b = 3.

A = [10, 4, 6, 16, 1, 6, 12, 13]

Then your method should return the following pairs:

	4, 1
	16, 13
	13, 10 

**Problem Solution:**

The algorithm has an average efficiency of **O(nlogn)** as it uses *merge sort* to sort the array and *two pointers* to traverse the sorted array. The algorithm reaches that average efficiency by first sorting the array in ascending order and then traversing it while checking for the difference between **adjacent** elements.

The algorithm uses two pointers, **first** and **second**. If the difference between the elements to which the pointers point is **lower than the diff**, we can move the second pointer to compare its element against the first element. If the difference between the elements to which the pointers point is **higher than the diff**, we can move the first pointer to compare its element against the second element. Otherwise, we found a pair and we put it in the new array.

The average efficiency of finding all pairs from the sorted array is around **O(2n)** because we have **two pointers to traverse the array**, meaning that we will technically traverse it twice. 

Since the most complex part of the solution is sorting the array with merge sort, the **magnitude is O(nlogn)**, while a more precise average complexity would be O(nlogn + 2n), not including managing the Pair[] array.

## Week 6 - Stack Assignment - [Maze Solving](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/MazeSolving)

**Problem Definition:**

Problem: Consider a maze made up of rectangular array of squares, such as the following one:

<table>

<th> 

    INITIAL
    XXXXXXXXXXXXXXXXXXXX 
          X       X   X X
    X XXXXX X X XXXXX X X
    X   X   X X X X     X
    X XXX XXXXX X XXXXX X
    X     X             X
    X X XXXXX XXX XXXXX X
    X X   X     X   X X X
    X XXXXXXXXXXX XXX XXX
    X     X   X X   X   X
    XXXXX XXX X XXX XXX X
    X   X X   X     X   X
    X X X XXX XXX X XXX X
    X X     X X   X   X X
    X XXXXXXX XXXXXXX X X
    X             X X X X
    XXXXX XXX X XXX X X X
    X     X X X   X     X
    X XXX X XXXXXXXXX XXX
    X   X   X            
    XXXXXXXXXXXXXXXXXXXXX

</th> 

<th> 

    SOLUTION
    -XXXXXXXXXXXXXXXXXXXX
    --    X-----  X   X X
    X-XXXXX-X X-XXXXX X X
    X-  X---X X-X X     X
    X-XXX-XXXXX-X XXXXX X
    X-----X    ---      X
    X X XXXXX XXX-XXXXX X
    X X   X     X-  X X X
    X XXXXXXXXXXX-XXX XXX
    X     X   X X---X   X
    XXXXX XXX X XXX-XXX X
    X   X X   X    -X   X
    X X X XXX XXX X-XXX X
    X X     X X   X---X X
    X XXXXXXX XXXXXXX-X X
    X             X X-X X
    XXXXX XXX X XXX X-X X
    X     X X X   X  -  X
    X XXX X XXXXXXXXX-XXX
    X   X   X        ----
    XXXXXXXXXXXXXXXXXXXXX

</th>

</table>

The “X” blocks represent a blocked square and form the walls of the maze. 
Let us consider mazes that have only one entrance and one exit, as in our example. 
Beginning at the entrance at the top left side of the maze, find a path to the exit at the bottom right side. 
You can move only up, down, left, or right. Square is either clear or blocked by an X character. 
Let a two-dimensional array represent the maze. Use a stack data structure to find a path through the maze. 
Some mazes might have more than one successful path, while others might have no path.

**Problem Solution:** 

See [*MazeSolver.java*](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/MazeSolving/src/maze/MazeSolver.java). 
The algorithm uses a stack to keep the possible track of the previous moves in order to backtrack in case it gets to a dead end.

The algorithm iterates over all possible moves until the goal is reached or the stack is empty (meaning no more possible moves).

The key is to check all possible directions (up, down, left, right) on whether they have not been visited, and they are not walls (X symbol).
If the move has not been taken, add it to the stack and shift current to point to it.

Another key moment is to check that you put the extracted node back in the stack when backtracking
because if you find the right path, this element will be missing since you popped out of the stack.

## Week 7 

<details> 


In [**List**](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/List)
we built a *Doubly Linked List* Data Structure. 
We wrote methods like *insert*, *remove*, *set*, *etc.*

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

    [Out]
    [200, 10, 600, 20, 30, 40, 500, 50, 60, 70, 80, 90, 100]
    [10, 600, 20, 30, 40, 500, 50, 60, 70, 80, 90, 100]
    [10, 600, 20, 30, 40, 500, 50, 60, 70, 80, 90]
    [10, 600, 20, 40, 500, 50, 60, 70, 80, 90]
    [10, 600, 20, 40, 500, 50, 60, 80, 90]



</details>