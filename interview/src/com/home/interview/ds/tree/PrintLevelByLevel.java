package com.home.interview.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintLevelByLevel {
	/*
	 * q1 - stores the current level nodes
	 * q2 - current level nodes' children
	 * When the first queue is emptied, we know that it must have reached the end of the current level,
	 * therefore we print a new line. Then we switch the emptied first queue with the second queue
	 * (which is populated with next level's nodes). Then we repeat the process.
	 */
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void printLevelOrder(Node root) {
		 Queue<Node> q1 = new LinkedList<Node>();
		 Queue<Node> q2 = new LinkedList<Node>();
		 if(root !=null) {
			 q1.add(root);
		 }
		 while(!q1.isEmpty()) {
			 Node currentNode = q1.poll();
			 if(currentNode != null) {
				 System.out.print(currentNode.data + " ");
				 q2.add(currentNode.left);
				 q2.add(currentNode.right);
			 }
			 if(q1.isEmpty()) {
				 System.out.println();
				 q1.addAll(q2);
				 q2.clear();
			 }
		 }
	}

	public static void main(String[] args) {
		Node root = new Node(1); 
        root.left = new Node(2); 
        root.right = new Node(3); 
        root.left.left = new Node(4); 
        root.left.right = new Node(5); 
        root.right.left = new Node(9); 
        root.right.right = new Node(10); 
        root.right.right.left = new Node(11); 
        printLevelOrder(root);
	}
}
