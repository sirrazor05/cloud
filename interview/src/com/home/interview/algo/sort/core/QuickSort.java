package com.home.interview.algo.sort.core;

/*
Quicksort is a divide and conquer algorithm. Quicksort first divides a large list into two smaller sub-lists: 
the low elements and the high elements.
Quicksort can then recursively sort the sub-lists.

The steps are:

    1. Pick an element, called a pivot, from the list.
    2. Reorder the list so that all elements with values less than the pivot come before the pivot,
	while all elements with values greater than the pivot come after it (equal values can go either way).
	After this partitioning, the pivot is in its final position. This is called the partition operation.
    3. Recursively apply the above steps to the sub-list of elements with smaller values and separately the sub-list 
      of elements with greater values.

The base case of the recursion are lists of size zero or one, which never need to be sorted.

Time complexity: 
- worst case O(n^2) when pivot produces two regions, one of size 1element, and other of size (n-1) elements recursively
- avarage case O(nlogn)when pivot chosses two regions such that both have n/2 size
The number of iterations the algorithm requires is O(logn).
Since each iteration is O(n)=>O(nlogn)

+ in place
+ it is widely used since average time is O(nlogn), one of the fastest algorithm
- the wors case complexity is O(n^2)
Applications:
- commercial applications
x never use in critical system unless assumed O(n^2)
Compared with INSERTION SORT
					worst		best		avarage
-----------------------------------------------------
selection sort	  |	O(n^2)	   O(n^2)		O(n^2)	
-----------------------------------------------------		
merge sort 	  	  |	O(nlogn)   O(nlogn))	O(nlogn)  O(n) extra space
-----------------------------------------------------
quicksort	  	  |	O(n^2)	  O(nlogn)		O(nlogn)	
-----------------------------------------------------		
insertionsort 	  |	O(n^2)	  	O(n)		  O(n^2)
	
*/

public class QuickSort {
	
	/* This function takes last element as pivot, places the pivot element at its correct 
    position in sorted array, and places all smaller (smaller than pivot) to left of 
    pivot and all greater elements to right of pivot */
	
	 int partition(int arr[], int low, int high){ 
	     int pivot = arr[high];  
	     int i = (low - 1); // index of smaller element 
	     for (int j = low; j < high; j++) { 
	         // If current element is smaller than the pivot 
	         if (arr[j] < pivot) { 
	             i++; 
	             // swap arr[i] and arr[j] 
	             int temp = arr[i]; 
	             arr[i] = arr[j]; 
	             arr[j] = temp; 
	         } 
	     } 
	
	     // swap arr[i+1] and arr[high] (or pivot) 
	     int temp = arr[i + 1]; 
	     arr[i + 1] = arr[high]; 
	     arr[high] = temp; 
	     return i + 1; 
	 } 
	
	
	 /* The main function that implements QuickSort() 
	   arr[] --> Array to be sorted, 
	   low  --> Starting index, 
	   high  --> Ending index */
	 
	 void sort(int arr[], int low, int high) { 
	     if (low < high) { 
	         /* pi is partitioning index, arr[pi] is  
	           now at right place */
	         int pi = partition(arr, low, high); 
	
	         // Recursively sort elements before 
	         // partition and after partition 
	         sort(arr, low, pi-1); 
	         sort(arr, pi+1, high); 
	     } 
	 } 
	
	public static void main(String []args){
		int x[]={4,6,7,8,1,2,11};
		QuickSort object=new QuickSort();
		object.sort(x, 0, x.length-1);
		for(int i=0;i<x.length;i++)
			System.out.print(x[i]+" ");
	}
	
}
