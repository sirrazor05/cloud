package com.home.interview.algo.sort.core;

/*
 * Merge Sort:
 *	Merge sort is a sorting technique, which is  based on comparison sorting algorithm, having complexity O(n log n). 
 * It is one of the types of stable sort, meaning, that in its implementation, input order of equal elements is preserved 
 * in the sorted output.  It was invented by John von Neumann in 1945. Merge sort is a divide and conquer algorithm.

 * The concept of Merge Sort is based on the following principle:
 * Divide the unsorted list into 'n' sub-lists, each containing 1 element (a list of 1 element is considered sorted).
 * Repeatedly merge sub-lists to produce new sub-lists until there is only 1 sub-list remaining. This will be the sorted list.
 * 

 * 
 * Compared with other algorithms:
 * - in the worst case, merge sort does about 39% fewer comparisons than quicksort does in avarage case.
 * - in terms of moves, merge sort's worst case is O(nlogn), the same complexity as quicksort's best case.
 * - more efficient than quicksort for some types of lists if the data to be sorted can only be efficiently accessed sequentially (e.g. LISP)
 * - although heapsort has the same time bounds, it requires only O(1) auxiliary space instead of merge sort's O(n)
 * - quicksort outperforms mergesort for sorting RAM based array.
 * - mergesort is a stable sort and is more efficient at handeling slow-to-access sequential media.
 * - adapted for linked list. 
 * 
 * 					worst		best		avarage
-----------------------------------------------------
selection sort	  |	O(n^2)	   O(n^2)		O(n^2)	
-----------------------------------------------------		
merge sort 	  	  |	O(nlogn)   O(nlogn))	O(nlogn)  O(n) extra space
-----------------------------------------------------
quicksort	  	  |	O(n^2)	  O(nlogn)		O(nlogn)	
-----------------------------------------------------		
insertionsort 	  |	O(n^2)	  	O(n)		  O(n^2)
 * 
 */

public class MergeSort {
	
	void merge(int a[], int low, int mid, int high){
	    int []b = new int[high - low +1];
	    int i = low;
	    int j = mid + 1;
	    int k = 0;
	    while (i <= mid && j <= high) { 	//cat timp nu am ajuns la jumatate (i) sau la sfarsit (j)
	        if (a[i] <= a[j]) {				//daca a(i)<=a(j) il punem pe a(i) in b
	            b[k++] = a[i++];
	        }else {							//altfel il punem pe a(j) in b
	            b[k++] = a[j++];
	        }
	    }
	    while (i <= mid) {					//daca au mai ramas elemente pana la mijloc, inseaman ca sunt mai mari decat elementele existente
	        b[k++] = a[i++];				//in b, le copiem pur si simplu
	    }
	    while (j <= high) {					//daca au mai ramas elemente pana la sfarsit, inseaman ca sunt mai mari decat elementele existente
	        b[k++] = a[j++];				//in b, le copiem pur si simplu
	    }
	    k--;
	    while (k >= 0) {
	        a[low + k] = b[k--];			// copiem elementele in sirul initial
	    }
	}
	
	void mergesort(int a[], int low, int high){
	    if (low < high) {
	        int m = (high + low)/2;
	        mergesort(a, low, m);
	        mergesort(a, m + 1, high);
	        merge(a, low, m, high);
	    }
	}

	public static void main(String[] args) {
		int x[]={4, 6, 7, 8, 1, 2, 11};
		MergeSort object = new MergeSort();
		object.mergesort(x, 0, x.length-1);
		for(int i = 0; i < x.length; i++)
			System.out.print(x[i] + " ");

	}

}
