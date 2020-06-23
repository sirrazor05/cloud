package com.home.interview.ds.list;

public class MergeTwoSortedLinkedLists {
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	static Node merged = null;
	static Node temp = null;
	
	public static void insert(int data) {
		if(merged == null) {
			merged = new Node(data);
		}else {
			if(temp == null) {
				temp = new Node(data);
				merged.next = temp;
			} else {
				temp.next = new Node(data);
				temp = temp.next;
			}
		}
	}
	
	public static void merge(Node a, Node b) {
		while (a != null && b != null) {
			if(a.data < b.data) {
				insert(a.data);
				a = a.next;
			} else {
				insert(b.data);
				b = b.next;
			}
		}
		while (a != null) {
			insert(a.data);
			a = a.next;
		}
		while (b != null) {
			insert(b.data);
			b = b.next;
		}
	}
	
	public static Node mergeRec(Node a, Node b) {
		if(a == null) return b; 
        if(b == null) return a; 
		
		if(a.data < b.data) {
			a.next =  mergeRec(a.next, b);
			return a;
		}else {
			b.next =  mergeRec(a, b.next);
			return b;
		}
	}
	
	public static void print(Node head) {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
	}
	
	public static void main(String[] args) {
		
		Node a = new Node(2); 
        a.next = new Node(4); 
        a.next.next = new Node(8); 
        a.next.next.next = new Node(16); 
        a.next.next.next.next = new Node(55);
        
        Node b = new Node(1); 
        b.next = new Node(3); 
        b.next.next = new Node(14); 
        b.next.next.next = new Node(17); 
        b.next.next.next.next = new Node(54);
        
        //merge(a, b);
        //print(merged);
        
        Node head = mergeRec(a, b);
        print(head);
	}

}
