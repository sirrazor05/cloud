package com.home.interview.algo.strings;

public class Permutation {
	
	//Given a string S. The task is to print all permutations of a given string.
	//https://www.geeksforgeeks.org/print-all-permutations-of-a-string-in-java/
	
	
	public static void printPermut(String str, String ans) {
		if(str.length() == 0) {
			System.out.println(ans);
			return;
		}
		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			String ros = str.substring(0, i) + str.substring(i+1);
			printPermut(ros, ans + ch);
		}
	}

	public static void main(String[] args) {
		String str="ABC";
		printPermut(str,"");
	}
}
