package ds.binarytree.test;

import ds.binarytree.BinaryTree;

public class BinaryTreeTest {
	
	public static void main(String[] args) {
		
		BinaryTree<String> bt = new BinaryTree<>();
		
		bt.add("A");
		bt.add("B");
		bt.add("C");
		bt.add("D");
		bt.add("E");
		bt.add("F");
		
		System.out.println("Height of the tree is " + bt.getHeight());
		
		System.out.println(bt);
		
		bt.remove("A");

		System.out.println(bt);
		
		bt.remove("F");
		
		System.out.println(bt);

	}
}
