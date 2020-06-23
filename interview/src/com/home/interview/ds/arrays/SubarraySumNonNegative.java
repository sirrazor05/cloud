package com.home.interview.ds.arrays;

public class SubarraySumNonNegative {
	/**
	 * Given an unsorted array A of size N of non-negative integers, find a continuous sub-array which adds to a given number S.
	 * !Important: make attention when array has 1 element and is sum
	 */
	
	 static int subArraySum1(int arr[], int n, int sum) { 
	     int curr_sum, i, j; 
	
	     // Pick a starting point 
	     for (i = 0; i < n; i++) { 
	         curr_sum = arr[i]; 
	
	         // try all subarrays starting with 'i' 
	         for (j = i + 1; j <= n; j++) { 
	             if (curr_sum == sum) { 
	                 int p = j - 1; 
	                 System.out.println("Sum found between indexes " + i + " and " + p); 
	                 return 1; 
	             } 
	             if (curr_sum > sum || j == n) 
	                 break; 
	             curr_sum = curr_sum + arr[j]; 
	         } 
	     } 
	     System.out.println("No subarray found"); 
	     return 0; 
	} 
	 /**
	  * Initialize a variable curr_sum as the first element.
	  *  curr_sum indicates the sum of the current sub-array. 
	  *  Start from the second element and add all elements one by one to the curr_sum.
	  *  If curr_sum exceeds the sum, then remove trailing elements while curr_sum is greater than the sum
	  *  If curr_sum becomes equal to the sum, then print the solution. 
	  */
	 static int subArraySum2(int arr[], int n, int sum) { 
		 int curSum = arr[0];
		 int startIndex = 0;
		 for(int i=1; i<=n; i++) {
			 while(curSum > sum && startIndex < i-1) {
				curSum = curSum - arr[startIndex++];
			 }
			 if(curSum == sum) {
				 System.out.println("Sum found between indexes " + startIndex + " and " + (i-1)); 
                 return 1; 
			 }
			if(i < n) { 
				curSum = curSum + arr[i];
			}
		 }
		 
		 System.out.println("No subarray found"); 
	     return 0;  
	 }
	

	public static void main(String[] args) {
		int arr[] = {1,2,3,4,5,6};
		subArraySum2(arr, 6, 12);

	}

}
