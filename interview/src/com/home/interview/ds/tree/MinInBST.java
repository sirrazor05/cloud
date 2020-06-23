package com.home.interview.ds.tree;

public class MinInBST {
	/*
	 * Find the node with minimum value in a Binary Search Tree
		This is quite simple. Just traverse the node from root to left recursively until left is NULL. The node whose left is NULL is the node with minimum value. 
	 */
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	static int minValue(Node node) { 
        Node current = node; 
        while (current.left != null) { 
            current = current.left; 
        } 
        return current.data; 
    } 
	
	static int findMin(Node node) {
		if(node.left == null) {
			return node.data;
		}
		return findMin(node.left);
	}

	public static void main(String[] args) {
		Node root = new Node(4); 
        root.left = new Node(2); 
        root.right = new Node(5); 
        root.left.left = new Node(1); 
        root.left.right = new Node(3); 
        
        System.out.println(findMin(root));
        System.out.println(minValue(root));

	}

}
