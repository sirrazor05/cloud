package com.home.interview.algo.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumOfAbsoluteDif {
	
	/*
	 * Given an array, we need to find the maximum sum of absolute difference of the array elements for a sequence of this array.
	 *  input : { 1, 2, 4, 8 }
		Output : 18
		Explanation : For the given array there are 
		several sequence possible
		like : {2, 1, 4, 8}
		       {4, 2, 1, 8} and some more.
		Now, the absolute difference of an array sequence will be
		like for this array sequence {1, 2, 4, 8}, the absolute
		difference sum is = |1-2| + |2-4| + |4-8| + |8-1| = 14
		For the given array, we get the maximum value for the sequence {1, 8, 2, 4} = |1-8| + |8-2| + |2-4| + |4-1| = 18
	 * 
	 * Algorithm: To get the maximum sum, we should have a sequence in which small and large elements comes alternate. This is done to get maximum difference.

		For the implementation of the above algorithm ->
		1. We will sort the array.
		2. Calculate the final sequence by taking one smallest element and largest element from the sorted array and make one vector array of this final sequence.
		3. Finally, calculate the sum of absolute difference between the elements of the array.
	 */
	
	// we assume that the integer has even elements
	private static int maxSumDifference(Integer[] a) {
		int n = a.length;
		List<Integer> finalSequence =  new ArrayList<Integer>();
		
		Arrays.sort(a);
		for (int i = 0; i < n/2; ++i) { 
            finalSequence.add(a[i]); 
            finalSequence.add(a[n-i-1]); 
        }
		
		int maximumSum = 0; 
		for (int i = 0; i < n-1; i++) {
			maximumSum += Math.abs(finalSequence.get(i) - finalSequence.get(i+1));
		}
		maximumSum += Math.abs(finalSequence.get(n-1) - finalSequence.get(0));
		
		return maximumSum;
	}	
	
	
	
	public static void main(String[] args) {
		Integer []a = {1, 2, 4, 8}; 
        System.out.print(maxSumDifference(a)); 
	}
}
