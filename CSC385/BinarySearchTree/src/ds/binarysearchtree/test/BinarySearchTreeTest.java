package ds.binarysearchtree.test;

import ds.binarysearchtree.BinarySearchTree;

public class BinarySearchTreeTest {

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
		
		
//		
//		System.out.println("Second BST");
//		
//		BinarySearchTree<Integer> bst2 = new BinarySearchTree<>();
//		
//		for (int i = 0; i < 100; i++) {
//			bst2.add(i*10);
//		}
//		
//		System.out.println(bst2.getHeight());
	}

}
