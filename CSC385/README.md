# CSC 385

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

## Week 8 - Binary Trees

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


## Week 9 - Binary Search Tree - [IntegerSet](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/IntegerSet)

**Problem Definition:**

Create a new classes called an IntegerSet and complete the *toString*, *add*, *contains*, *union*, and *intersection* methods.

**Probel Solution:**

For the *toString* method we use level-order traversal with the application of queue. 
By simply putting elements from left to right in the queue we should get the level-order traversal.
For the *add* method we use the AVL tree add method to add elements according to their key and then rebalance the tree.
For the *contains* method we simply find the element using the property of the tree to search for elements in O(logN).
For the *union* method we simply iterate two trees and add each of their element to a new tree.
For the *intersection* method we iterate one tree, check if its element is in the other tree, and then add that element to a new tree.

<details> <summary> Testing Code </summary>

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

	Testing Add and Contains Methods

	Added { 5 4 6 7 } to the IntegerSet
	The resulting tree: { 4 5 6 7 }

	Added 8 to the IntegerSet
	The updated IntegerSet: { 4 5 6 7 8 }

	Checking if the IntegerSet contains 23: false
	-----------
	Testing IntegerSet constructor with an array { 40 23 80 23 0 100 }

	IntegerSet: { 0 23 40 80 100 }
	-----------
	Testing IntegerSet { 4 5 6 7 8 } intersection with another IntegerSet { 3 6 8 10 }

	Their intersection: { 6 8 }
	-----------
	Testing IntegerSet { 4 5 6 7 8 } union with with another IntegerSet { 3 6 8 10 }

	Their union: { 3 4 5 6 7 8 10 }
	-----------
	Provided tests

	IntegerSet { 1 2 3 4 }
	Contains 3? true
	Contains 6? false

	IntegerSet { 1 2 3 4 } intersects with { 3 4 5 6 7 }
	Intersection: { 3 4 }

	IntegerSet { 1 2 3 } unions with { 3 6 }
	Union: { 1 2 3 6 }

</details>

### Week 9 Lab

In [**BinarySearchTree**](https://github.com/dtemir/college-courses-CS/blob/master/CSC385/BinarySearchTree)
we built a *Binary Search Tree* Data Structure.
We wrote methods like *add*, *remove* (includes several helped methods), and *levelOrderString*.

We then modified the BST to serve as an AVL tree that balances its nodes to keep the access time of O(logN).
The methods implemented to convert the BST into AVL is include *rebalance*, *fixHeight*, *rightRotation*, *leftRotation*, and others.
By converting the tree into the AVL tree, we maintain it balanced throughout.

<details> <summary>Lab Material</summary>

	public static void main(String[] args) {

		// TEST 1 (Add and Contains)
		System.out.println("Testing Add and Contains Methods");
		System.out.println();

		IntegerSet is = new IntegerSet();
		is.add(5);
		is.add(4);
		is.add(6);
		is.add(7);
		System.out.println("Added { 5 4 6 7 } to the IntegerSet");
		System.out.println("The resulting tree: " + is);
		System.out.println();

		is.add(8);
		System.out.println("Added 8 to the IntegerSet");
		System.out.println("The updated IntegerSet: " + is);
		System.out.println();

		System.out.println("Checking if the IntegerSet contains 23: " + is.contains(23));
		System.out.println("-----------");

		// TEST 2 (Array Constructor)
		System.out.println("Testing IntegerSet constructor with an array { 40 23 80 23 0 100 }");
		System.out.println();

		int[] array = { 40, 23, 80, 23, 0, 100 };
		IntegerSet is2 = new IntegerSet(array);
		System.out.println("IntegerSet: " + is2);
		System.out.println("-----------");

		// TEST 3 (Intersection)
		System.out.println("Testing IntegerSet " + is + " intersection with another IntegerSet { 3 6 8 10 }");
		System.out.println();

		int[] array2 = { 3, 6, 8, 10 };
		IntegerSet is3 = new IntegerSet(array2);
		System.out.println("Their intersection: " + is.intersection(is3));
		System.out.println("-----------");

		// TEST 4 (Union)
		System.out.println("Testing IntegerSet " + is + " union with with another IntegerSet { 3 6 8 10 }");
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

## Week 12 - Hash Types - [Hashing](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/Hashing)

**Problem Definition:**

Given a collection of lists, use a HashSet to find the elements that are common in all lists, for simplicity let us assume that the lists have distinct items.

For example, consider the following collection of lists of integers:

	A1 = [3, 4, 9, 8, 12, 15, 7, 13]
	A2 = [15, 24, 50, 12, 3, 9]
	A3 = [78, 65, 24, 13, 9, 3, 12]
	A4 = [ 15, 78, 14, 3, 2, 9, 44, 12]

Then the common elements in this collection would be: **[3, 9, 12]**

**Problem Solution:**

Using Java's HashSet class.

We achieve the **O(kn)** complexity, where k is the number of lists and n is the maximum number of elements, by **using two HashSets** that store repeated values.

1. First, we put all elements of the very first list in **set A**. 
2. Then, we iterate through the rest of the lists. 
3. Each time we shift to a new list, we create a new **set B**. 
4. When going through the elements of that list, we see if each element is **contained in the set A**. 
5. If it is contained in the set A, we **add it to the set B**.
6. After each list, we **update set A** to point to set B. 
7. This way we make sure to **sift through the elements**, making sure that each element in the initial set can be encountered in the following lists.
8. Finally, we **copy down the elements from set A** to an ArrayList and return it.

<details> <summary>Testing Code</summary>

	public static void main(String args[]) {
	    	// Test 1: Integer Lists
	        List<List<Integer>> intLists = new LinkedList<>();

	        intLists.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
	        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
	        intLists.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
	        intLists.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));

	        List<Integer> intResult = findCommonElements(intLists);

	        System.out.println("Common elements of the integer list");
	        System.out.println(intResult + "\n");

	        // Test 2: String Lists
	        List<List<String>> stringLists = new LinkedList<>();

	        stringLists.add(new ArrayList<String>(Arrays.asList("a", "b", "d", "c", "h", "e")));
	        stringLists.add(new ArrayList<String>(Arrays.asList("g", "b", "e", "j", "u", "z", "h", "d")));
	        stringLists.add(new ArrayList<String>(Arrays.asList("y", "p", "b", "d")));

	        List<String> stringResult = findCommonElements(stringLists);

	        System.out.println("Common elements of the string list");
	        System.out.println(stringResult + "\n");
	        
	        // Test 3: One Integer List
	        List<List<Integer>> singleList = new LinkedList<>();
	        
	        singleList.add(new ArrayList<Integer>(Arrays.asList(3, 4, 9, 8, 12, 15, 7, 13)));
	        
	        List<Integer> singleResult = findCommonElements(singleList);
	        
	        System.out.println("Common elements of the single list");
	        System.out.println(singleResult + "\n");
	        
	        // Test 4: No Common Elements
	        List<List<Integer>> noCommonsList = new LinkedList<>();

	        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(4, 8, 15, 7, 13)));
	        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(15, 24, 50, 12, 3, 9)));
	        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(78, 65, 24, 13, 9, 3, 12)));
	        noCommonsList.add(new ArrayList<Integer>(Arrays.asList(15, 78, 14, 3, 2, 9, 44, 12)));

	        List<Integer> noCommonsResult = findCommonElements(noCommonsList);

	        System.out.println("No common elements in the integer list");
	        System.out.println(noCommonsResult + "\n");
	        
	        // Test 5: Empty Collection
	        
	        List<List<Integer>> emptyList = new LinkedList<>();
	        
	        List<Integer> emptyResult = findCommonElements(emptyList);
	        
	        System.out.println("Empty collection");
	        System.out.println(emptyResult + "\n");
	        
	}

	
	Common elements of the integer list
	[3, 9, 12]

	Common elements of the string list
	[b, d]

	Common elements of the single list
	[3, 4, 7, 8, 9, 12, 13, 15]

	No common elements in the integer list
	[]

	Empty collection
	[]

</details>

## Final Assignment - Getting Reverse Crown of BST - [Final](https://github.com/dtemir/college-courses-CS/tree/master/CSC385/Final)

**Problem Definiton:**

The crown of a node is all the leaf nodes that fall under that given node.
Given an AVLTree, write a method that returns a list of the crown of a given element in descending sorted order (reverse crown of the element).

Suppose the following elements are added to the tree from left to right.

	[25, 30, 50, 65, 75, 80, 85]
	The crown of 65 is [25, 50, 75, 85]
	The reverse crown of 65 is just [85, 75, 50, 25].

**Problem Solution:**

We should just use a recursive approach to find nodes in the tree that do not have children on the left and right.

<details> <summary> Code Solution </summary>

	public List<T> getReverseCrown(T data) {		
		Node topNode = searchForNode(root, data);		
		
		List<T> listToReturn = new LinkedList<>(); // initialize the list to pass by reference		
		getReverseCrown(topNode, listToReturn); // call the solution and pass the list
		
		return listToReturn;
	}

	/*
	 * Adds all the leaves of a given node to the passed by reference list 
	 */
	private void getReverseCrown (Node current, List<T> listToReturn) {
		if (current.left == null && current.right == null) { // leaf case: add it to the list
			listToReturn.add(current.data);
		}
		
		// ESSENTIAL: right must come first and left come second to get reverse list
		if (current.right != null) { // check right sub-tree
			getReverseCrown(current.right, listToReturn);
		}
		
		if (current.left != null) { // check left sub-tree
			getReverseCrown(current.left, listToReturn);
		}
	}
	
	/*
	 * Looks for the node that carries the given data in getReverseCrown
	 */
	private Node searchForNode(Node current, T data) {		
		if (current == null) { // couldn't find a node with the data
			throw new NoSuchElementException("No such node in the tree");
		}
		
		int res = current.data.compareTo(data);
		if (res == 0) { // found a node with the data
			return current;
		}
		if (res < 0) { // check left sub-tree
			return searchForNode(current.right, data);
		}
		
		return searchForNode(current.left, data); // check right sub-tree		
	}

</details>
