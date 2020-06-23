package com.home.interview.ds.tree;

public class ConvertTreeToItsMirrorIter {

	/*
	 * Algorithm – Mirror(tree) Method 1 :

	(1)  Call Mirror for left-subtree    i.e., Mirror(left-subtree)
	(2)  Call Mirror for right-subtree  i.e., Mirror(right-subtree)
	(3)  Swap left and right subtrees.
	          temp = left-subtree
	          left-subtree = right-subtree
	          right-subtree = temp
	 */
	
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	public static Node mirror(Node node) {
		 if (node == null) { 
	         return node;
		 }
		 
		/* do the subtrees */
        Node left = mirror(node.left); 
        Node right = mirror(node.right); 
  
        /* swap the left and right pointers */
        node.left = right; 
        node.right = left; 
  
        return node; 
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
