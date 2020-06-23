package com.home.interview.ds.tree;


public class SerDeSer {
	
	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void serialize(Node node, StringBuilder str) {
		if(node == null) {
			str.append("$,");
			return;
		}
		str.append(node.data +",");
		serialize(node.left, str);
		serialize(node.right, str);
	}
	
	// Decodes your encoded data to tree.
	public static Node deserialize(String data) {
	    if(data == null) {
	        return null;
	    }
	 
	    //use array to pass by reference and change value along recursion calls
	    int[] t = {0};
	    String[] arr = data.split(",");
	 
	    return helper(arr, t);
	}
	 
	public static Node helper(String[] arr, int[] t){
	    if(arr[t[0]].equals("$")){
	        return null;
	    }
	    Node root = new Node(Integer.parseInt(arr[t[0]]));
	 
	    t[0] = t[0] + 1;
	    root.left = helper(arr, t);
	    t[0] = t[0] + 1;
	    root.right = helper(arr, t);
	    return root;
	}
	
	static void preOrder(Node node) { 
        if (node == null) { 
            return; 
        }
        
        System.out.print(node.data + " "); 
        preOrder(node.left); 
        preOrder(node.right); 
} 

	public static void main(String[] args) {
		Node root = new Node(1); 
        root.left = new Node(2); 
        root.right = new Node(3); 
        root.left.left = new Node(4); 
        root.left.right = new Node(5);
        
        
        StringBuilder str = new StringBuilder();
        serialize(root, str);
        
        System.out.println(str.toString());
        
        Node rootDeser = deserialize(str.toString());
        preOrder(rootDeser);
	}

}
