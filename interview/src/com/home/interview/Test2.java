package com.home.interview;

import java.util.List;

public class Test2 {
	
	public static int bs(Integer[] array, int left, int right, int elem) {
		   
	    if (left > right) {
	        return left;
	    } else {
	        int middle;
	        middle = (left + right) / 2;
	        if (left == right) { // used to return the insert position
	            return left;
	        } else if (elem > array[middle]) {
	            return bs(array, middle + 1, right, elem);
	        } else if ((elem < array[middle])) {
	            return bs(array, left, middle, elem); //<-- was: middle-1
	        } else {
	        	
	            return middle; // element existed into array
	        }
	    }
	}
	
	
	public static void main(String[] args) {
		
		Integer[] array = {1, 2, 3, 4};
		
		System.out.println(Test2.bs(array,0,3,10));
		
	}

}
