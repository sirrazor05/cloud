package com.home.interview.ds.list;

public class DeleteDuplicateFromSortedList {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void print(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
	}
	
	public static Node removeDuplicates(Node head) {
		Node current = head;
		
		while (current.next != null) {
			if (current.data == current.next.data) {
				current.next = current.next.next;
				continue;
			} else {
				current = current.next;
			}
		}
		return head;
	}
	
	
	public static void main(String[] args) {
		
		Node a = new Node(1); 
        a.next = new Node(1); 
        a.next.next = new Node(3); 
        a.next.next.next = new Node(3); 
        a.next.next.next.next = new Node(3);
        
        print(removeDuplicates(a));
		
	}

}
