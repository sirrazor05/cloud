package com.home.interview.ds.tree;

public class CheckIdenticalTrees {
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static boolean sameTree(Node a, Node b) {
		/*1. both empty */
		if(a == null && b == null) {
			return true;
		}
		/* 2. both non-empty -> compare them */
		if (a != null && b != null) {
			return a.data == b.data && sameTree(a.left, b.left) && sameTree(a.right, b.right);
		}
		/* 3. one empty, one not -> false */
		return false;
		
	}
	
	
	public static void main(String[] args) {
		
		Node a = new Node(4); 
        a.left = new Node(2); 
        a.right = new Node(5); 
        a.left.left = new Node(1); 
        a.left.right = new Node(3); 
        a.left.right.right = new Node(6); 
        
        
        Node b = new Node(4); 
        b.left = new Node(2); 
        b.right = new Node(5); 
        b.left.left = new Node(1); 
        b.left.right = new Node(3); 
        b.left.right.right = new Node(6); 
        
        System.out.println(sameTree(a, b));
		
	}

}
