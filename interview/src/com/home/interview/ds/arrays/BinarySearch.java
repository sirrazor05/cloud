package com.home.interview.ds.arrays;

public class BinarySearch {
	
	
	public static int binarySearch(int arr[], int l, int r, int x) {
		int middle = (l + r) / 2;
		if(l == r && arr[middle] != x) {
			return -1;
		}
		if(arr[middle] == x) {
			return middle;
		}
		if(x > arr[middle]) {
			return binarySearch(arr, middle + 1, r, x);
		}else {
			return binarySearch(arr, l, middle - 1, x);
		}
	}
	
	public static void main(String[] args) {
		 int arr[] = { 2, 3, 4, 10, 40 }; 
	     int n = arr.length; 
	     int x = 110; 
	     int result = binarySearch(arr, 0, n - 1, x); 
	     System.out.println(result);
	}
}
