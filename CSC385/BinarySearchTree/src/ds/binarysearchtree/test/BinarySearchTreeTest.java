package ds.binarysearchtree.test;

import ds.binarysearchtree.BinarySearchTree;

public class BinarySearchTreeTest {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<>();
		
		bst.add(50);
		bst.add(25);
		bst.add(75);
		bst.add(12);
		bst.add(30);
		bst.add(65);
		bst.add(85);
		
		System.out.println("Level Order Traversal of the inital BST");
		System.out.println(bst);
		System.out.println();
		
		System.out.println("Current size is " + bst.getSize());
		bst.add(25);
		System.out.println("After adding 25 again, the size is same " + bst.getSize());
		System.out.println();
		
		bst.remove(50);
		System.out.println("BST after removing node with key 50");
		System.out.println(bst);
	}

}
