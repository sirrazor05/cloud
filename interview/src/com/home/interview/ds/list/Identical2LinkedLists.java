package com.home.interview.ds.list;

public class Identical2LinkedLists {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static boolean identicalRec(Node a, Node b) {
		if(a == null && b == null) {
			return true;
		}
		if(a == null || b == null) {
			return false;
		}
		
		if(a.data != b.data) {
			return false;
		}
		
		return identicalRec(a.next, b.next);
		
	}
	
	
	public static void main(String[] args) {
		
		Node a = new Node(1); 
        a.next = new Node(2); 
        a.next.next = new Node(3); 
        a.next.next.next = new Node(4); 
        a.next.next.next.next = new Node(5);
        
        Node b = new Node(1); 
        b.next = new Node(2); 
        b.next.next = new Node(3); 
        b.next.next.next = new Node(4); 
        b.next.next.next.next = new Node(5);
        
        System.out.println(identicalRec(a, b));
		
	}

}
