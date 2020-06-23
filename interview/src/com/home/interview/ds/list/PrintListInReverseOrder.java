package com.home.interview.ds.list;

public class PrintListInReverseOrder {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void printReversed(Node node) {
		if(node != null) {
			printReversed(node.next);
			System.out.print(node.data +" ");
		}
	}
	
	
	public static void main(String[] args) {
		Node root = new Node(1); 
        root.next = new Node(2); 
        root.next.next = new Node(3); 
        root.next.next.next = new Node(4); 
        root.next.next.next.next = new Node(5);
        printReversed(root);
	}
}
