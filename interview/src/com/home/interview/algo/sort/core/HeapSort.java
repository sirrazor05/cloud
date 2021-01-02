package com.home.interview.algo.sort.core;

import java.util.Arrays;

import com.home.interview.ds.heap.HeapFromArray;

//https://www.geeksforgeeks.org/heap-sort/

public class HeapSort {

	public static void sort(int arr[]) {
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			HeapFromArray.heapify(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap
			HeapFromArray.heapify(arr, i, 0);
		}
	}

	public static void main(String[] args) {
		int a[] = { 3, 16, 4, 2, 8, 7, 10, 1, 9, 14 };
		System.out.println("Original array : " + Arrays.toString(a));
		HeapSort.sort(a);
		System.out.println("Sorted array: " + Arrays.toString(a));
	}
}
