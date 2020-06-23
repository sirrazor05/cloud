package com.home.interview.algo.dp;

import java.util.Arrays;

public class LongestIncreasingSubSequence {
	
	public static int lis(int []arr, int prev, int index) {
		if(index == arr.length) {
			return 0;
		}
		int taken = 0;
		if(prev < arr[index]) {
			taken = 1 + lis(arr, arr[index], index + 1);
		} 
		int	notTaken = lis(arr, prev, index + 1);
		return Math.max(taken, notTaken);
	}
	
	//https://leetcode.com/problems/longest-increasing-subsequence/solution/

	/**
	 * Important, we need to keep track of max, because the last increasing can be in the middle
	 * Complexity Analysis
	    Time complexity : O(n2). Two loops of n are there.
	    Space complexity : O(n). dp array of size n is used.
	 */
		
	public static int lis(int arr[], int n) {
		if(n == 0) {
			return 0;
		}
		int dp[] = new int[n];
		dp[0] = 1;
		int maxRes = 1;
		
		for(int i = 1; i < n; i++) {
			int localMax = 0;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i]) {
					localMax = Math.max(localMax, dp[j]);
				}
				dp[i] = 1 + localMax;
				maxRes = Math.max(maxRes, dp[j]);
			}
		}
		return maxRes;
	}
	
	
	/**LIS with binary search and DP
	 * Consider the example:
	input: [0, 8, 4, 12, 2]
	dp: [0]
	dp: [0, 8]
	dp: [0, 4]
	dp: [0, 4, 12]
	dp: [0 , 2, 12] which is not the longest increasing subsequence, but length of dp array results in length of Longest Increasing Subsequence.
	 */
	
	public static int lisBs(int arr[], int n) {
		int dp[] = new int[n];
		int len = 0;
		for (int num : arr) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
		return len;
	}

	public static void main(String[] args) {
		int arr[] = {3, 34, 4, 12, 5, 2};
		System.out.println(lis(arr, Integer.MIN_VALUE, 0));
	}

}
