package com.home.interview.ds.tree;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertTreeToItsMirrorRec {

	
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	public static void mirror(Node root) {
		if (root == null) {
	        return; 
		}
		 Queue<Node> q = new LinkedList<>(); 
		 q.add(root); 
		  
	    // Do BFS. While doing BFS, keep swapping 
	    // left and right children 
	    while (q.size() > 0) 
	    { 
	        // pop top node from queue 
	        Node curr = q.peek(); 
	        q.remove(); 
	  
	        // swap left child with right child 
	        Node temp = curr.left; 
	        curr.left = curr.right; 
	        curr.right = temp;
	  
	        // push left and right children 
	        if (curr.left != null) { 
	            q.add(curr.left); 
	        }
	        if (curr.right != null) {
	            q.add(curr.right); 
	        }
	    } 
		
	}
	
	
	static void inOrder(Node node) { 
	        if (node == null) 
	            return; 
	        inOrder(node.left); 
	        System.out.print(node.data + " "); 
	  
	        inOrder(node.right); 
	} 
	
	public static void main(String[] args) {
		
		Node root = new Node(4); 
        root.left = new Node(2); 
        root.right = new Node(5); 
        root.left.left = new Node(1); 
        root.left.right = new Node(3);
        inOrder(root);
        mirror(root);
        
        System.out.println();
        
        inOrder(root);
	}
}
