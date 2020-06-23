package com.home.interview.algo.dp;

public class SubsetSum {
	
	/**
	 * 
	 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
		Example:

		Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
		Output:  True  //There is a subset (4, 5) with sum 9.
		
		Recursive method

		The Subset sum problem can be divided into two cases:
		
		    We include current element in subset and recurse the remaining elements within remaining sum
		    We exclude current element from subset and recurse for remaining elements with initial sum
		    Finally, we return true if we get subset by including or excluding current item else we return false.
		
		The base case of the recursion would be when- 
		1. sum becomes negative. 
		2. no items are left  -> check if the last element is equal with sum, return true, false otherwise
		We return true when sum becomes 0 i.e. subset is found.

	 */
	
	public static boolean hasSubSetRec(int arr[], int n, int sum) {
		if(sum == 0) {
			return true;
		}
		if(n == arr.length - 1) {
			if(arr[n] == sum) {
				return true;
			}
			return false;
		}

		return hasSubSetRec(arr, n + 1, sum) || hasSubSetRec(arr, n + 1, sum - arr[n]);
	}
	
	public static int [][] dp;
	
	public static void initDp(int sum, int n) {
		dp = new int[sum+1][n];
		 for (int i = 0; i <= sum; i++) {
			 for (int j = 0; j < n; j++) {
				 dp[i][j] = -1;
			 }
		 }
	}
	
	public static boolean subSetMem(int[] arr, int sum, int n) {
		if(sum == 0) {
			return true;
		}
		if(sum < 0 || n < 0) {
			return false;
		}
		
		if (dp[sum][n] != -1) { 
            return dp[sum][n] == 1; 
        } 
		
		boolean result = subSetMem(arr, sum, n-1) || subSetMem(arr, sum - arr[n], n-1);
		dp[sum][n] = result ? 1 : 0;
		return dp[sum][n] == 1; 
	}
	
	//https://www.youtube.com/watch?v=s6FhG--P7z0&t=2s

	/**
	 * !!IMPORTANT!!: when comparing with arr [j-1] is j-1 not j because array has from 0 to n-1, not as subset 0 to n
	 */
	
	//bottom up fashion.
	public static boolean subSetTab(int[] arr, int n, int sum) {
		boolean [][]subset = new boolean [sum+1][n+1];
		
		for(int i = 0; i <= n; i++) {
			subset[0][i] = true;
		}
		
		for(int i = 1; i <= sum; i++) {
			subset[i][0] = false;
		}
		
		for(int i = 1; i <= sum; i++) {
			for(int j = 1; j <= n; j++) {
				if(i < arr[j-1]) {
					subset[i][j] = subset[i][j-1];
				}else {
					subset[i][j] = subset[i][j-1] || subset[i - arr[j-1]][j-1];
				}
			}
		}
		return subset[sum][n];
	}

	public static void main(String[] args) {
		int arr[] = {3, 34, 4, 12, 5, 2};
		
		System.out.println(hasSubSetRec(arr, 0, 9));

	}

}
