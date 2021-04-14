# CSC 385

- [Week 5 - Sorting Assignment](#week-5---sorting-assignment---difference-pairs)
- [Week 6 - Stack Assignment](#week-6---stack-assignment---maze-solving)
- [Week 7 - Sorted List Assignment](#week-7---sorted-list-assignment---sorted-list)

## Week 5 - Sorting Assignment - [Difference Pairs](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/DifferencePairs)

**Problem Definition:**

Given an array of integers, find all pairs of integers, a and b, where a – b is equal to a given number.
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

Consider a maze made up of rectangular array of squares, such as the following one:

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

## Week 7 - Sorted List Assignment - [Sorted List](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/SortedList)

**Problem Definition:** 

Implement a linked list that remains sorted. 
The linked list should be able to story any object if the object has implemented the Comparable interface.

    pubic class SortedLinkedList<T extends Comparable<? super T>>

The list should be kept in ascending order. 
The following methods must be implemented:
* **clear** - Should reset the list to be empty.
* **getSize** - Returns the size of the list.
* **toString** - Returns a string representation of the list.
* **add** - Adds a new object to the list in its sorted position.
* **removeAt** - Removes an item from a given index.
* **get** - Returns the item from a given position.
* **contains** - Checks if a value exists.
* **removeAll** - Takes another SortedList and removes its elements from the list.

**Problem Solution:**

See [*SortedList.java*](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/SortedList/src/SortedList.java).
The most complicated methods were **add** and **removeAll** because they required a lot of work with pointers.
To add in ascending order, we must consider four cases: 
1. When there are no elements in the list.
2. When there is **one** node in the list.
3. When we add the new node to **the end** of the list.
4. When we add the new node to **the start** of the list.

To remove all elements of one list from the current list, we must consider three cases:
1. When the current list item is **equal** to the other list item.
2. When the current list item is **less** than the other list item.
3. When the current list item is **bigger** than the other list item.

<details> <summary>Testing Code</summary>

    *****TESTING TOSTRING WITH INTEGERS*********

    Adding [-19, -14, -7, 14, 5, -1, 0, -5, 19, 20]
    List: [-19, -14, -7, -5, -1, 0, 5, 14, 19, 20]
    
    Adding more numbers to same list
    
    Added [5, 12, -18, 2]
    List: [-19, -18, -14, -7, -5, -1, 0, 2, 5, 5, 12, 14, 19, 20]
    
    The above list prints should show a sorted list.
    
    ******TESTING TOSTRING WITH CHARACTERS*******
    
    Added [f, j, k, d, v, l, n, l, k, d]
    List: [d, d, f, j, k, k, l, l, n, v]
    
    The above list should be a sorted list of characters

    ******TESTING CONTAINS*******
    List: [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
    
    Does the list have 6?	true
    Does the list have 20?	true
    Does the list have -10?	false
    Does the list have 3?	false
    
    Should be 
    true
    true
    false
    false
    
    *******TESTING GET METHOD********
    
    Using list: [0, 1, 3, 3, 6, 7, 8, 14, 16, 19]
    Getting element at position 7: 14
    
    Testing out of bounds index positive value.
    Threw an IndexOutOfBoundsException.  Pass.
    
    Testing out of bounds index negative value.
    Threw an IndexOutOfBoundsException.  Pass.
    
    Testing EmptyCollectionException.
    Threw EmptyCollectionException. Pass.
    
    *******TESTING REMOVEAT********
    
    Using list: [5, 9, 10, 13, 13, 14, 14, 14, 15, 19]
    
    Remove element at position 3: [5, 9, 10, 13, 14, 14, 14, 15, 19]
    
    Remove element at position 0: [9, 10, 13, 14, 14, 14, 15, 19]
    
    Remove element at position size - 1: [9, 10, 13, 14, 14, 14, 15]
    
    Testing out of bounds index positive value.
    Threw an IndexOutOfBoundsException.  Pass.
    
    Testing out of bounds index negative value.
    Threw an IndexOutOfBoundsException.  Pass.
    
    Testing EmptyCollectionException.
    Threw EmptyCollectionException. Pass.
    
    ******TESTING REMOVEALL (INTEGERS)*******
    Using lists 
        list1=[0, 1, 2, 3, 4, 5, 5, 6, 8, 9]
        list2=[0, 2, 4, 7, 9]
    
    list1.removeAll(list2)
        list1=[1, 3, 5, 5, 6, 8]
        list2=[0, 2, 4, 7, 9]
    
    ******TESTING REMOVEALL (CHARS)*******
    Using lists 
        list1=[a, a, n, p, p, p, p, t, t, z]
        list2=[e, e, h, i, v, x]
    
    list1.removeAll(list2)
        list1=[a, a, n, p, p, p, p, t, t, z]
        list2=[e, e, h, i, v, x]
    
    ******TESTING REMOVEALL (CORNER CASES)*******
    Using lists 
        list1=[2, 2, 2, 9, 9]
        list2=[2, 9]
    
    list1.removeAll(list2)
        list1=[]
        list2=[2, 9]
    
    Using lists 
        list1=[1, 2, 3, 4]
        list1=[1, 2, 3, 4]
    
    list1.removeAll(list2)
        list1=[]
        list1=[]

</details>

### Week 7 Lab

In [**List**](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/List)
we built a *Doubly Linked List* Data Structure. 
We wrote methods like *insert*, *remove*, *set*, *etc.*

<details> <summary>Lab Material</summary>
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

## Week 8 - Binary Trees - [IntegerSet](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/IntegerSet)

In 

### Week 8 Lab

In [**BinaryTree**](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/BinaryTree)
we built a *Binary Tree* Data Structure.
We wrote methods like *add*, *remove*, *levelOrderString*, and *postOrderString*.

<details> <summary>Lab Material</summary>

	public static void main(String[] args) {
		
		BinaryTree<String> bt = new BinaryTree<>();

		System.out.println("Height of the tree is " + bt.getHeight());
		
		bt.add("A");
		bt.add("B");
		bt.add("C");
		bt.add("D");
		bt.add("E");
		bt.add("F");
		
		System.out.println(bt);
		
		bt.remove("A");

		System.out.println(bt);
		
		bt.remove("F");
		
		System.out.println(bt);

	}

	Height of the tree is 2
	{ A B C D E F }
	{ F B C D E }
	{ E B C D }

</details>


## Week 9 - Binary Search Tree

### Week 9 Lab

In [**BinarySearchTree**](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/BinarySearchTree)
we built a *Binary Search Tree* Data Structure.
We wrote methods like *add*, *remove* (includes several helped methods), and *levelOrderString*.

We then modified the BST to serve as an AVL tree that balances its nodes to keep the access time of O(logN).
The methods implemented to convert the BST into AVL is include *rebalance*, *fixHeight*, *rightRotation*, *leftRotation*, and others.
By converting the tree into the AVL tree, we maintain it balanced throughout.

<details> <summary>Lab Material</summary>

	public static void main(String[] args) {
		
		System.out.println("Testing general Binary Search Tree");
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		bst.add(50);
		bst.add(25);
		bst.add(75);
		bst.add(12);
		bst.add(30);
		bst.add(65);
		bst.add(85);
		bst.add(2);
		System.out.println("Height of the BST: " + bst.getHeight());
		System.out.println("Level Order Traversal of the BST");
		System.out.println(bst);
		System.out.println("---------");
		
		System.out.println("Current size is " + bst.getSize());
		bst.add(25);
		System.out.println("After adding 25 again, the size is same " + bst.getSize());
		bst.remove(50);
		System.out.println("After removing 50, the BST is");
		System.out.println(bst);
		System.out.println("---------");
		
		System.out.println("Testing AVL Tree");
		BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
		for (int i = 1; i <= 1024; i++) {
			bst2.add(i);
		}
		System.out.println("Height of the tree: " + bst2.getHeight());
		System.out.println("Level Order Traversal of the AVL Tree");
		System.out.println(bst2);
		System.out.println("---------");
		
		System.out.println("After removing 4, 5, and 6");
		bst2.remove(4);
		bst2.remove(5);
		bst2.remove(6);
		System.out.println("Height of the tree: " + bst2.getHeight());
		System.out.println("Level Order Traversal of the AVL Tree");
		System.out.println(bst2);
		System.out.println();
	
	}	


	Testing general Binary Search Tree
	Height of the BST: 3
	Level Order Traversal of the BST
	{ (50 | 25 | 75 )  (25 | 12 | 30 )  (75 | 65 | 85 )  (12 | 2 |  )  (30 |  |  )  (65 |  |  )  (85 |  |  )  (2 |  |  )  }
	---------
	Current size is 8
	After adding 25 again, the size is same 8
	After removing 50, the BST is
	{ (65 | 25 | 75 )  (25 | 12 | 30 )  (75 |  | 85 )  (12 | 2 |  )  (30 |  |  )  (85 |  |  )  (2 |  |  )  }
	---------
	Testing AVL Tree
	Height of the tree: 10
	Level Order Traversal of the AVL Tree
	{ (512 | 256 | 768 )  (256 | 128 | 384 )  (768 | 640 | 896 )  (128 | 64 | 192 )  (384 | 320 | 448 )...
	---------
	After removing 4, 5, and 6
	Height of the tree: 10
	Level Order Traversal of the AVL Tree
	{ (512 | 256 | 768 )  (256 | 128 | 384 )  (768 | 640 | 896 )  (128 | 64 | 192 )  (384 | 320 | 448 )...

</details>
