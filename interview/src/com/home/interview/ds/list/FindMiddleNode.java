package com.home.interview.ds.list;


public class FindMiddleNode {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static void printMiddle(Node head) {
		Node pSlow = head;
		Node pFast = head;
		while (pFast != null && pFast.next != null) {
			pSlow = pSlow.next;
			pFast = pFast.next.next;
		}
		System.out.println(pSlow.data);
	}
	
	
	public static void main(String[] args) {
		Node a = new Node(1); 
        a.next = new Node(2); 
        a.next.next = new Node(7); 
        a.next.next.next = new Node(10); 
        a.next.next.next.next = new Node(1);
        a.next.next.next.next.next = new Node(6);
        a.next.next.next.next.next.next = new Node(3);
        printMiddle(a);
	}

}
