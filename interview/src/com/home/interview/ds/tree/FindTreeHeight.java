package com.home.interview.ds.tree;

public class FindTreeHeight {
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static int getHeight(Node node) {
		if(node == null) {
			return 0;
		}
		return 1 + Math.max(getHeight(node.left), getHeight(node.right));
	}
	
	
	public static void main(String[] args) {
		Node root = new Node(4); 
        root.left = new Node(2); 
        root.right = new Node(5); 
        root.left.left = new Node(1); 
        root.left.right = new Node(3); 
        
        System.out.println(getHeight(root));
		
	}

}
