package com.home.interview.ds.list;

public class ReverseALinkedList {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static Node reverse(Node head) {
		Node current = head;
		Node prev = null;
		Node next = null;
		while(current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
		}
		return prev;
	}
	
	public static void print(Node head) {
		Node current = head;
		while(current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		
		Node root = new Node(1); 
        root.next = new Node(2); 
        root.next.next = new Node(3); 
        root.next.next.next = new Node(4); 
        root.next.next.next.next = new Node(5);
        
        print(root);
        System.out.println();
        print(reverse(root));
		
	}

}
