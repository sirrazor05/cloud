package com.home.interview.bitmanip;

public class LonelyInteger {

	/*
	 * [Lonely Integer](https://www.hackerrank.com/challenges/lonely-integer/problem) 
	 * 1) Any number xor'd with itself will give zero. 
	 * 2) Any number xor'd with zero will give the number.
	 * 3) We are told there is an odd number of numbers in the array and they are all pairs of the same number, apart from one.
	 * 
	 * So if we xor all the numbers in the array together then any which are the
	 * same will cancel out - and give zero as the result of all the xors. Then we
	 * are left with the unique number, which xor's with zero and so gives the
	 * unique number as the answer. Solution in O(n)
	 */

	static int lonelyinteger(int[] a) {
		int result = 0;
		for (int i = 0; i < a.length; i++) {
			result ^= a[i];
		}
		return result;
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 3, 2, 1 };
		int res = lonelyinteger(a);
		System.out.println(res);
	}
}
