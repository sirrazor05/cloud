package com.home.interview.algo.dp;

public class LongestPalindromicSubSequence {
	
	public static int lpsRec(char seq[], int n, int i, int j) {
		// Base Case 1: If there is only 1 character  
        if (i == j) { 
            return 1; 
        } 
  
        // Base Case 2: If there are only 2 characters and both are same  
        if (seq[i] == seq[j] && i + 1 == j) { 
            return 2; 
        }
        
        // If the first and last characters match 
        if(seq[i] == seq[j]) {
        	return 2 + lpsRec(seq, n, i+1, j-1);
        }
		
        // If the first and last characters do not match  
        return Math.max(lpsRec(seq, n, i, j-1), lpsRec(seq, n, i+1, j));
	}
	
	//https://www.youtube.com/watch?v=_nCsPn7_OgI
	public static int lpsTab(char []seq, int n) { 
	    int i, j, len; 
	    // Create a table to store results of subproblems 
	    int L[][] = new int[n][n];  
	      
	    // Strings of length 1 are palindrome of lentgh 1 
	    for (i = 0; i < n; i++) { 
	        L[i][i] = 1; 
	    }
        // len is length of substring 
        for (len = 2; len <= n; len++) { 
            for (i = 0; i < n-len+1; i++){ //diagonal length
                j = i + len - 1; 
                if (seq[i] == seq[j] && len == 2) {
                	L[i][j] = 2; 
                }else if (seq[i] == seq[j]) {
                	L[i][j] = L[i+1][j-1] + 2; 
                }else {
                	L[i][j] = Math.max(L[i][j-1], L[i+1][j]); 
                }
            } 
        } 
	    return L[0][n-1]; 
	} 
	
	public static void main(String[] args) {
		String str = "abdcbaa";
		System.out.println(lpsTab(str.toCharArray(), str.length()));
	}
}
