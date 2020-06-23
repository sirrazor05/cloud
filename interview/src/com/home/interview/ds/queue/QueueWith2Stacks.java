package com.home.interview.ds.queue;

import java.util.Stack;

public class QueueWith2Stacks {
	
	Stack<Integer> in = new Stack<Integer>();
	Stack<Integer> out = new Stack<Integer>();
	
	public void enqueue(Integer data) {
		in.add(data);
	}
	
	public Integer dequeue() {
		if(out.isEmpty()) {
			while(!in.isEmpty()) {
				Integer v = in.pop();
				out.push(v);
			}
		}
		return out.pop();
	}

	public static void main(String[] args) {
		QueueWith2Stacks queue = new QueueWith2Stacks();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		
		System.out.println(queue.dequeue());
		
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		
	}

}
