package com.home.interview.ds.heap;

//https://www.geeksforgeeks.org/max-heap-in-java/

public class MaxHeap {

	private int[] heap;
	private int size;
	private int maxsize;

	public MaxHeap(int maxsize) {
		this.size = 0;
		this.maxsize = maxsize;
		this.heap = new int[this.maxsize + 1];
		this.heap[0] = Integer.MAX_VALUE;
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private void swap(int fpos, int spos) {
		int tmp = heap[fpos];
		heap[fpos] = heap[spos];
		heap[spos] = tmp;
	}

	public void insert(int element) {
		heap[++size] = element;

		// Traverse up and fix violated property (bottom-up heapify)
		int current = size;
		while (heap[current] > heap[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(
					" PARENT : " + heap[i] + " LEFT CHILD : " + heap[2 * i] + " RIGHT CHILD :" + heap[2 * i + 1]);
			System.out.println();
		}
	}

	// top-down heapify
	public void heapify(int i) {
		int largest = i;
		int l = 2 * i;
		int r = 2 * i + 1;

		// If left child is larger than root
		if (l <= size && heap[l] > heap[largest]) {
			largest = l;
		}
		// If right child is larger than largest so far
		if (r <= size && heap[r] > heap[largest]) {
			largest = r;
		}
		// If largest is not root
		if (largest != i) {
			swap(i, largest);
			// Recursively heapify the affected sub-tree
			heapify(largest);
		}
	}

	// Remove an element from max heap
	public int extractMax() {
		int popped = heap[1];
		heap[1] = heap[size--];
		heapify(1);
		return popped;
	}

	public static void main(String[] arg) {
		System.out.println("The Max Heap is ");
		MaxHeap maxHeap = new MaxHeap(15);
		maxHeap.insert(5);
		maxHeap.insert(3);
		maxHeap.insert(17);
		maxHeap.insert(10);
		maxHeap.insert(84);
		maxHeap.insert(19);
		maxHeap.insert(6);
		maxHeap.insert(22);
		maxHeap.insert(9);

		maxHeap.print();

		System.out.println("The max val is " + maxHeap.extractMax());
		maxHeap.print();

	}

}
