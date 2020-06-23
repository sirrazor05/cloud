package com.home.interview.ds.list;

public class LoopDetection {
	
	/**
	 * Floyd’s Cycle-Finding Algorithm: This is the fastest method and has been described below:

	    Traverse linked list using two pointers. 
	    Move one pointer(slow_p) by one and another pointer(fast_p) by two. 
	    If these pointers meet at the same node then there is a loop. If pointers do not meet then linked list doesn’t have a loop

	 */
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	
	// Function that detects loop in the list 
    public static boolean detectLoop(Node node){
        Node slow = node, fast = node; 
        while (slow != null && fast != null && fast.next != null) { 
            slow = slow.next; 
            fast = fast.next.next; 
  
            // If slow and fast meet at same point then loop is present 
            if (slow == fast) { 
                return true; 
            } 
        } 
        return false; 
    } 
	
	
	public static void main(String[] args) {
		
		Node a = new Node(1); 
        a.next = new Node(2); 
        a.next.next = new Node(3); 
        a.next.next.next = new Node(4); 
        a.next.next.next.next = new Node(5);
        a.next.next.next.next.next = a;
        
        System.out.println(detectLoop(a));
	}

}
