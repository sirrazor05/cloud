package com.home.interview.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstTraversal {
	
	//Level order traversal of a tree is breadth first traversal for the tree.
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void BFT(Node root) {
		 Queue<Node> queue = new LinkedList<Node>();
		 if(root != null) {
			 queue.add(root);
		 }
		 while(!queue.isEmpty()) {
			 Node n = queue.poll();
			 System.out.print(n.data + " ");
			 if(n.left != null) {
				 queue.add(n.left);
			 }
			 if(n.right != null) {
				 queue.add(n.right);
			 }
		 }
	}
	
	public static void main(String[] args) {
		Node root = new Node(1); 
        root.left = new Node(2); 
        root.right = new Node(3); 
        root.left.left = new Node(4); 
        root.left.right = new Node(5); 
        BFT(root);
	}

}
