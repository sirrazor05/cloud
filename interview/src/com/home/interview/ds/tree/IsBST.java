package com.home.interview.ds.tree;

public class IsBST {
	
	/*  Method1
	 *  1) Do In-Order Traversal (Left, Root, Right) of the given tree and store the result in a temp array.
		3) Check if the temp array is sorted in ascending order, if it is, then the tree is BST.
		Optimization: keep track of previows node and check that the current is grater
		
		Time Complexity: O(n)
	 */
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	static Node prev = null;
	
	static boolean isBST1(Node node) {
		if(node != null) {
			if(!isBST1(node.left)) {
				return false;
			};
			
			if(prev != null && node.data <= prev.data) {
				return false;
			}
			prev = node;
			return isBST1(node.right);
		}
		return true;
	}
		
	public static void main(String[] args) {
		Node root = new Node(4); 
        root.left = new Node(2); 
        root.right = new Node(5); 
        root.left.left = new Node(1); 
        root.left.right = new Node(3); 
        
        System.out.println(isBST1(root));
		
		
	}
}
