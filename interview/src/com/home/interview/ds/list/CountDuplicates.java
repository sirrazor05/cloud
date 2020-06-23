package com.home.interview.ds.list;

import java.util.HashSet;

public class CountDuplicates {
	
	//Given a linked list. The task is to count the number of duplicate nodes in the linked list.
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	
	// Function to count the number of 
	// duplicate nodes in the linked list 
	static int countDuplicateNode(Node head) { 
	    if (head == null) {
	    	return 0;
	    }
	  
	    // Create a hash table insert head 
	    HashSet<Integer> s = new HashSet<>(); 
	  
	    // Traverse through remaining nodes  
	    int count = 0; 
	    for (Node curr = head; curr != null; curr = curr.next)  { 
	        if (s.contains(curr.data)) {
	            count++;  
	        }
	        s.add(curr.data); 
	    } 
	  
	    // Return the count of duplicate nodes 
	    return count; 
	} 
	
	public static void main(String[] args) {
		
		Node a = new Node(1); 
        a.next = new Node(1); 
        a.next.next = new Node(3); 
        a.next.next.next = new Node(3); 
        a.next.next.next.next = new Node(4);
        
        System.out.println(countDuplicateNode(a));
	}

}
