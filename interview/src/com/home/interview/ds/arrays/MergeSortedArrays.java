package com.home.interview.ds.arrays;

public class MergeSortedArrays {
	// o(n) extra space, O(m+n)
	public static int[] merge(int a[], int []b, int m, int n) {
		int res[] = new int[m + n];
		int i = 0;
		int j = 0;
		int k = 0;
		while(i < m && j < n) {
			if(a[i] <= b[j]) {
				res[k++] = a[i++];
			}else {
				res[k++] = b[j++];
			}
		}
		while(i < m) {
			res[k++] = a[i++];
		}
		while(j < n) {
			res[k++] = b[j++];
		}
		return res;
	}
	// o(1) extra space, O(m*n) worst case
	public static void merge2(int a[], int []b, int m, int n) {
		for(int i = n-1; i >= 0; i--) {
			int last = a[m-1];
			int j=0;
			// move elements to the right grater than b[i]
			for(j = m-2; j>=0 && a[j] > b[i]; j--) {
				a[j+1] = a[j];
			}
			// if elements moved or last element of a > b[i], interchange
			if(j != m-2 || last > b[i]) {
				a[j+1] = b[i];
				b[i] = last;
			}
		}
	}
	

	public static void main(String[] args) {
        int a[] = {1, 5, 9, 10, 15, 20};
        int b[] = {2, 3, 8, 19};
        
        merge2(a, b, a.length, b.length);
        for(int i = 0; i < a.length; i++) {
        	System.out.print(a[i] + " ");
        }
        System.out.println();
        for(int i = 0; i < b.length; i++) {
        	System.out.print(b[i] + " ");
        }
	}
}
