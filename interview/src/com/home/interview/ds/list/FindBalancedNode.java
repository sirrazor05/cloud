package com.home.interview.ds.list;

public class FindBalancedNode {
	/*
	 *  Input: 1 -> 2 -> 7 -> 10 -> 1 -> 6 -> 3 -> NULL
		Output: 10
		Sum of nodes on the left of 10 is 1 + 2 + 7 = 10
		And, to the right of 10 is 1 + 6 + 3 = 10
	 * Approach:

    First, find the total sum of the all node values.
    Now, traverse the linked list one by one and while traversing keep track of all the previous nodes 
    value sum and find the sum of the remaining node by subtracting current node value and the sum of the previous nodes value from the total sum.
    Compare both the sums, if they are equal then current node is the required node else print -1.

	 */
	
	static class Node {
		int data;
		Node next;

		Node(int data) {
			this.data = data;
		}
	}
	
	public static int findBalancedNode(Node a) {
		if(a == null || a.next == null) {
			return -1;
		}
		
		int tSum = 0;
		Node curr_node = a;
		
		while(curr_node != null) {
			tSum = tSum + curr_node.data;
			curr_node = curr_node.next;
		}
		
		int current_sum = 0;
		int remaining_sum = 0;
		
		curr_node = a;
		while(curr_node != null) {
			remaining_sum = tSum - (current_sum + curr_node.data);
			if(current_sum == remaining_sum) {
				return curr_node.data;
			}
			current_sum += curr_node.data;
			curr_node = curr_node.next;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
		Node a = new Node(1); 
        a.next = new Node(2); 
        a.next.next = new Node(7); 
        a.next.next.next = new Node(10); 
        a.next.next.next.next = new Node(1);
        a.next.next.next.next.next = new Node(6);
        a.next.next.next.next.next.next = new Node(3);
        
        System.out.println(findBalancedNode(a));
		
	}

}
