package com.home.interview.algo.dp;

public class LongestCommonSubSequence {
	
	/**
	 * LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
	 * LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
	   LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
	   
	   Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively. 
	   And let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y. Following is the recursive definition of L(X[0..m-1], Y[0..n-1]).

		If last characters of both sequences match (or X[m-1] == Y[n-1]) then
		L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])
		
		If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
		L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2]) )
		
		Examples:
		1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. So length of LCS can be written as:
		L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)
		
			A G G T A B
		G	- - 4 - - -
		X	- - - - - -
		T	- - - 3 - -
		X	- - - - - -
		A	- - - - 2 -
		Y	- - - - - -
		B	- - - - - 1
		
		
		2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. So length of LCS can be written as:
		L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )
		
		So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.
		
		2) Overlapping Subproblems:
		Following is simple recursive implementation of the LCS problem. The implementation simply follows the recursive structure mentioned above.
	 */
	
	public static int [][] dp;
	
	public static void initDp(int m, int n) {
		dp = new int[m][n];
		 for (int i = 0; i < m; i++) {
			 for (int j = 0; j < n; j++) {
				 dp[i][j] = -1;
			 }
		 }
	}
	//top down.
	public static int lcsMemo(char[] x, char[] y, int m, int n) {
		if(m == 0 || n == 0) {
			return 0;
		}
		if (dp[m - 1][n - 1] != -1) { 
            return dp[m - 1][n - 1]; 
        } 
		if(x[m-1] == y[n-1]) {
			dp[m-1][n-1] = 1 + lcsMemo(x, y, m-1, n-1);
			return dp[m - 1][n - 1]; 
		}else {
			dp[m-1][n-1] = Math.max(lcsMemo(x, y, m, n-1), lcsMemo(x, y, m-1, n));
			return dp[m - 1][n - 1]; 
		}
	}
	
	public static int lcsRec(char[] x, char[] y, int m, int n) {
		if(m == 0 || n == 0) {
			return 0;
		}
		if(x[m-1] == y[n-1]) {
			return 1 + lcsRec(x, y, m-1, n-1);
		}else {
			return Math.max(lcsRec(x, y, m, n-1), lcsRec(x, y, m-1, n));
		}
	}
	
	//bottom up fashion.
	public static int lcsTab(char[] x, char[] y, int m, int n) {
		int [][]l = new int [m+1][n+1];
		
		for(int i = 0; i <= m; i++) {
			for(int j = 0; j <= n; j++) {
				if(i == 0 || j == 0) {
					l[i][j] = 0;
				}else if(x[i-1] == y[j-1]) {
					l[i][j] = l[i-1][j-1] + 1;
				}else {
					l[i][j] = Math.max(l[i][j-1], l[i-1][j]);
				}
			}
		}
		return l[m][n];
	}
	

	public static void main(String[] args) {
		String s1 = "AGGTAB"; 
	    String s2 = "GXTXAYB"; 
	  
	    char[] X=s1.toCharArray(); 
	    char[] Y=s2.toCharArray(); 
	    int m = X.length; 
	    int n = Y.length; 
  
	    initDp(m, n);
	    System.out.println("Length of LCS is" + " " + lcsMemo( X, Y, m, n)); 
	    
	    
	}

}
