package com.home.interview.ds.stack;

import java.util.Stack;

public class MinStack {
	
	Stack<Integer> dataSt = new Stack<Integer>();
	Stack<Integer> minSt = new Stack<Integer>();
	
	public void push(Integer data) {
		dataSt.push(data);
		if(minSt.isEmpty() || data < minSt.peek()) {
			minSt.push(data);
		}else {
			minSt.push(minSt.peek());
		}
	}
	
	public Integer pop() {
		minSt.pop();
		return dataSt.pop();
	}
	
	public Integer min() {
		return minSt.peek();
	}
	

	public static void main(String[] args) {
		MinStack stack = new MinStack();
		stack.push(5);
		stack.push(4);
		stack.push(9);
		stack.push(47);
		stack.push(2);
		stack.push(8);
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
		stack.pop();
		System.out.println(stack.min());
	}

}
