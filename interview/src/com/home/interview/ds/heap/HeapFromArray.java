package com.home.interview.ds.heap;

import java.util.Arrays;

//https://www.geeksforgeeks.org/heap-sort/
//Introduction_to_algorithms-3rd edition
//https://www.geeksforgeeks.org/building-heap-from-array/
public class HeapFromArray {

	public static void heapify(int arr[], int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l] > arr[largest]) {
			largest = l;
		}
		// If right child is larger than largest so far
		if (r < n && arr[r] > arr[largest]) {
			largest = r;
		}
		// If largest is not root
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;

			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}

	public static void buildHeap(int arr[], int n) {
		// Index of last non-leaf node
		int startIdx = (n / 2) - 1;

		// Perform reverse level order traversal
		// from last non-leaf node and heapify
		// each node
		for (int i = startIdx; i >= 0; i--) {
			heapify(arr, n, i);
		}
	}

	public static void main(String[] args) {

		int a[] = { 3, 16, 4, 2, 8, 7, 10, 1, 9, 14 };

		System.out.println("Original array : " + Arrays.toString(a));
		HeapFromArray.buildHeap(a, a.length);
		System.out.println("Heapified array: " + Arrays.toString(a));
	}

}
