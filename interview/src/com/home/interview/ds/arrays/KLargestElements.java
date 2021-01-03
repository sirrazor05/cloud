package com.home.interview.ds.arrays;

import java.util.Arrays;
import java.util.Collections;

import com.home.interview.ds.heap.MaxHeap;

//https://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/

// Question: Write an efficient program for printing k largest elements in an array. Elements in array can be in any order.
// For example, if given array is [1, 23, 12, 9, 30, 2, 50] and you are asked for the largest 3 elements i.e., k = 3 then your program should print 50, 30 and 23.

public class KLargestElements {

	// Method 3(Use Sorting)
	// 1) Sort the elements in descending order in O(nLogn)
	// 2) Print the first k numbers of the sorted array O(k).
	private static void sortingMethod(Integer arr[], int k) {
		Arrays.sort(arr, Collections.reverseOrder());
		System.out.print("First " + k + " elemens: ");
		for (int i = 0; i < k; i++) {
			System.out.print(arr[i] + " ");
		}
	}

	// Method 4 (Use Max Heap)
	// 1) Build a Max Heap tree in O(n)
	// 2) Use Extract Max k times to get k maximum elements from the Max Heap
	// O(klogn)
	// Time complexity:O(n+klogn)
	private static void maxHeapMethod(Integer arr[], int k) {
		MaxHeap heap = new MaxHeap(arr.length);
		for (int i = 0; i < arr.length; i++) {
			heap.insert(arr[i]);
		}
		System.out.print("First " + k + " elemens: ");
		for (int i = 0; i < k; i++) {
			System.out.print(heap.extractMax() + " ");
		}
	}

	public static void main(String[] args) {
		Integer arr[] = { 1, 23, 12, 9, 30, 2, 50 };
		int k = 3;

		sortingMethod(arr, k);
		System.out.println();
		maxHeapMethod(arr, k);
	}

}
