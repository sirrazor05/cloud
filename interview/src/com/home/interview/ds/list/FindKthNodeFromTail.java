package com.home.interview.ds.list;

import com.home.interview.ds.list.FindMiddleNode.Node;

public class FindKthNodeFromTail {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	/**
	 * Maintain two pointers – reference pointer and main pointer.
	 *  Initialize both reference and main pointers to head. 
	 *  First, move reference pointer to n nodes from head. 
	 *  Now move both pointers one by one until the reference pointer reaches the end.
	 *  Now the main pointer will point to nth node from the end. Return the main pointer.
	 */
	
	
	public static void printNthFromLast(Node head, int k) {
		if(head == null || k == 0) {
			System.out.println("The head is null or k = 0 !");
			return;
		}
		
		Node main = head;
		Node ref = head;
		int count = 0;
		
		while(count < k) {
			if(ref == null) {
				System.out.println(k + " is greater than the no of nodes in the list");
				return;
			}
			
			ref = ref.next;
			count++;
		}
		while(ref != null) {
			main = main.next;
			ref = ref.next;
		}
		System.out.println("Node no. " + k + " from last is " + main.data); 
	}
	
	

	public static void main(String[] args) {
		Node a = new Node(1); 
        a.next = new Node(2); 
        a.next.next = new Node(7); 
        a.next.next.next = new Node(10); 
        a.next.next.next.next = new Node(1);
        a.next.next.next.next.next = new Node(6);
        a.next.next.next.next.next.next = new Node(3);
        
        printNthFromLast(a, 4);

	}

}
