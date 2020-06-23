package com.home.interview.ds.arrays;

import java.util.Arrays;
import java.util.HashSet;

public class IsSubset {
	
	/**
	 * 
	Find whether an array is subset of another array | Added Method 3
	
	Given two arrays: arr1[0..m-1] and arr2[0..n-1]. Find whether arr2[] is a subset of arr1[] or not. Both the arrays are not in sorted order. It may be assumed that elements in both array are distinct.
	
	Examples:

    Input: arr1[] = {11, 1, 13, 21, 3, 7}, arr2[] = {11, 3, 7, 1}
    Output: arr2[] is a subset of arr1[]
    
    Solution: 
    
    Sort both arrays: arr1[] and arr2[] which takes O(mLogm + nLogn)
    Use Merge type of process to see if all elements of sorted arr2[] are present in sorted arr1[].
    
    Another method using hashing: put arr1 in hash, traverse arr2 and see all elements are inside

    
	 */
	
	public static boolean isSubset(int arr1[], int arr2[], int m, int n) {
		if(n > m) {
			return false;
		}
		
		int i = 0;
		int j = 0;
		
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		
		while (i < m && j < n) {
			if(arr1[i] < arr2[j]) { 
                i++;
            }else if(arr1[i] == arr2[j]){ 
                i++; 
                j++; 
            } else if(arr1[i] > arr2[j]) { 
                return false; 
            }
		}
		
		return (j < n) ? false : true;
	}
	
	public static boolean isSubsetHasing(int arr1[], int arr2[], int m, int n) {
		HashSet<Integer> hset= new HashSet<>(); 
        
        // hset stores all the values of arr1 
        for(int i = 0; i < m; i++) { 
            if(!hset.contains(arr1[i])) { 
                hset.add(arr1[i]); 
            }
        } 
              
        // loop to check if all elements of arr2 also 
        // lies in arr1 
        for(int i = 0; i < n; i++) { 
            if(!hset.contains(arr2[i])) { 
                return false; 
            }
        } 
        return true; 
	}

	public static void main(String[] args) {
		int arr1[] = {11, 1, 13, 21, 3, 7}; 
        int arr2[] = { 3, 7, 1}; 
          
        int m = arr1.length; 
        int n = arr2.length; 
          
        if(isSubsetHasing(arr1, arr2, m, n)) { 
        	System.out.println("arr2 is a subset of arr1"); 
        } else {
        	System.out.println("arr2 is not a subset of arr1"); 
        }
	}

}
