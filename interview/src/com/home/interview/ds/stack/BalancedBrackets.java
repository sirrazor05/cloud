package com.home.interview.ds.stack;

import java.util.Stack;

public class BalancedBrackets {
	
	private static boolean matchParenthisis(String str) {
		Stack<Character> st = new Stack<Character>();
		char []ch = str.toCharArray();
		for(char c: ch) {
			if (c == '{' || c == '[' || c == '(') {
				st.push(c);
			}else {
				if (c == ']' && !st.isEmpty() && st.pop() == '[') {
					continue;
				} else if (c == '}' && !st.isEmpty() && st.pop() == '{') {
					continue;
				} else if (c == ')' && !st.isEmpty() && st.pop() == '(') {
					continue;
				} else {
					return false;
				}
			}
		}
		if (!st.isEmpty()) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String str = "({(dfsgdgsf){}[]})[]";
		System.out.println(matchParenthisis(str));
	}
}
